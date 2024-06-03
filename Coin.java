package bump.boat;

public class Coin {
    private int amount;

    public Coin(int initialAmount) {
        this.amount = initialAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void addCoins(int amount) {
        this.amount += amount;
    }

    public boolean spendCoins(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
            return true;
        } else {
            return false;
        }
    }
}