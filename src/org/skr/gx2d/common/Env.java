package org.skr.gx2d.common;


import org.skr.gx2d.utils.ModShapeRenderer;

/**
 * Created by rat on 15.08.14.
 */
public class Env {
    public static boolean debugRender = false;
    public static boolean drawBodyHandlerBBox = false;
    public static boolean drawSpriteBBox = false;
    public static final TextureAtlasHandle taHandle = new TextureAtlasHandle();
    public static final PhysWorld world = new PhysWorld();
    public static final SceneProvider sceneProvider = new SceneProvider();
    public static ModShapeRenderer mshr;

//    public static final Engine scriptEngine = new Engine();
//    public static final PhysScriptEE engineExtension = new PhysScriptEE( scriptEngine );



}
