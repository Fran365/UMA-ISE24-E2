public abstract class PowerUp {
    String name;
    public PowerUp(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public abstract void applyEffect(Boat boat);
    public abstract void revertEffect(Boat boat);
}
