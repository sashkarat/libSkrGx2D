package org.skr.gx2d.physnodes.physdef;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.JointDef;
import org.skr.gx2d.physnodes.JointHandler;

/**
 * Created by rat on 07.01.15.
 */
public class JointDefinition {

    JointDef.JointType type = JointDef.JointType.Unknown;
    long bodyAId = -1;
    long bodyBId = -1;
    boolean collideConnected = false;
    float length = 0;
    Vector2 anchorA = new Vector2();
    Vector2 anchorB = new Vector2();
    float maxForce = 1;
    float maxTorque = 1;
    float ratio = 1;
    long jointAId = -1;
    long jointBId = -1;
    Vector2 linearOffset = new Vector2();
    float angularOffset = 0;
    float correctionFactor = 0.3f;
    Vector2 target = new Vector2();
    float frequencyHz = 5;
    float dampingRatio = 0.7f;
    Vector2 axis = new Vector2();
    float referenceAngle = 0;
    boolean enableLimit = false;
    float lowerTranslation = 0;
    float upperTranslation = 0;
    boolean enableMotor = false;
    float maxMotorForce = 0;
    float motorSpeed = 0;
    Vector2 groundAnchorA = new Vector2();
    Vector2 groundAnchorB = new Vector2();
    float lowerAngle = 0;
    float upperAngle = 0;
    float maxMotorTorque = 0;
    float maxLength = 0;

    public JointDef.JointType getType() {
        return type;
    }

    public void setType(JointDef.JointType type) {
        this.type = type;
    }

    public long getBodyAId() {
        return bodyAId;
    }

    public void setBodyAId(long bodyAId) {
        this.bodyAId = bodyAId;
    }

    public long getBodyBId() {
        return bodyBId;
    }

    public void setBodyBId(long bodyBId) {
        this.bodyBId = bodyBId;
    }

    public boolean isCollideConnected() {
        return collideConnected;
    }

    public void setCollideConnected(boolean collideConnected) {
        this.collideConnected = collideConnected;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public Vector2 getAnchorA() {
        return anchorA;
    }

    public void setAnchorA(Vector2 anchorA) {
        this.anchorA.set( anchorA );
    }

    public Vector2 getAnchorB() {
        return anchorB;
    }

    public void setAnchorB(Vector2 anchorB) {
        this.anchorB.set( anchorB );
    }

    public float getMaxForce() {
        return maxForce;
    }

    public void setMaxForce(float maxForce) {
        this.maxForce = maxForce;
    }

    public float getMaxTorque() {
        return maxTorque;
    }

    public void setMaxTorque(float maxTorque) {
        this.maxTorque = maxTorque;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public long getJointAId() {
        return jointAId;
    }

    public void setJointAId(long jointAId) {
        this.jointAId = jointAId;
    }

    public long getJointBId() {
        return jointBId;
    }

    public void setJointBId(long jointBId) {
        this.jointBId = jointBId;
    }

    public Vector2 getLinearOffset() {
        return linearOffset;
    }

    public void setLinearOffset(Vector2 linearOffset) {
        this.linearOffset.set( linearOffset );
    }

    public float getAngularOffset() {
        return angularOffset;
    }

    public void setAngularOffset(float angularOffset) {
        this.angularOffset = angularOffset;
    }

    public float getCorrectionFactor() {
        return correctionFactor;
    }

    public void setCorrectionFactor(float correctionFactor) {
        this.correctionFactor = correctionFactor;
    }

    public Vector2 getTarget() {
        return target;
    }

    public void setTarget(Vector2 target) {
        this.target.set( target );
    }

    public float getFrequencyHz() {
        return frequencyHz;
    }

    public void setFrequencyHz(float frequencyHz) {
        this.frequencyHz = frequencyHz;
    }

    public float getDampingRatio() {
        return dampingRatio;
    }

    public void setDampingRatio(float dampingRatio) {
        this.dampingRatio = dampingRatio;
    }

    public Vector2 getAxis() {
        return axis;
    }

    public void setAxis(Vector2 axis) {
        this.axis.set( axis );
    }

    public float getReferenceAngle() {
        return referenceAngle;
    }

    public void setReferenceAngle(float referenceAngle) {
        this.referenceAngle = referenceAngle;
    }

    public boolean isEnableLimit() {
        return enableLimit;
    }

    public void setEnableLimit(boolean enableLimit) {
        this.enableLimit = enableLimit;
    }

    public float getLowerTranslation() {
        return lowerTranslation;
    }

    public void setLowerTranslation(float lowerTranslation) {
        this.lowerTranslation = lowerTranslation;
    }

    public float getUpperTranslation() {
        return upperTranslation;
    }

    public void setUpperTranslation(float upperTranslation) {
        this.upperTranslation = upperTranslation;
    }

    public boolean isEnableMotor() {
        return enableMotor;
    }

    public void setEnableMotor(boolean enableMotor) {
        this.enableMotor = enableMotor;
    }

    public float getMaxMotorForce() {
        return maxMotorForce;
    }

    public void setMaxMotorForce(float maxMotorForce) {
        this.maxMotorForce = maxMotorForce;
    }

    public float getMotorSpeed() {
        return motorSpeed;
    }

    public void setMotorSpeed(float motorSpeed) {
        this.motorSpeed = motorSpeed;
    }

    public Vector2 getGroundAnchorA() {
        return groundAnchorA;
    }

    public void setGroundAnchorA(Vector2 groundAnchorA) {
        this.groundAnchorA.set( groundAnchorA );
    }

    public Vector2 getGroundAnchorB() {
        return groundAnchorB;
    }

    public void setGroundAnchorB(Vector2 groundAnchorB) {
        this.groundAnchorB.set( groundAnchorB );
    }

    public float getLowerAngle() {
        return lowerAngle;
    }

    public void setLowerAngle(float lowerAngle) {
        this.lowerAngle = lowerAngle;
    }

    public float getUpperAngle() {
        return upperAngle;
    }

    public void setUpperAngle(float upperAngle) {
        this.upperAngle = upperAngle;
    }

    public float getMaxMotorTorque() {
        return maxMotorTorque;
    }

    public void setMaxMotorTorque(float maxMotorTorque) {
        this.maxMotorTorque = maxMotorTorque;
    }

    public float getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(float maxLength) {
        this.maxLength = maxLength;
    }

}
