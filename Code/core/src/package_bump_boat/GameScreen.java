package package_bump_boat;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Main Game Screen class for Dragon Boat Game. This is the main game loop,
 * handling all the game logic and rendering.
 */
public class GameScreen implements Screen {
	
	private SpriteBatch batch = new SpriteBatch();
	private Texture background = new Texture("mainMenuBackground.jpg");
	
	private final int WIDTH = 1080, HEIGHT = 720;
	
	private int backgroundOffset = 0;
	private boolean started = false;
	private Lane[] lanes;
	
	public GameScreen(MyGdxGame game, boolean loaded) {
		super();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		/*
         * Display background.
         */
        batch.begin();
        batch.draw(background, 0, 0, 0, background.getHeight() - HEIGHT - backgroundOffset, WIDTH, HEIGHT);
        batch.end();

        /*
         * Display obstacles
         */
        for (Lane lane : lanes) {
            if (!started)
                break;
            for (int j = 0; j < lane.obstacles.size(); j++) {
                Obstacle o = lane.obstacles.get(j);
                batch.begin();
                batch.draw(o.getTexture(), o.getX(), o.getY());
                batch.end();
            }
        }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
 
	
}
