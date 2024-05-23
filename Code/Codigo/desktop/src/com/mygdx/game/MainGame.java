package com.mygdx.game;
import package_bump_boat.MainGameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import package_bump_boat.MainGameScreen;

public class MainGame extends Game {

    @Override
    public void create() {
        this.setScreen(new MainGameScreen());
    }

    @Override
    public void render() {
        super.render(); // This will call the render method of the active screen
    }

    @Override
    public void dispose() {
        getScreen().dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        //config.setWindowIcon("LibGDX");
        config.setWindowedMode(800, 600);
        
        new Lwjgl3Application(new MainGame(), config);
    }
}
