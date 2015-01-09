package org.skr.gx2d.node;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import org.skr.gx2d.node.annotation.NodeData;
import org.skr.gx2d.node.annotation.NodeDataAccessor;
import org.skr.gx2d.node.annotation.NodeDataMap;
import org.skr.gx2d.node.annotation.NodeField;

import java.util.HashMap;

/**
 * Created by rat on 04.01.15.
 */

@NodeDataMap(
        data = {
                @NodeData(name = "name",
                        accessor = @NodeDataAccessor(
                                read = "getName",
                                write = "setName",
                                type = String.class
                )),
                @NodeData(name = "visible",
                        accessor = @NodeDataAccessor(
                                read = "isVisible",
                                write = "setVisible",
                                type = boolean.class
                        ))
               }
)
public abstract class Node extends Group implements NodeWorkFlow, Disposable {

    public static enum Type {
        Scene(0),
        ModelHandler(1),
        Model(2),
        PhysSet(3),
        BodyHandler(4),
        JointHandler(5),
        FixtureSet(6),
        Sprite(7);

        public final Integer key;
        public static final int firstFreeKey = 8;

        Type(Integer key) {
            this.key = key;
        }
    }

    public static enum ChainDirection {
        UpDown,
        DownUp
    }

    public static enum Command {
        SetActive,
        SetVisible,
        Custom
    }

    private static long prevId = System.currentTimeMillis();

    public static long nextId() {
        long id = prevId;
        while ( id == prevId )
            id = System.currentTimeMillis();
        prevId = id;
        return id;
    }

    private Node topNode = null;
    private Node prevNode = null;
    private Node nextNode = null;

    @NodeField
    long id = -1;

    @NodeField
    boolean active = true;

    public Node() {
        this.id = nextId();
        setUserObject( this );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        if ( id < 0 )
            id = nextId();
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean state) {
        this.active = activate( state ) && state;
    }

    public Node getTopNode() {
        return topNode;
    }

    public void setTopNode(Node topNode) {
        this.topNode = topNode;
        if ( nextNode != null )
            nextNode.setTopNode( topNode );
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public Node getFirstNode() {
        Node prev = this;
        while ( true ) {
            if (prev.prevNode == null)
                return prev;
            prev = prev.prevNode;
        }
    }

    public Node getLastNode() {
        Node next = this;
        while ( true ) {
            if (next.nextNode == null)
                return next;
            next = next.nextNode;
        }
    }

    public void setPrevNode(Node node) {
        if ( this.prevNode != null )
            this.prevNode.setNextNode( node );
        if ( node != null )
            node.setNextNode( this );
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node node) {
        if ( node == null ) {
            if ( this.nextNode != null )
                this.nextNode.prevNode = null;
        } else {
            node.setTopNode(this.topNode);
            node.prevNode = this;
        }
        this.nextNode = node;
    }

    public Node appendNode( Node node ) {
        Node last = getLastNode();
        last.setNextNode(node);
        return node;
    }

    public void insertAfter( Node node ) {
        Node oldNext = nextNode;
        setNextNode( node );
        if ( oldNext != null && node != null )
            node.setNextNode( oldNext );
    }

    public void insertBefore( Node node ) {
        Node oldPrev = this.prevNode;
        setPrevNode( node );
        if ( oldPrev != null && node != null )
            oldPrev.setNextNode( node );
    }

    public Node getNode(long id ) {
        if ( this.id == id )
            return this;
        if ( nextNode == null )
            return null;
        return nextNode.getNode( id );
    }

    public Node getNode( Type type ) {
        if ( this.isType( type ) )
            return this;
        if ( nextNode == null )
            return null;
        return nextNode.getNode(type);
    }

    public void removeFromChain() {
        if ( prevNode != null )
            prevNode.setNextNode( nextNode );
        else if ( nextNode != null )
            nextNode.setPrevNode(null);

        prevNode = null;
        nextNode = null;
    }

    public void executeChainCmd(Command cmd, Object param, ChainDirection dir ) {
        if ( dir == ChainDirection.UpDown )
            executeUpDownChainCmd( cmd, param );
        else
            executeDownUpChainCmd( cmd, param );
    }

    protected void executeUpDownChainCmd( Command cmd, Object param ) {
        Node nxt = this.getFirstNode();
        while ( nxt != null ) {
            nxt.executeCmd( cmd, param );
            nxt = nxt.nextNode;
        }
    }

    protected void executeDownUpChainCmd( Command cmd, Object param ) {
        Node prev = this.getLastNode();
        while ( prev != null ) {
            prev.executeCmd( cmd, param );
            prev = prev.prevNode;
        }
    }

      public String chainToString() {
        StringBuilder sb = new StringBuilder();
        Node next = this;
        while( next != null ) {
            sb.append( next.toString() );
            next = next.nextNode;
            if ( next != null )
                sb.append(" -> ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Node(" + getType() + "/" + getId() + "/" + getName()+")";
    }

    public void executeCmd( Command cmd, Object param) {
        switch ( cmd ) {
            case SetActive:
                setActive((Boolean) param );
                return;
            case SetVisible:
                setVisible((Boolean) param);
                return;
            case Custom:
                break;
        }
    }

    protected abstract boolean activate( boolean state ) ;
    public abstract Type getType();
    public abstract boolean isType( Type type );

    //====== static =====



}
