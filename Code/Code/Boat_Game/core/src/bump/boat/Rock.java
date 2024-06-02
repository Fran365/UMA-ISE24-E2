package bump.boat;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class Rock extends Obstacle {
	
	public Rock(HashMap<String, Texture> textures, float x, float y) {
		super(textures, x, y, (float) 3.0, null, null, "Rock");
	}
}
