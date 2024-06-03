package bump.boat;
public abstract class PowerUp {
    String name;
    Rectangle bounds;
    public Texture texture;

    public PowerUp(String name, String texturePath){
        this.name = name;
        this.texture = new Texture(texturePath);
        this.bounds = new Rectangle(0, 0, 20, 20);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setPosition(int x, int y) {
        this.bounds.setLocation(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.texture, this.x, this.y);
    }

    public void dispose() {
        this.texture.dispose();
    }


    public String getName(){
        return this.name;
    }
    public abstract void applyEffect(Boat boat);
    public abstract void revertEffect(Boat boat);
}
