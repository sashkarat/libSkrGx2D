package org.skr.gx2d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ByteArray;

import java.nio.ByteBuffer;

/**
 * Created by rat on 07.08.14.
 */
public class Utils {

    public static boolean printError(String tag, String msg ) {
        if ( Gdx.app == null )
            System.err.println(tag + " " + msg );
        else
            Gdx.app.error(tag, msg);
        return false;
    }

    public static boolean printMsg(String tag, String msg ) {
        if ( Gdx.app == null )
            System.out.println(tag + " " + msg );
        else
            Gdx.app.log(tag, msg);
        return false;
    }
}
