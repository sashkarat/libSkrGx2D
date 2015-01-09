package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 13.07.14.
 */
public class PrismaticJointItem extends JointHandler {

    PrismaticJoint joint;
    Vector2 axis = new Vector2();


    @Override
    protected void onJointSet() {
        joint = (PrismaticJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {
        PrismaticJointDef jd = new PrismaticJointDef();

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
        Vector2 axis = jhDef.getAxis();
        jd.initialize( bodyA, bodyB, anchor, axis);

        jd.enableLimit = jhDef.isEnableLimit();
        jd.enableMotor = jhDef.isEnableMotor();
        jd.maxMotorForce = jhDef.getMaxMotorForce();
        jd.motorSpeed = jhDef.getMotorSpeed();
        jd.upperTranslation = jhDef.getUpperTranslation();
        jd.lowerTranslation = jhDef.getLowerTranslation();

        this.axis.set( jhDef.getAxis() );

        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setAnchorA( joint.getAnchorA() );
        jhDef.setAxis( this.axis );
        jhDef.setEnableMotor( joint.isMotorEnabled() );
        jhDef.setEnableLimit( joint.isLimitEnabled() );
        jhDef.setMotorSpeed( joint.getMotorSpeed() );
        jhDef.setMaxMotorForce( joint.getMaxMotorForce() );
        jhDef.setUpperTranslation( joint.getUpperLimit() );
        jhDef.setLowerTranslation( joint.getLowerLimit() );
    }

    @Override
    public Vector2 getAxis() {
        return axis;
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
    public float getMaxMotorForce() {
        return joint.getMaxMotorForce();
    }

    @Override
    public void setMaxMotorForce(float maxMotorForce) {
        joint.setMaxMotorForce( maxMotorForce );
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
    public float getUpperTranslation() {
        return joint.getUpperLimit();
    }

    @Override
    public void setUpperTranslation(float upperTranslation) {
        if ( upperTranslation <= getLowerTranslation() )
            return;
        joint.setLimits( joint.getLowerLimit(), upperTranslation);
    }

    @Override
    public float getLowerTranslation() {
        return joint.getLowerLimit();
    }

    @Override
    public void setLowerTranslation(float lowerTranslation) {
        if ( lowerTranslation > getUpperTranslation() )
            return;
        joint.setLimits( lowerTranslation, joint.getUpperLimit() );
    }
}
