package org.skr.gx2d.node;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Disposable;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.node.annotation.NodeDataAnnotation;
import org.skr.gx2d.node.annotation.NodeDataAccessorAnnotation;
import org.skr.gx2d.node.annotation.NodeDataMapAnnotation;
import org.skr.gx2d.node.annotation.Nfa;
import org.skr.gx2d.utils.Utils;

/**
 * Created by rat on 04.01.15.
 */

@NodeDataMapAnnotation(
        data = {
                @NodeDataAnnotation(name = "name",
                        accessor = @NodeDataAccessorAnnotation(
                                read = "getName",
                                write = "setName",
                                type = String.class
                )),
                @NodeDataAnnotation(name = "visible",
                        accessor = @NodeDataAccessorAnnotation(
                                read = "isVisible",
                                write = "setVisible",
                                type = boolean.class
                        ))
               }
)
public abstract class Node extends Group implements NodeWorkFlow, Disposable, Iterable <Node> {

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

    @Nfa
    long id = -1;

    @Nfa
    boolean active = true;

    @Nfa
    boolean drawable = true;

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

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

    @Override
    public Node topNode() {
        return topNode;
    }

    @Override
    public Node setTopNode(Node topNode) {
        this.topNode = topNode;
        if ( nextNode != null )
            nextNode.setTopNode( topNode );
        return this;
    }

    @Override
    public Node prevNode() {
        return prevNode;
    }

    @Override
    public Node firstNode() {
        Node prev = this;
        while ( true ) {
            if (prev.prevNode == null)
                return prev;
            prev = prev.prevNode;
        }
    }

    @Override
    public Node lastNode() {
        Node next = this;
        while ( true ) {
            if (next.nextNode == null)
                return next;
            next = next.nextNode;
        }
    }

    @Override
    public Node setPrevNode(Node node) {
        this.prevNode = node;
        if ( node != null )
            node.setNextNode( this );
        return this;
    }

    @Override
    public Node nextNode() {
        return nextNode;
    }

    @Override
    public Node setNextNode(Node node) {
        this.nextNode = node;
        if ( node != null ) {
            node.setTopNode(this.topNode);
            node.prevNode = this;
        }
        return this;
    }

    @Override
    public Node appendNode( Node node ) {
        Node last = lastNode();
        last.setNextNode(node);
        return this;
    }

    @Override
    public Node insertAfter( Node node ) {
        Node oldNext = nextNode;
        setNextNode( node );
        if ( oldNext != null && node != null )
            node.setNextNode( oldNext );
        return this;
    }

    @Override
    public Node insertBefore( Node node ) {
        Node oldPrev = this.prevNode;
        setPrevNode( node );
        if ( oldPrev != null && node != null )
            oldPrev.setNextNode( node );
        return this;
    }

    @Override
    public NodeIterator<Node> iterator(){
        return new NodeIterator<Node>( this );
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

    @Override
    public Node removeFromChain() {
        if ( prevNode != null )
            prevNode.setNextNode( nextNode );
        else if ( nextNode != null )
            nextNode.setPrevNode(null);

        prevNode = null;
        nextNode = null;
        return this;
    }

    @Override
    public boolean isNodeInChain(Node node) {
        Node nd = firstNode();
        while ( nd != null ) {
            if ( nd == node )
                return true;
            nd = nd.nextNode;
        }
        return false;
    }

    public void executeChainCmd(Command cmd, Object param, ChainDirection dir ) {
        if ( dir == ChainDirection.UpDown )
            executeUpDownChainCmd( cmd, param );
        else
            executeDownUpChainCmd( cmd, param );
    }

    protected void executeUpDownChainCmd( Command cmd, Object param ) {
        Node nxt = this.firstNode();
        while ( nxt != null ) {
            nxt.executeCmd( cmd, param );
            nxt = nxt.nextNode;
        }
    }

    protected void executeDownUpChainCmd( Command cmd, Object param ) {
        Node prev = this.lastNode();
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
        return "N[" + getType() + "/" + getName() + "/" + getId()+"]";
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

    @Override
    public final void act(float delta) {
        if ( ! active )
            return;

        nodeAct( delta );

        super.act(delta);

        nodePostAct( delta );
    }

    protected abstract void nodeAct( float delta );

    protected void nodePostAct(float delta ) {

    }

    @Override
    public final void draw(Batch batch, float parentAlpha) {
        if ( !  (isVisible() && active ) )
            return;
        super.draw(batch, parentAlpha);

        if ( ! drawable )
            return;
        nodeDraw( batch, parentAlpha );

        if ( Env.get().debugRender )
            nodeDebugDraw( batch, parentAlpha );
    }

    protected abstract void nodeDraw(Batch batch, float parentAlpha );

    protected void nodeDebugDraw( Batch batch, float parentAlpha ) {

    }

    @Override
    public void preExport() {

    }

    @Override
    public void postExport() {

    }

    protected abstract boolean activate( boolean state ) ;
    public abstract Type getType();
    public abstract boolean isType( Type type );


}
