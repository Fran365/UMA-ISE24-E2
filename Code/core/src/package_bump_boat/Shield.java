public class Shield extends PowerUp {

    public Shield(String name) {
        super("Shield");
    }

    @Override
    public void applyEffect(Boat boat) {
        boat.setRobustness(boat.getRobustness() + boat.setRobustness() * 0.2);
    }

    @Override
    public void revertEffect(Boat boat) {
        boat.setRobustness(boat.getRobustness() - boat.getRobustness() * 0.2);
    }
}
