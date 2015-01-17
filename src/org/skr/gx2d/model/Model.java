package org.skr.gx2d.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import org.skr.SkrScript.ValuePool;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.SceneItem;
import org.skr.gx2d.node.annotation.NodeAddMethodAnnotation;
import org.skr.gx2d.node.annotation.Nfa;
import org.skr.gx2d.physnodes.PhysSet;
import org.skr.gx2d.scene.ModelHandler;


/**
 * Created by rat on 31.05.14.
 */

public class Model extends SceneItem {

    @Nfa
    private boolean active = true;

    @Nfa
    @NodeAddMethodAnnotation( name = "addPhysSet" )
    PhysSet physSet = null;

    ValuePool vpPool = new ValuePool();

    public Model() {
    }

    @Override
    protected void nodeAct(float delta) {

    }

    @Override
    protected void nodeDraw(Batch batch, float parentAlpha) {

    }

    public PhysSet getPhysSet() {
        return physSet;
    }

    public PhysSet setPhysSet(PhysSet physSet) {
        this.physSet = physSet;
        this.physSet.setTopNode( this );
        checkOnSceneState(this.physSet);
        return this.physSet;
    }

    public PhysSet addPhysSet(PhysSet phs ) {
        if ( physSet == null )
            return setPhysSet( phs );
        checkOnSceneState(phs);
        return (PhysSet) physSet.appendNode( phs );
    }

    @Override
    protected boolean activate(boolean state) {
        return false;
    }


    public ModelHandler getModelHandler() {
        return (ModelHandler) topNode();
    }

    @Override
    public Type getType() {
        return Type.Model;
    }

    @Override
    public boolean isType(Type type) {
        return type == Type.Model;
    }

    @Override
    public void dispose() {
        if ( physSet == null )
            return;
        for ( Node node : physSet )
            node.dispose();

    }

    @Override
    public void constructGraphics() {
        if ( physSet ==  null )
            return;
        for ( Node node :physSet ) {
            node.constructGraphics();
            addActor(node);
        }
    }

    @Override
    public void constructPhysics() {
        if ( physSet ==  null )
            return;
        for ( Node node :physSet )
            node.constructPhysics();
    }

    @Override
    public void destroyGraphics() {
        if ( physSet == null )
            return;
        for ( Node node : physSet ) {
            node.destroyGraphics();
            node.remove();
        }
    }

    @Override
    public void destroyPhysics() {
        if ( physSet == null )
            return;
        for ( Node node : physSet )
            node.destroyPhysics();
    }
}
