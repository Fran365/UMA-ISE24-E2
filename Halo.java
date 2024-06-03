package bump.boat;
public class Halo extends PowerUp{

    public Halo(String name) {
        super("Halo");
    }

    @Override
    public void applyEffect(Boat boat) {
        if(boat.getRobustness() <= 0.0){
            boat.setDisqualified(false);
            boat.setRobustness(boat.getTotalRobustness() * 0.5);
        }
    }

    @Override
    public void revertEffect(Boat boat) {

    }
}
