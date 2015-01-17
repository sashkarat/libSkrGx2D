package org.skr.gx2d.script;

import com.badlogic.gdx.Gdx;
import org.skr.SkrScript.*;
import org.skr.gx2d.node.Node;

/**
 * Created by rat on 26.10.14.
 */
public class NodeScript extends Script {

    long id = -1;
    String name;
    NodeScriptSource source = null;

    protected NodeScript() {
        id = Node.nextId();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName() + " (ID: " + String.valueOf( getId() ) + ")";
    }

    public boolean setSource( NodeScriptSource source, boolean build ) {
        this.source = source;
        return !build || build();
    }

    public NodeScriptSource getSource() {
        return source;
    }

    public boolean build() {
        try {
            enabled = Builder.build(source.sourceText, this, true);
        } catch ( Exception e ) {
            e.printStackTrace();
            enabled = false;
        }
        if ( !enabled )
            Gdx.app.log("PhysScript.build", "Script: " + this + " set disabled.");
        return enabled;
    }
}
