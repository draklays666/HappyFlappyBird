package io.github.some_example_name.characters;

import static io.github.some_example_name.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import io.github.some_example_name.MyGdxGame;


public class Bird {

    int x;
    float y;
    int width, height;
    float speedY;

    private static final float gravity = -800f;
    int frameCounter;
    Texture[] framesArray;
    MyGdxGame myGdxGame;
    int frameMultiplier = 10;

    public Bird(MyGdxGame game,int x, int y, int speed, int width, int height) {
        this.myGdxGame = game;
        this.x = x;
        this.y = y;
        this.speedY = speed;
        this.width = width;
        this.height = height;
        frameCounter = 0;
        skinChange();
    }

    public Texture[] skinChange() {
        if ("ufo".equals(myGdxGame.selectedBirdSkin)) {
            framesArray = new Texture[]{
                new Texture("birdTiles/ufo/ufo.png"),
            };
        } else if ("default".equals(myGdxGame.selectedBirdSkin)) {
            framesArray = new Texture[]{
                new Texture("birdTiles/default/bird0.png"),
                new Texture("birdTiles/default/bird1.png"),
                new Texture("birdTiles/default/bird2.png"),
                new Texture("birdTiles/default/bird1.png")
            };
        }
        return framesArray;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void onClick() {
        speedY = 350f;
    }
    public void setSpeedY() {
        this.speedY = 0f;
    }
    public void fly(float delta) {
        speedY += gravity * delta;
        y += speedY * delta;
    }

    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > SCR_HEIGHT) return false;
        return true;
    }

    public void draw(Batch batch) {
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }

}
