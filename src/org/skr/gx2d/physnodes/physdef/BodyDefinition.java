package org.skr.gx2d.physnodes.physdef;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.MassData;

/**
 * Created by rat on 07.01.15.
 */
public class BodyDefinition {

    BodyDef bodyDef = new BodyDef();

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
    }

}
