package bump.boat;

import com.badlogic.gdx.graphics.Texture;

public class Boat {
    private String name;
    private int speed;
    private int acceleration;
    private int robustness;
    private int maneuverability;
    private Texture image;

    public Boat(String name, int speed, int acceleration, int robustness, int maneuverability, Texture image) {
        this.name = name;
        this.speed = speed;
        this.acceleration = acceleration;
        this.robustness = robustness;
        this.maneuverability = maneuverability;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public int getRobustness() {
        return robustness;
    }

    public int getManeuverability() {
        return maneuverability;
    }

    public Texture getImage() {
        return image;
    }
}