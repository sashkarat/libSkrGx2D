package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.MotorJoint;
import com.badlogic.gdx.physics.box2d.joints.MotorJointDef;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 19.07.14.
 */
public class MotorJointHandler extends JointHandler {

    MotorJoint joint;


    @Override
    protected void onJointSet() {
        joint = (MotorJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {
        MotorJointDef jd = new MotorJointDef();

        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler( jhDef.getBodyAId() );

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();

        jd.initialize( bodyA, bodyB );

        jd.maxForce = jhDef.getMaxForce();
        jd.maxTorque = jhDef.getMaxTorque();
        jd.correctionFactor = jhDef.getCorrectionFactor();

        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setLinearOffset( joint.getLinearOffset() );
        jhDef.setAngularOffset( joint.getAngularOffset() );
        jhDef.setMaxForce( joint.getMaxForce() );
        jhDef.setMaxTorque( joint.getMaxTorque() );
        jhDef.setCorrectionFactor( joint.getCorrectionFactor() );
    }

    @Override
    public void setLinearOffset(Vector2 linearOffset) {
        joint.setLinearOffset( linearOffset );
    }

    @Override
    public Vector2 getLinearOffset() {
        return joint.getLinearOffset();
    }

    @Override
    public float getAngularOffset() {
        return joint.getAngularOffset();
    }

    @Override
    public void setAngularOffset(float angularOffset) {
        joint.setAngularOffset( angularOffset );
    }

    @Override
    public float getMaxForce() {
        return joint.getMaxForce();
    }

    @Override
    public void setMaxForce(float maxForce) {
        joint.setMaxForce( maxForce );
    }

    @Override
    public float getMaxTorque() {
        return joint.getMaxTorque();
    }

    @Override
    public void setMaxTorque(float maxTorque) {
        joint.setMaxTorque( maxTorque );
    }

    @Override
    public float getCorrectionFactor() {
        return joint.getCorrectionFactor();
    }

    @Override
    public void setCorrectionFactor(float correctionFactor) {
        joint.setCorrectionFactor( correctionFactor );
    }
}
