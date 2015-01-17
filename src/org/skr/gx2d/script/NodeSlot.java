package org.skr.gx2d.script;

import org.skr.SkrScript.Slot;
import org.skr.gx2d.node.Node;

/**
 * Created by rat on 15.11.14.
 */
public class NodeSlot extends Slot {

    Node node;
    byte nodeDts;

    public NodeSlot(Node node, byte nodeDts) {
        this.node = node;
        this.nodeDts = nodeDts;
    }

    public NodeScript getPhysScript() {
        return (NodeScript) getScript();
    }

    public Object getNode() {
        return node;
    }

    public byte getNodeDts() {
        return nodeDts;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if ( getScript() != null )
            sb.append( getScript().toString() );
        else
            sb.append("null");
        sb.append("@");
        sb.append(node.toString());
        return sb.toString();
    }
}
