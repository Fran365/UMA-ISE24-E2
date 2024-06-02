package bump.boat

import com.badlogic.gdx.Screen;

public class ScreenManager {
    private static ScreenManager instance;
    private Game game;

    private ScreenManager() {
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void initialize(Game game) {
        this.game = game;
    }

    public void showScreen(ScreenType screenType) {
        Screen currentScreen = game.getScreen();
        if (currentScreen != null) {
            currentScreen.dispose();
        }
        Screen newScreen = screenType.getScreen(game);
        game.setScreen(newScreen);
    }

    public enum ScreenType {
        MAIN_MENU {
            @Override
            public Screen getScreen(Game game) {
                return new MainMenuScreen(game);
            }
        },
        SHIP_SELECTION {
            @Override
            public Screen getScreen(Game game) {
                return new ShipSelectionMenu(game);
            }
        },
        SHOP_MENU {
            @Override
            public Screen getScreen(Game game) {
                return new ShopMenu(game);
            }
        };

        public abstract Screen getScreen(Game game);
    }
}