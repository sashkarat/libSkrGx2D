package org.skr.gx2d.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import org.skr.gx2d.common.Env;
import org.skr.gx2d.utils.ModShapeRenderer;

/**
 * Created by rat on 13.07.14.
 */
public abstract class BaseScreen implements Screen, InputProcessor {


    private Stage stage;
    private OrthographicCamera camera;


    protected ModShapeRenderer shapeRenderer;
    protected BitmapFont font;
    protected Batch fontBatch;


    private boolean displayGrid = true;
    private boolean displayGridText = true;
    private boolean displayGridFirst = true;

    public BaseScreen() {
        ScreenViewport vp = new ScreenViewport();
        stage = new Stage( vp );
        camera = (OrthographicCamera) stage.getCamera();
        camera.position.set(0, 0, 0);

        shapeRenderer = new ModShapeRenderer();
        font = new BitmapFont();
        fontBatch = new SpriteBatch();
    }

    public Stage getStage() {
        return stage;
    }

    public ModShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public boolean isDisplayGrid() {
        return displayGrid;
    }

    public void setDisplayGrid(boolean displayGrid) {
        this.displayGrid = displayGrid;
    }

    public boolean isDisplayGridText() {
        return displayGridText;
    }

    public void setDisplayGridText(boolean displayGridText) {
        this.displayGridText = displayGridText;
    }

    public boolean isDisplayGridFirst() {
        return displayGridFirst;
    }

