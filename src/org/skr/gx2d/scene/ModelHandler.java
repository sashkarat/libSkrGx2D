package org.skr.gx2d.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.model.Model;
import org.skr.gx2d.node.NodeFactory;
import org.skr.gx2d.node.SceneItem;
import org.skr.gx2d.node.annotation.Nfa;

/**
 * Created by rat on 05.01.15.
 */

public class ModelHandler extends SceneItem {

    @Nfa
    String modelFilePath = "";

    String jsonString = "";

    Model model;

    public ModelHandler() {
    }

    @Override
    protected void nodeAct(float delta) {

    }

    @Override
    protected void nodeDraw(Batch batch, float parentAlpha) {

    }

    @Override
    protected boolean activate(boolean state) {
//        Utils.printMsg("ModelHandler.activate", "state: " + state + " " + this );
        return state;
    }

    public ModelHandler(String name ) {
        super();
        setName( name );
    }

    public Scene getScene() {
        return (Scene) topNode();
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

    public boolean loadModelFromResource() {
        destroyGraphics();
        destroyPhysics();
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
        model.setTopNode( this );
        return model != null;
    }

    @Override
    public void constructGraphics() {
        if ( model != null ) {
            addActor( model );
            model.constructGraphics();
        }
    }

    @Override
    public void constructPhysics() {
        if ( model != null )
            model.constructPhysics();
    }

    @Override
    public void destroyGraphics() {
        if ( model != null ) {
            model.remove();
            model.destroyGraphics();
        }
    }

    @Override
    public void destroyPhysics() {
        if ( model != null )
            model.destroyPhysics();
    }

    public void setModel(Model model) {
        destroyGraphics();
        destroyPhysics();
        dispose();

        this.model = model;
        this.model.setTopNode( this );

        if ( topNode() == null )
            return;

        if ( getScene().isPhysicsConstructed() )
            model.constructPhysics();
        if ( getScene().isGraphicsConstructed() ) {
            addActor( model );
            model.constructGraphics();
        }
    }
}
