package org.skr.gx2d.editor;

import com.badlogic.gdx.utils.Array;
import org.skr.gx2d.common.Gx2DApplication;

/**
 * Created by rat on 18.01.15.
 */
public abstract class Gx2DEditorApplication extends Gx2DApplication {

    Array< AbstractEditorScreen > editorScreens = new Array<AbstractEditorScreen>();

    public void addEditorScreen(AbstractEditorScreen aes) {
        if ( ! editorScreens.contains( aes, true ) )
            editorScreens.add( aes );
    }

    public void removeEditorScreen( AbstractEditorScreen aes ) {
        editorScreens.removeValue( aes, true );
    }

    @Override
    protected void onCreate() {
        for ( AbstractEditorScreen aes : editorScreens )
            aes.create();
    }

    public void toggleEditorScreen( int index ) {
        try {
            toggleCurrentScreen( editorScreens.get(index) );
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDispose() {
        for ( AbstractEditorScreen aes : editorScreens )
            aes.dispose();
    }

}
