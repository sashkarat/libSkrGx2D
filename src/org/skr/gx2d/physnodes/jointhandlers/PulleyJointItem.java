package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;
import com.badlogic.gdx.physics.box2d.joints.PulleyJointDef;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 13.07.14.
 */
public class PulleyJointItem extends JointHandler {

    PulleyJoint joint;

    @Override
    protected void onJointSet() {
        joint = (PulleyJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {

        PulleyJointDef jd = new PulleyJointDef();

        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler( jhDef.getBodyAId() );

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();

        jd.initialize(bodyA, bodyB, jhDef.getGroundAnchorA(), jhDef.getGroundAnchorB(),
                jhDef.getAnchorA(), jhDef.getAnchorB(), jhDef.getRatio() );

        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setGroundAnchorA( joint.getGroundAnchorA() );
        jhDef.setGroundAnchorB( joint.getGroundAnchorB() );
        jhDef.setRatio( joint.getRatio() );
    }


    @Override
    public float getRatio() {
        return joint.getRatio();
    }

    @Override
    public Vector2 getGroundAnchorA() {
        return joint.getGroundAnchorA();
    }

    @Override
    public Vector2 getGroundAnchorB() {
        return joint.getGroundAnchorB();
    }
}