    public void setDisplayGridFirst(boolean displayGridFirst) {
        this.displayGridFirst = displayGridFirst;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    private static  float downPosX = 0;
    private static  float downPosY = 0;
    private boolean mouseDragged = false;
    private boolean screenDraggeed = false;



    protected void checkoutCameraZoom() {
        if ( camera.zoom > 100 )
            camera.zoom = 100;

        if ( camera.zoom < 0.0125f )
            camera.zoom = 0.0125f;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if ( (System.currentTimeMillis() - dClickTime) > 500 ) {
            dClickButton = -1000;
        }

        mouseDragged = false;
        if ( button == Input.Buttons.MIDDLE ) {
            downPosX = screenX;
            downPosY = screenY;
            return true;
        } else if ( Gdx.input.isButtonPressed( Input.Buttons.RIGHT ) ) {
            downPosX = screenX;
            downPosY = screenY;
            return true;
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        mouseDragged = true;
        if ( Gdx.input.isButtonPressed( Input.Buttons.MIDDLE ) ) {

            float offsetX = screenX - downPosX;
            float offsetY = screenY - downPosY;

            downPosX = screenX;
            downPosY = screenY;

            camera.translate( - offsetX * camera.zoom, offsetY * camera.zoom, 0);
            screenDraggeed = true;
            return true;
        } else if ( Gdx.input.isButtonPressed( Input.Buttons.RIGHT ) ) {
            float offsetX = screenX - downPosX;
            float offsetY = screenY - downPosY;

            downPosX = screenX;
            downPosY = screenY;

            float len = offsetX * offsetX + offsetY * offsetY;

            len = (float) Math.sqrt( len );
            if ( offsetY < 0 ) {
                len = - len;
            }

            float del = 100.f;

            if ( camera.zoom < 0.4f )
                del *= 10f;
            if ( camera.zoom > 2f )
                del /= 10f;

            camera.zoom += (len / del ) ;

            checkoutCameraZoom();

            screenDraggeed = true;
            return true;
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if ( screenDraggeed ) {
            screenDraggeed = false;
            return true;
        }

        if ( !mouseDragged ) {
            if ( processClicking(screenX, screenY, button) )
                return true;
        }
        mouseDragged = false;
        return false;
    }

    long dClickTime = 0;
    int dClickButton = -1000;

    protected boolean processClicking( int x, int y, int button ) {
        if ( clicked( x, y, button ) ) {
            dClickButton = -1;
            return true;
        }
        if ( dClickButton == -1000 ) {
            dClickButton = button;
            dClickTime = System.currentTimeMillis();
            return  false;
        }

        if ( dClickButton != button ) {
            dClickButton = -1000;
            return false;
        }

        if ( (System.currentTimeMillis() - dClickTime) > 500 ) {
            dClickButton = -1000;
            return false;
        }
        return doubleClicked( x, y, button );
    }

    protected boolean clicked( int screenX, int screenY, int button ) {
        return false;
    }

    protected boolean doubleClicked(int screenX, int screenY, int button ) {
        return false;
    }


    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if ( amount > 0)
            camera.zoom *= 2;
        else if (amount < 0)
            camera.zoom /= 2;

        checkoutCameraZoom();

        return true;
    }

    protected abstract void draw();

    protected void act( float delta ) {
        //dumb
    }

    void drawGrid( int deltaX, int deltaY, int delta) {

        float z = camera.zoom;

        float w = camera.viewportWidth * z;
        float h = camera.viewportHeight * z;

        float x1 = camera.position.x - w / 2;
        float x2 = x1 + w;

        float y1 = camera.position.y - h / 2;
        float y2 = y1 + h;

        int x = ((int) ( x1 / deltaX )) * deltaX;
        int y = ((int) ( y1 / deltaY )) * deltaY;

        shapeRenderer.setColor( 0.2f, 0.2f, 0.2f, 1f);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        while ( x < x2 ) {
            shapeRenderer.line(x, y1, x, y2);
            x+= deltaX;
        }

        while ( y < y2 ) {
            shapeRenderer.line( x1, y, x2, y );
            y+=deltaY;
        }

        x = ((int) ( x1 / deltaX )) * delta;
        y = ((int) ( y1 / deltaY )) * delta;

        shapeRenderer.setColor( 0.2f, 0.3f, 0, 1);

        while ( x < x2 ) {
            shapeRenderer.line(x, y1, x, y2);
            x+= delta;
        }

        while ( y < y2 ) {
            shapeRenderer.line( x1, y, x2, y );
            y+= delta;
        }


        shapeRenderer.setColor( 0.5f, 0.5f, 0.5f, 1);

        shapeRenderer.line( 0, y1, 0, y2 );
        shapeRenderer.line(x1, 0, x2, 0);

        shapeRenderer.end();

//        Gdx.app.log("EditorScreen.drawGrid", "Camera: " + camera.position + " " + camera.viewportWidth);
    }

    private void drawGridText( int delta ) {

        float z = camera.zoom;

        float w = camera.viewportWidth * z;
        float h = camera.viewportHeight * z;

        float x1 = camera.position.x - w / 2;
        float x2 = x1 + w;

        float y1 = camera.position.y - h / 2;
        float y2 = y1 + h;

        int x = ((int) ( x1 / delta )) * delta;
        int y = ((int) ( y1 / delta )) * delta;


        fontBatch.setProjectionMatrix( camera.projection );
        fontBatch.getProjectionMatrix().scl( camera.zoom );

        float offsetX =  - camera.position.x ;
        float offsetY =  - camera.position.y ;

        fontBatch.begin();

        font.setColor( 1, 1, 1, 1);
        font.drawMultiLine( fontBatch, "FPS: " + Gdx.graphics.getFramesPerSecond(),
                 - camera.viewportWidth/2 + 20, camera.viewportHeight/2 - 20);

        while ( x < x2 ) {
            font.setColor( 0, 1, 0.2f, 1);
            font.drawMultiLine( fontBatch, " " + x,
                    (x + offsetX) / z, (y2 + offsetY) / z - 2 );
            font.setColor( 0.2f, 0.8f, 1, 1);
            font.drawMultiLine( fontBatch, " " + Env.get().world.toPhys(x),
                    (x + offsetX) / z, (y1 + offsetY) / z + 20 );
            x += delta;
        }

        while ( y < y2 ) {
            font.setColor( 0, 1, 0.2f, 1);
            font.drawMultiLine( fontBatch, " " + y,
                    (x1 + offsetX) / z + 2, (y + offsetY) / z );
            font.setColor( 0.2f, 0.8f, 1, 1);
            font.drawMultiLine( fontBatch, " " + Env.get().world.toPhys(y),
                    (x2 + offsetX) / z - 35, (y + offsetY) / z );
            y+= delta;
        }



        fontBatch.end();

    }

    protected void debugRender() {
        // stub
    }

    @Override
    public void render(float delta) {

        act( delta );

        Gdx.gl20.glClearColor(0, 0.1f, 0.05f, 1);
        Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT | Gdx.gl20.GL_DEPTH_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix( stage.getBatch().getProjectionMatrix() );
        shapeRenderer.setTransformMatrix( stage.getBatch().getTransformMatrix() );

        int gridDelta = 10;

        if ( camera.zoom > 3 )
            gridDelta = 100;
        if ( camera.zoom > 4)
            gridDelta = 200;
        if ( camera.zoom > 8 )
            gridDelta = 1000;
        if ( camera.zoom < 0.25 )
            gridDelta = 1;

        if ( displayGridFirst ) {
            if (displayGrid)
                drawGrid(gridDelta, gridDelta, gridDelta * 5);

            if (displayGridText)
                drawGridText(gridDelta * 10);
        }

        stage.act( delta );
        stage.draw();

        if ( Env.get().debugRender ) {
            debugRender();
        }


        draw();

        if ( !displayGridFirst ) {
            if (displayGrid)
                drawGrid(gridDelta, gridDelta, gridDelta * 5);

            if (displayGridText)
                drawGridText(gridDelta * 10);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void show() {
        Env.get().sceneProvider.setStage( stage );
    }

    @Override
    public void hide() {
        Env.get().sceneProvider.setStage( null );
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
}
