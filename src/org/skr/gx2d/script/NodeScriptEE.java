package org.skr.gx2d.script;

import org.skr.SkrScript.*;
import org.skr.gx2d.script.dts.*;

/**
 * Created by rat on 13.12.14.
 */
public class NodeScriptEE extends EngineExtension {

    protected static int baseAddr = Def.EXTENDED_BUILD_IN_FUNCTION_ADDRESS;

    public static int genAddr() {
        return baseAddr--;
    }

    public static final byte DTS_LONG = Def.EXTENDED_DTS_CODE;
    public static final byte DTS_VECTOR2 = Def.EXTENDED_DTS_CODE - 1;
    public static final byte DTS_SCENE = Def.EXTENDED_DTS_CODE - 2;
    public static final byte DTS_MODEL_HANDLER = Def.EXTENDED_DTS_CODE - 3;
    public static final byte DTS_MODEL = Def.EXTENDED_DTS_CODE - 4;
    public static final byte DTS_PHYSSET = Def.EXTENDED_DTS_CODE - 5;
    public static final byte DTS_BODY_HANDLER = Def.EXTENDED_DTS_CODE - 6;
    public static final byte DTS_JOINT_HANDLER = Def.EXTENDED_DTS_CODE - 7;
    public static final byte DTS_FIXTURE_SET = Def.EXTENDED_DTS_CODE - 8;
    public static final byte DTS_SPRITE = Def.EXTENDED_DTS_CODE - 9;
    public static final byte DTS_ENUM = Def.EXTENDED_DTS_CODE - 10;

    public static final int PROP_ID = 0;
    public static final int PROP_NAME = 1;
    public static final int PROP_TYPE = 2;
    public static final int PROP_POSITION = 3;
    public static final int PROP_ANGLE = 4;
    public static final int PROP_LINEAR_VEL = 5;
    public static final int PROP_ANGULAR_VEL = 6;
    public static final int PROP_LINEAR_DAMPING = 7;
    public static final int PROP_ANGULAR_DAMPING = 8;
    public static final int PROP_ALLOW_SLEEP = 9;
    public static final int PROP_AWAKE = 10;
    public static final int PROP_FIXED_ROT = 11;
    public static final int PROP_BULLET = 12;
    public static final int PROP_ACTIVE = 13;
    public static final int PROP_GRAVITY_SCALE = 14;
    public static final int PROP_MASS = 15;
    public static final int PROP_ABSORBED_IMPULSE = 24;
    public static final int PROP_FRICTION = 25;
    public static final int PROP_RESTITUTION = 26;
    public static final int PROP_DENSITY = 27;
    public static final int PROP_BI_A = 28;
    public static final int PROP_BI_B = 29;
    public static final int PROP_COLLIDE_CONNECTED = 30;
    public static final int PROP_LENGTH = 31;
    public static final int PROP_ANCHOR_A = 32;
    public static final int PROP_ANCHOR_B = 33;
    public static final int PROP_FREQUENCY = 34;
    public static final int PROP_DAMPING_RATIO = 35;
    public static final int PROP_RATIO = 36;
    public static final int PROP_MAX_LENGTH = 37;
    public static final int PROP_MAX_FORCE = 38;
    public static final int PROP_MAX_MOTOR_FORCE = 39;
    public static final int PROP_MOTOR_SPEED = 40;
    public static final int PROP_MAX_MOTOR_TORQUE = 41;
    public static final int PROP_MAX_TORQUE = 42;
    public static final int PROP_ANGULAR_OFFSET = 43;
    public static final int PROP_LOWER_ANGLE = 44;
    public static final int PROP_UPPER_ANGLE = 45;
    public static final int PROP_CORRECTION_FACTOR = 46;
    public static final int PROP_REFERENCE_ANGLE = 47;
    public static final int PROP_LOWER_TRANSLATION = 48;
    public static final int PROP_UPPER_TRANSLATION = 49;
    public static final int PROP_ENABLE_LIMIT = 50;
    public static final int PROP_ENABLE_MOTOR = 51;
    public static final int PROP_JI_A = 52;
    public static final int PROP_JI_B = 53;
    public static final int PROP_LINEAR_OFFSET = 54;
    public static final int PROP_AXIS = 55;
    public static final int PROP_GROUND_A = 56;
    public static final int PROP_GROUND_B = 57;
    public static final int PROP_TEXNAME = 58;
    public static final int PROP_FRAMEDURATION = 59;
    public static final int PROP_PLAYMODE = 60;
    public static final int PROP_WIDTH = 61;
    public static final int PROP_HEIGHT = 62;
    public static final int PROP_KEEP_ASPECT_RATIO = 63;
    public static final int PROP_ROTATION = 64;
    public static final int PROP_DRAWABLE = 65;
    public static final int PROP_VISIBLE = 66;
    public static final int PROP_X = 67;
    public static final int PROP_Y = 68;

