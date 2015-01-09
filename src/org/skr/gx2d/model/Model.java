package org.skr.gx2d.model;

import com.badlogic.gdx.physics.box2d.*;
import org.skr.SkrScript.ValuePool;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.annotation.NodeField;


import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by rat on 31.05.14.
 */

public class Model extends Node {

    private World world;

    @NodeField
    private boolean active = true;


    ValuePool vpPool = new ValuePool();


    protected Model() {
    }

    @Override
    protected boolean activate(boolean state) {
        return false;
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
    protected boolean upload() {
        return false;
    }


    @Override
    public void dispose() {

    }

    @Override
    public void constructPhysics() {

    }
}
