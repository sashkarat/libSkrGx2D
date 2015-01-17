package org.skr.gx2d.script.dts;

import com.badlogic.gdx.Gdx;
import org.skr.SkrScript.Engine;
import org.skr.SkrScript.PropertyPool;
import org.skr.SkrScript.RunContext;
import org.skr.SkrScript.Value;
import org.skr.gx2d.physnodes.FixtureSet;
import org.skr.gx2d.script.NodeScriptEE;

/**
 * Created by rat on 16.12.14.
 */
public class NodeScriptFixtureSet {

    public static void setup() {
        setupProperties();
        setupFunctions();
    }

    public static void setupProperties() {

        NodeScriptEE.setProperty(NodeScriptEE.PROP_FRICTION, NodeScriptEE.DTS_FIXTURE_SET, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                FixtureSet fs = (FixtureSet) obj.val();
                res.setAsFloat(fs.getFriction());
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                FixtureSet fs = (FixtureSet) obj.val();
                fs.setFriction(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_RESTITUTION, NodeScriptEE.DTS_FIXTURE_SET, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                FixtureSet fs = (FixtureSet) obj.val();
                res.setAsFloat(fs.getRestitution());
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                FixtureSet fs = (FixtureSet) obj.val();
                if ( !value.isNumber() ) {
                    return Engine.peSetPropInvalidType(NodeScriptEE.PROP_RESTITUTION, rc);
                }
                fs.setRestitution(value.asFloat(rc));
                return true;
            }
        });

        NodeScriptEE.setProperty(NodeScriptEE.PROP_DENSITY, NodeScriptEE.DTS_FIXTURE_SET, new PropertyPool.Adapter() {
            @Override
            public boolean get(Value obj, Value res, RunContext rc) {
                FixtureSet fs = (FixtureSet) obj.val();
                res.setAsFloat(fs.getDensity());
                return true;
            }

            @Override
            public boolean set(Value obj, Value value, RunContext rc) {
                FixtureSet fs = (FixtureSet) obj.val();
                fs.setDensity(value.asFloat(rc));
                return true;
            }
        });
    }

    public static void setupFunctions() {

    }

}
