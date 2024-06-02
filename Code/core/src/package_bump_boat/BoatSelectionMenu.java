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

public class BoatSelectionMenu implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private List<Boat> boats;
    private int selectedBoatIndex;
    private Texture backgroundImage;
    private Player player;

    public BoatSelectionMenu(Game game, Player player) {
        this.player = player;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        backgroundImage = new Texture(Gdx.files.internal("assets/startMenu.png"));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/Nugo.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size() = 24;
        font = generator.generateFont(parameter);
        generator.dispose();

        boats = new ArrayList<>();
        boats.add(new Boat("Speedster", 90, 70, 50, 85, new Texture(Gdx.files.internal("assets/speedsterBoat.png"))));
        boats.add(new Boat("Tank", 60, 50, 90, 40, new Texture(Gdx.files.internal("assets/tankBoat.png"))));
        boats.add(new Boat("Balanced", 75, 75, 75, 75, new Texture(Gdx.files.internal("assets/balancedBoat.png"))));
        boats.add(new Boat("Agile", 80, 60, 60, 90, new Texture(Gdx.files.internal("assets/agileBoat.png"))));

        selectedBoatIndex = 0;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case com.badlogic.gdx.Input.Keys.LEFT:
                        selectedBoatIndex = (selectedBoatIndex - 1 + boats.size()) % boats.size();
                        break;
                    case com.badlogic.gdx.Input.Keys.RIGHT:
                        selectedBoatIndex = (selectedBoatIndex + 1) % boats.size();
                        break;
                    case com.badlogic.gdx.Input.Keys.ENTER:
                        player.setPlayerBoat(boats[selectedBoatIndex]);
                        break;
                    case com.badlogic.gdx.Input.Keys.ESCAPE:
                        ScreenManager.getInstance().showScreen(ScreenManager.ScreenType.MAIN_MENU);
                        break;
                }
                return true;
            }
        });
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

        Boat selectedBoat = boats.get(selectedBoatIndex);

        font.draw(batch, "Select your boat:", 350, 450);
        batch.draw(selectedBoat.getImage(), 350, 200);

        font.draw(batch, "Name: " + selectedBoat.getName(), 50, 150);
        font.draw(batch, "Speed: " + selectedBoat.getSpeed(), 50, 120);
        font.draw(batch, "Acceleration: " + selectedBoat.getAcceleration(), 50, 90);
        font.draw(batch, "Robustness: " + selectedBoat.getRobustness(), 50, 60);
        font.draw(batch, "Maneuverability: " + selectedBoat.getManeuverability(), 50, 30);

        drawBar(batch, 200, 120, selectedBoat.getSpeed());
        drawBar(batch, 200, 90, selectedBoat.getAcceleration());
        drawBar(batch, 200, 60, selectedBoat.getRobustness());
        drawBar(batch, 200, 30, selectedBoat.getManeuverability());

        batch.end();
    }

    private void drawBar(SpriteBatch batch, float x, float y, int value) {
        Texture barTexture = new Texture(Gdx.files.internal("bar.png"));
        batch.draw(barTexture, x, y, value, 10);
        barTexture.dispose(); // Dispose the texture after drawing to free up resources
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
        for (Boat boat : boats) {
            boat.getImage().dispose();
        }
    }
}