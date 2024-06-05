package bump.boat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Boat extends Component implements KeyListener {
    public Texture texture;
    public double acceleration;
    public double maneuverability;
    public double robustness;
    public double totalRobustness;
    public double speed;
    public boolean disqualified;
    public int currentLane;
    public double[] lanes;
    public Rectangle bounds;
    private Timer movementTimer;
    private boolean isAIControlled;
    private MyGdxgame game;

    public Boat(String texturePath, double acceleration,double maneuverability,double robustness,double speed, boolean isAIControlled, MyGdxgame game) {
        this.texture = new Texture(texturePath);
        this.acceleration = acceleration;
        this.maneuverability = maneuverability;
        this.robustness = robustness;
        this.speed = speed;
        this.bounds = new Rectangle(0, 0, 50, 50);
        this.isAIControlled = isAIControlled;
        this.game = game;
        if (!isAIControlled) {
            addKeyListener(this);
            setFocusable(true);
        }

        // Start the forward movement timer
        startMovement();
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x, this.y);
    }

    public void dispose() {
        this.texture.dispose();
    }

    public double getSpeed() {
        return speed;
    }

    public double getTotalRobustness() {
        return totalRobustness;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getRobustness() {
        return robustness;
    }

    public void setRobustness(double robustness) {
        this.robustness = robustness;
    }

    public boolean isDisqualified() {
        return disqualified;
    }

    public void setDisqualified(boolean disqualified) {
        this.disqualified = disqualified;
    }

    public void applyPowerUp(PowerUp powerup){
        powerup.applyEffect(this);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                powerup.revertEffect(Boat.this);
            }
        }, 10000);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(int x, int y) {
        this.bounds.setLocation(x, y);
    }

    public void moveLeft() {
        if (bounds.x > 0) {
            bounds.translate(-10, 0); // Move left by 10 units
        }
    }

    public void moveRight() {
        if (bounds.x + bounds.width < getParent().getWidth()) {
            bounds.translate(10, 0); // Move right by 10 units
        }
    }

    public void collide(Lane lane) {
        for (Obstacle obstacle : lane.obstacles) {
            if (this.bounds.intersects(obstacle.getBounds())){
                this.acceleration *= 0.5; // Reduce acceleration by 50%
                this.speed *= 0.7; // Reduce speed by 30%
                this.robustness -= 10; // Reduce robustness by 10 units
                if (this.robustness <= 0) {
                    this.disqualified = true;
                    deleteBoat();
                }
            }
        }
    }

    private void deleteBoat() {
        //revisar volver al menu en la clase MyGdxGame
    }

    public void collideWithPowerUp(PowerUp powerup) {
        if (this.bounds.intersects(powerup.getBounds())) {
            applyPowerUp(powerup);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight();
        }
    }

    public void keyReleased(KeyEvent e) {}

    public void startMovement() {
        movementTimer = new Timer();
        movementTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!disqualified) {
                    speed += acceleration;
                    bounds.translate(0, (int) speed);
                    //render() llamado desde MyGame
                } else {
                    movementTimer.cancel();
                }
            }
        }, 0, 100); // Update every 100ms (10 times per second)
    }


}

