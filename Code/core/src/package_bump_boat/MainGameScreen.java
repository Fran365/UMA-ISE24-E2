package package_bump_boat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import package_bump_boat.Player;

public class MainGameScreen extends ScreenAdapter {
    private SpriteBatch batch;
    private Array<Obstacle> obstacles;
    private Player player;

    @Override
    public void show() {
        batch = new SpriteBatch();
        obstacles = new Array<>();
        obstacles.add(new Obstacle("roca.png", 100, 150));
        obstacles.add(new Obstacle("roca.png", 300, 150));
        // Add more obstacles as needed

        player = new Player("player.png", 50, 50);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (Obstacle obstacle : obstacles) {
            obstacle.render(batch);
        }
        player.render(batch);
        batch.end();

        checkCollisions();
    }

    private void checkCollisions() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getBounds().overlaps(player.getBounds())) {
                System.out.println("Collision detected!");
                // Handle collision (e.g., stop player movement, end game, etc.)
            }
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        for (Obstacle obstacle : obstacles) {
            obstacle.dispose();
        }
        player.dispose();
    }
}
