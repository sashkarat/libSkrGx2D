package org.skr.gx2d.utils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by rat on 23.03.14.
 */
public class RectangleExt extends Rectangle {


    public RectangleExt() {
        super();
    }

    public RectangleExt(float x, float y, float width, float height) {
        super(x,y, width, height);
    }

    public RectangleExt( Vector2 a, Vector2 b ) {
        super( a.x, a.y, b.x - a.x, b.y - a.y );
        makePositiveDimension();
    }

    public RectangleExt(Rectangle r) {
        super(r);
    }

    public float getLeft() {
        return this.x;
    }

    public float getRight() {
        return this.x + this.width;
    }

    public float getTop() {
        return this.y + this.height;
    }

    public float getBottom() {
        return this.y;
    }

    public RectangleExt setRight( float x ) {
        this.width = x - this.x;
        return this;
    }

    public RectangleExt setLeft( float x ) {
        this.x = x;
        return this;
    }

    public RectangleExt setBottom( float y ) {
        this.y = y;
        return this;
    }

    public RectangleExt setTop( float y ) {
        this.height = y - this.y;
        return this;
    }

    public float getAspectRatio() {
        return getHeight() / getWidth();
    }

    public float getCenterX() {
        return this.x + this.width / 2;
    }

    public float getCenterY() {
        return this.y + this.height / 2;
    }

    public static String getRecStr( RectangleExt r ) {
        return " l: " + r.getLeft() + " r: " + r.getRight() +
                " b: " + r.getBottom() + " t: " + r.getTop();
    }

    public void makePositiveDimension() {

        if ( this.width < 0 ) {
            float w = - this.width;
            this.x = getRight();
            this.width = w;
        }

        if ( this.height < 0 ) {
            float h = - this.height;
            this.y = getTop();
            this.height = h;
        }
    }
}
