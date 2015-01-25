package org.skr.gx2d.physnodes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.annotation.NodeAddMethodAnnotation;
import org.skr.gx2d.node.annotation.Nfa;
import org.skr.gx2d.physnodes.physdef.BodyDefinition;
import org.skr.gx2d.sprite.Sprite;
import org.skr.gx2d.utils.BBox;
import org.skr.gx2d.utils.RectangleExt;

/**
 * Created by rat on 06.01.15.
 */
public class BodyHandler extends Sprite {

    Body body = null;
    RectangleExt boundingBox = new RectangleExt();

    @Nfa
    @NodeAddMethodAnnotation( name = "addFixtureSet")
    FixtureSet fixtureSet;

    @Nfa
    BodyDefinition bhDef;

    @Nfa
    float absorbedImpulse = 0;

    public BodyHandler() {
        super();
        bhDef = new BodyDefinition();
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
        body.setUserData( this );
    }

    public BodyHandler getBodyHandler( long id ) {
        return (BodyHandler) getNode( id );
    }

    @Override
    protected boolean activate(boolean state) {
        super.activate( state );
        return false;
    }

    @Override
    public Type getType() {
        return Type.BodyHandler;
    }

    @Override
    public boolean isType(Type type) {
        return type == Type.BodyHandler || type == Type.Sprite;
    }

    public float getAbsorbedImpulse() {
        return absorbedImpulse;
    }

    public void setAbsorbedImpulse(float absorbedImpulse) {
        this.absorbedImpulse = absorbedImpulse;
    }

    public Vector2 getBodyHandlerWorldCenter() {
        if ( body == null )
            return null;
        return Env.get().world.toView( body.getWorldCenter() );
    }


    public FixtureSet getFixtureSet() {
        return fixtureSet;
    }

    public FixtureSet setFixtureSet(FixtureSet fixtureSet) {
        this.fixtureSet = fixtureSet;
        this.fixtureSet.setTopNode( this );
        checkOnSceneState(fixtureSet);
        return this.fixtureSet;
    }

    public FixtureSet addFixtureSet( FixtureSet fs ) {
        if ( fixtureSet == null )
            return setFixtureSet( fs );
        fixtureSet.appendNode( fs );
        checkOnSceneState( fs );
        return fixtureSet;
    }

    public FixtureSet getFixtureSet( Vector2 viewLocalPoint ) {
        for ( Node n : fixtureSet ) {
            FixtureSet fs = (FixtureSet) n;
            for ( Fixture fx : fs.getFixtures() ) {
                chBBox.set(getX(), getY(), 0, 0 );
                FixtureSet.getBoundingBoxForFixture( chBBox, fx );
                if ( chBBox.contains( viewLocalPoint ) )
                    return fs;
            }
        }
        return null;
    }

    private void updateTransform() {
        if ( body == null )
            return;
        Vector2 pos = Env.get().world.physToView( body.getPosition() );
        float angle = body.getAngle();
        setPosition( pos.x, pos.y );
        setRotation(MathUtils.radiansToDegrees * angle );
    }

    private final static RectangleExt chBBox = new RectangleExt();

    public RectangleExt getBodyBoundingBox() {
        updateBodyBoundingBox();
        return boundingBox;
    }

    private void updateBodyBoundingBox() {

        boundingBox.set(getX(), getY(), 0, 0 );
        chBBox.set(getX(), getY(), 0, 0 );

        for ( Fixture fs : body.getFixtureList() ) {

            FixtureSet.getBoundingBoxForFixture( chBBox, fs );

            chBBox.setX( chBBox.getX() + getX() );
            chBBox.setY( chBBox.getY() + getY() );

            chBBox.set(BBox.getBBox(chBBox, getX() - chBBox.getX(),
                    getY() - chBBox.getY(), getRotation()));
            if ( boundingBox.getWidth() == 0 ) {
                boundingBox.set( chBBox );
            } else {
                boundingBox.set(BBox.getBBox(boundingBox, chBBox));
            }
        }

        chBBox.set( getSpriteBoundingBox() );

        if ( boundingBox.getWidth() == 0 ) {
            boundingBox.set( chBBox );
        } else {
            boundingBox.set(BBox.getBBox(boundingBox, chBBox));
        }
    }

    @Override
    protected void nodeAct(float delta) {
        updateTransform();
        super.nodeAct( delta );
    }

    private void drawBoundingBox(Batch batch, float parentAlpha ) {
        updateBodyBoundingBox();

        Env.get().mshr.setProjectionMatrix(batch.getProjectionMatrix());
        Env.get().mshr.setTransformMatrix( batch.getTransformMatrix() );

        batch.end();

        Env.get().mshr.setColor( 1, 1, 1, 1);
        Env.get().mshr.begin(ShapeRenderer.ShapeType.Line);

        Env.get().mshr.rect( boundingBox.getLeft(), boundingBox.getBottom(),
                boundingBox.getWidth(), boundingBox.getHeight() );

        Env.get().mshr.end();

        batch.begin();
    }

    @Override
    protected void nodeDebugDraw(Batch batch, float parentAlpha) {
        super.nodeDebugDraw(batch, parentAlpha);
        if (Env.get().drawBodyHandlerBBox)
            drawBoundingBox( batch, parentAlpha );
    }

    static final Matrix3 mtx = new Matrix3();

    public static Vector2 stageToBodyLocal(BodyHandler bodyHandler, Vector2 coord) {
        mtx.idt();
        mtx.translate(bodyHandler.getX(), bodyHandler.getY());
        mtx.rotate( bodyHandler.getRotation() );

        coord.mul( mtx.inv() );
        return coord;
    }

    public static BodyDef getBodyDefFromBody( Body body ) {

        BodyDef bd = new BodyDef();

        bd.type = body.getType();
        bd.position.set( body.getPosition() );
        bd.angle = body.getAngle();
        bd.linearVelocity.set( body.getLinearVelocity() );
        bd.angularVelocity = body.getAngularVelocity();
        bd.linearDamping = body.getLinearDamping();
        bd.angularDamping = body.getAngularDamping();
        bd.allowSleep = body.isSleepingAllowed();
        bd.awake = body.isAwake();
        bd.fixedRotation = body.isFixedRotation();
        bd.bullet = body.isBullet();
        bd.active = body.isActive();
        bd.gravityScale = body.getGravityScale();

        return bd;
    }

    protected void createBody( BodyDefinition bhDef ) {
        Body body = Env.get().world.box2dWorld().createBody(bhDef.getBodyDef());
        if ( body == null )
            return;
        setBody(body);
        body.setUserData( this );
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    protected void updateBodyDefinition() {
        bhDef = null;
        if ( body == null )
            return;
        bhDef = new BodyDefinition();
        BodyDef bodyDef = getBodyDefFromBody( body );
        bhDef.setBodyDef(bodyDef);
    }

    @Override
    public void preExport() {
        super.preExport();
        updateBodyDefinition();
    }

    @Override
    public void postExport() {
        super.postExport();
        bhDef = null;
    }

    @Override
    public void constructPhysics() {
        if ( bhDef == null )
            return;
        createBody( bhDef );
        if ( fixtureSet == null )
            return;
        for ( Node node : fixtureSet )
            node.constructPhysics();
    }

    @Override
    public void destroyPhysics() {

        if ( body != null ) {
            World w = body.getWorld();
            w.destroyBody(body);
            body = null;
        }
        super.destroyPhysics();
    }
}
