package io.github.some_example_name.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.characters.Bird;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.TextButton;



public class ScreenShop implements Screen {


    MyGdxGame myGdxGame;
    TextButton buttonSelected;
    ScreenShop test;

    MovingBackground background;
    TextButton buttonExitS;
    TextButton buttonDefault;
    TextButton buttonUfo;
    Texture skin1;
    Texture skin2;
    private Bird Bird;

    public ScreenShop(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        background = new MovingBackground("backgrounds/bg_shop.png");

        buttonExitS = new TextButton(800, 50, "Exit");
        buttonDefault = new TextButton(45, 230, "Default");
        buttonUfo = new TextButton(475,230, "Ufo");
        buttonSelected = new TextButton(45, 230, "Selected");

        skin1 = new Texture("birdTiles/default/bird1.png");
        skin2 = new Texture("birdTiles/ufo/ufo.png");
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
            if (buttonDefault.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.selectedBirdSkin = MyGdxGame.SKIN_DEFAULT;
            }
            if (buttonUfo.isHit((int) touch.x, (int) touch.y)) {
                myGdxGame.selectedBirdSkin = MyGdxGame.SKIN_UFO;

            }


        }
            ScreenUtils.clear(1, 0, 0, 1);
            myGdxGame.camera.update();
            myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
            myGdxGame.batch.begin();

            background.draw(myGdxGame.batch);
            buttonExitS.draw(myGdxGame.batch);
            buttonUfo.draw(myGdxGame.batch);
            myGdxGame.batch.draw(skin1,45,410); // texture
            myGdxGame.batch.draw(skin2,475,410);
            buttonDefault.draw(myGdxGame.batch);


            if (myGdxGame.selectedBirdSkin.equals(MyGdxGame.SKIN_UFO)) {
                buttonUfo.changeText("Selected");
                buttonUfo.changePos(475,230);
                buttonDefault.changeText("Default");
                buttonDefault.changePos(45,230);

            } else if (myGdxGame.selectedBirdSkin.equals(MyGdxGame.SKIN_DEFAULT)) {
                buttonDefault.changeText("Selected");
                buttonDefault.changePos(45,230);
                buttonUfo.changeText("Ufo");
                buttonUfo.changePos(475,230);

            }





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
        buttonDefault.dispose();
        buttonSelected.dispose();
        skin1.dispose();
        buttonUfo.dispose();
    }
}

