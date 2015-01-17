package org.skr.gx2d.script.dts;

import com.badlogic.gdx.graphics.g2d.Animation;
import org.skr.SkrScript.*;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.script.NodeScriptEE;
import org.skr.gx2d.sprite.Sprite;

/**
 * Created by rat on 16.12.14.
 */
public class NodeScriptSprite {

    public static final int E_PM_NORMAL             = NodeScriptEE.genAddr();
    public static final int E_PM_REVERSED           = NodeScriptEE.genAddr();
    public static final int E_PM_LOOP               = NodeScriptEE.genAddr();
    public static final int E_PM_LOOP_REVERSED      = NodeScriptEE.genAddr();
    public static final int E_PM_LOOP_PINGPONG      = NodeScriptEE.genAddr();
    public static final int E_PM_LOOP_RANDOM        = NodeScriptEE.genAddr();

    public static void setup() {
        setupProperties();
        setupFunctions();
    }

    public static void setupProperties() {
        NodeScriptEE.setProperty(NodeScriptEE.PROP_TEXNAME, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsString(sprite.getTexRegionName());
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setTexRegionName(value.asString());
                return sprite.updateTextureRegion( Env.get().taHandle.getAtlas() );
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_FRAMEDURATION, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsFloat( sprite.getFrameDuration() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setFrameDuration( value.asFloat(rc) );
                return sprite.updateTextureRegion(Env.get().taHandle.getAtlas());
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_PLAYMODE, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.set(sprite.getPlayMode(), NodeScriptEE.DTS_ENUM );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setPlayMode((Animation.PlayMode) value.val());
                return sprite.updateTextureRegion(Env.get().taHandle.getAtlas());
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_WIDTH, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsFloat( sprite.getWidth() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setWidth( value.asFloat(rc) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_HEIGHT, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsFloat( sprite.getHeight() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setHeight( value.asFloat(rc) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_KEEP_ASPECT_RATIO, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsBool( sprite.isKeepAspectRatio() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setKeepAspectRatio( value.asBool(rc) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ROTATION, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsFloat( sprite.getRotation() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setRotation( value.asFloat(rc) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_DRAWABLE, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsBool( sprite.isDrawable() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setDrawable( value.asBool( rc ) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_VISIBLE, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsBool( sprite.isVisible() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setVisible( value.asBool(rc) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_X, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsFloat( sprite.getX() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setX( value.asFloat(rc) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_Y, NodeScriptEE.DTS_SPRITE, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                res.setAsFloat( sprite.getY() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                Sprite sprite = (Sprite) obj.val();
                sprite.setY( value.asFloat(rc) );
                return true;
            }
        });
    }

    public static void setupFunctions() {

        NodeScriptEE.setBuildInFunction(E_PM_NORMAL, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(Animation.PlayMode.NORMAL, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_PM_REVERSED, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(Animation.PlayMode.REVERSED, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_PM_LOOP, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(Animation.PlayMode.LOOP, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_PM_LOOP_REVERSED, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(Animation.PlayMode.LOOP_REVERSED, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_PM_LOOP_PINGPONG, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(Animation.PlayMode.LOOP_PINGPONG, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_PM_LOOP_RANDOM, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(Animation.PlayMode.LOOP_RANDOM, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

    }
}
