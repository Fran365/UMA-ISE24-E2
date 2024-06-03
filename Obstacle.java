package bump.boat;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Obstacle {
    protected Texture texture;
    protected int x, y;
    protected float damage;
    protected int width, height;
    protected String name;
    //private HashMap<String, Texture> textures = new HashMap<String, Texture>();
    //HashMap should be created in the main game

    public Obstacle(HashMap<String, Texture> textures, int x, int y, float damage, Integer width, Integer height, String name) {
        this.texture = textures.get(name);
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.height = height;
        this.name = name;
        
        if(width != null) {
        	this.width = width;
        }
        else {
        	this.width = texture.getWidth();
        }
        
        if(height != null) {
        	this.height = height;
        }
        else {
        	this.height = texture.getHeight();
        }
        //this.textures = textures;
    }
    
    public Texture getTexture() {
    	return texture;
    }
    
    public void setTexture(String t) {
    	texture = new Texture(t);
    }
    
    public String getName() {
    	return name;
    }
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public float getDamage() {
    	return damage;
    }
    
    public void setY(int new_y) {
    	x = new_y;
    }
    
    public void setX(int new_x) {
    	x = new_x;
    }

  /*  public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }
    
    public void dispose() {
        texture.dispose();
    }*/
}
