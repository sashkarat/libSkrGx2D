package org.skr.gx2d.physnodes.physdef;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.MassData;

/**
 * Created by rat on 07.01.15.
 */
public class BodyDefinition extends PhysDefinition {

    BodyDef bodyDef = new BodyDef();
    MassData massData;

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

    public MassData getMassData() {
        return massData;
    }

    public void setMassData(MassData massData) {
        this.massData = massData;
    }

}
