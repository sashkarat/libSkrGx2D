package org.skr.gx2d.physnodes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.node.annotation.NodeField;
import org.skr.gx2d.physnodes.physdef.BodyDefinition;
import org.skr.gx2d.physnodes.physdef.PhysDefinition;
import org.skr.gx2d.physnodes.physdef.PhysNode;
import org.skr.gx2d.sprite.Sprite;
import org.skr.gx2d.utils.BBox;
import org.skr.gx2d.utils.RectangleExt;

/**
 * Created by rat on 06.01.15.
 */
public class BodyHandler extends Sprite implements PhysNode {

    BodyDefinition bhDef;
    Body body = null;
    RectangleExt boundingBox = new RectangleExt();

    @NodeField
    boolean overrideMassData = false;

    @NodeField
    float absorbedImpulse = 0;

    public BodyHandler() {
        super();
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
        body.setUserData( this );
    }

    public boolean isOverrideMassData() {
        return overrideMassData;
    }

    public void setOverrideMassData(boolean overrideMassData) {
        this.overrideMassData = overrideMassData;
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

    @Override
    protected boolean upload() {
        super.upload();
        return false;
    }

    private void updateTransform() {
        if ( body == null )
            return;
        Vector2 pos = Env.world.physToView( body.getPosition() );
        float angle = body.getAngle();
        setPosition( pos.x, pos.y );
        setRotation(MathUtils.radiansToDegrees * angle );
    }

    private final static RectangleExt chBBox = new RectangleExt();

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
    public void act(float delta) {
        if ( ! isActive() )
            return;
        updateTransform();
        super.act(delta);
    }

    private void drawBoundingBox(Batch batch, float parentAlpha ) {
        updateBodyBoundingBox();

        Env.mshr.setProjectionMatrix(batch.getProjectionMatrix());
        Env.mshr.setTransformMatrix( batch.getTransformMatrix() );

        batch.end();

        Env.mshr.setColor( 1, 1, 1, 1);
        Env.mshr.begin(ShapeRenderer.ShapeType.Line);

        Env.mshr.rect( boundingBox.getLeft(), boundingBox.getBottom(),
                boundingBox.getWidth(), boundingBox.getHeight() );

        Env.mshr.end();

        batch.begin();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if ( ! isVisible() || ! isDrawable() )
            return;

        if (Env.drawBodyHandlerBBox)
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

    @Override
    public void dispose() {
        super.dispose();
    }

    protected void updateBodyDefinition() {
        bhDef = new BodyDefinition();
        BodyDef bodyDef = getBodyDefFromBody( body );
        bhDef.setBodyDef(bodyDef);
        bhDef.setMassData(body.getMassData());
    }

    @Override
    public PhysDefinition getPhysDef() {
        bhDef = null;
        updateBodyDefinition();
        return bhDef;
    }

    @Override
    public void setPhysDef(PhysDefinition phd) {
        bhDef = null;
        if ( phd instanceof BodyDefinition)
            bhDef = (BodyDefinition) phd;
    }

    @Override
    public void constructGraphics() {

    }
}
