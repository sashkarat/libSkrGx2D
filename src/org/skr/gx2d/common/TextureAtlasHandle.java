package org.skr.gx2d.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import org.skr.gx2d.utils.Utils;

/**
 * Created by rat on 04.01.15.
 */
public class TextureAtlasHandle implements Disposable {
    public interface AtlasStateListener {
        public void toBeDisposed( TextureAtlas atlas );
        public void loaded( TextureAtlas atlas );
    }

    TextureAtlas atlas;
    String internalTextureAtlasPath;
    Array< String > textureRegionNames = new Array<String>();
    Array<AtlasStateListener> atlasStateListeners = new Array<AtlasStateListener>();

    @Override
    public void dispose() {
        if ( atlas == null )
            return;
        for( AtlasStateListener l : atlasStateListeners )
            l.toBeDisposed( atlas );
        atlas.dispose();
        atlas = null;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public String getInternalTextureAtlasPath() {
        return internalTextureAtlasPath;
    }

    public void setInternalTextureAtlasPath(String internalTextureAtlasPath) {
        this.internalTextureAtlasPath = internalTextureAtlasPath;
    }

    public Array<String> getTextureRegionNames() {
        return textureRegionNames;
    }

    public void addAtlasStateListener( AtlasStateListener listener ) {
        atlasStateListeners.add( listener );
    }

    public void removeAtlasStateListener( AtlasStateListener listener ) {
        atlasStateListeners.removeValue( listener, true );
    }

    public boolean uploadAtlas() {
        dispose();

        FileHandle packFile = Gdx.files.internal( internalTextureAtlasPath );

        if ( ! packFile.exists() )
            return Utils.printError("TextureAtlasHandle.uploadAtlas", packFile.toString() + " does not exist");

        TextureAtlas.TextureAtlasData atlasData = new TextureAtlas.TextureAtlasData(
                packFile, packFile.parent(), false );

        atlas = new TextureAtlas( atlasData );

        this.textureRegionNames.clear();

        Array<TextureAtlas.TextureAtlasData.Region> regions = atlasData.getRegions();

        for (TextureAtlas.TextureAtlasData.Region region : regions ) {
            if ( region.index > 1)
                continue;
            this.textureRegionNames.add( region.name );
        }

        this.textureRegionNames.sort();

        for ( AtlasStateListener l : atlasStateListeners )
            l.loaded( atlas );

//        Utils.printMsg("TextureAtlasHandle.uploadAtlas", "successfully done. Available regions: ");
//        for (String name : textureRegionNames )
//            Utils.printMsg("TextureAtlasHandle.uploadAtlas", "  " + name);
//        Utils.printMsg("TextureAtlasHandle.uploadAtlas", "------------------\n");

        return true;
    }
}
