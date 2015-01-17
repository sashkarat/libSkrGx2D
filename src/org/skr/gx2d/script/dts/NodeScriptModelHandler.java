package org.skr.gx2d.script.dts;

import org.skr.SkrScript.*;
import org.skr.gx2d.scene.ModelHandler;
import org.skr.gx2d.script.NodeScriptEE;

/**
 * Created by rat on 16.12.14.
 */
public class NodeScriptModelHandler {

    public static final int F_MI_GET_MODEL = NodeScriptEE.genAddr();

    public static void setup() {
        setupProperties();
        setupFunctions();
    }

    public static void setupProperties() {

    }

    public static void setupFunctions() {

        NodeScriptEE.setBuildInFunction(F_MI_GET_MODEL, new FunctionPool.Adapter() {
            @Override
            public boolean act(ValuePool args, int numOfArgs, Value res, RunContext rc) {
                ModelHandler mi = (ModelHandler) args.get(0).val();
                res.set(mi.getTop(), NodeScriptEE.DTS_MODEL);
                return true;
            }
        });
    }
}
