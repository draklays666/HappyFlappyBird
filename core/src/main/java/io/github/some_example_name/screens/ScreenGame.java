package io.github.some_example_name.screens;

import static io.github.some_example_name.MyGdxGame.SCR_HEIGHT;
import static io.github.some_example_name.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.MyGdxGame;
import io.github.some_example_name.characters.Bird;
import io.github.some_example_name.characters.Tube;
import io.github.some_example_name.components.MovingBackground;
import io.github.some_example_name.components.PointCounter;

public class ScreenGame implements Screen {

    final int pointCounterMarginTop = 60;
    final int pointCounterMarginRight = 400;

    MyGdxGame myGdxGame;

    Bird bird;
    PointCounter pointCounter;
    MovingBackground background;
    private ShapeRenderer debugRenderer;

    int tubeCount = 3;
    Tube[] tubes;

    int gamePoints;
    boolean isGameOver;
    private BitmapFont font;
    Sound deathSound = Gdx.audio.newSound(Gdx.files.internal("sounds/death.mp3"));
    Music gameMusic;

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;

        font = new BitmapFont();

        initTubes();
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/theoryOE.mp3"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.3f);
        background = new MovingBackground("backgrounds/game_bg.png");

        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
        debugRenderer = new ShapeRenderer();
    }


    @Override
    public void show() {
        bird = new Bird(myGdxGame,20, SCR_HEIGHT / 2, 10, 250, 200);
        gamePoints = 0;
        isGameOver = false;
        bird.setY(SCR_HEIGHT / 2);
        initTubes();
        gameMusic.play();
    }

    @Override
    public void render(float delta) {

        if (isGameOver) {
            myGdxGame.screenRestart.gamePoints = gamePoints;
            myGdxGame.setScreen(myGdxGame.screenRestart);
            bird.setSpeedY();
            deathSound.play();
            gameMusic.stop();
        }

        if (Gdx.input.justTouched()) {
            bird.onClick();
        }

        background.move();
        bird.fly(delta);
        if (!bird.isInField()) {
            System.out.println("not in field");
            isGameOver = true;
        }
        for (Tube tube : tubes) {
            tube.move(delta);
            if (tube.isHit(bird)) {
                isGameOver = true;
                System.out.println("hit");
            } else if (tube.needAddPoint(bird)) {
                gamePoints += 1;
                tube.setPointReceived();
                System.out.println(gamePoints);
            }
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();

        background.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);

        font.draw(myGdxGame.batch, "fps " + Gdx.graphics.getFramesPerSecond(), 10, SCR_HEIGHT - 10);

        myGdxGame.batch.end();
        // hitboxShow
        //debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        //bird.debugDraw(debugRenderer);
        //debugRenderer.end();

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
        debugRenderer.dispose();
        bird.dispose();
        background.dispose();
        pointCounter.dispose();
        for (int i = 0; i < tubeCount; i++) {
            tubes[i].dispose();
        }
        font.dispose();
        deathSound.dispose();
        gameMusic.dispose();
    }

    void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }

}
