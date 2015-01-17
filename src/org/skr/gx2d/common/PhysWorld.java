package org.skr.gx2d.common;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import org.skr.gx2d.utils.ModShapeRenderer;

/**
 * Created by rat on 08.06.14.
 */
public class PhysWorld  implements Disposable {


    static private class WorldDebugRenderer extends Box2DDebugRenderer{
        Matrix4 box2dProjection = new Matrix4();
        OrthographicCamera currentCam = null;

        public WorldDebugRenderer(boolean drawBodies, boolean drawJoints, boolean drawAABBs, boolean drawInactiveBodies, boolean drawVelocities, boolean drawContacts) {
            super(drawBodies, drawJoints, drawAABBs, drawInactiveBodies, drawVelocities, drawContacts);
            renderer = new ModShapeRenderer();
        }

        public void render( World world, Stage stage, float scaleFactor) {
            if ( stage.getCamera() instanceof OrthographicCamera ) {
                currentCam = (OrthographicCamera) stage.getCamera();
            }
            box2dProjection.set( stage.getBatch().getProjectionMatrix() );
            box2dProjection.scl( scaleFactor );
            render(world, box2dProjection );
            currentCam = null;
        }


        @Override
        protected void renderBody(Body body) {
            super.renderBody(body);


            // draw Body Center

            renderer.setColor(0.5f, 1, 0.8f, 1);
            float x = body.getWorldCenter().x;
            float y = body.getWorldCenter().y;

            float s2 = 0.008f;

            if ( currentCam != null )
                s2 *= currentCam.zoom;

            float s = s2*2;

            renderer.rect( x - s2, y - s2, s, s);

//            Array<Fixture> fixtureList = body.getFixtureList();
//
//            for ( Fixture fixture : fixtureList ) {
//                if ( fixture.getType() == Shape.Type.Polygon ) {
//                    drawPolygonShape( (PolygonShape) fixture.getShape() );
//                }
//            }
        }

//        private static Vector2 aV = new Vector2();
//        private static Vector2 bV = new Vector2();
//
//        protected void drawPolygonShape( PolygonShape psh ) {
//            int c = psh.getVertexCount();
//            for ( int i = 0; i < c; i++ ) {
//                psh.getVertex( i,  aV );
//                psh.getVertex( (i+1) % c, bV);
//
//                renderer.line(aV, bV);
//            }
//        }
    }

    World box2dWorld;
    Vector2 gravity = new Vector2( 0, -9.8f );
    float scale = 10;
    float timing = 1f/120f;
    WorldDebugRenderer debugRenderer = new WorldDebugRenderer(true, true, false, true, false, true);
    final Vector2 tmpVect = new Vector2();

    public PhysWorld() {
    }

    public void createBox2DWorld( float scale ) {
        dispose();
        box2dWorld = new World( gravity, true);
    }

    public World box2dWorld() {
        return box2dWorld;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void debugRender( Stage stage) {
        debugRenderer.render(box2dWorld, stage, scale);
    }

    public void step() {
       box2dWorld.step(timing, 8, 8);
    }

    public void step( float timeStep, int velocityIterations, int positionIterations ) {
        box2dWorld.step( timeStep, velocityIterations, positionIterations);
    }

    public float toView(float v) {
        return v * scale;
    }

    public Vector2 toView(Vector2 v) {
        return v.scl( scale );
    }

    public Vector2 physToView(Vector2 v) {
        return tmpVect.set(v).scl( scale );
    };

    public float toPhys(float v) {
        return v / scale;
    }

    public Vector2 toPhys( Vector2 v) {
        return v.scl( 1f / scale );
    }

    public Vector2 viewToPhys(Vector2 v) {
        return tmpVect.set(v).scl( 1f / scale );
    }

    public float unTime( float value ) {
        return value / timing;
    }

    public void act() {
        box2dWorld.step(timing, 10, 8);
    }

    private static final Array<Body> bodies = new Array<Body>();

    @Override
    public void dispose() {
        if ( box2dWorld == null )
            return;
        box2dWorld.getBodies( bodies );
        for ( Body body : bodies )
            box2dWorld.destroyBody( body );
    }

}
