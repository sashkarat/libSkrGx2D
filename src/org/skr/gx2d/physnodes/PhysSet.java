package org.skr.gx2d.physnodes;

import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.annotation.NodeField;

/**
 * Created by rat on 06.01.15.
 */
public class PhysSet extends Node {


    @NodeField
    BodyHandler bodyHandler;
    @NodeField
    JointHandler jointHandler;


    public BodyHandler getBodyHandler() {
        return bodyHandler;
    }

    public BodyHandler setBodyHandler(BodyHandler bodyHandler) {
        this.bodyHandler = bodyHandler;
        this.bodyHandler.setTopNode( this );
        return this.bodyHandler;
    }

    public JointHandler getJointHandler() {
        return jointHandler;
    }

    public JointHandler setJointHandler(JointHandler jointHandler) {
        this.jointHandler = jointHandler;
        this.jointHandler.setTopNode( this );
        return this.jointHandler;
    }

    public BodyHandler addBodyHandler( BodyHandler bh ) {
        if ( this.bodyHandler == null )
            return setBodyHandler( bh );
        return (BodyHandler) bh.appendNode( bh );
    }

    public JointHandler addJointHandler( JointHandler jh ) {
        if ( this.jointHandler == null )
            return setJointHandler( jh );
        return (JointHandler) jointHandler.appendNode( jh );
    }

    @Override
    protected boolean activate(boolean state) {
        return false;
    }

    @Override
    public Type getType() {
        return Type.PhysSet;
    }

    @Override
    public boolean isType(Type type) {
        return type == Type.PhysSet;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void constructGraphics() {

    }

    @Override
    public void constructPhysics() {

    }
}
