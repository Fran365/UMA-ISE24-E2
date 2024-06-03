package bump.boat;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class Rock extends Obstacle {
	
	public Rock(HashMap<String, Texture> textures, int x, int y, int width, int heigth) {
		super(textures, x, y, (float) 3.0, width, heigth, "Rock");
	}
}
