package bump.boat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class Duck extends Obstacle {
	private String direction;
	private Lane[] lanes;
	private int laneNum;
	
	public Duck(HashMap<String, Texture> textures, int x, int y, int width, int heigth, String direction, Lane[] lanes, int laneNum) {
		super(textures, x, y, (float) 1.0, width, heigth, "Duck");
		this.direction = direction;
		this.laneNum = laneNum;
		this.lanes = lanes;
	}
	
	private void changeTexture(String new_texture) {
		super.setTexture(new_texture);
	}
	
	public void changeDirection() {
		HashMap <String, String> directions = new HashMap<>();
		
		directions.put("West", "East");
		directions.put("East", "West");
		
		direction = directions.get(direction);
		
		if(direction == "East") changeTexture("duckEast.jpg");
		else changeTexture("duckWest.jpg");
	}

	public void move(int moveValue, int backgroundOffset) {
		boolean canGoEast = false;
		boolean canGoWest = false;
		
		if(this.getX() > this.lanes[laneNum].getLeftBoundary() && this.getX() + this.getWidth() < this.lanes[laneNum].getRightBoundary()) {
			canGoEast = true;
			canGoWest = true;
		}
		else if (this.getX() <= this.lanes[laneNum].getLeftBoundary()) {
			canGoEast = true;
			canGoWest = false;
		}
		else if (this.getX() + this.getWidth() >= this.lanes[laneNum].getRightBoundary()) {
			canGoEast = false;
			canGoWest = true;
		}
		
		if(canGoEast && this.direction.equals("East")) {
			this.setX(this.getX() + moveValue);
		}
		if(canGoWest && this.direction.equals("West")) {
			this.setX(this.getX() - moveValue);
		}
		
		int randomMove = 20;
		if(new Random().nextInt(randomMove) == randomMove - 1) {
			changeDirection();
		}
	}
}
