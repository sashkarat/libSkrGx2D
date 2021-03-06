import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.common.Gx2DApplication;
import org.skr.gx2d.editor.AbstractEditorScreen;
import org.skr.gx2d.model.Model;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.NodeFactory;
import org.skr.gx2d.physnodes.BodyHandler;
import org.skr.gx2d.physnodes.PhysSet;
import org.skr.gx2d.scene.ModelHandler;
import org.skr.gx2d.scene.Scene;
import org.skr.gx2d.scene.SceneWorkFlow;
import org.skr.gx2d.utils.Utils;

/**
 * Created by rat on 04.01.15.
 */
public class Testing {

    static Scene testScene = new Scene();
    static Model testModel = new Model();

    static class TestScreen extends AbstractEditorScreen implements SceneWorkFlow.SceneStateListener {

        public TestScreen() {
            testScene.addSceneStateListener( this );
        }

        @Override
        public void show() {
            Env.get().sceneProvider.setActiveScene( Env.get().sceneProvider.addScene( testScene) );
            super.show();
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void dispose() {

        }

        @Override
        protected void draw() {

        }

        @Override
        public void sceneInitialized(SceneWorkFlow sceneWorkFlowImpl) {
            Utils.printMsg("Testing.sceneInitialized", " SCENE: " + sceneWorkFlowImpl );
        }

        @Override
        public void sceneGraphicsConstructed(SceneWorkFlow sceneWorkFlowImpl) {
            Utils.printMsg("Testing.sceneGraphicsConstructed", " SCENE: " + sceneWorkFlowImpl );
            testModel.setName( "testModel" );
            testModel.addPhysSet( new PhysSet() );
            testModel.getPhysSet().setName( "testPhysSet" );

            BodyHandler bh = new BodyHandler();

            bh.setName( " funnyBody" );

            testModel.getPhysSet().addBodyHandler( bh );


            testScene.addModelHandler( new ModelHandler("testMh") );
            testScene.getModelHandler().setModel( testModel );
        }

        @Override
        public void scenePhysicsConstructed(SceneWorkFlow sceneWorkFlowImpl) {
            Utils.printMsg("Testing.scenePhysicsConstructed", " SCENE: " + sceneWorkFlowImpl );
        }

        @Override
        public void sceneDisposing(SceneWorkFlow sceneWorkFlowImpl) {
            Utils.printMsg("Testing.onSceneDispose", " SCENE: " + sceneWorkFlowImpl );

            if ( NodeFactory.saveNodeToFile(testModel, Gdx.files.local("data/testModel.gx2d")) ) {
                ModelHandler mh = testModel.getModelHandler();
                if ( mh != null )
                    mh.setModelFilePath("data/testModel.gx2d");
            }


            if ( ! NodeFactory.saveNodeToFile(testScene, Gdx.files.local("data/test.gx2d") ) ) {
                Utils.printError("Testing.main", " fail ");
                return;
            }

            Scene sceneCpy = (Scene) NodeFactory.createNodeFromFile( Gdx.files.local("data/test.gx2d") );
            sceneCpy.setName( sceneCpy.getName() + "_cpy");
            String jsonStr = NodeFactory.getNodeJSonString(sceneCpy, true);
            Utils.printMsg("Testing.main", "\n" + jsonStr + "\n*******************" );
        }
    }

    static class GdxApp extends Gx2DApplication {

        TestScreen screen;

        @Override
        protected void onCreate() {
            screen = new TestScreen();
            setScreen( screen );

            testScene.setName("testScene");

        }

        @Override
        protected void onDispose() {

        }
    }

    public static void main( String [] args ) {

        Gx2DApplication.Config config = new Gx2DApplication.Config();
        config.internalTextureAtlasPath = "data/demo_atlas/demoAtlas.atlas";

        GdxApp gApp = new GdxApp();

        gApp.setCfg( config );

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "libSkrGx2DTesting";
        cfg.width = 800;
        cfg.height = 480;


        new LwjglApplication( gApp, cfg);
//        nodeChainingTest();
    }

    static void nodeChainingTest() {

        ModelHandler m0 = new ModelHandler("m0");
        ModelHandler m1 = new ModelHandler("m1");
        ModelHandler m2 = new ModelHandler("m2");
        ModelHandler m3 = new ModelHandler("m3");

        m0.appendNode(m1).appendNode(m2).appendNode(m3);
        Utils.printMsg("Testing.", "chain: " + m0.chainToString());

        Utils.printMsg("\nTesting." , "remove m2: " );
        m2.removeFromChain();
        Utils.printMsg("Testing.", "chain: " + m0.chainToString());

        Utils.printMsg("\nTesting." , "remove m0: " );
        m0.removeFromChain();
        Utils.printMsg("Testing.", "chain: " + m0.chainToString());
        Utils.printMsg("Testing." , "chain: " + m1.chainToString() );

        Utils.printMsg("\nTesting." , "remove m3: " );
        m3.removeFromChain();
        Utils.printMsg("Testing." , "chain: " + m1.chainToString() );

        Utils.printMsg("\nTesting." , "m3 set at first" );
        m1.setPrevNode( m3 );
        Utils.printMsg("Testing." , "chain: " + m3.chainToString() );


        Utils.printMsg("\nTesting." , "append m0 to m3" );
        m3.appendNode( m0 );
        Utils.printMsg("Testing." , "chain: " + m3.chainToString() );

        Utils.printMsg("\nTesting." , "m2 insert before m0" );
        m0.insertBefore( m2 );
        Utils.printMsg("Testing." , "chain: " + m3.chainToString() );

        Utils.printMsg("\nTesting." , "remove m1 and insert m1 after m2" );
        m1.removeFromChain();
        m2.insertAfter( m1 );
        Utils.printMsg("Testing." , "chain: " + m3.chainToString() );

        m2.executeChainCmd( Node.Command.SetActive, true, Node.ChainDirection.DownUp );

        for ( Node mh : m2 ) {
            Utils.printMsg("Testing.", "mh: " + mh );
        }
    }
}
