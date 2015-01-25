package org.skr.gx2d.physnodes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.SceneItem;
import org.skr.gx2d.physnodes.physdef.FixtureSetDefinition;
import org.skr.gx2d.physnodes.physdef.ShapeDefinition;
import org.skr.gx2d.utils.BBox;
import org.skr.gx2d.utils.RectangleExt;

/**
 * Created by rat on 06.01.15.
 */
public class FixtureSet extends SceneItem {

    FixtureSetDefinition fsDef = null;

    BodyHandler bodyHandler;

    Shape.Type shapeType = Shape.Type.Polygon;
    public float friction = 0.2f;
    public float restitution = 0;
    public float density = 0.1f;

    Array< Fixture> fixtures = new Array<Fixture>();

    @Override
    protected boolean activate(boolean state) {
        return false;
    }

    @Override
    public Type getType() {
        return Type.FixtureSet;
    }

    @Override
    public boolean isType(Type type) {
        return type == Type.FixtureSet;
    }

    @Override
    public Node setTopNode(Node topNode) {
        super.setTopNode(topNode);
        bodyHandler = null;
        if ( topNode() == null || !topNode().isType( Type.BodyHandler))
            return this;
        bodyHandler = (BodyHandler) topNode();
        return this;
    }

    @Override
    protected void nodeAct(float delta) {

    }

    @Override
    protected void nodeDraw(Batch batch, float parentAlpha) {

    }

    public Shape.Type getShapeType() {
        return shapeType;
    }

    public void setShapeType(Shape.Type shapeType) {
        this.shapeType = shapeType;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
        updateFriction();
    }

    public float getRestitution() {
        return restitution;
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
        updateRestitution();
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
        updateDensity();
    }

