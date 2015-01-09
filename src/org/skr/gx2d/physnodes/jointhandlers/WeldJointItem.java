package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 20.07.14.
 */
public class WeldJointItem extends JointHandler {

    WeldJoint joint;

    @Override
    protected void onJointSet() {
        joint = (WeldJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {

        WeldJointDef jd = new WeldJointDef();

        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler( jhDef.getBodyAId() );

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();


        jd.initialize(bodyA, bodyB, jhDef.getAnchorA());

        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setAnchorA(joint.getAnchorA());
    }


    @Override
    public float getFrequencyHz() {
        return joint.getFrequency();
    }

    @Override
    public void setFrequencyHz(float frequencyHz) {
        joint.setFrequency( frequencyHz );
    }

    @Override
    public float getDampingRatio() {
        return joint.getDampingRatio();
    }

    @Override
    public void setDampingRatio(float dampingRatio) {
        joint.setDampingRatio( dampingRatio );
    }
}
