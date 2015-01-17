package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 13.07.14.
 */
public class RevoluteJointHandler extends JointHandler {

    RevoluteJoint joint;


    @Override
    protected void onJointSet() {
        joint = (RevoluteJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {

        RevoluteJointDef jd = new RevoluteJointDef();

        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler( jhDef.getBodyAId() );

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();

        Vector2 anchor = jhDef.getAnchorA();

        jd.initialize( bodyA, bodyB, anchor );
        jd.enableLimit = jhDef.isEnableLimit();
        jd.enableMotor = jhDef.isEnableMotor();
        jd.lowerAngle = jhDef.getLowerAngle();
        jd.upperAngle = jhDef.getUpperAngle();
        jd.maxMotorTorque = jhDef.getMaxMotorTorque();
        jd.motorSpeed = jhDef.getMotorSpeed();
        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setEnableLimit(joint.isLimitEnabled());
        jhDef.setEnableMotor(joint.isMotorEnabled());
        jhDef.setLowerAngle(joint.getLowerLimit());
        jhDef.setUpperAngle(joint.getUpperLimit());
        jhDef.setMaxMotorTorque(joint.getMaxMotorTorque());
        jhDef.setMotorSpeed(joint.getMotorSpeed());
    }

    @Override
    public boolean isEnableLimit() {
        return joint.isLimitEnabled();
    }

    @Override
    public void setEnableLimit(boolean enableLimit) {
        joint.enableLimit( enableLimit );
    }

    @Override
    public boolean isEnableMotor() {
        return joint.isMotorEnabled();
    }

    @Override
    public void setEnableMotor(boolean enableMotor) {
       joint.enableMotor( enableMotor );
    }

    @Override
    public float getReferenceAngle() {
        return joint.getReferenceAngle();
    }

    @Override
    public float getMaxMotorTorque() {
        return joint.getMaxMotorTorque();
    }

    @Override
    public void setMaxMotorTorque(float maxTorque) {
        joint.setMaxMotorTorque( maxTorque );
    }

    @Override
    public float getLowerAngle() {
        return joint.getLowerLimit();
    }

    @Override
    public void setLowerAngle(float lowerAngle) {
        if ( lowerAngle > joint.getUpperLimit() )
            return;
        joint.setLimits( lowerAngle, joint.getUpperLimit() );
    }

    @Override
    public float getUpperAngle() {
        return joint.getUpperLimit();
    }

    @Override
    public void setUpperAngle(float upperAngle) {
        if ( upperAngle <= joint.getLowerLimit() )
            return;
        joint.setLimits( joint.getLowerLimit(), upperAngle );
    }

    @Override
    public float getMotorSpeed() {
        return joint.getMotorSpeed();
    }

    @Override
    public void setMotorSpeed(float motorSpeed) {
        joint.setMotorSpeed( motorSpeed );
    }
}
