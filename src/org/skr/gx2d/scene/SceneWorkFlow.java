package org.skr.gx2d.scene;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by rat on 04.01.15.
 */
public interface SceneWorkFlow {
    public interface SceneStateListener {
        public void sceneInitialized(SceneWorkFlow sceneWorkFlowImpl);
        public void sceneGraphicsConstructed(SceneWorkFlow sceneWorkFlowImpl);
        public void scenePhysicsConstructed(SceneWorkFlow sceneWorkFlowImpl);
        public void sceneDisposing(SceneWorkFlow sceneWorkFlowImpl);
    }
    public void addSceneStateListener( SceneStateListener sceneStateListener );
    public void removeSceneStateListener( SceneStateListener sceneStateListener );
    public boolean initialize( Stage stage );
    public Stage getStage();
}
