package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 19.07.14.
 */
public class FrictionJointHandler extends JointHandler {
    FrictionJoint joint;

    @Override
    protected void onJointSet() {
        joint = (FrictionJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {
        FrictionJointDef jd = new FrictionJointDef();

        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler( jhDef.getBodyAId() );

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();


        jd.initialize(bodyA, bodyB, jhDef.getAnchorA() );

        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setAnchorA( joint.getAnchorA() );
        jhDef.setMaxForce( joint.getMaxForce() );
        jhDef.setMaxTorque( joint.getMaxTorque() );
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
}
