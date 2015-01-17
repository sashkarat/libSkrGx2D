package org.skr.gx2d.node;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import org.skr.gx2d.node.annotation.*;
import org.skr.gx2d.utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

/**
 * Created by rat on 05.01.15.
 */
public class NodeJSonAccessor implements Json.Serializable {

    private Node rootNode = null;

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public void write(Json json) {
        if ( rootNode == null )
            return;
        writeNodeChain(rootNode, json);
    }

    protected void writeNodeChain( Node node, Json json ) {
        while ( node != null ) {
            writeNode( node, json);
            node = node.nextNode();
        }
    }

    protected void writeNode( Node node, Json json ) {
//        Utils.printMsg("NodeJSonAccessor.writeNode", ": " + node.getClass() );
        node.preExport();
        Class<?> nodeClass = node.getClass();
        json.writeObjectStart( nodeClass.getName() );
        if ( nodeClass.isAnnotationPresent( NodeFactoryAnnotation.class) ) {
            if ( !writeNodeFactoryParameters(node, json))
                return;
        }
        Stack< Class<?> > hierarchy = new Stack<Class<?>>();
        Class<?> cl = nodeClass;
        while ( cl != Node.class ) {
            hierarchy.push(cl);
            cl = cl.getSuperclass();
        }

        writeAsClass(node, cl, json);
        while ( !hierarchy.isEmpty() )
            writeAsClass(node, hierarchy.pop(), json);
        json.writeObjectEnd();
        node.postExport();
    }

