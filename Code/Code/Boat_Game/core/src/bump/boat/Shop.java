package bump.boat;


import java.util.ArrayList;
import java.util.List;

public class Shop {
    private List<PowerUp> powerUps;

    public Shop() {
        powerUps = new ArrayList<>();
        powerUps.add(new PowerUp("Speed Boost", 50));
        powerUps.add(new PowerUp("Shield", 75));
        powerUps.add(new PowerUp("Double Damage", 100));
    }

    public List<PowerUp> getAvailablePowerUps() {
        return powerUps;
    }

    public boolean purchasePowerUp(PowerUp powerUp, Coin coin) {
        if (coin.spendCoins(powerUp.getPrice())) {
            // Aquí se podría agregar lógica para dar el power-up al jugador.
            return true;
        } else {
            return false;
        }
    }
}