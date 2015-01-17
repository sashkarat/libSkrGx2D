package org.skr.gx2d.physnodes;

import com.badlogic.gdx.physics.box2d.JointDef;
import org.skr.gx2d.physnodes.jointhandlers.*;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 17.01.15.
 */
public class JointHandlerFactory {
    public static JointHandler create(JointDef.JointType type ) {
        JointHandler jh = null;
        switch ( type ) {
            case Unknown:
                return null;
            case RevoluteJoint:
                return new RevoluteJointHandler();
            case PrismaticJoint:
                return new PrismaticJointHandler();
            case DistanceJoint:
                return new DistanceJointHandler();
            case PulleyJoint:
                return new PulleyJointHandler();
            case MouseJoint:
                return null;
            case GearJoint:
                return new GearJointHandler();
            case WheelJoint:
                return new WheelJointHandler();
            case WeldJoint:
                return new WeldJointHandler();
            case FrictionJoint:
                return new FrictionJointHandler();
            case RopeJoint:
                return new RopeJointHandler();
            case MotorJoint:
                return new MotorJointHandler();
            default:
                return null;
        }
    }

    public JointHandler create( JointDefinition jhDef ) {
        JointHandler jh = create( jhDef.getType() );
        jh.jhDef = jhDef;
        return jh;
    }
}
