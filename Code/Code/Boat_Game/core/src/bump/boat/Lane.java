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
		camera = oc;
		this.laneNum = laneNum;
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
	
	
	
	

