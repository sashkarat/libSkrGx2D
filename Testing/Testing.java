import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Animation;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.common.Gx2DApplication;
import org.skr.gx2d.editor.BaseScreen;
import org.skr.gx2d.node.Node;
import org.skr.gx2d.node.NodeFactory;
import org.skr.gx2d.scene.ModelHandler;
import org.skr.gx2d.scene.Scene;
import org.skr.gx2d.sprite.Sprite;
import org.skr.gx2d.utils.Utils;

import java.lang.reflect.Method;

/**
 * Created by rat on 04.01.15.
 */
public class Testing {

    static Scene testScene = new Scene();

    static class TestScreen extends BaseScreen {

        @Override
        public void show() {
            Env.sceneProvider.setActiveScene( Env.sceneProvider.addScene( testScene) );
            super.show();
        }

        @Override
        protected void draw() {

        }
    }

    static class GdxApp extends Gx2DApplication {

        TestScreen screen;

        @Override
        protected void onCreate() {
            screen = new TestScreen();
            setScreen( screen );

            testScene.setName("testScene");
            testScene.addModelHandler(new ModelHandler("mh1"));
            testScene.addModelHandler(new ModelHandler("mh2"));
            Sprite sprite = new Sprite();
            sprite.setName("justAnEmptySprite");
            sprite.setPlayMode( Animation.PlayMode.LOOP_RANDOM );
//            testScene.addSubNode( sprite );
//        String jsonStr = NodeFactory.getNodeJSonString(testScene, true);
//        Utils.printMsg("Testing.main", "\n" + jsonStr + "\n*******************" );

            if ( ! NodeFactory.saveNodeToFile(testScene, Gdx.files.local("data/test.gx2d") ) ) {
                Utils.printError("Testing.main", " fail ");
                return;
            }

            Scene sceneCpy = (Scene) NodeFactory.createNodeFromFile( Gdx.files.local("data/test.gx2d") );
            sceneCpy.setName( sceneCpy.getName() + "_cpy");
            String jsonStr = NodeFactory.getNodeJSonString(sceneCpy, true);
            Utils.printMsg("Testing.main", "\n" + jsonStr + "\n*******************" );



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
        ModelHandler m0 = new ModelHandler("m0"), m1 = new ModelHandler("m1"),
                m2 = new ModelHandler("m2"), m3 = new ModelHandler("m3");

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
    }
}
