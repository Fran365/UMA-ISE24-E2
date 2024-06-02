package bump.boat;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class Log extends Obstacle{
	
	public Log(HashMap<String, Texture> textures, float x, float y) {
		super(textures, x, y, (float) 2.0, null, null, "Log");
	}

}
