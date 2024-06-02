public class Turbine extends PowerUp {

    public Turbine(String name) {
        super("Turbine");
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
