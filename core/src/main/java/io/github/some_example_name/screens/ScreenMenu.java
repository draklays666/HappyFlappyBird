package io.github.some_example_name.screens;


import static io.github.some_example_name.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;


import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.characters.Bird;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.TextButton;

public class ScreenMenu implements Screen {

    MyGdxGame myGdxGame;

    MovingBackground background;
    TextButton buttonStart;
    TextButton buttonExit;
    TextButton buttonShop;
    Texture logo;
    Bird bird;

    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        buttonStart = new TextButton(80, 500, "Start");
        buttonExit = new TextButton(80, 110, "Exit");
        buttonShop = new TextButton(80, 370, "Skins");
        background = new MovingBackground("backgrounds/restart_bg.png");
        logo = new Texture("backgrounds/logo.png");
        bird = new Bird(myGdxGame,730, 245, 0, 250, 200);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {

            Vector3 touch = myGdxGame.camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)
            );
            if (buttonShop.isHit((int) touch.x, (int) touch.y)){
                myGdxGame.setScreen(myGdxGame.screenShop);
            }else if (buttonStart.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.setScreen(myGdxGame.screenGame);
            } else if (buttonExit.isHit((int) touch.x, (int) touch.y)) {
                Gdx.app.exit();
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        buttonStart.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        buttonShop.draw(myGdxGame.batch);
        myGdxGame.batch.draw(logo, 600,490);

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
        buttonExit.dispose();
        buttonStart.dispose();
        buttonShop.dispose();
        logo.dispose();
        bird.dispose();
    }
}
