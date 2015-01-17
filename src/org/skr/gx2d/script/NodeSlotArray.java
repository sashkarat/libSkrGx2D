package org.skr.gx2d.script;

import com.badlogic.gdx.utils.Array;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.node.Node;

/**
 * Created by rat on 25.12.14.
 */
public class NodeSlotArray {

    Node node;
    Byte dts;

    public NodeSlotArray(Node node, Byte dts) {
        this.node = node;
        this.dts = dts;
    }

    Array<NodeSlot> slots = new Array<NodeSlot>();

    public Array<NodeSlot> getSlots() {
        return slots;
    }

    public void run() {
        for ( int i = 0; i < slots.size; i++ ) {
            Env.scriptEngine.run( slots.get(i) );
        }
    }
}
