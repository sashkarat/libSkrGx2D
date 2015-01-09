package org.skr.gx2d.scene;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import org.skr.SkrScript.ValuePool;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.annotation.NodeAddMethod;
import org.skr.gx2d.node.annotation.NodeField;
import org.skr.gx2d.utils.ModShapeRenderer;

/**
 * Created by rat on 04.01.15.
 */
public class Scene extends Node implements SceneWorkFlow {

    World world;
    CameraController cameraController;
    OrthographicCamera camera;

    ValuePool valPool = new ValuePool();

    @NodeField
    String modelDirectory = "";

    @NodeField
    float viewCenterX = 0;
    @NodeField
    float viewCenterY = 0;

    @NodeField
    float viewLeft = -500;
    @NodeField
    float viewRight = 500;
    @NodeField
    float viewTop = 500;
    @NodeField
    float viewBottom = -500;

    @NodeField
    @NodeAddMethod( name = "addModelHandler" )
    ModelHandler modelHandler;

    boolean activePhysics = false;
    float targetFps = 60f;
    float timing = 1f / targetFps;
    int velocityIterations = 8;
    int positionIterations = 10;

    CollisionSolver collisionSolver;
    ModShapeRenderer shapeRenderer;

    public Scene() {
        super();
        collisionSolver = new CollisionSolver( this );
    }

    @Override
    protected boolean activate(boolean state) {
        return false;
    }

    @Override
    public Type getType() {
        return Type.Scene ;
    }

    @Override
    public boolean isType(Type type) {
        return type == Type.Scene;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
        world.setContactListener( collisionSolver );
    }

    public String getModelDirectory() {
        return modelDirectory;
    }

    public void setModelDirectory(String modelDirectory) {
        this.modelDirectory = modelDirectory;
    }

    public float getViewCenterX() {
        return viewCenterX;
    }

    public void setViewCenterX(float viewCenterX) {
        this.viewCenterX = viewCenterX;
    }

    public float getViewCenterY() {
        return viewCenterY;
    }

    public void setViewCenterY(float viewCenterY) {
        this.viewCenterY = viewCenterY;
    }

    public float getViewLeft() {
        return viewLeft;
    }

    public void setViewLeft(float viewLeft) {
        this.viewLeft = viewLeft;
    }

    public float getViewRight() {
        return viewRight;
    }

    public void setViewRight(float viewRight) {
        this.viewRight = viewRight;
    }

    public float getViewTop() {
        return viewTop;
    }

    public void setViewTop(float viewTop) {
        this.viewTop = viewTop;
    }

    public float getViewBottom() {
        return viewBottom;
    }

    public void setViewBottom(float viewBottom) {
        this.viewBottom = viewBottom;
    }

    public boolean isActivePhysics() {
        return activePhysics;
    }

    public void setActivePhysics(boolean activePhysics) {
        this.activePhysics = activePhysics;
    }

    public float getTargetFps() {
        return targetFps;
    }

    public void setTargetFps(float targetFps) {
        this.targetFps = targetFps;
    }

    public float getTiming() {
        return timing;
    }

    public void setTiming(float timing) {
        this.timing = timing;
    }

    public int getVelocityIterations() {
        return velocityIterations;
    }

    public void setVelocityIterations(int velocityIterations) {
        this.velocityIterations = velocityIterations;
    }

    public int getPositionIterations() {
        return positionIterations;
    }

    public void setPositionIterations(int positionIterations) {
        this.positionIterations = positionIterations;
    }

    public ModelHandler getModelHandler() {
        return modelHandler;
    }

    public ModelHandler setModelHandler(ModelHandler modelHandler) {
        this.modelHandler = modelHandler;
        this.modelHandler.setTopNode( this );
        return this.modelHandler;
    }

    public ModelHandler addModelHandler( ModelHandler mh ) {
        if ( modelHandler == null )
            return setModelHandler( mh );
        return (ModelHandler) modelHandler.appendNode( mh );
    }

    @Override
    public boolean initialize(Stage stage) {
        setStage( stage );
        cameraController = new CameraController( this );
        shapeRenderer = new ModShapeRenderer();
        camera = (OrthographicCamera) stage.getCamera();
        return false;
    }

    @Override
    public void constructGraphics() {
        ModelHandler mh = modelHandler;
        while ( mh != null ) {
            addActor( mh );
            mh.constructGraphics();
            mh = (ModelHandler) mh.getNextNode();
        }
    }

    @Override
    public void constructPhysics() {
    }

    public void doPhysWorldStep() {
        world.step( timing, velocityIterations, positionIterations );
    }

    @Override
    public void act(float delta) {

        if ( activePhysics ) {
            collisionSolver.reset();
            doPhysWorldStep();
            collisionSolver.runScripts();
        }

        super.act(delta);
        cameraController.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if ( !Env.debugRender )
            return;

        shapeRenderer.setProjectionMatrix( batch.getProjectionMatrix() );
        shapeRenderer.setTransformMatrix( batch.getTransformMatrix() );
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(viewLeft + 10, viewBottom + 10,
                viewRight - viewLeft - 20, viewTop - viewBottom - 20);
        shapeRenderer.end();
    }


    @Override
    public void dispose() {
        ModelHandler mh = modelHandler;
        while ( mh != null ) {
            mh.dispose();
            mh = (ModelHandler) mh.getNextNode();
        }
    }
}
