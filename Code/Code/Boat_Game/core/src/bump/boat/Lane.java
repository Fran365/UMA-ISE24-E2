package bump.boat;

import java.util.*;

import com.mygdx.*;

import com.mygdx.game.*;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Lane {
	private int LeftBoundary, RightBoundary;
	//private int numOfCompetitors;
	private int maxNumObstacles;
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	
	/*private Vector2 path_1;
	private Vector2 path_2;
	private Vector2 path_3;
	private Vector2 path_4;
	private Vector2 path_5;*/
	
	private OrthographicCamera camera;
	private Code code;
	private Lane[] lanes;
	private int laneNum;
	
	public Lane(int LeftBoundary, int RightBoundary, int maxNumObstacles) {
		this.LeftBoundary = LeftBoundary;
		this.RightBoundary = RightBoundary;
		this.maxNumObstacles = maxNumObstacles;
		laneNum = 0;
		obstacles = new ArrayList<Obstacle>();
	}
	
	public Lane(int noc, int ln, OrthographicCamera oc, int laneNum, Lane[] lanes, int LeftBoundary, int RightBoundary, int maxNumObstacles) {//, Code c) {
		this.LeftBoundary = LeftBoundary;
		this.RightBoundary = RightBoundary;
		this.maxNumObstacles = maxNumObstacles;
		//numOfCompetitors = noc;
		camera = oc;
		this.laneNum = laneNum;
		//code = c;
		/*path_1 = new Vector2(-laneWidth * 2, camera.position.y);
		path_2 = new Vector2(-laneWidth, camera.position.y);
		path_3 = new Vector2(0,camera.position.y);
		path_4 = new Vector2(laneWidth, camera.position.y);
		path_5 = new Vector2(laneWidth * 2, camera.position.y);*/
	}
	
	public int getLeftBoundary() {
		return LeftBoundary;
	}
	
	public int getRightBoundary() {
		return RightBoundary;
	}
	
	public void spawnObstacle(HashMap<String, Texture> textures, int x, int y, String obstacleType) throws Exception {
		String[] possibleDirs = {"East", "West"};
		Random rnd = new Random();
		Obstacle toAdd = null;
		switch(obstacleType) {
			case("Rock"):
				toAdd = new Rock(textures, x, y);
			case("Log"):
				toAdd = new Log(textures, x, y);
			case("Duck"):
				toAdd = new Duck(textures, x, y, possibleDirs[rnd.nextInt(2)], lanes, laneNum);
		}
		if(toAdd == null) throw new Exception("The name inserted doesn't identify a valid obstacle");
		obstacles.add(toAdd);
	}
}
	
	/*public void update() {
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
		batch.begin();
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
		batch.end();
	}*/
	
	

