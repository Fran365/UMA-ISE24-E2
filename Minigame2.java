package com.mygdx.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Minigame2 implements Screen {
    final Code code;
    private float screenWidth, screenHeight;
    private Texture background, fish, shark;
    private SpriteBatch batch;
    private BitmapFont font;
    private boolean hasWon, hasLost;
    private float delay, sharkX, fishX, acceleration, delay2;

    public Minigame2(final Code code) {
        this.code = code;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        hasWon = false;
        hasLost = false;
        acceleration = 1;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(0, 0, 0, 1);
        font.getData().setScale(5, 5);

        // Load textures
        fish = this.code.manager.get("fishHorizontal.png");
        background = this.code.manager.get("seaBackground.png");
        shark = this.code.manager.get("shark.png");

        sharkX = screenWidth / 30;
        fishX = screenWidth / 3 - 100;
    }

    @Override
    public void render(float delta) {
        delay += delta;

        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);

        if (!hasLost) {
            batch.draw(fish, fishX, screenHeight / 3 - 50, 150, 90);
        }

        batch.draw(shark, sharkX, screenHeight / 5, 420, 300);

        if (!hasLost) {
            // Fish movement
            if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
                fishX += 15;
            }
        }

        if (!hasWon) {
            // Shark movement
            if (delay > 3.0f) {
                sharkX += acceleration;
                acceleration += 0.008;
            }
        }

        // Collision detection
        // Shark catches the fish
        if (fishX <= sharkX + 250) {
            font.draw(batch, "YOU LOST", 100, screenHeight - 70);
            hasLost = true;
        }
        // Fish reaches the end
        if (fishX >= screenWidth) {
            font.draw(batch, "YOU WON", 100, screenHeight - 70);
            hasWon = true;
        }

        if (hasWon || hasLost) {
            delay2 += delta;
        }

        if (delay2 > 3f) {
            this.code.setScreen(new GameScreen(this.code)); // Assuming GameScreen is a transition or main menu screen
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Implement logic to handle screen resizing if necessary
    }

    @Override
    public void pause() {
        // Implement logic for game pause
    }

    @Override
    public void resume() {
        // Implement logic for game resume
    }

    @Override
    public void hide() {
        // Implement logic for when the screen is hidden
    }

    @Override
    public void dispose() {
        // Dispose resources to prevent memory leaks
        batch.dispose();
        font.dispose();
        background.dispose();
        fish.dispose();
        shark.dispose();
    }
}