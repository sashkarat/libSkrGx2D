package org.skr.gx2d.node;

import java.util.Iterator;

/**
 * Created by rat on 09.01.15.
 */
public class NodeIterator <T extends NodeWorkFlow >implements Iterator<Node> {
    Node node;

    public NodeIterator(T node) {
        this.node = node.firstNode();
    }

    @Override
    public boolean hasNext() {
        return node != null ;
    }

    @Override
    public Node next() {
        Node ret = node;
        node = node.nextNode();
        return ret;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
