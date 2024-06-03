package bump.boat;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;

public class Log extends Obstacle{
	
	public Log(HashMap<String, Texture> textures, int x, int y, int width, int heigth) {
		super(textures, x, y, (float) 3.0, width, heigth, "Log");
	}

}
