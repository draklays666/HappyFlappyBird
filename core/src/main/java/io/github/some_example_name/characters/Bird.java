package io.github.some_example_name.characters;

import static io.github.some_example_name.MyGdxGame.SCR_HEIGHT;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


import io.github.some_example_name.MyGdxGame;


public class Bird {

    int x;
    float y;
    int width, height;
    float speedY;
    public Rectangle bodyHitbox = new Rectangle();
    public Rectangle headHitbox = new Rectangle();
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
        } if ("default".equals(myGdxGame.selectedBirdSkin)) {
            framesArray = new Texture[]{
                new Texture("birdTiles/default/bird0.png"),
                new Texture("birdTiles/default/bird1.png"),
                new Texture("birdTiles/default/bird2.png"),
                new Texture("birdTiles/default/bird1.png")
            };
        } if ("pigeon".equals(myGdxGame.selectedBirdSkin)) {
            framesArray = new Texture[]{
                new Texture("birdTiles/pigeon/1.png"),
                new Texture("birdTiles/pigeon/2.png"),
                new Texture("birdTiles/pigeon/3.png"),
                new Texture("birdTiles/pigeon/4.png")
            };
        }
        return framesArray;
    }

    public void updateHitboxes() {
        if ("ufo".equals(myGdxGame.selectedBirdSkin)) {
            bodyHitbox.set(
                x + width * 0.3f - 30,
                y + 15,
                width * 0.65f,
                height * 0.55f
            );

            headHitbox.set(
                x + width * 0.29f,
                y + height * 0.6f,
                width * 0.4f,
                height * 0.385f
            );
        } if ("default".equals(myGdxGame.selectedBirdSkin)){
            bodyHitbox.set(
                x + width * 0.2f,
                y,
                width * 0.6f,
                height * 0.6f
            );

            headHitbox.set(
                x + width * 0.5f + 15,
                y + height * 0.5f,
                width * 0.35f,
                height * 0.4f
            );
        } if ("pigeon".equals(myGdxGame.selectedBirdSkin)) {
            bodyHitbox.set(
                x + width * 0.1f,
                y + 15,
                width * 0.7f,
                height * 0.7f
            );
        }
    }
    public void debugDraw(ShapeRenderer debugRenderer) {
        debugRenderer.setColor(Color.RED);
        debugRenderer.rect(bodyHitbox.x, bodyHitbox.y, bodyHitbox.width, bodyHitbox.height);
        debugRenderer.setColor(Color.BLUE);
        debugRenderer.rect(headHitbox.x, headHitbox.y, headHitbox.width, headHitbox.height);
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
        updateHitboxes();
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