    protected boolean writeNodeFactoryParameters(Node node, Json json ) {
        try {
            Class<?> nodeClass = node.getClass();
            NodeFactoryAnnotation nfa = nodeClass.getAnnotation( NodeFactoryAnnotation.class );
            NodeDataAccessorAnnotation nda = nfa.parameter();
            Method readMethod = nodeClass.getMethod(nda.read());
            Object param = readMethod.invoke(node);
            json.writeValue("factoryParameter", param, nda.type() );
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void writeAsClass(Node node, Class<?> nodeClass, Json json) {

        if ( nodeClass.isAnnotationPresent(NodeDataMapAnnotation.class))
            writeNodeDataMap( node, nodeClass, json);

        for (Field field : nodeClass.getDeclaredFields() ) {
            if ( ! field.isAnnotationPresent(Nfa.class) )
                continue;

            try {
                if ( !field.isAccessible() )
                    field.setAccessible( true );
                Object value = field.get( node );

                if ( value instanceof Node ) {
                    json.writeObjectStart( field.getName() );
                    writeNodeChain((Node) value, json);
                    json.writeObjectEnd();
                    continue;
                }
//                if ( nodeClass == Node.class && field.getName().equals("subNode") ) {
//                    writeSubNodes(node, json);
//                    continue;
//                }

                json.writeValue( field.getName(), value, field.getType() );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    protected void writeNodeDataMap(Node node, Class<?> nodeClass, Json json ) {
        NodeDataMapAnnotation annMap = nodeClass.getAnnotation(NodeDataMapAnnotation.class);
        for (NodeDataAnnotation annData : annMap.data() ) {
            NodeDataAccessorAnnotation annAcc = annData.accessor();
            try {
                Method read = nodeClass.getMethod(annAcc.read());
                if ( !read.isAccessible() )
                    read.setAccessible(true);
                Object value = read.invoke(node);
                json.writeValue(annData.name(), value, annAcc.type() );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        rootNode = readNodeChain(json, jsonData );
    }

    protected Node readNodeChain(Json json, JsonValue parentJsonData ) {
        Node firstNode = null;

        JsonValue jv = parentJsonData.child();
        while ( jv != null ) {
            Node node = readNode( json, jv );

            if ( firstNode == null )
                firstNode = node;
            else
                firstNode.appendNode( node );
            jv = jv.next;
        }
        return firstNode;
    }

    public Node readNode(Json json, JsonValue jv ) {
        Class<?> nodeClass = null;
        Node node = null;
        try {
            nodeClass = Class.forName(jv.name() );
            node = createNode( nodeClass, json, jv );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        if ( node == null )
            return null;
//        Utils.printMsg("NodeJSonAccessor.readNode: ", node.toString() );

        Stack< Class<?> > hierarchy = new Stack<Class<?>>();
        Class<?> cl = nodeClass;
        while ( cl != Node.class ) {
            hierarchy.push(cl);
            cl = cl.getSuperclass();
        }

        readAsClass(node, cl, json, jv);

        while ( !hierarchy.isEmpty() )
            readAsClass(node, hierarchy.pop(), json, jv);
        return node;
    }

    public Node createNode( Class<?> nodeClass, Json json, JsonValue jv ) {
        Node node = null;
        try {
            if ( ! nodeClass.isAnnotationPresent( NodeFactoryAnnotation.class) ) {
                node = (Node) nodeClass.getConstructor().newInstance();
                return node;
            }

            NodeFactoryAnnotation nfa = nodeClass.getAnnotation( NodeFactoryAnnotation.class );
            NodeDataAccessorAnnotation nda = nfa.parameter();
            Method createMethod = nfa.factory().getMethod(nfa.createMethodName(), nda.type());
            JsonValue jvParam = jv.get("factoryParameter");
            Object parameter = json.readValue( nda.type(), jvParam );
            node = (Node) createMethod.invoke( null, parameter );

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return node;
    }

    public void readAsClass(Node node, Class<?> nodeClass, Json json, JsonValue jv) {

        if ( nodeClass.isAnnotationPresent( NodeDataMapAnnotation.class) )
            readNodeDataMap( node, nodeClass, json, jv );

        for (Field field : nodeClass.getDeclaredFields() ) {
            if ( ! field.isAnnotationPresent(Nfa.class) )
                continue;
            if (  ! field.isAccessible() )
                field.setAccessible( true );
            JsonValue fjv = jv.get( field.getName() );
            if ( fjv == null )
                continue;
            try {
                if ( Node.class.isAssignableFrom(field.getType()) ) {
                    readSubNode( node, nodeClass, field, json, fjv );
                    continue;
                }

                Object val =  json.readValue(field.getType(), fjv);

                if ( field.getType().isArray() && val instanceof Array ) {
                    Array<?> arrayList = (Array<?>) val;
                    field.set(node, arrayList.toArray(field.getType().getComponentType() ) );
                    continue;
                }

                field.set(node, val);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    protected void readSubNode( Node topNode, Class<?> topNodeCl, Field field, Json json, JsonValue jv ) {
        String methodStr;

        if ( field.isAnnotationPresent(NodeAddMethodAnnotation.class) ) {
            NodeAddMethodAnnotation annAdd = field.getAnnotation( NodeAddMethodAnnotation.class );
            methodStr = annAdd.name();
        } else {
            methodStr = "add" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
        }
        Node subNode = readNodeChain( json, jv );
        if ( subNode == null )
            return;
        subNode.setTopNode( topNode );
        try {
            Method method = topNodeCl.getMethod(methodStr, field.getType() );
            method.invoke(topNode, subNode);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    protected void readNodeDataMap(Node node, Class<?> nodeClass, Json json, JsonValue jv ) {
        NodeDataMapAnnotation annMap = nodeClass.getAnnotation(NodeDataMapAnnotation.class);
        for (NodeDataAnnotation annData : annMap.data() ) {
            NodeDataAccessorAnnotation annAcc = annData.accessor();
            try {
                JsonValue djv = jv.get( annData.name() );
                if ( djv == null )
                    continue;

                Method write = nodeClass.getMethod(annAcc.write(), annAcc.type() );
                if ( !write.isAccessible() )
                    write.setAccessible(true);

                Object val =  json.readValue(annAcc.type(), djv);
                write.invoke( node, val );
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
