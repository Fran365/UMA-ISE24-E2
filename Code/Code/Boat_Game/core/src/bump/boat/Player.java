package bump.boat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Texture texture;
    private float x, y;
    private Rectangle bounds;
    private playerBoat;
    public Player(String texturePath, float x, float y) {
        this.texture = new Texture(texturePath);
        this.x = x;
        this.y = y;
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        this.playerBoat = new Boat("Balanced", 75, 75, 75, 75, new Texture(Gdx.files.internal("balancedBoat.png")));
    }

    public void setPlayerBoat(Boat b) {
        playerBoat = b;
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
