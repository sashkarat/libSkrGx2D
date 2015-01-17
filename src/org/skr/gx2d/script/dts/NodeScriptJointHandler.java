package org.skr.gx2d.script.dts;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.JointDef;
import org.skr.SkrScript.*;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.script.NodeScriptEE;

/**
 * Created by rat on 16.12.14.
 */
public class NodeScriptJointHandler {

    public static final int E_DISTANCE_JOINT    = NodeScriptEE.genAddr();
    public static final int E_FRICTION_JOINT    = NodeScriptEE.genAddr();
    public static final int E_GEAR_JOINT        = NodeScriptEE.genAddr();
    public static final int E_MOTOR_JOINT       = NodeScriptEE.genAddr();
    public static final int E_PRISMATIC_JOINT   = NodeScriptEE.genAddr();
    public static final int E_PULLEY_JOINT      = NodeScriptEE.genAddr();
    public static final int E_REVOLUTE_JOINT    = NodeScriptEE.genAddr();
    public static final int E_ROPE_JOINT        = NodeScriptEE.genAddr();
    public static final int E_WELD_JOINT        = NodeScriptEE.genAddr();
    public static final int E_WHEEL_JOINT       = NodeScriptEE.genAddr();



    public static void setup() {
        setupProperties();
        setupFunctions();
    }

    public static void setupProperties() {

        NodeScriptEE.setProperty(NodeScriptEE.PROP_TYPE, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set(ji.getJoint().getType(), NodeScriptEE.DTS_ENUM );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_TYPE, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_BI_A, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set( ji.getBodyHandlerA(), NodeScriptEE.DTS_BODY_HANDLER );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_BI_A, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_BI_B, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set( ji.getBodyHandlerB(), NodeScriptEE.DTS_BODY_HANDLER );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_BI_B, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_COLLIDE_CONNECTED, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsBool( ji.isCollideConnected() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_COLLIDE_CONNECTED, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_LENGTH, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getLength() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setLength( value.asFloat(rc) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ANCHOR_A, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set(ji.getAnchorA(), NodeScriptEE.DTS_VECTOR2 );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_ANCHOR_A, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ANCHOR_B, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set(ji.getAnchorB(), NodeScriptEE.DTS_VECTOR2);
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_ANCHOR_B, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_FREQUENCY, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getFrequencyHz() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setFrequencyHz( value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_DAMPING_RATIO, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getDampingRatio() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setDampingRatio(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_RATIO, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getRatio() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setRatio(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_MAX_LENGTH, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getMaxLength() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setMaxLength(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_MAX_FORCE, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getMaxForce() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setMaxForce( value.asFloat(rc) );
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_MAX_MOTOR_FORCE, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getMaxMotorForce() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setMaxForce( value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_MOTOR_SPEED, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getMotorSpeed() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setMotorSpeed( value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_MAX_MOTOR_TORQUE, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getMaxMotorTorque() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setMaxMotorTorque(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_MAX_TORQUE, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getMaxMotorTorque() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setMaxTorque(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ANGULAR_OFFSET, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat(ji.getAngularOffset());
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setAngularOffset(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_LOWER_ANGLE, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getLowerAngle() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setLowerAngle(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_UPPER_ANGLE, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getUpperAngle() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setUpperAngle(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_CORRECTION_FACTOR, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getCorrectionFactor() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setCorrectionFactor(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_REFERENCE_ANGLE, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getReferenceAngle() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_REFERENCE_ANGLE, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_LOWER_TRANSLATION, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getLowerTranslation() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setLowerTranslation(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_UPPER_TRANSLATION, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsFloat( ji.getUpperTranslation() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setUpperTranslation(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ENABLE_LIMIT, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsBool( ji.isEnableLimit() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setEnableLimit(value.asBool(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_ENABLE_MOTOR, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.setAsBool( ji.isEnableMotor() );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setEnableMotor(value.asBool(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_JI_A, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set( ji.getJointA(), NodeScriptEE.DTS_JOINT_HANDLER );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_JI_A, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_JI_B, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set( ji.getJointB(), NodeScriptEE.DTS_JOINT_HANDLER );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_JI_B, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_LINEAR_OFFSET, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set(ji.getLinearOffset(), NodeScriptEE.DTS_VECTOR2);
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                ji.setLinearOffset((Vector2) value.val());
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_AXIS, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set( ji.getAxis(), NodeScriptEE.DTS_VECTOR2 );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_AXIS, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_GROUND_A, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set( ji.getGroundAnchorA(), NodeScriptEE.DTS_VECTOR2 );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_GROUND_A, rc);
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_GROUND_B, NodeScriptEE.DTS_JOINT_HANDLER, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                JointHandler ji = (JointHandler) obj.val();
                res.set( ji.getGroundAnchorB(), NodeScriptEE.DTS_VECTOR2 );
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                return Engine.pePropertyIsReadOnly(NodeScriptEE.PROP_GROUND_B, rc);
            }
        });

    }

    public static void setupFunctions() {
        NodeScriptEE.setBuildInFunction(E_DISTANCE_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.DistanceJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_FRICTION_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.FrictionJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_GEAR_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.GearJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_MOTOR_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.MotorJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_PRISMATIC_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.PrismaticJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_PULLEY_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.PulleyJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_REVOLUTE_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.RevoluteJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_ROPE_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.RopeJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_WELD_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.WeldJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

        NodeScriptEE.setBuildInFunction(E_WHEEL_JOINT, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                res.set(JointDef.JointType.WheelJoint, NodeScriptEE.DTS_ENUM);
                return true;
            }
        });

    }

}
