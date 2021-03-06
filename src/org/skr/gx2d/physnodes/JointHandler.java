package org.skr.gx2d.physnodes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.World;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.node.SceneItem;
import org.skr.gx2d.node.annotation.Nfa;
import org.skr.gx2d.node.annotation.NodeDataAccessorAnnotation;
import org.skr.gx2d.node.annotation.NodeFactoryAnnotation;
import org.skr.gx2d.physnodes.physdef.JointDefinition;

/**
 * Created by rat on 06.01.15.
 */

@NodeFactoryAnnotation(
        factory = JointHandlerFactory.class,
        createMethodName = "create",
        parameter = @NodeDataAccessorAnnotation(
                    read = "getType",
                    type = JointDef.JointType.class
        )
)
public abstract class JointHandler extends SceneItem {

    @Nfa
    JointDefinition jhDef = null;

    Joint joint;

    protected JointHandler() {

    }

    public JointHandler getJointHandler( long id ) {
        return (JointHandler) getNode( id );
    }

    public JointHandler getJointHandler( Joint joint ) {
        return ((JointHandler)firstNode()).getJointHandlerByJoint( joint );
    }

    protected JointHandler getJointHandlerByJoint( Joint joint ) {
        if ( this.joint == joint)
            return this;
        if ( nextNode() == null )
            return null;
        return ((JointHandler) nextNode()).getJointHandlerByJoint( joint );
    }

    public JointDef.JointType getJointType() {
        if ( joint != null )
            return joint.getType();
        if ( jhDef != null )
            return jhDef.getType();
        return JointDef.JointType.Unknown;
    }


    @Override
    protected boolean activate(boolean state) {
        return false;
    }

    @Override
    public Type getType() {
        return Type.JointHandler;
    }

    @Override
    public boolean isType(Type type) {
        return type == Type.JointHandler;
    }

    public final Joint getJoint() {
        return joint;
    }

    public final void setJoint(Joint joint) {
        this.joint = joint;
        onJointSet();
    }

    public final long getBodyAId() {
        BodyHandler bi = getBodyHandlerA();
        if ( bi == null )
            return -1;
        return bi.getId();
    }

    public final long getBodyBId() {
        BodyHandler bi = getBodyHandlerB();
        if ( bi == null )
            return -1;
        return bi.getId();
    }

    public BodyHandler getBodyHandlerA() {
        if ( joint == null )
            return null;
        Body body = joint.getBodyA();
        if ( body == null )
            return null;
        return (BodyHandler) body.getUserData();
    }

    public BodyHandler getBodyHandlerB() {
        if ( joint == null )
            return null;
        Body body = joint.getBodyB();
        if ( body == null )
            return null;
        return (BodyHandler) body.getUserData();
    }

    public final boolean isCollideConnected() {
        return joint.getCollideConnected();
    }

    public final Vector2 getAnchorA() {
        return joint.getAnchorA();
    }

    public final Vector2 getAnchorB() {
        return joint.getAnchorB();
    }

    public float getLength() {
        return 0;
    }

    public void setLength(float length) {
    }

    public float getMaxForce() {
        return 0;
    }

    public void setMaxForce(float maxForce) {
    }

    public float getMaxTorque() {
        return 0;
    }

    public void setMaxTorque(float maxTorque) {

    }

    public float getRatio() {
        return 0;
    }

    public void setRatio(float ratio) {

    }

    public long getJointAId() {
        return -1;
    }

    public long getJointBId() {
        return -1;
    }


    public JointHandler getJointHandlerA() {
        return null;
    }

    public JointHandler getJointHandlerB() {
        return null;
    }

    public Vector2 getLinearOffset() {
        return null;
    }

    public void setLinearOffset(Vector2 linearOffset) {

    }

    public float getAngularOffset() {
        return 0;
    }

    public void setAngularOffset(float angularOffset) {

    }

    public float getCorrectionFactor() {
        return 0;
    }

    public void setCorrectionFactor(float correctionFactor) {

    }

    public float getFrequencyHz() {
        return 0;
    }

    public void setFrequencyHz(float frequencyHz) {

    }

    public float getDampingRatio() {
        return 0;
    }

    public void setDampingRatio(float dampingRatio ) {

    }

    public Vector2 getAxis() {
        return null;
    }

    public float getReferenceAngle() {
        return 0;
    }

    public boolean isEnableLimit() {
        return false;
    }

    public void setEnableLimit(boolean enableLimit) {

    }

    public float getLowerTranslation() {
        return 0;
    }

    public void setLowerTranslation(float lowerTranslation) {

    }

    public float getUpperTranslation() {
        return 0;
    }

    public void setUpperTranslation(float upperTranslation) {

    }

    public boolean isEnableMotor() {
        return false;
    }

    public void setEnableMotor(boolean enableMotor) {

    }

    public float getMaxMotorForce() {
        return 0;
    }

    public void setMaxMotorForce(float maxMotorForce) {

    }

    public float getMotorSpeed() {
        return 0;
    }

    public void setMotorSpeed(float motorSpeed) {

    }

    public Vector2 getGroundAnchorA() {
        return null;
    }

    public Vector2 getGroundAnchorB() {
        return null;
    }

    public float getLowerAngle() {
        return 0;
    }

    public void setLowerAngle(float lowerAngle) {

    }

    public float getUpperAngle() {
        return 0;
    }

    public void setUpperAngle(float upperAngle) {

    }

    public float getMaxMotorTorque() {
        return 0;
    }

    public void setMaxMotorTorque(float maxMotorTorque) {

    }

    public float getMaxLength() {
        return 0;
    }

    public void setMaxLength(float maxLength) {

    }

    public JointHandler getJointA() {
        return null;
    }

    public JointHandler getJointB() {
        return null;
    }

    protected abstract void onJointSet();

    protected abstract JointDef createJointDef( JointDefinition jhDef );

    public abstract void updateJointDefinition( JointDefinition jhDef );

    public void updateJointDefinition() {

        if ( getJoint() == null )
            return;

        jhDef = new JointDefinition();

        jhDef.setAnchorA( getAnchorA() );
        jhDef.setAnchorB( getAnchorB() );
        jhDef.setCollideConnected( isCollideConnected() );
        jhDef.setType( joint.getType() );

        jhDef.setBodyAId( getBodyAId() );
        jhDef.setBodyBId( getBodyBId() );

        updateJointDefinition(jhDef);
    }

    public JointDefinition getJhDef() {
        return jhDef;
    }

    protected void createJoint( JointDefinition jhDef ) {
        JointDef jd = createJointDef( jhDef );

        if ( jd == null )
            return;

        jd.collideConnected = jhDef.isCollideConnected();

        Joint joint;
        joint = Env.get().world.box2dWorld().createJoint( jd );

        if ( joint == null )
            return;

        setJoint(joint);
        joint.setUserData( this );
    }

    public PhysSet getPhysSet() {
        return (PhysSet) topNode();
    }


    @Override
    protected void nodeAct(float delta) {

    }

    @Override
    protected void nodeDraw(Batch batch, float parentAlpha) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void preExport() {
        updateJointDefinition();
    }

    @Override
    public void postExport() {
        jhDef = null;
    }

    @Override
    public void constructGraphics() {

    }

    @Override
    public void constructPhysics() {
        if ( jhDef == null )
            return;
        createJoint( jhDef );
    }

    @Override
    public void destroyGraphics() {

    }

    @Override
    public void destroyPhysics() {
        if ( joint == null )
            return;
        if ( joint.getBodyA() == null )
            return;
        World w = joint.getBodyA().getWorld();
        w.destroyJoint( joint );
        joint = null;
    }
}
