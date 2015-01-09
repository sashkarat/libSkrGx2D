package org.skr.gx2d.physnodes.jointhandlers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 06.07.14.
 */
public class DistanceJointHandler extends JointHandler {

    private DistanceJoint joint = null;

    @Override
    protected JointDef createJointDef(JointDefinition jhDef) {
        DistanceJointDef jd = new DistanceJointDef();

        BodyHandler bh = getPhysSet().getBodyHandler();

        BodyHandler bha = bh.getBodyHandler(jhDef.getBodyAId());

        if ( bha == null )
            return null;
        Body bodyA = bha.getBody();

        bha = bh.getBodyHandler(jhDef.getBodyBId());

        if ( bha == null )
            return null;

        Body bodyB = bha.getBody();
        jd.initialize( bodyA, bodyB, jhDef.getAnchorA(), jhDef.getAnchorB() );

        jd.dampingRatio = jhDef.getDampingRatio();
        jd.frequencyHz = jhDef.getFrequencyHz();

        return jd;
    }

    @Override
    public void updateJointDefinition(JointDefinition jhDef) {
        jhDef.setDampingRatio(joint.getDampingRatio());
        jhDef.setFrequencyHz( joint.getFrequency() );
        jhDef.setLength( joint.getLength() );
    }

    @Override
    public float getLength() {
        return joint.getLength();
    }

    @Override
    public void setLength(float length) {
        joint.setLength( length );
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
    public void setDampingRatio(float dumpingRatio) {
        joint.setDampingRatio( dumpingRatio );
    }

    @Override
    protected void onJointSet() {
        joint = ( DistanceJoint ) getJoint();
    }
}
