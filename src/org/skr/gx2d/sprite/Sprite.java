package org.skr.gx2d.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.common.TextureAtlasHandle;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.annotation.NodeData;
import org.skr.gx2d.node.annotation.NodeDataAccessor;
import org.skr.gx2d.node.annotation.NodeDataMap;
import org.skr.gx2d.node.annotation.NodeField;
import org.skr.gx2d.utils.BBox;
import org.skr.gx2d.utils.RectangleExt;

import java.util.Stack;

/**
 * Created by rat on 06.01.15.
 */

@NodeDataMap(
        data = {
                @NodeData(name = "width",
                        accessor = @NodeDataAccessor(
                                read = "getWidth",
                                write = "setWidth",
                                type = float.class
                        )),
                @NodeData(name = "height",
                        accessor = @NodeDataAccessor(
                                read = "getHeight",
                                write = "setHeight",
                                type = float.class
                        )),
                @NodeData(name = "x",
                        accessor = @NodeDataAccessor(
                                read = "getX",
                                write = "setX",
                                type = float.class
                        )),
                @NodeData(name = "y",
                        accessor = @NodeDataAccessor(
                                read = "getY",
                                write = "setY",
                                type = float.class
                        )),
                @NodeData(name = "originX",
                        accessor = @NodeDataAccessor(
                                read = "getOriginX",
                                write = "setOriginX",
                                type = float.class
                        )),
                @NodeData(name = "originY",
                        accessor = @NodeDataAccessor(
                                read = "getOriginY",
                                write = "setOriginY",
                                type = float.class
                        )),
                @NodeData(name = "scaleX",
                        accessor = @NodeDataAccessor(
                                read = "getScaleX",
                                write = "setScaleX",
                                type = float.class
                        )),
                @NodeData(name = "scaleY",
                        accessor = @NodeDataAccessor(
                                read = "getScaleY",
                                write = "setScaleY",
                                type = float.class
                        )),
                @NodeData(name = "rotation",
                        accessor = @NodeDataAccessor(
                                read = "getRotation",
                                write = "setRotation",
                                type = float.class
                        )),
                @NodeData(name = "color",
                        accessor = @NodeDataAccessor(
                                read = "getColor",
                                write = "setColor",
                                type = Color.class
                        ))
        }
)


public class Sprite extends Node {

    @Override
    public void constructPhysics() {

    }

    public interface RenderableUserObject {
        public void render( Sprite sprite, Batch batch );
    }

    @NodeField
    String texRegionName = "";

    @NodeField
    float frameDuration = 0.02f;

    @NodeField
    Animation.PlayMode playMode = Animation.PlayMode.LOOP;

    @NodeField
    boolean keepAspectRatio = false;

    @NodeField
    boolean drawable = true;

    private TextureRegion currentRegion;
    private Animation animation;
    private float stateTime = 0;

    RectangleExt boundingBox = new RectangleExt();

    private RenderableUserObject renderableUserObject;

    TextureAtlasHandle.AtlasStateListener atlasStateListener = new TextureAtlasHandle.AtlasStateListener() {
        @Override
        public void toBeDisposed(TextureAtlas atlas) {
            currentRegion = null;
            animation = null;
            stateTime = 0;
        }

        @Override
        public void loaded(TextureAtlas atlas) {
            updateTextureRegion( atlas );
        }
    };


    public Sprite() {
        if (Env.taHandle != null )
            Env.taHandle.addAtlasStateListener( atlasStateListener );
    }

    public String getTexRegionName() {
        return texRegionName;
    }

    public void setTexRegionName(String texRegionName) {
        this.texRegionName = texRegionName;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }

    public Animation.PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(Animation.PlayMode playMode) {
        this.playMode = playMode;
    }

    public boolean isKeepAspectRatio() {
        return keepAspectRatio;
    }

