package org.skr.gx2d.scene;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import org.skr.SkrScript.ValuePool;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.annotation.NodeAddMethodAnnotation;
import org.skr.gx2d.node.annotation.Nfa;
import org.skr.gx2d.utils.ModShapeRenderer;
import org.skr.gx2d.utils.Utils;

/**
 * Created by rat on 04.01.15.
 */
public class Scene extends Node implements SceneWorkFlow {

    Array<SceneStateListener> sceneStateListeners = new Array<SceneStateListener>();

    CameraController cameraController;
    OrthographicCamera camera;

    ValuePool valPool = new ValuePool();

    @Nfa
    String modelDirectory = "";

    @Nfa
    float viewCenterX = 0;
    @Nfa
    float viewCenterY = 0;

    @Nfa
    float viewLeft = -500;
    @Nfa
    float viewRight = 500;
    @Nfa
    float viewTop = 500;
    @Nfa
    float viewBottom = -500;

    @Nfa
    @NodeAddMethodAnnotation( name = "addModelHandler" )
    ModelHandler modelHandler;

    boolean activePhysics = false;
    float targetFps = 60f;
    float timing = 1f / targetFps;
    int velocityIterations = 8;
    int positionIterations = 10;

    CollisionSolver collisionSolver;
    ModShapeRenderer shapeRenderer;

    boolean graphicsConstructed = false;
    boolean physicsConstructed = false;

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

    public boolean isGraphicsConstructed() {
        return graphicsConstructed;
    }

    public boolean isPhysicsConstructed() {
        return physicsConstructed;
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

        if ( physicsConstructed ) {
            if ( ! mh.isModelLoaded() )
                mh.loadModelFromResource();
            mh.constructPhysics();
        }

        if ( graphicsConstructed ) {
            if ( ! mh.isModelLoaded() )
                mh.loadModelFromResource();
            mh.constructGraphics();
            addActor( mh );
        }
        return (ModelHandler) modelHandler.appendNode( mh );
    }

    public ModelHandler removeModelHandler( ModelHandler mh ) {

        if ( modelHandler == null )
            return mh;

        if ( ! modelHandler.isNodeInChain( mh ) )
            return mh;

        mh.remove();
        mh.removeFromChain();

        return mh;
    }

    @Override
    public void addSceneStateListener(SceneStateListener sceneStateListener) {
        sceneStateListeners.add( sceneStateListener );
    }

    @Override
    public void removeSceneStateListener(SceneStateListener sceneStateListener) {
        sceneStateListeners.removeValue( sceneStateListener, true );
    }

    @Override
    public boolean initialize(Stage stage) {
        setStage( stage );
        cameraController = new CameraController( this );
        shapeRenderer = new ModShapeRenderer();
        camera = (OrthographicCamera) stage.getCamera();
        for ( int i = 0; i < sceneStateListeners.size; i++ )
            sceneStateListeners.get(i).sceneInitialized(this);
        return false;
    }

    @Override
    public void constructGraphics() {

        destroyGraphics();

        if ( modelHandler != null ) {

            for (Node n : modelHandler) {
                ModelHandler mh = (ModelHandler) n;
                if (!mh.isModelLoaded())
                    if (!mh.loadModelFromResource())
                        continue;
                addActor(mh);
                mh.constructGraphics();
            }
        }

        graphicsConstructed = true;
        for ( int i = 0; i < sceneStateListeners.size; i++ )
            sceneStateListeners.get(i).sceneGraphicsConstructed(this);
    }

    @Override
    public void constructPhysics() {

        destroyPhysics();

        Env.get().world.box2dWorld().setContactListener(collisionSolver);

        if ( modelHandler != null ) {

            for (Node n : modelHandler) {
                ModelHandler mh = (ModelHandler) n;
                if (!mh.isModelLoaded())
                    if (!mh.loadModelFromResource())
                        continue;
                mh.constructPhysics();
            }
        }

        physicsConstructed = true;

        for ( int i = 0; i < sceneStateListeners.size; i++ )
            sceneStateListeners.get(i).scenePhysicsConstructed(this);
    }

    @Override
    public void destroyGraphics() {
        for (Actor ac : getChildren() )
            ac.remove();
        if ( modelHandler == null )
            return;
        for ( Node node : modelHandler ) {
            ModelHandler mh = (ModelHandler) node;
            mh.destroyGraphics();
        }

        graphicsConstructed = false;
    }

    @Override
    public void destroyPhysics() {
        if ( modelHandler == null )
            return;
        for ( Node node : modelHandler ) {
            ModelHandler mh = (ModelHandler) node;
            mh.destroyPhysics();
        }
        Env.get().world.box2dWorld().setContactListener(null);
        physicsConstructed = false;
    }

    public void doPhysWorldStep() {
        Env.get().world.step(timing, velocityIterations, positionIterations);
    }

    @Override
    protected void nodeAct(float delta) {
        if ( activePhysics ) {
            collisionSolver.reset();
            doPhysWorldStep();
            collisionSolver.runScripts();
        }
    }

    @Override
    protected void nodePostAct(float delta) {
        cameraController.act(delta);
    }

    @Override
    protected void nodeDraw(Batch batch, float parentAlpha) {

    }

    @Override
    protected void nodeDebugDraw(Batch batch, float parentAlpha) {
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
        for ( int i = 0; i < sceneStateListeners.size; i++ )
            sceneStateListeners.get(i).sceneDisposing(this);
        if ( modelHandler == null )
            return;
        for ( Node node : modelHandler )
            node.dispose();
    }
}
