package org.skr.gx2d.common;


import com.badlogic.gdx.utils.Array;
import org.skr.SkrScript.Engine;
import org.skr.gx2d.script.NodeScriptEE;
import org.skr.gx2d.utils.ModShapeRenderer;

/**
 * Created by rat on 15.08.14.
 */
public class Env {
    public static final Engine scriptEngine = new Engine();
    public static final NodeScriptEE engineExtension = new NodeScriptEE( scriptEngine );

    protected static final Array<Env> envArray = new Array<Env>();
    protected static Env activeEnv = null;

    public static Env get() {
        return activeEnv;
    }

    public static Env get( int index ) {
        try{
            return envArray.get( index );
        } catch ( IndexOutOfBoundsException e ) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getEnvCount() {
        return envArray.size;
    }

    public static Env addNewEnv() {
        Env env = new Env();
        envArray.add( env );
        return env;
    }

    public static void setActiveEnv( int index ) {
        try {
            activeEnv = envArray.get(index);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public static void setActiveEnv( Env env ) {
        activeEnv = env;
    }

    static {
        setActiveEnv( addNewEnv() );
    }

    public boolean debugRender = false;
    public boolean drawBodyHandlerBBox = false;
    public boolean drawSpriteBBox = false;
    public final TextureAtlasHandle taHandle = new TextureAtlasHandle();
    public final PhysWorld world = new PhysWorld();
    public final SceneProvider sceneProvider = new SceneProvider();
    public ModShapeRenderer mshr;

    protected Env() {

    }

}
