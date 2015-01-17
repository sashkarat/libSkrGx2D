package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.GearJoint;
import com.badlogic.gdx.physics.box2d.joints.GearJointDef;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 19.07.14.
 */
public class GearJointHandler extends JointHandler {
    GearJoint joint;

    @Override
    protected void onJointSet() {
        joint = (GearJoint) getJoint();
    }

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {

        GearJointDef jd = new GearJointDef();


        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler( jhDef.getBodyAId() );

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();

        Joint jA, jB;

        JointHandler jh = (JointHandler) firstNode().getNode( jhDef.getJointAId() );
        if ( jh == null ) {
            Gdx.app.error("GearJointHandler.createJointDef", "Error: JointItem A not defined");
            return null;
        }
        jA = jh.getJoint();

        if ( jA.getType() != JointDef.JointType.RevoluteJoint ) {
            Gdx.app.error("GearJointHandler.createJointDef", "Error: Joint A Type mismatch ");
            return null;
        }
        jh = (JointHandler) firstNode().getNode( jhDef.getJointBId() );
        if ( jh == null ) {
            Gdx.app.error("GearJointHandler.createJointDef", "Error: JointItem B not defined");
            return null;
        }
        jB = jh.getJoint();

        if ( !( jB.getType() == JointDef.JointType.RevoluteJoint ||
                jB.getType() == JointDef.JointType.PrismaticJoint )) {
            Gdx.app.error("GearJointHandler.createJointDef", "Error: Joint B Type mismatch ");
            return null;
        }
        jd.bodyA = bodyA;
        jd.bodyB = bodyB;
        jd.joint1 = jA;
        jd.joint2 = jB;
        jd.ratio = jhDef.getRatio();
        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setRatio( joint.getRatio() );


        if ( joint.getJoint1().getUserData() != null  )
            jhDef.setJointAId( ((Node) joint.getJoint1().getUserData()).getId() );
        else
            jhDef.setJointAId( -1 );

        if ( joint.getJoint2().getUserData() != null )
            jhDef.setJointBId( ((Node) joint.getJoint2().getUserData()).getId() );
        else
            jhDef.setJointBId(-1);
    }
    @Override
    public float getRatio() {
        return joint.getRatio();
    }

    @Override
    public void setRatio(float ratio) {
        joint.setRatio( ratio );
    }

    @Override
    public long getJointAId() {
        if ( joint.getJoint1().getUserData() != null  )
             return ((Node) joint.getJoint1().getUserData()).getId();
        return -1;
    }

    @Override
    public long getJointBId() {
        if ( joint.getJoint1().getUserData() != null  )
            return ((Node) joint.getJoint2().getUserData()).getId();
        return -1;
    }

    @Override
    public JointHandler getJointA() {
        return (JointHandler) joint.getJoint1().getUserData();
    }

    @Override
    public JointHandler getJointB() {
        return (JointHandler) joint.getJoint2().getUserData();
    }
}
