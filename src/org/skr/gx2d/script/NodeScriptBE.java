package org.skr.gx2d.script;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.JointDef;
import org.skr.SkrScript.Builder;
import org.skr.gx2d.script.dts.*;

/**
 * Created by rat on 13.12.14.
 */
public class NodeScriptBE {
    public static void install () {
        Builder.addExtendedDTS( NodeScriptEE.DTS_LONG, "Long" );
        Builder.addExtendedDTS( NodeScriptEE.DTS_VECTOR2, "Vector2" );

        Builder.addExtendedDTS( NodeScriptEE.DTS_SCENE,         "Scene" );
        Builder.addExtendedDTS( NodeScriptEE.DTS_MODEL_HANDLER, "ModelHandler" );
        Builder.addExtendedDTS( NodeScriptEE.DTS_MODEL,         "Model" );
        Builder.addExtendedDTS( NodeScriptEE.DTS_BODY_HANDLER,  "BodyHandler" );
        Builder.addExtendedDTS( NodeScriptEE.DTS_JOINT_HANDLER, "JointHandler" );
        Builder.addExtendedDTS( NodeScriptEE.DTS_FIXTURE_SET,   "FixtureSet" );
        Builder.addExtendedDTS( NodeScriptEE.DTS_SPRITE,        "Sprite" );
        Builder.addExtendedDTS( NodeScriptEE.DTS_ENUM,          "Enum" );

        Builder.addExtendedProperty(NodeScriptEE.PROP_ID, "_id");
        Builder.addExtendedProperty( NodeScriptEE.PROP_NAME, "_name" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_TYPE, "_type" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_POSITION, "_position" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ANGLE, "_angle" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_LINEAR_VEL, "_linearVel" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ANGULAR_VEL, "_angularVel" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_LINEAR_DAMPING, "_linearDamping" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ANGULAR_DAMPING, "_angularDamping" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ALLOW_SLEEP, "_allowSleep" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_AWAKE, "_awake" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_FIXED_ROT, "_fixedRot" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_BULLET, "_bullet" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ACTIVE, "_active" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_GRAVITY_SCALE, "_gravityScale" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_MASS, "_mass" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ABSORBED_IMPULSE, "_absorbedImpulse" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_FRICTION, "_friction" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_RESTITUTION, "_restitution" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_DENSITY, "_density" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_BI_A, "_biA" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_BI_B, "_biB" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_COLLIDE_CONNECTED, "_collideConnected" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_LENGTH, "_length" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ANCHOR_A, "_anchorA" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ANCHOR_B, "_anchorB" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_FREQUENCY, "_frequency" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_DAMPING_RATIO, "_dampingRatio" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_RATIO, "_ratio" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_MAX_LENGTH, "_maxLength" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_MAX_FORCE, "_maxForce" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_MAX_MOTOR_FORCE, "_motorForce" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_MOTOR_SPEED, "_motorSpeed" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_MAX_MOTOR_TORQUE, "_maxMotorTorque" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_MAX_TORQUE, "_maxTorque" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ANGULAR_OFFSET, "_angularOffset" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_LOWER_ANGLE, "_lowerAngle" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_UPPER_ANGLE, "_upperAngle" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_CORRECTION_FACTOR, "_correctionFactor" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_REFERENCE_ANGLE, "_referenceAngle" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_LOWER_TRANSLATION, "_lowerTranslation" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_UPPER_TRANSLATION, "_upperTranslation" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ENABLE_LIMIT, "_enableLimit" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ENABLE_MOTOR, "_enableMotor" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_JI_A, "_jiA" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_JI_B, "_jiB" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_LINEAR_OFFSET, "_linearOffset" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_AXIS, "_axis" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_GROUND_A, "_groundA" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_GROUND_B, "_groundB" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_TEXNAME, "_texName" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_FRAMEDURATION, "_frameDuration" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_PLAYMODE, "_playMode" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_WIDTH, "_width" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_HEIGHT, "_height" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_KEEP_ASPECT_RATIO, "_keepAspectRatio" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_ROTATION, "_rotation" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_DRAWABLE, "_drawable" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_VISIBLE, "_visible" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_X, "_x" );
        Builder.addExtendedProperty( NodeScriptEE.PROP_Y, "_y" );

        Builder.addExtendedBuildInFunction("vec2", (byte)2, true, NodeScriptVector2.F_CRT_VEC);
        Builder.addExtendedBuildInFunction("vec2Add", (byte)3, true, NodeScriptVector2.F_VEC_ADD);
        Builder.addExtendedBuildInFunction("vec2Add", (byte)2, true, NodeScriptVector2.F_VEC_ADD);
        Builder.addExtendedBuildInFunction("vec2Angle", (byte)1, true, NodeScriptVector2.F_VEC_ANGLE);
        Builder.addExtendedBuildInFunction("vec2Clamp", (byte)3, true, NodeScriptVector2.F_VEC_CLAMP);
        Builder.addExtendedBuildInFunction("vec2Cross", (byte)2, true, NodeScriptVector2.F_VEC_CROSS);
        Builder.addExtendedBuildInFunction("vec2Cross", (byte)3, true, NodeScriptVector2.F_VEC_CROSS);
        Builder.addExtendedBuildInFunction("vec2Dot", (byte)2, true, NodeScriptVector2.F_VEC_DOT);
        Builder.addExtendedBuildInFunction("vec2Dot", (byte)3, true, NodeScriptVector2.F_VEC_DOT);
        Builder.addExtendedBuildInFunction("vec2Dst", (byte)2, true, NodeScriptVector2.F_VEC_DST);
        Builder.addExtendedBuildInFunction("vec2Dst", (byte)3, true, NodeScriptVector2.F_VEC_DST);
        Builder.addExtendedBuildInFunction("vec2Dst2", (byte)2, true, NodeScriptVector2.F_VEC_DST2);
        Builder.addExtendedBuildInFunction("vec2Dst2", (byte)3, true, NodeScriptVector2.F_VEC_DST2);
        Builder.addExtendedBuildInFunction("vec2EpsilonEqual", (byte)3, true, NodeScriptVector2.F_VEC_EPS_EQ);
        Builder.addExtendedBuildInFunction("vec2EpsilonEqual", (byte)4, true, NodeScriptVector2.F_VEC_EPS_EQ);
        Builder.addExtendedBuildInFunction("vec2HasOppositeDir", (byte)2, true, NodeScriptVector2.F_VEC_HAS_OPP_DIR);
        Builder.addExtendedBuildInFunction("vec2HasSameDir", (byte)2, true, NodeScriptVector2.F_VEC_HAS_SAME_DIR);
        Builder.addExtendedBuildInFunction("vec2IsCollinear", (byte)2, true, NodeScriptVector2.F_VEC_IS_COLLINEAR);
        Builder.addExtendedBuildInFunction("vec2IsCollinear", (byte)3, true, NodeScriptVector2.F_VEC_IS_COLLINEAR);
        Builder.addExtendedBuildInFunction("vec2IsOnLine", (byte)2, true, NodeScriptVector2.F_VEC_IS_ONLINE);
        Builder.addExtendedBuildInFunction("vec2IsOnLine", (byte)3, true, NodeScriptVector2.F_VEC_IS_ONLINE);
        Builder.addExtendedBuildInFunction("vec2IsPerpendicular", (byte)2, true, NodeScriptVector2.F_VEC_IS_PERPENDIC);
        Builder.addExtendedBuildInFunction("vec2IsPerpendicular", (byte)3, true, NodeScriptVector2.F_VEC_IS_PERPENDIC);
        Builder.addExtendedBuildInFunction("vec2IsUnit", (byte)1, true, NodeScriptVector2.F_VEC_IS_UNIT);
        Builder.addExtendedBuildInFunction("vec2IsUnit", (byte)2, true, NodeScriptVector2.F_VEC_IS_UNIT);
        Builder.addExtendedBuildInFunction("vec2IsZero", (byte)1, true, NodeScriptVector2.F_VEC_IS_ZERO);
        Builder.addExtendedBuildInFunction("vec2IsZero", (byte)2, true, NodeScriptVector2.F_VEC_IS_ZERO);
        Builder.addExtendedBuildInFunction("vec2Len", (byte)1, true, NodeScriptVector2.F_VEC_LEN);
        Builder.addExtendedBuildInFunction("vec2Len2", (byte)1, true, NodeScriptVector2.F_VEC_LEN2);
        Builder.addExtendedBuildInFunction("vec2Lerp", (byte)3, true, NodeScriptVector2.F_VEC_LERP);
        Builder.addExtendedBuildInFunction("vec2Limit", (byte)2, true, NodeScriptVector2.F_VEC_LIMIT);
        Builder.addExtendedBuildInFunction("vec2Limit2", (byte)2, true, NodeScriptVector2.F_VEC_LIMIT2);
        Builder.addExtendedBuildInFunction("vec2Nor", (byte)1, true, NodeScriptVector2.F_VEC_NOR);
        Builder.addExtendedBuildInFunction("vec2Rotate", (byte)2, true, NodeScriptVector2.F_VEC_ROT);
        Builder.addExtendedBuildInFunction("vec2Scale", (byte)3, true, NodeScriptVector2.F_VEC_SCL);
        Builder.addExtendedBuildInFunction("vec2Scale", (byte)2, true, NodeScriptVector2.F_VEC_SCL);
        Builder.addExtendedBuildInFunction("vec2Set", (byte)2, true, NodeScriptVector2.F_VEC_SET);
        Builder.addExtendedBuildInFunction("vec2Set", (byte)3, true, NodeScriptVector2.F_VEC_SET);
        Builder.addExtendedBuildInFunction("vec2SetAngle", (byte)2, true, NodeScriptVector2.F_VEC_SET_ANG);
        Builder.addExtendedBuildInFunction("vec2SetLen", (byte)2, true, NodeScriptVector2.F_VEC_SET_LEN);
        Builder.addExtendedBuildInFunction("vec2SetLen2", (byte)2, true, NodeScriptVector2.F_VEC_SET_LEN2);
        Builder.addExtendedBuildInFunction("vec2SetZero", (byte)1, true, NodeScriptVector2.F_VEC_SET_ZERO);
        Builder.addExtendedBuildInFunction("vec2Sub", (byte)2, true, NodeScriptVector2.F_VEC_SUB);
        Builder.addExtendedBuildInFunction("vec2Sub", (byte)3, true, NodeScriptVector2.F_VEC_SUB);

        Builder.addExtendedBuildInFunction("mhGetModel", (byte)1, true, NodeScriptModelHandler.F_MI_GET_MODEL);


        Builder.addExtendedBuildInFunction(BodyDef.BodyType.DynamicBody.name(),
                (byte)0, true, NodeScriptBodyHandler.E_DYN_BODY);
        Builder.addExtendedBuildInFunction(BodyDef.BodyType.KinematicBody.name(),
                (byte)0, true, NodeScriptBodyHandler.E_KYN_BODY);
        Builder.addExtendedBuildInFunction(BodyDef.BodyType.StaticBody.name(),
                (byte)0, true, NodeScriptBodyHandler.E_STAT_BODY);

        Builder.addExtendedBuildInFunction(JointDef.JointType.DistanceJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_DISTANCE_JOINT );
        Builder.addExtendedBuildInFunction(JointDef.JointType.FrictionJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_FRICTION_JOINT);
        Builder.addExtendedBuildInFunction(JointDef.JointType.GearJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_GEAR_JOINT);
        Builder.addExtendedBuildInFunction(JointDef.JointType.MotorJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_MOTOR_JOINT);
        Builder.addExtendedBuildInFunction(JointDef.JointType.PrismaticJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_PRISMATIC_JOINT);
        Builder.addExtendedBuildInFunction(JointDef.JointType.PulleyJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_PULLEY_JOINT);
        Builder.addExtendedBuildInFunction(JointDef.JointType.RevoluteJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_REVOLUTE_JOINT);
        Builder.addExtendedBuildInFunction(JointDef.JointType.RopeJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_ROPE_JOINT);
        Builder.addExtendedBuildInFunction(JointDef.JointType.WeldJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_WELD_JOINT);
        Builder.addExtendedBuildInFunction(JointDef.JointType.WheelJoint.name(),
                (byte)0, true, NodeScriptJointHandler.E_WHEEL_JOINT);

        Builder.addExtendedBuildInFunction(Animation.PlayMode.NORMAL.name(),
                (byte)0, true, NodeScriptSprite.E_PM_NORMAL );
        Builder.addExtendedBuildInFunction(Animation.PlayMode.REVERSED.name(),
                (byte)0, true, NodeScriptSprite.E_PM_REVERSED);
        Builder.addExtendedBuildInFunction(Animation.PlayMode.LOOP.name(),
                (byte)0, true, NodeScriptSprite.E_PM_LOOP);
        Builder.addExtendedBuildInFunction(Animation.PlayMode.LOOP_REVERSED.name(),
                (byte)0, true, NodeScriptSprite.E_PM_LOOP_REVERSED);
        Builder.addExtendedBuildInFunction(Animation.PlayMode.LOOP_PINGPONG.name(),
                (byte)0, true, NodeScriptSprite.E_PM_LOOP_PINGPONG);
        Builder.addExtendedBuildInFunction(Animation.PlayMode.LOOP_RANDOM.name(),
                (byte)0, true, NodeScriptSprite.E_PM_LOOP_RANDOM);

    }




}
