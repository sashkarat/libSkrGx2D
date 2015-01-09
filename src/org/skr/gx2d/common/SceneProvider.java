package org.skr.gx2d.common;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import org.skr.gx2d.scene.Scene;

/**
 * Created by rat on 04.01.15.
 */
public class SceneProvider implements Disposable{
    Stage stage;
    Array<Scene> scenes = new Array<Scene>();
    Scene activeScene = null;

    public SceneProvider() {
    }

    public void setStage(Stage stage) {
        Scene aS = activeScene;

        removeActiveScene();

        this.stage = stage;

        if ( stage == null )
            return;

        for ( Scene scene : scenes ) {
            scene.initialize(stage);
            scene.constructPhysics();
            scene.constructGraphics();
        }

        if ( aS != null )
            setActiveScene( aS );
    }

    public Scene addScene( Scene scene) {
        scenes.add( scene );
        if ( stage != null ) {
            scene.initialize(stage);
            scene.constructPhysics();
            scene.constructGraphics();
        }
        return scene;
    }

    public void removeScene( Scene scene, boolean doDispose ) {
        if ( activeScene == scene )
            removeActiveScene();
        if ( ! scenes.removeValue( scene, true) )
            return;
        if ( doDispose )
            scene.dispose();
    }
    public int getSceneCount() {
        return scenes.size;
    }

    public Scene getScene( int index ) {
        if ( index < 0 || index >= scenes.size )
            return null;
        return scenes.get( index );
    }

    public void setActiveScene( int index ) {
        Scene scene = getScene( index );
        if ( scene == null )
            return;
        setActiveScene( scene );
    }

    public void setActiveScene( Scene scene ) {
        removeActiveScene();
        if ( stage != null)
            stage.addActor( scene );
        activeScene = scene;
    }

    public void removeActiveScene() {
        if ( this.stage != null && activeScene != null )
            activeScene.remove();
        activeScene = null;
    }

    public Scene getActiveScene() {
        return activeScene;
    }

    @Override
    public void dispose() {
        removeActiveScene();
        for ( Scene scene : scenes)
            scene.dispose();
    }
}
