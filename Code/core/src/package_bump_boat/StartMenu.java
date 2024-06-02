package package_bump_boat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;

public class StartMenu implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont titleFont;
    private BitmapFont optionFont;
    private Texture backgroundImage;
    private String[] options;
    private int selectedOption;

    public StartMenu(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/riffic.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 36;
        titleFont = generator.generateFont(parameter);
        parameter.size = 24;
        optionFont = generator.generateFont(parameter);
        generator.dispose();
        backgroundImage = new Texture(Gdx.files.internal("assets/startMenu.png"));
        options = new String[]{"Start Game", "Ship Selection", "Shop"};
        selectedOption = 0;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case com.badlogic.gdx.Input.Keys.UP:
                        selectedOption = (selectedOption - 1 + options.length) % options.length;
                        break;
                    case com.badlogic.gdx.Input.Keys.DOWN:
                        selectedOption = (selectedOption + 1) % options.length;
                        break;
                    case com.badlogic.gdx.Input.Keys.ENTER:
                        handleSelection();
                        break;
                }
                return true;
            }
        });
    }

    private void handleSelection() {
        switch (selectedOption) {
            case 0:
                // Start the game
                break;
            case 1:
                ScreenManager.getInstance().showScreen(ScreenManager.ScreenType.SHIP_SELECTION);
                break;
            case 2:
                ScreenManager.getInstance().showScreen(ScreenManager.ScreenType.SHOP_MENU);
                break;
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroundImage, 0, 0, 800, 480);

        titleFont.draw(batch, "Bump Boat", 0, 400, 800, Align.center, false);

        for (int i = 0; i < options.length; i++) {
            if (i == selectedOption) {
                optionFont.setColor(1, 1, 0, 1); // Yellow color for selected option
            } else {
                optionFont.setColor(1, 1, 1, 1); // White color for unselected options
            }
            optionFont.draw(batch, options[i], 0, 300 - i * 50, 800, Align.center, false);
        }

        batch.end();
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
        titleFont.dispose();
        optionFont.dispose();
        backgroundImage.dispose();
    }
}