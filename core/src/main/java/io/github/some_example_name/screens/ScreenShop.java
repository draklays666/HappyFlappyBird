package io.github.some_example_name.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.TextButton;


public class ScreenShop implements Screen {


    MyGdxGame myGdxGame;

    MovingBackground background;
    TextButton buttonExitS;

    public ScreenShop(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        background = new MovingBackground("backgrounds/restart_bg.png");
        buttonExitS = new TextButton(800, 50, "Exit");
    }


    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );
            if (buttonExitS.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenMenu);
            }
//          if (button.isHit((int) touch.x, (int) touch.y)) {
//                myGdxGame.setScreen(myGdxGame.screenGame);
//              }
//          if (button.isHit((int) touch.x, (int) touch.y)) {
//                Gdx.app.exit();
//              }
        }
            ScreenUtils.clear(1, 0, 0, 1);
            myGdxGame.camera.update();
            myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
            myGdxGame.batch.begin();

            background.draw(myGdxGame.batch);
            buttonExitS.draw(myGdxGame.batch);



            myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        buttonExitS.dispose();
    }
}

