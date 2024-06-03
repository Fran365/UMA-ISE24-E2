import bump.boat.PowerUp;

public class Shield extends PowerUp {

    public Shield(String name) {
        super("Shield");
    }

    @Override
    public void applyEffect(Boat boat) {
        boat.setSpeed(boat.getSpeed() + boat.getSpeed() * 0.2);
        boat.setAcceleration(boat.getAcceleration() + boat.getAcceleration() * 0.2);
    }

    @Override
    public void revertEffect(Boat boat) {
        boat.setSpeed(boat.getSpeed() - boat.getSpeed() * 0.2);
        boat.setAcceleration(boat.getAcceleration() - boat.getAcceleration() * 0.2);
    }
}
