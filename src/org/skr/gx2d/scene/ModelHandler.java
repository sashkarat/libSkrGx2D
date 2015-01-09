package org.skr.gx2d.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import org.skr.gx2d.model.Model;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.NodeFactory;
import org.skr.gx2d.node.annotation.NodeField;
import org.skr.gx2d.utils.Utils;

/**
 * Created by rat on 05.01.15.
 */
public class ModelHandler extends Node {

    @NodeField
    String modelFilePath = "";

    String jsonString = "";

    Model model;

    @Override
    protected boolean activate(boolean state) {
//        Utils.printMsg("ModelHandler.activate", "state: " + state + " " + this );
        return false;
    }

    public ModelHandler(String name ) {
        super();
        setName( name );
    }

    public Scene getScene() {
        return (Scene) getTopNode();
    }

    @Override
    public Type getType() {
        return Type.ModelHandler;
    }

    @Override
    public boolean isType(Type type) {
        return type == Type.ModelHandler;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public String getModelFilePath() {
        return modelFilePath;
    }

    public void setModelFilePath(String modelFilePath) {
        this.modelFilePath = modelFilePath;
    }

    @Override
    public void dispose() {
        if ( model != null )
            model.dispose();
        model = null;
    }

    public boolean isModelLoaded() {
        return model != null;
    }

    public boolean loadModel() {
        dispose();

        if ( ! modelFilePath.isEmpty() ) {
            FileHandle fh = Gdx.files.internal( getScene().getModelDirectory() + "/" + modelFilePath );
            if ( ! fh.exists() )
                return false;
            jsonString =  fh.readString();
        }

        if ( jsonString.isEmpty() )
            return false;

        model = (Model) NodeFactory.createNodeFromJSon( jsonString );
        return model != null;
    }

    @Override
    public void constructGraphics() {

    }

    @Override
    public void constructPhysics() {

    }
}