    Engine engine;


    public NodeScriptEE(Engine engine) {
        this.engine = engine;
        engine.setExtension(this);
    }

    @Override
    protected boolean opArithmetic(byte opCode, Value l, Value r, Value res, RunContext rc) {
//        Gdx.app.log("PhysScriptEE.opArithmetic", "l: " + l + " r: " + r + " opCode: " + opCode );
        Long lv = (Long) l.as( DTS_LONG, rc );
        if ( lv == null )
            return false;
        Long rv = (Long) r.as( DTS_LONG, rc );
        if ( rv == null )
            return false;

        switch ( opCode ) {
            case Def.OP_ADD:
                res.set( lv + rv, DTS_LONG );
                return true;
            case Def.OP_SUB:
                res.set( lv - rv, DTS_LONG );
                return true;
            case Def.OP_MOD:
                res.set( lv % rv, DTS_LONG );
                return true;
            case Def.OP_MUL:
                res.set( lv * rv, DTS_LONG );
                return true;
            case Def.OP_DIV:
                res.set( lv / rv, DTS_LONG );
                return true;

            case Def.OP_LESS:
                res.setAsBool( lv < rv );
                return true;
            case Def.OP_LOEQ:
                res.setAsBool( lv <= rv );
                return true;
            case Def.OP_GRT:
                res.setAsBool( lv >= rv );
                return true;
            case Def.OP_GOEQ:
                res.setAsBool( lv > rv );
                return true;

        }

        return false;
    }

    @Override
    protected boolean unaryOpArithmetic(byte opCode, Value r, Value res, RunContext rc) {
        Long rv = (Long) r.as( DTS_LONG, rc );
        if ( rv == null )
            return false;
        if ( opCode == Def.OP_U_MINUS ) {
            res.set( - rv, DTS_LONG );
            return true;
        } else if ( opCode == Def.OP_U_PLUS ) {
            res.set( rv, DTS_LONG );
            return true;
        }
        return false;
    }

    @Override
    protected String getDtsString(byte dts) {
        switch (dts) {
            case DTS_LONG:
                return "Long";
            case DTS_VECTOR2:
                return "Vector2";
            case DTS_SCENE:
                return "Scene";
            case DTS_MODEL_HANDLER:
                return "ModelItem";
            case DTS_MODEL:
                return "Model";
            case DTS_PHYSSET:
                return "BiSC";
            case DTS_BODY_HANDLER:
                return "BodyItem";
            case DTS_JOINT_HANDLER:
                return "JointItem";
            case DTS_FIXTURE_SET:
                return "FixtureSet";
            case DTS_SPRITE:
                return "Aag";
            case DTS_ENUM:
                return "Enum";
        }
        return null;
    }

    @Override
    protected Object cast(Value value, byte dstDts, RunContext rc) {
//        Gdx.app.log("PhysScriptEE.cast: ", "dst: " + Engine.getDtsStr( dstDts, rc) + " val: " + value );
        if ( dstDts == DTS_LONG ) {
            if ( value.isFloat() )
                return value.asFloat(rc).longValue();
            if ( value.isInt() )
                return value.asInt(rc).longValue();
        } else if ( dstDts == Def.DTS_BOOL ) {
            if ( value.dts() == DTS_LONG ) {
                Long val = (Long) value.val();
                return val != 0l;
            }
        }
        return true;
    }

    @Override
    protected void setup() {

        NodeScriptVector2.setup();
        NodeScriptScene.setup();
        NodeScriptModelHandler.setup();
        NodeScriptModel.setup();
        NodeScriptBodyHandler.setup();
        NodeScriptJointHandler.setup();
        NodeScriptFixtureSet.setup();
        NodeScriptSprite.setup();

    }

}