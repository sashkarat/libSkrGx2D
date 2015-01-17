package org.skr.gx2d.node;

import org.skr.gx2d.common.Env;
import org.skr.gx2d.scene.Scene;

/**
 * Created by rat on 10.01.15.
 */
public abstract class SceneItem extends Node {
    protected void checkOnSceneState( Node subNode ) {
        Scene scene = Env.get().sceneProvider.getActiveScene();
        if ( scene == null )
            return;
        if ( scene.isPhysicsConstructed() )
            subNode.constructPhysics();
        if (scene.isGraphicsConstructed() ) {
            subNode.constructGraphics();
            addActor( subNode );
        }
    }
}
