package package_bump_boat;

import com.badlogic.gdx.Game;

public class ShopMenu implements Screen {
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BitmapFont font;
    private Shop shop;
    private Coin coin;

    public ShopMenu(Game game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        font = new BitmapFont();
        shop = new Shop();
        coin = new Coin(200);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                int adjustedY = 480 - screenY;
                for (int i = 0; i < shop.getAvailablePowerUps().size(); i++) {
                    if (adjustedY > 400 - i * 40 && adjustedY < 440 - i * 40) {
                        PowerUp powerUp = shop.getAvailablePowerUps().get(i);
                        if (shop.purchasePowerUp(powerUp, coin)) {
                            System.out.println("Purchased: " + powerUp.getName());
                        } else {
                            System.out.println("Not enough coins for: " + powerUp.getName());
                        }
                    }
                }
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == com.badlogic.gdx.Input.Keys.ESCAPE) {
                    ScreenManager.getInstance().showScreen(ScreenManager.ScreenType.MAIN_MENU);
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
        font.draw(batch, "Coins: " + coin.getAmount(), 10, 470);
        for (int i = 0; i < shop.getAvailablePowerUps().size(); i++) {
            PowerUp powerUp = shop.getAvailablePowerUps().get(i);
            font.draw(batch, powerUp.getName() + " - Price: " + powerUp.getPrice() + " coins", 10, 440 - i * 40);
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
        font.dispose();
    }
}