    public void setKeepAspectRatio(boolean keepAspectRatio) {
        this.keepAspectRatio = keepAspectRatio;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

    @Override
    protected boolean activate(boolean state) {
        return true;
    }

    @Override
    public Type getType() {
        return Type.Sprite;
    }

    @Override
    public boolean isType(Type type) {
        return type == Type.Sprite;
    }

    @Override
    protected boolean upload() {
        return false;
    }

    public boolean updateTextureRegion(TextureAtlas atlas) {

        if ( animation != null )
            animation = null;
        if ( currentRegion != null )
            currentRegion = null;

        if ( texRegionName.isEmpty() ) {
            return false;
        }

        stateTime = 0;

        if ( atlas != null ) {
            Array<TextureAtlas.AtlasRegion> tex = atlas.findRegions(texRegionName);
            animation = new Animation(frameDuration, tex, playMode);
            stateTime = 0;
            int l = animation.getKeyFrames().length;
            if ( l == 0 ) {
                animation = null;
                currentRegion = null;
                Gdx.app.error("Sprite.updateTextureRegion", "WARNING: " + "<Name: "
                        + getName() + "> unable to load: " + texRegionName);
                return false;
            }
            currentRegion = animation.getKeyFrame(stateTime);
        }

        checkAspectRatio();
        return true;
    }


    public RenderableUserObject getRenderableUserObject() {
        return renderableUserObject;
    }

    public void setRenderableUserObject(RenderableUserObject renderableUserObject) {
        this.renderableUserObject = renderableUserObject;
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        checkAspectRatio();
    }

    @Override
    public void setHeight(float height) {
        if ( keepAspectRatio )
            height = getHeightByWidth( height );
        super.setHeight(height);
    }


    public float getHeightByWidth( float height) {
        if ( !keepAspectRatio )
            return height;
        if ( animation == null ) {
            return height;
        }

        TextureRegion reg = animation.getKeyFrame(0);

        float aspectRatio = (float) reg.getRegionHeight() / (float)reg.getRegionWidth();

        return getWidth() * aspectRatio ;
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        checkAspectRatio();
    }

    @Override
    public void sizeBy(float size) {
        super.sizeBy(size);
        checkAspectRatio();
    }

    @Override
    public void sizeBy(float width, float height) {
        super.sizeBy(width, height);
        checkAspectRatio();
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        checkAspectRatio();
    }

    void checkAspectRatio() {

        if ( !keepAspectRatio )
            return;
        if ( animation == null ) {
            return;
        }

        setHeight( getHeightByWidth( getHeight() ) );

    }

    @Override
    public void act(float delta) {
        if ( !isActive() )
            return;
        super.act(delta);

        if ( animation == null )
            return;

        stateTime += delta;
        currentRegion = animation.getKeyFrame( stateTime );
//        updateSpriteBoundingBox();
    }


    public RectangleExt getSpriteBoundingBox() {
        updateSpriteBoundingBox();
        return boundingBox;
    }

    public boolean contains( Vector2 localPoint ) {

        float w2 = Math.abs( getWidth() / 2 );

        if( localPoint.x <  - w2 ) {
            return false;
        }
        if ( localPoint.x > w2 ) {
            return false;
        }

        float h2 = Math.abs( getHeight() /2 );

        if ( localPoint.y < -h2 )
            return false;

        if ( localPoint.y > h2 )
            return false;

        return true;
    }

    public Sprite getSprite(Vector2 localCoord) {

        Sprite spriteRes;

        Vector2 chCoord = new Vector2();

        for ( Actor a : getChildren() ) {
            if ( !( a instanceof Sprite ) )
                continue;
            Sprite sprite = (Sprite) a;
            chCoord.set(localCoord);
            sprite.parentToLocalCoordinates(chCoord);
            spriteRes = sprite.getSprite(chCoord);
            if ( spriteRes != null )
                return spriteRes;
        }
        if ( contains( localCoord ) ) {
            return this;
        }
        return null;
    }

    private final static RectangleExt box = new RectangleExt();

    private final RectangleExt chBBox = new RectangleExt();

    private void updateSpriteBoundingBox() {

        box.set( getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight() );
        boundingBox.set(BBox.getBBox(box, getWidth() / 2, getHeight() / 2, getRotation()));

        for ( int i = 0; i < getChildren().size; i++) {
            Actor a = getChildren().get(i);
            if ( ! a.isVisible() )
                continue;
            if ( a.getUserObject() == null )
                continue;
            if ( ! ((Node) a.getUserObject()).isType(Type.Sprite) )
                continue;
            Sprite spriteCh = (Sprite) a.getUserObject();
            chBBox.set(spriteCh.getSpriteBoundingBox());
            chBBox.setX( chBBox.getX() + getX() );
            chBBox.setY( chBBox.getY() + getY() );

            chBBox.set( BBox.getBBox(chBBox, getX() - chBBox.getX(), getY() - chBBox.getY(), getRotation()) );

            boundingBox.set( BBox.getBBox( boundingBox, chBBox ) );
        }
    }


    private void drawBoundingBox( Batch batch, float parentAlpha ) {

        // bounding box will be drawn around top Sprite only
        if ( getParent() != null ) {
            if ( getParent().getUserObject() == null )
                return;
            Node pNode = (Node) getParent().getUserObject();
            if ( pNode.getType() == Type.Sprite || pNode.getType() == Type.BodyHandler)
                return;
        }


        batch.end();
        Env.mshr.setProjectionMatrix(batch.getProjectionMatrix());
        Env.mshr.setTransformMatrix(batch.getTransformMatrix());

        Env.mshr.begin(ShapeRenderer.ShapeType.Line);


        if ( boundingBox != null ) {
            Env.mshr.setColor(0.7f, 0.7f, 0.7f, 1);
            Env.mshr.rect(boundingBox.getX(), boundingBox.getY(),
                    boundingBox.getWidth(), boundingBox.getHeight());
        }

        Env.mshr.end();
        batch.begin();
    }

    @Override
    public void draw( Batch batch, float parentAlpha ) {

        if ( ! isVisible() )
            return;

        if ( isDrawable() && currentRegion != null ) {

            batch.setColor(getColor());

            float hW = getWidth() / 2;
            float hH = getHeight() / 2;

            batch.draw(currentRegion, getX() - hW, getY() - hH, hW, hH, getWidth(), getHeight(), 1, 1, getRotation());

            if ( Env.debugRender && Env.drawSpriteBBox ) {
                drawBoundingBox(batch, parentAlpha );
            }
        }

        if (renderableUserObject != null)
            renderableUserObject.render(this, batch);

        super.draw( batch, parentAlpha );
    }

    static final Stack<Actor> actorsTmpStack = new Stack<Actor>();
    static final Matrix3 mtx = new Matrix3();


    @Override
    public void dispose() {
        animation = null;
        currentRegion = null;
        Env.taHandle.removeAtlasStateListener( atlasStateListener );
    }

    public static Vector2 stageToActorLocal(Actor actor, Vector2 stageSpaceCoordinates) {

        mtx.idt();
        actorsTmpStack.clear();

        Actor act = actor;
        actorsTmpStack.push( act );

        while ( act.hasParent() ) {
            act = act.getParent();
            actorsTmpStack.push( act );
        }

        while ( !actorsTmpStack.isEmpty() ) {
            Actor a = actorsTmpStack.pop();
            mtx.translate(a.getX(), a.getY());
            mtx.rotate(a.getRotation());
        }


        stageSpaceCoordinates.mul( mtx.inv() );

        return stageSpaceCoordinates;

    }

    public static Vector2 rotateStageToLocal(Actor actor, Vector2 stageVector ) {
        actorsTmpStack.clear();

        Actor act = actor;

        while ( act.hasParent() ) {
            act = act.getParent();
            actorsTmpStack.push( act );
        }

        float rot = 0;

        while ( !actorsTmpStack.isEmpty() ) {
            Actor a = actorsTmpStack.pop();
            rot += a.getRotation();
        }
        stageVector.rotate( -rot );
        return stageVector;
    }

}
