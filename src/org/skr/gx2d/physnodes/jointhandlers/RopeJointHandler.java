package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 19.07.14.
 */
public class RopeJointHandler extends JointHandler {

    RopeJoint joint;

    @Override
    protected void onJointSet() {
        joint = (RopeJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {

        RopeJointDef jd = new RopeJointDef();
        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler( jhDef.getBodyAId() );

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();


        jd.bodyA = bodyA;
        jd.bodyB = bodyB;
        jd.localAnchorA.set( bodyA.getLocalPoint(jhDef.getAnchorA()) );
        jd.localAnchorB.set( bodyB.getLocalPoint(jhDef.getAnchorB()) );
        jd.maxLength = jhDef.getAnchorA().dst( jhDef.getAnchorB() );

        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setAnchorA(joint.getBodyA().getWorldPoint(joint.getLocalAnchorA()));
        jhDef.setAnchorB(joint.getBodyB().getWorldPoint(joint.getLocalAnchorB()));
        jhDef.setMaxLength(joint.getMaxLength());
    }

    @Override
    public float getMaxLength() {
        return joint.getMaxLength();
    }

    @Override
    public void setMaxLength(float maxLength) {
        joint.setMaxLength( maxLength );
    }
}
