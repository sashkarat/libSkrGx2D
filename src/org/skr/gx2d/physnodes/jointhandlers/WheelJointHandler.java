package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.WheelJoint;
import com.badlogic.gdx.physics.box2d.joints.WheelJointDef;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 19.07.14.
 */
public class WheelJointHandler extends JointHandler {

    WheelJoint joint;

    @Override
    protected void onJointSet() {
        joint = (WheelJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {

        WheelJointDef jd = new WheelJointDef();

        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler( jhDef.getBodyAId() );

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();


        jd.initialize(bodyA, bodyB, jhDef.getAnchorA(), jhDef.getAxis());
        jd.dampingRatio = jhDef.getDampingRatio();
        jd.enableMotor = jhDef.isEnableMotor();
        jd.frequencyHz = jhDef.getFrequencyHz();
        jd.maxMotorTorque = jhDef.getMaxMotorTorque();
        jd.motorSpeed = jhDef.getMotorSpeed();

        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {

        Body b = joint.getBodyA();
        jhDef.setAxis(b.getWorldVector(joint.getLocalAxisA()));
        jhDef.setAnchorA(joint.getAnchorA());
        jhDef.setDampingRatio(joint.getSpringDampingRatio());
        jhDef.setEnableMotor(joint.isMotorEnabled());
        jhDef.setFrequencyHz(joint.getSpringFrequencyHz());
        jhDef.setMaxMotorTorque(joint.getMaxMotorTorque());
        jhDef.setMotorSpeed(joint.getMotorSpeed());
    }

    @Override
    public Vector2 getAxis() {
        Body b = joint.getBodyA();
        return b.getWorldVector(joint.getLocalAxisA());
    }

    @Override
    public void setDampingRatio(float dampingRatio) {
        joint.setSpringDampingRatio( dampingRatio );
    }

    @Override
    public float getDampingRatio() {
        return joint.getSpringDampingRatio();
    }

    @Override
    public void setMotorSpeed(float motorSpeed) {
        joint.setMotorSpeed( motorSpeed );
    }

    @Override
    public float getMotorSpeed() {
        return joint.getMotorSpeed();
    }

    @Override
    public void setEnableMotor(boolean enableMotor) {
        joint.enableMotor( enableMotor );
    }

    @Override
    public boolean isEnableMotor() {
        return joint.isMotorEnabled();
    }

    @Override
    public void setFrequencyHz(float frequencyHz) {
        joint.setSpringFrequencyHz( frequencyHz );
    }

    @Override
    public float getFrequencyHz() {
        return joint.getSpringFrequencyHz();
    }

    @Override
    public float getMaxMotorTorque() {
        return joint.getMaxMotorTorque();
    }

    @Override
    public void setMaxMotorTorque(float maxMotorTorque) {
        joint.setMaxMotorTorque( maxMotorTorque );
    }
}
