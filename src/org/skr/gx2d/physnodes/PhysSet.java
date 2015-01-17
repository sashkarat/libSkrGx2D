package org.skr.gx2d.physnodes;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.SceneItem;
import org.skr.gx2d.node.annotation.NodeAddMethodAnnotation;
import org.skr.gx2d.node.annotation.Nfa;

/**
 * Created by rat on 06.01.15.
 */
public class PhysSet extends SceneItem {

    @Nfa
    @NodeAddMethodAnnotation( name = "addBodyHandler")
    BodyHandler bodyHandler;

    @Nfa
    @NodeAddMethodAnnotation( name = "addJointHandler")
    JointHandler jointHandler;

    public BodyHandler getBodyHandler() {
        return bodyHandler;
    }

    public BodyHandler setBodyHandler(BodyHandler bodyHandler) {
        this.bodyHandler = bodyHandler;
        this.bodyHandler.setTopNode( this );
        checkOnSceneState( this.bodyHandler );
        return this.bodyHandler;
    }

    public JointHandler getJointHandler() {
        return jointHandler;
    }

    public JointHandler setJointHandler(JointHandler jointHandler) {
        this.jointHandler = jointHandler;
        this.jointHandler.setTopNode( this );
        checkOnSceneState( this.jointHandler );
        return this.jointHandler;
    }

    public BodyHandler addBodyHandler( BodyHandler bh ) {
        if ( this.bodyHandler == null )
            return setBodyHandler( bh );
        checkOnSceneState( bh );
        return (BodyHandler) this.bodyHandler.appendNode( bh );
    }

    public JointHandler addJointHandler( JointHandler jh ) {
        if ( this.jointHandler == null )
            return setJointHandler( jh );
        checkOnSceneState( jh );
        return (JointHandler) jointHandler.appendNode( jh );
    }

    @Override
    protected void nodeAct(float delta) {

    }

    @Override
    protected void nodeDraw(Batch batch, float parentAlpha) {

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
        if ( bodyHandler != null ) {
            for ( Node node : bodyHandler)
                node.dispose();
        }

        if ( jointHandler != null ) {
            for ( Node node : jointHandler )
                node.dispose();
        }
    }

    @Override
    public void constructGraphics() {
        if ( bodyHandler != null ) {
            for ( Node node : bodyHandler) {
                node.constructGraphics();
                addActor( node );
            }
        }

        if ( jointHandler != null ) {
            for ( Node node : jointHandler ) {
                node.constructGraphics();
                addActor( node );
            }
        }
    }

    @Override
    public void constructPhysics() {
        if ( bodyHandler != null ) {
            for ( Node node : bodyHandler)
                node.constructPhysics();
        }

        if ( jointHandler != null ) {
            for ( Node node : jointHandler )
                node.constructPhysics();
        }
    }

    @Override
    public void destroyGraphics() {
        if ( bodyHandler != null ) {
            for ( Node node : bodyHandler) {
                node.destroyGraphics();
                node.remove();
            }
        }

        if ( jointHandler != null ) {
            for ( Node node : jointHandler ) {
                node.destroyGraphics();
                node.remove();
            }
        }
    }

    @Override
    public void destroyPhysics() {
        if ( bodyHandler != null ) {
            for ( Node node : bodyHandler)
                node.destroyPhysics();
        }

        if ( jointHandler != null ) {
            for ( Node node : jointHandler )
                node.destroyPhysics();
        }
    }
}