    public Array<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(Array<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    public Body getBody() {
        return getBodyHandler().getBody();
    }

    public BodyHandler getBodyHandler() {
        return (BodyHandler) topNode();
    }

    public void removeAllFixtures() {

        for ( Fixture f : fixtures ) {
            bodyHandler.getBody().destroyFixture(f);
        }
        fixtures.clear();
    }

    private void updateFriction() {
        for ( Fixture f : fixtures)
            f.setFriction( friction );
    }

    private void updateRestitution() {
        for ( Fixture f : fixtures)
            f.setRestitution( restitution );
    }

    private void updateDensity() {
        for ( Fixture f : fixtures)
            f.setDensity( density );
    }

    public static ShapeDefinition getShapeDefinition(Fixture fixture) {

        ShapeDefinition shd = new ShapeDefinition();
        Shape shape = fixture.getShape();
        switch ( shape.getType() ) {

            case Circle:
                CircleShape csh = ( CircleShape ) shape;
                shd.setPosition(csh.getPosition());
                shd.setRadius(csh.getRadius());
                break;

            case Edge:

                EdgeShape esh = ( EdgeShape ) shape;

                shd.getVertices().add( new Vector2() );
                shd.getVertices().add( new Vector2() );

                esh.getVertex1( shd.getVertices().get(0) );
                esh.getVertex2( shd.getVertices().get(1) );

                break;

            case Polygon:

                PolygonShape psh = ( PolygonShape ) shape;
                for ( int i = 0; i < psh.getVertexCount(); i++) {
                    Vector2 v = new Vector2();
                    psh.getVertex( i, v);
                    shd.getVertices().add( v );
                }

                break;
            case Chain:
                ChainShape chsh = ( ChainShape ) shape;
                shd.setLooped( chsh.isLooped() );
                for ( int i = 0; i < chsh.getVertexCount(); i++) {
                    Vector2 v = new Vector2();
                    chsh.getVertex( i, v);
                    shd.getVertices().add( v );
                }
                break;
        }
        return shd;
    }

    public void createFixtures( FixtureSetDefinition fsDef ) {
        setDensity( fsDef.getDensity() );
        setRestitution( fsDef.getRestitution() );
        setFriction( fsDef.getFriction() );
        setShapeType( fsDef.getShapeType() );
        createFixtures( fsDef.getShapeDefArray() );
    }


    public void createFixtures( Array< ShapeDefinition> shapeDefArray ) {

        removeAllFixtures();

        for ( ShapeDefinition shd : shapeDefArray ) {
            Fixture fx = createFixture( shd );
            if ( fx == null )
                continue;
            fixtures.add( fx );
        }
    }


    public Fixture createFixture ( ShapeDefinition shd ) {
        FixtureDef fixtureDef = new FixtureDef();

        Shape shape = null;

        switch ( shapeType ) {
            case Circle:
                shape = createCircleShape( shd );
                break;
            case Edge:
                shape  = createEdgeShape( shd );
                break;
            case Polygon:
                shape = createPolygonShape( shd );
                break;
            case Chain:
                shape = createChainShape( shd );
                break;
        }

        if ( shape == null )
            return null;

        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.shape = shape;
        Fixture fixture = bodyHandler.getBody().createFixture( fixtureDef );

        return fixture;

    }

    private Shape createCircleShape( ShapeDefinition shd ) {

        CircleShape sh = new CircleShape();
        sh.setPosition( shd.getPosition() );
        sh.setRadius( shd.getRadius() );

        return sh;
    }

    private Shape createEdgeShape( ShapeDefinition shd ) {

        EdgeShape sh = new EdgeShape();
        sh.set( shd.getVertices().get(0),  shd.getVertices().get(1) );
        return sh;
    }

    private Shape createPolygonShape( ShapeDefinition shd ) {
        PolygonShape sh = new PolygonShape();
        Vector2 [] vertices = new Vector2[ shd.getVertices().size ];
        int i = 0;
        for ( Vector2 v : shd.getVertices() )
            vertices[ i++ ] = v;

        sh.set( vertices );

//        Gdx.app.log("FixtureSet.createPolygonShape","SHD::vertices: " + shd.getVertices().size +
//                " Array::size: " + vertices.length + " i: " + i + " SH:: vertices: " + sh.getVertexCount() );

        if( vertices.length != sh.getVertexCount() ) {
            Gdx.app.log("FixtureSet.createPolygonShape", "WARNING: unable to create shape. Vertex count mismatch");
            return null;
        }

        return sh;
    }

    private Shape createChainShape( ShapeDefinition shd ) {
        ChainShape sh = new ChainShape();

        Vector2 [] vertices = new Vector2[ shd.getVertices().size ];
        int i = 0;
        for ( Vector2 v : shd.getVertices() )
            vertices[ i++ ] = v;

        if ( shd.isLooped() ) {
            sh.createLoop( vertices );
        } else {
            sh.createChain( vertices );
        }
        return sh;
    }

    private static RectangleExt tmpBBox = new RectangleExt();

    public boolean overlaps( RectangleExt rect ) {

        for ( Fixture fx : fixtures ) {
            getBoundingBoxForFixture(tmpBBox, fx );
            if ( rect.overlaps(tmpBBox ) )
                return true;
        }

        return false;
    }

    public static void getBoundingBoxForFixture( RectangleExt bbox, Fixture fs ) {
        switch ( fs.getType() ) {
            case Circle:
                getBoundingBoxForCircleShape( bbox, (CircleShape) fs.getShape() );
                break;
            case Edge:
                getBoundingBoxForEdgeShape( bbox, (EdgeShape) fs.getShape() );
                break;
            case Polygon:
                getBoundingBoxForPolygonShape( bbox, (PolygonShape) fs.getShape());
                break;
            case Chain:
                getBoundingBoxForChainShape( bbox, (ChainShape) fs.getShape() );
                break;
        }
    }

    public static void getBoundingBoxForCircleShape(RectangleExt bbox, CircleShape circleShape ) {
        Vector2 pos = circleShape.getPosition();
        float r = circleShape.getRadius();
        Env.get().world.toView(pos);
        r = Env.get().world.toView( r );
        bbox.set( pos.x - r, pos.y - r, r + r, r + r );
    }

    private static final Vector2 vA = new Vector2();
    private static final Vector2 vB = new Vector2();

    public static void getBoundingBoxForEdgeShape(RectangleExt bbox, EdgeShape edgeShape ) {
        edgeShape.getVertex1( vA );
        edgeShape.getVertex2( vB );

        Env.get().world.toView(vA);
        Env.get().world.toView(vB);

        bbox.set( BBox.getBBox(vA, vB) );
    }

    public static void getBoundingBoxForChainShape( RectangleExt bbox, ChainShape chainShape ) {
        BBox.bBoxProcessingBegin();
        int c = chainShape.getVertexCount();
        for ( int i = 0; i < c; i++) {
            chainShape.getVertex( i, vA);
            Env.get().world.toView(vA);
            BBox.bBoxProcessingAddPoint( vA );
        }
        bbox.set( BBox.bBoxProcessingEnd() );
    }

    public static void getBoundingBoxForPolygonShape( RectangleExt bbox, PolygonShape polygonShape ) {
        BBox.bBoxProcessingBegin();
        int c = polygonShape.getVertexCount();
        for ( int i = 0; i < c; i++) {
            polygonShape.getVertex(i, vA);
            Env.get().world.toView(vA);
            BBox.bBoxProcessingAddPoint( vA );
        }
        bbox.set(BBox.bBoxProcessingEnd());
    }

    @Override
    public void dispose() {

    }

    protected void updateFsDef() {
        fsDef = new FixtureSetDefinition();
        fsDef.setDensity(density);
        fsDef.setRestitution(restitution);
        fsDef.setFriction(friction);
        fsDef.setShapeType(shapeType);

        int c = fixtures.size;

        for ( int i = 0; i < c; i++) {
            ShapeDefinition shd = getShapeDefinition(fixtures.get(i));
            fsDef.getShapeDefArray().add( shd );
        }
    }

    public FixtureSetDefinition getFsDef() {
        updateFsDef();
        FixtureSetDefinition ret = fsDef;
        fsDef = null;
        return ret;
    }

    @Override
    public void preExport() {
        updateFsDef();
    }

    @Override
    public void postExport() {
        fsDef = null;
    }

    @Override
    public void constructGraphics() {

    }

    @Override
    public void constructPhysics() {
        if ( fsDef == null )
            return;
        createFixtures( fsDef );
        fsDef = null;
    }

    @Override
    public void destroyGraphics() {

    }

    @Override
    public void destroyPhysics() {

    }
}
