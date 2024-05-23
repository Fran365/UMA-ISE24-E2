package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Code {
    public AssetManager manager;

    public Code() {
    	manager = new AssetManager();
    	manager.load("pantallapartida/carril.jpg", Texture.class);
    	manager.finishLoading();
    }
}