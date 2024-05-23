package package_bump_boat;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Code;

public class Leg {
	private int laneWidth;
	private int numOfCompetitors;
	private int legNum;
	private Vector2 path_1;
	private Vector2 path_2;
	private Vector2 path_3;
	private Vector2 path_4;
	private Vector2 path_5;
	private OrthographicCamera camera;
	private Code code;
	
	public Leg(int laneW, int noc, int ln, OrthographicCamera oc, Code c) {
		laneWidth = laneW;
		numOfCompetitors = noc;
		legNum = ln;
		camera = oc;
		code = c;
		path_1 = new Vector2(-laneWidth * 2, camera.position.y);
		path_2 = new Vector2(-laneWidth, camera.position.y);
		path_3 = new Vector2(0,camera.position.y);
		path_4 = new Vector2(laneWidth, camera.position.y);
		path_5 = new Vector2(laneWidth * 2, camera.position.y);
		
	}
	
	public void update() {
		path_1 = new Vector2(path_1.x,camera.position.y);
		path_2 = new Vector2(path_2.x,camera.position.y);
		path_3 = new Vector2(path_3.x,camera.position.y);
		path_4 = new Vector2(path_4.x,camera.position.y);
		path_5 = new Vector2(path_5.x,camera.position.y);
	}
	
	public Vector2 obtainMiddle(int path) {
		Vector2 pos = new Vector2(0,0);
		switch(path) {
		case 1:
			pos = new Vector2((path_1.x + path_2.x)/2, path_1.y);
			break;
		case 2:
			pos = new Vector2((path_2.x + path_3.x)/2, path_1.y);
			break;
		case 3:
			pos = new Vector2((path_3.x + path_4.x)/2, path_1.y);
			break;
		case 4:
			pos = new Vector2((path_4.x + path_5.x)/2, path_1.y);
			break;
		}
		return pos;
	}
	
	public Vector2 obtainPath(int path) {
		Vector2 pos = new Vector2(0,0);
		switch(path) {
		case 1:
			pos = new Vector2(path_1.x, path_1.y);
			break;
		case 2:
			pos = new Vector2(path_2.x, path_2.y);
			break;
		case 3:
			pos = new Vector2(path_3.x, path_3.y);
			break;
		case 4:
			pos = new Vector2(path_4.x, path_4.y);
			break;
		case 5:
			pos = new Vector2(path_5.x, path_5.y);
			break;
		}
		return pos;
	}
	
	public void draw(SpriteBatch batch) {
		Texture text = this.code.manager.get("pantallapartida/carril.jpg", Texture.class);
		Sprite barrier_1 = new Sprite(text,29,1079);
		Sprite barrier_2 = new Sprite(text,29,1079);
		Sprite barrier_3 = new Sprite(text,29,1079);
		Sprite barrier_4 = new Sprite(text,29,1079);
		Sprite barrier_5 = new Sprite(text,29,1079);
		
		
		barrier_1.setScale(0.1f);
		barrier_2.setScale(0.1f);
		barrier_3.setScale(0.1f);
		barrier_4.setScale(0.1f);
		barrier_5.setScale(0.1f);
		
		
		barrier_1.setCenter(path_1.x, path_1.y);
		barrier_2.setCenter(path_2.x, path_2.y);
		barrier_3.setCenter(path_3.x, path_3.y);
		barrier_4.setCenter(path_4.x, path_4.y);
		barrier_5.setCenter(path_5.x, path_5.y);
		
		
		barrier_1.draw(batch);
		barrier_2.draw(batch);
		barrier_3.draw(batch);
		barrier_4.draw(batch);
		barrier_5.draw(batch);
	}
}

