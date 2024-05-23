package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Code;

import package_bump_boat.Leg;
import package_bump_boat.MyGdxGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
/*public class DesktopLauncher {
	public static void main(String[] args){
		/*Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setFullscreenMode(config.getDisplayMode());
		config.setTitle("Dragon Boat Racing");
		config.setWindowIcon("Icono.png");
		new Lwjgl3Application(new Code(), config);
		
		int laneWidth = 5;
		int numOfCompetitors = 3;
		int legNum = 1;
		OrthographicCamera camera = new OrthographicCamera();
		Code code = new Code();
		Leg l1 = new Leg(laneWidth, numOfCompetitors, legNum, camera);
	}
}*/

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("LibGDX");
		new Lwjgl3Application(new MyGdxGame(), config);
	}
}

