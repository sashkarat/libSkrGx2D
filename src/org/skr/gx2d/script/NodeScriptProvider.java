package org.skr.gx2d.script;

import com.badlogic.gdx.utils.Array;

/**
 * Created by rat on 16.11.14.
 */
public class NodeScriptProvider {
    Array<NodeScript> scripts = new Array<NodeScript>();

    public void addScript( NodeScript script ) {
        scripts.add( script );
    }

    public NodeScript addScript(String name, NodeScriptSource source ) {
        NodeScript script = new NodeScript();
        script.setName( name );
        script.setSource( source, true );
        scripts.add(script);
//        Gdx.app.log("PhysScriptProvider.addSource", "script: " + script);
        return script;
    }

    public boolean removeScript( NodeScript script ) {
        return scripts.removeValue(script, true);
    }
    public boolean contains( NodeScript script ) {
        return scripts.contains( script, true );
    }

}
