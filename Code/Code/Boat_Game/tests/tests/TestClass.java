package tests;

import boat.game.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestClass {

    private Boat boat;
    private Boat anotherBoat;
    private Log log;
    private Rock rock;
    private Duck duck;

    @Before
    public void setUp() {
        boat = new Boat("playerBoat.png", 10.0, 10.0, 10.0, 10.0, false);
        GameScreen gameScreen = new GameScreen();
        anotherBoat = new Boat("playerBoat.png", 15.0, 15.0, 15.0, 15.0, false);
        String direction = "West";
        OrthographicCamera oc = new OrthographicCamera();
        Lane[] lanes = new Lane[2];
        GameScreen gs = new GameScreen();

        log = new Log(gameScreen.textures, 50, 70, 1024, 1024);
        rock = new Rock(gameScreen.textures, 50, 70, 1024, 1024);
        duck = new Duck(gameScreen.textures, 50, 70, 1024, 1024, direction, gs.lanes, 0);
    }

    @Test
    public void testTC001() {
        Boat boat1 = this.boat;
        Boat boat2 = this.anotherBoat;
        Assert.assertNotEquals(boat1.acceleration, boat2.acceleration);
        Assert.assertNotEquals(boat1.speed, boat2.speed);
        Assert.assertNotEquals(boat1.robustness, boat2.robustness);
        Assert.assertNotEquals(boat1.maneuverability, boat2.maneuverability);
    }


    @Test
    public void testTC002() throws InterruptedException {
        GameScreen gameScreen = new GameScreen();
        gameScreen.startNewLeg();
        double initialSpeed = boat.getSpeed();
        Thread.sleep(5000); // Simula il passare del tempo
        Assert.assertTrue(initialSpeed > boat.getSpeed());
    }

    @Test
    public void testTC003() throws InterruptedException {
        double initialRobustness = boat.getRobustness();
        GameScreen gameScreen = new GameScreen();
        gameScreen.collision(boat, initialRobustness, log);
        Assert.assertEquals(initialRobustness - 2, boat.getRobustness());
    }

    @Test
    public void testTC004() {
        double initialRobustness = boat.getRobustness();
        GameScreen gameScreen = new GameScreen();
        gameScreen.collision(boat, initialRobustness, rock);
        Assert.assertEquals(initialRobustness - 3, boat.getRobustness());
    }

    @Test
    public void testTC005() {
        double initialRobustness = boat.getRobustness();
        GameScreen gameScreen = new GameScreen();
        gameScreen.collision(boat, initialRobustness, duck);
        Assert.assertEquals(initialRobustness - 1, boat.getRobustness());
    }

    @Test
    public void testTC006() throws InterruptedException {
        int initialX = duck.getX();
        int initialY = duck.getY();
        Thread.sleep(5000); // Simula il passare del tempo
        Assert.assertTrue(initialX != duck.getX() || initialY != duck.getY());
    }

    @Test
    public void testTC007() {
        boat.setRobustness(3);
        GameScreen gameScreen = new GameScreen();
        gameScreen.collision(boat, boat.getRobustness(), rock);
        Assert.assertTrue(gameScreen.legLost(boat));
    }

    @Test
    public void testTC008() {
        GameScreen gameScreen = new GameScreen();
        gameScreen.setLegWon(true);
        Assert.assertTrue(gameScreen.minigameStarted);
    }

    @Test
    public void testTC09() throws InterruptedException {
        double initialSpeed = boat.getSpeed();
        PowerUp pu = new Turbine(boat);
        pu.applyEffect(boat);
        Thread.sleep(1000); // Aspetta un secondo per vedere l'effetto
        Assert.assertTrue(boat.getSpeed() > initialSpeed);
        Thread.sleep(5000); // Aspetta che l'effetto finisca
        Assert.assertEquals(initialSpeed, boat.getSpeed());
    }

    @Test
    public void testTC010() {
        double initialRobustness = boat.getRobustness();
        PowerUp pu = new Shield(boat);
        pu.applyEffect(boat);
        GameScreen gameScreen = new GameScreen();
        gameScreen.collision(boat, initialRobustness, rock);
        Assert.assertEquals(initialRobustness, boat.getRobustness());
    }

    @Test
    public void testTC011() {
        double initialRobustness = boat.getRobustness();
        boat.setRobustness(1);
        PowerUp pu = new Halo(boat);
        pu.applyEffect(boat);
        Assert.assertEquals(initialRobustness / 2, boat.getRobustness());
    }

    @Test
    public void testTC012() {
        double initialSpeed = boat.getSpeed();
        GameScreen gameScreen = new GameScreen();
        gameScreen.setLegWon(true);
        Assert.assertTrue(boat.getSpeed() > initialSpeed);
    }

    @Test
    public void testTC013() {
        GameScreen gameScreen = new GameScreen();
        Lane currLane = gameScreen.lanes[0];
        boat.setPosition(gameScreen.lanes[0].getRightBoundary() + 1, 10);
        Assert.assertTrue(gameScreen.legLost(boat));
    }

    @Test
    public void testTC014() {
        GameScreen gameScreen = new GameScreen();
        gameScreen.startNewLeg();
        boat.setRobustness(3);
        gameScreen.collision(boat, boat.getRobustness(), rock);
        Assert.assertTrue(gameScreen.legLost(boat));
    }

}
