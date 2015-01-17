package org.skr.gx2d.common;

import com.badlogic.gdx.*;
import org.skr.gx2d.utils.ModShapeRenderer;
import org.skr.gx2d.utils.Utils;

/**
 * Created by rat on 30.05.14.
 */
public abstract class Gx2DApplication extends Game {

    public static class Config {
        public float physWorldScale = 100.0f;
        public String internalTextureAtlasPath = "";
    }

    private static Gx2DApplication instance = null;

    Screen currentScreen;
    Config cfg = new Config();

    public Gx2DApplication() {
        Gx2DApplication.instance = this;
    }

    public static Gx2DApplication get() {
        return Gx2DApplication.instance;
    }

    public Config getCfg() {
        return cfg;
    }

    public void setCfg(Config cfg) {
        this.cfg = cfg;
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    protected void toggleCurrentScreen(Screen screen) {
        currentScreen = screen;
        setScreen( currentScreen );
    }

    protected  abstract void onCreate();
    protected abstract void onDispose();

    @Override
    public void create() {
        Env.get().world.createBox2DWorld( cfg.physWorldScale );
        Env.get().mshr = new ModShapeRenderer();
        if ( ! cfg.internalTextureAtlasPath.isEmpty() ) {
            Env.get().taHandle.setInternalTextureAtlasPath(cfg.internalTextureAtlasPath);
            Env.get().taHandle.uploadAtlas();
        }
        onCreate();
    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
        if ( screen instanceof InputProcessor ) {
            Gdx.input.setInputProcessor((InputProcessor) screen);
        }
    }

    @Override
    public void dispose() {
        super.dispose();

        onDispose();

        for ( int i = 0; i < Env.getEnvCount(); i++ ) {
            Env.get(i).sceneProvider.dispose();
            Env.get(i).taHandle.dispose();
            Env.get(i).world.dispose();
        }

        Utils.printMsg("Gx2DApplication.dispose", " done");
    }

}
