package com.mygdx.code;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;

public class Minigame implements Screen {
    final Code code;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private ProgressBar progressBar;
    private float screenWidth, screenHeight;
    private Texture background, boat, coin;
    private boolean gameWon;
    private int score, maxScore;
    private String scoreText;

    public Minigame(final Code code) {
        this.code = code;
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();
        maxScore = 10; // Maximum coins
        score = 0;
        gameWon = false;
    }

    @Override
    public void show() {
        stage = new Stage();
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2, 2);

        // Load textures
        background = new Texture("textures.png");
        boat = new Texture("boat.png");
        coin = new Texture("coin.png");

        setupProgressBar();
    }

    private void setupProgressBar() {
        ProgressBarStyle progressBarStyle = new ProgressBarStyle();
        progressBarStyle.background = new TextureRegionDrawable(new Texture("minigame/progress_bg.png"));
        progressBarStyle.knob = new TextureRegionDrawable(new Texture("minigame/progress_knob.png"));
        progressBarStyle.knobBefore = new TextureRegionDrawable(new Texture("minigame/progress_filled.png"));

        progressBar = new ProgressBar(0, maxScore, 1, false, progressBarStyle);
        progressBar.setBounds(50, screenHeight - 50, screenWidth - 100, 20);
        stage.addActor(progressBar);
    }

    @Override
    public void render(float delta) {
        handleInput();
        updateGameLogic(delta);
        drawGame();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Keys.SPACE) && !gameWon) {
            score++;
            progressBar.setValue(score);
            if (score >= maxScore) {
                gameWon = true;
            }
        }
    }

    private void updateGameLogic(float delta) {
        // Game logic updates, such as moving coins or the boat
    }

    private void drawGame() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, screenWidth, screenHeight);
        batch.draw(boat, screenWidth / 2 - 40, 50, 80, 120);
        font.draw(batch, "Score: " + score, 100, screenHeight - 20);
        batch.end();
        stage.act(delta);
        stage.draw();
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
        batch.dispose();
        font.dispose();
        background.dispose();
        boat.dispose();
        coin.dispose();
        stage.dispose();
    }
}
****