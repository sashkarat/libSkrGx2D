package org.skr.gx2d.physnodes.physdef;

import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;

/**
 * Created by rat on 07.01.15.
 */
public class FixtureSetDefinition extends PhysDefinition {
    Shape.Type shapeType = Shape.Type.Polygon;
    public float friction = 0.2f;
    public float restitution = 0;
    public float density = 0.1f;
    Array<ShapeDefinition> shapeDefArray = new Array<ShapeDefinition>();

    public Shape.Type getShapeType() {
        return shapeType;
    }

    public void setShapeType(Shape.Type shapeType) {
        this.shapeType = shapeType;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public float getRestitution() {
        return restitution;
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public Array<ShapeDefinition> getShapeDefArray() {
        return shapeDefArray;
    }

    public void setShapeDefArray(Array<ShapeDefinition> shapeDefArray) {
        this.shapeDefArray = shapeDefArray;
    }
}
