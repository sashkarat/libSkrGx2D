package org.skr.gx2d.node;

/**
 * Created by rat on 08.01.15.
 */
public interface NodeWorkFlow {

    /*
    * @return next node in chain
    * */
    public Node nextNode();

    /*
    * @return prev. node in chain.
    * */
    public Node prevNode();

    /*
    * Set node after this node as the next node. New node will be the last in chain.
    * @param node: the new node
    * @return this node
    * */
    public Node setNextNode(Node node);

    /*
    * Set node before this node as the prev node. New node will be the first in chain.
    * @param node: the new node
    * @return this node
    * */
    public Node setPrevNode(Node node);

    /*
    * @return the first node in chain.
    * */
    public Node firstNode();

    /*
    * @return the last node in chain
    * */
    public Node lastNode();

    /*
    * append new node to the last node in chain.
    * @param node: the new node
    * @return this node
    * */
    public Node appendNode(Node node);

    /*
    * insert new node into the chain after this node. It does not break the chain.
    * @param node: the new node
    * @return this node
    * */
    public Node insertAfter(Node node);

    /*
    * insert new node into the chain before this node. It does not break the chain.
    * @param node: the new node
    * @return this node
    * */
    public Node insertBefore(Node node);

    /*
    * Remove node from its chain.
    * @return this node
    * */
    public Node removeFromChain();

    /*
    * Set node as the top node.
    * @param node: new top node
    * @return this node
    * */
    public Node setTopNode(Node node);

    /*
    * @return topNode node
    * */
    public Node topNode();

    /*
    * @return true if node is in chain
    * */
    public boolean isNodeInChain( Node node );

    public void constructGraphics();
    public void constructPhysics();
    public void destroyGraphics();
    public void destroyPhysics();

    public void preExport();
    public void postExport();

}
