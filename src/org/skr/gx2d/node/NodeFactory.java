package org.skr.gx2d.node;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import org.skr.gx2d.model.Model;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.FixtureSet;
import org.skr.gx2d.physnodes.JointHandler;
import org.skr.gx2d.physnodes.PhysSet;
import org.skr.gx2d.scene.ModelHandler;
import org.skr.gx2d.scene.Scene;
import org.skr.gx2d.sprite.Sprite;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by rat on 04.01.15.
 */
public class NodeFactory {

    protected static Class<? extends Node> nodeClass(Node.Type type) {
        switch ( type ) {
            case Scene:
                return Scene.class;
            case ModelHandler:
                return ModelHandler.class;
            case Model:
                return Model.class;
            case PhysSet:
                return PhysSet.class;
            case BodyHandler:
                return BodyHandler.class;
            case JointHandler:
                return JointHandler.class;
            case FixtureSet:
                return FixtureSet.class;
            case Sprite:
                return Sprite.class;
        }
        return null;
    }

    protected static boolean isNodeClassFamily( String name ) {
        if ( ! name.contains("."))
            return false;
        try {
            Class<?> cl = Class.forName(name);
            return Node.class.isAssignableFrom(cl);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    protected static Node createNode( Node.Type type, Node topNode ) {
        Class<?> cl = nodeClass(type);
        if ( cl == null )
            return null;
        try {
            Node node =  (Node) cl.getConstructor().newInstance( );
            node.setTopNode( topNode );
            return node;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Node createNodeFromJSon( String jsonString ) {
        Json js = new Json();
        NodeJSonAccessor nja = js.fromJson( NodeJSonAccessor.class, jsonString );
        if ( nja.getRootNode() == null )
            return null;
        Node node = nja.getRootNode();
        return node;
    }

    public static Node createNodeFromFile( FileHandle handle ) {
        try {
            return createNodeFromJSon(handle.readString());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getNodeJSonString( Node node, boolean prettyPrint ) {
        Json js = new Json();
        NodeJSonAccessor nja = new NodeJSonAccessor();
        nja.setRootNode( node );
        String jsonStr = null;
        try {
            jsonStr = js.toJson(nja, NodeJSonAccessor.class);
        } catch (Exception e ) {
            e.printStackTrace();
        }
        if ( jsonStr == null )
            return null;
        if ( ! prettyPrint )
            return jsonStr;
        return js.prettyPrint( jsonStr );
    }

    public static boolean saveNodeToFile( Node node, FileHandle fileHandle ) {
        try {
            fileHandle.writeString( getNodeJSonString(node, true), false );
        } catch ( Exception e ) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
