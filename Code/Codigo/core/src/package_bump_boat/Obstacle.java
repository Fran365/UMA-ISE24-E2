package package_bump_boat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Obstacle {
    private Texture texture;
    private float x, y;
    private Rectangle bounds;

    public Obstacle(String texturePath, float x, float y) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
