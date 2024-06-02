package bump.boat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import l_y_p.*;
/**
 * Main Game Screen class for Dragon Boat Game. This is the main game loop,
 * handling all the game logic and rendering.
 */
public class GameScreen implements Screen {
	Random random = new Random();
	static final int OBS_WIDTH = 1080;
	static final int OBS_HEIGHT = 720;
	static final int LENGTH = 20000;
	static final int NUM_LANES = 3;
	static final float relation = (Gdx.graphics.getWidth()/Gdx.graphics.getHeight())+15;
	static final float STEP_TIME = 1f/60f;
	static int numObstacles = 20;
	static int numPowerUps = 4;
	float accumulator = 0;
	private int ids=0;
	//private float time = 0;
	World physics;
	Boat player;
	ArrayList<Boat> AI_Boats = new ArrayList<Boat>();
	ArrayList<AI> AIs = new ArrayList<AI>();
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	BoatType selectedType;
	OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer;
	BackgroundGame background;
	Lane[] lanes;
	ArrayList<Body> toDelete = new ArrayList<Body>();
	Body finishLine;
	Vector2 playerPos;
	int powerUpsCount = 0;
	int difficulty = 0;
	
	HashMap<String, Texture> textures= new HashMap<String, Texture>();
	String[] possibleObstacles = {"DuckEast", "DuckWest", "Log", "Rock"};
	
	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();
	Stage stage = new Stage();
	
	public enum State {
		RUNNING,
		PAUSE
	}
	
	private State state;
	
	public GameScreen() {
		this.lanes = new Lane[NUM_LANES];
		
		textures.put("DuckEast", new Texture("duckEast.jpg"));
		textures.put("DuckWest", new Texture("duckWest.jpg"));
		textures.put("Log", new Texture("log.jpg"));
		textures.put("Rock", new Texture("rock.jpg"));
		state = State.RUNNING;
	}
	
	private Body createBody(Vector2 position, BodyType type, float density, float friction, float restitution, boolean sensor, Vector2 size) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(position);
		Body body = physics.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(size.x/relation, size.y/relation);
		fixtureDef.shape = poly;
		fixtureDef.density = density;
		fixtureDef.friction = friction;
		fixtureDef.restitution = restitution;
		fixtureDef.isSensor = sensor;
		body.createFixture(fixtureDef);
		poly.dispose();
		return body;
	}
	
	@Override
	public void show() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth()/relation, Gdx.graphics.getHeight()/relation);  
		background = new BackgroundGame(relation, camera);
		
		for(int i = 0; i < NUM_LANES; i++) {
			int numOfObstacles = 8 * difficulty + random.nextInt(5);
			if(i == 0) {
				lanes[i] = new Lane(LENGTH, 0, Gdx.graphics.getWidth() / lanes.length, numOfObstacles);
			} else {
				int LeftBoundary = i * Gdx.graphics.getWidth() / lanes.length;
				int RightBoundary = (i + 1) * Gdx.graphics.getWidth() / lanes.length;
				lanes[i] = new Lane(LENGTH, camera, i, lanes, LeftBoundary, RightBoundary, numOfObstacles);
			}
		}

		Box2D.init();
		physics = new World(new Vector2(0, 0), true);
		physics.setContactListener(new Collisions(toDelete, difficulty));
		
		finishLine = createBody(new Vector2(0, 1200), BodyType.StaticBody, 0.2f, 1f, 0.6f, true, new Vector2(Gdx.graphics.getWidth(), 100));
		Sprite finishSprite = new Sprite(new Texture("pantallapartida/Meta.jpg"), 745, 114);
		finishSprite.setScale(3f/relation);
		finishLine.setUserData(new UserData(finishSprite, ids));
		ids++;
		
		int numLane = 0;
		for(Lane lane: lanes) {
			for(int i = 0; i < lane.getNumOfObstacles(); i++) {
				String name = possibleObstacles[random.nextInt(4)];
				if(name == "duckEast") {
					name = "Duck";
				}
				if(name == "duckWest") {
					name = "Duck";
				}
				int x = random.nextInt(lane.getLeftBoundary(), lane.getRightBoundary());
				int y = random.nextInt(LENGTH);
				lane.spawnObstacle(textures, x, y, name, OBS_WIDTH, OBS_HEIGHT);	
			}
			numLane++;
		}
		
		Sprite boatSprite = new Sprite(this.code.manager.get(this.code.selectedBoatType.boat, Texture.class), 1024, 1024);
		boatSprite.setScale(0.20f/relation);
		playerPos = new Vector2(0, 0);
		Body playerBoat = createBody(playerPos, BodyType.DynamicBody, 0.2f, 1f, 0.6f, false, new Vector2(25, 85));
		player = new Boat(this.code.selectedBoatType, playerBoat);
		playerBoat.setUserData(new UserData(boatSprite, ids, player));
		ids++;
		
		for(int i = 0; i < 3; i++) {
			Body AIBoat = createBody(new Vector2(10 + 10 * i, 0), BodyType.DynamicBody, 0.2f, 1f, 0.6f, false, new Vector2(25, 85));
			AIBoat.setTransform(lanes.obtainMiddle(i + 2), AIBoat.getAngle());
			BoatType aiType = this.code.boatTypes.get((int) (this.code.boatTypes.size() * Math.random()));
			Boat aiBoat = new Boat(aiType, AIBoat, true);
			AI ai = new AI(this.code.difficulty, aiType);
			boatSprite = new Sprite(this.code.manager.get(aiType.boat, Texture.class), 1024, 1024);
			boatSprite.setScale(0.20f/relation);
			AIBoat.setUserData(new UserData(boatSprite, ids, aiBoat));
			AI_Boats.add(aiBoat);
			AIs.add(ai);
			ids++;
		}
		
		playerBoat.setTransform(lanes.obtainMiddle(1), playerBoat.getAngle());
	}

	@Override
	public void render(float delta) {
		switch (state) {
			case RUNNING:
				if(this.code.playerWon && this.code.wins == 4) {
					this.code.finished = 0;
					this.code.playerWon = false;
					this.code.difficulty = 1;
					this.code.music.stop();
					this.code.music = this.code.manager.get("musica/fuerapartida.ogg");
					this.code.music.setVolume((float)Math.pow(this.code.volume, 2));
					this.code.music.play();
					this.code.setScreen(new MainMenuScreen(this.code));
				} else if(this.code.playerWon) {
					this.code.difficulty++;
					this.code.finished = 0;
					this.code.playerWon = false;
					double rnd = Math.random();
					if(rnd < 1 && rnd >= 0.66) {
						this.code.music.stop();
						this.code.music = this.code.manager.get("musica/minijuego1.ogg");
						this.code.music.setVolume((float)Math.pow(this.code.volume, 2));
						this.code.music.play();
						this.code.setScreen(new Minigame(this.code));
					} else if(rnd < 0.66f && rnd >= 0.33) {
						this.code.music.stop();
						this.code.music = this.code.manager.get("musica/minijuego2.ogg");
						this.code.music.setVolume((float)Math.pow(this.code.volume, 2));
						this.code.music.play();
						this.code.setScreen(new Minigame2(this.code));
					} else if(rnd < 0.33f) {
						this.code.music.stop();
						this.code.music =

 this.code.manager.get("musica/minijuego1.ogg");
						this.code.music.setVolume((float)Math.pow(this.code.volume, 2));
						this.code.music.play();
						this.code.setScreen(new Minigame3(this.code));
					}
				}
				
				if(this.code.finished == 3) {
					this.code.finished = 0;
					this.code.playerWon = false;
					this.code.difficulty = 1;
					this.code.music.stop();
					this.code.music = this.code.manager.get("musica/fuerapartida.ogg");
					this.code.music.setVolume((float)Math.pow(this.code.volume, 2));
					this.code.music.play();
					this.code.setScreen(new MainMenuScreen(this.code));
				}

				if(player.applied) {
					player.time += 100 * delta;
					if(player.time > 1000) {
						player.time = 0;
						player.applied = false;
						player.resetStats();
					}
				}
			
				ScreenUtils.clear(1, 1, 1, 1);
				if(Gdx.input.isKeyPressed(code.moveLeft)) {
					player.turnLeft();
				}
				if(Gdx.input.isKeyPressed(code.moveRight)) {
					player.turnRight();
				}
				if(Gdx.input.isKeyPressed(code.moveUp)) {
					player.accelerate();
				}
				if(Gdx.input.isKeyPressed(code.brake)) {
					player.brake();
				}
				if(!(Gdx.input.isKeyPressed(code.moveLeft)) && 
				   !(Gdx.input.isKeyPressed(code.moveRight)) &&
				   !(Gdx.input.isKeyPressed(code.moveUp)) &&
				   !(Gdx.input.isKeyPressed(code.brake))) {
					player.tiredness += 0.16666f * delta;
				}
				for(int i = 0; i < this.AI_Boats.size(); i++) {
					AI ai = this.AIs.get(i);
					Boat aiBoat = this.AI_Boats.get(i);
					float[] action = ai.getAction(aiBoat.body.getPosition(), aiBoat.body.getAngle(), obstacles, lanes.obtainMiddle(i + 2), new Vector2(10, 10), aiBoat.body.getAngularVelocity(), aiBoat.body.getLinearVelocity());
					if(action[1] == 0) {
						aiBoat.forceAI = action[2];
						aiBoat.turnRight();
					}
					if(action[1] == 1) {
						aiBoat.forceAI = action[2];
						aiBoat.turnLeft();
					}
					if(action[1] == 2) {
						float angle = aiBoat.body.getAngle();
						float newAngle = 0;
						if(Math.abs(angle) > 0.01f) {
							if(angle > 0) {
								newAngle = angle - 0.007f;
							} else {
								newAngle = angle + 0.007f;
							}
						} else {
							newAngle = angle;
						}
						aiBoat.body.setTransform(aiBoat.body.getPosition(), newAngle);
					}
					if(action[0] == 1) {
						aiBoat.accelerate();
					}
					if(action[0] == 0) {
						aiBoat.brake();
					}
				}
				if(Gdx.input.isKeyPressed(Keys.SPACE) && !player.applied) {
					PowerUp power = player.usePowerUp();
					if(power != null) {
						player.applied = true;
						player.selected.acceleration += power.acceleration;
						player.selected.mobility += power.mobility;
						player.health += power.healing;
						if(player.health > player.selected.maxHealth * Boat.baseMaxHealth) {
							player.health = player.selected.maxHealth * Boat.baseMaxHealth;
						}
						player.tiredness += power.tiredness;
						if(player.tiredness > 100) {
							player.tiredness = 100;
						}
						player.power = null;
					}
				}
				if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
					state = State.PAUSE;
				}
				
				Vector2 pos2 = player.body.getPosition();
				camera.position.set(new Vector3(camera.position.x, pos2.y + 20, 0));
				camera.update();
				Array<Body> bodies = new Array<Body>();
				physics.getBodies(bodies);
				Iterator<Body> iter = bodies.iterator();
				Body body;
				Sprite sprite;
				background.animate(delta);
				lanes.update();
				this.code.batch.setProjectionMatrix(camera.combined);
				this.code.batch.begin();
				background.draw(this.code.batch);
				lanes.draw(this.code.batch);
				while (iter.hasNext()) {
					body = iter.next();
					UserData data = (UserData) body.getUserData();
					sprite = (Sprite) data.photo;
					Vector2 pos = body.getPosition();
					if(data.type == 1 && !data.boat.isAI) {
						playerPos = new Vector2(pos.x, pos.y);
					}
					if(data.type == 1 && data.boat.isAI && pos.y > (camera.viewportHeight + playerPos.y) + camera.viewportHeight / 2) {
						Vector2 velocity = body.getLinearVelocity();
						Vector2 velCopy = new Vector2(velocity.x, velocity.y);
						velCopy.rotateDeg(180);
						velCopy.scl(0.8f);
						body.applyForce(velCopy, pos, true);
					}
					if(data.type == 1 && (data.id == 0 || data.id == 1 || data.id == 2 || data.id == 3)) {
						Vector2 rightLanePos = lanes.obtainLane(data.id + 2);
						Vector2 leftLanePos = lanes.obtainLane(data.id + 1);
						if(pos.x > rightLanePos.x || pos.x < leftLanePos.x) {
							Vector2 velocity = body.getLinearVelocity();
							Vector2 velCopy = new Vector2(velocity.x, velocity.y);
							velCopy.rotateDeg(180);
							velCopy.scl(0.8f);
							body.applyForce(velCopy, pos, true);
						}
					}
					if((data.type == 3 || data.type == 2) && (pos.x < playerPos.x - camera.viewportWidth || pos.x > playerPos.x + camera.viewportWidth || pos.y < playerPos.y - camera.viewportHeight)) {
						if(!this.toDelete.contains(body)) {
							this.toDelete.add(body);
						}
					}
					if(body.getPosition().x < -camera.viewportWidth / 2) {
						body.setTransform(-camera.viewportWidth / 2, pos.y, body.getAngle());
						body.setLinearVelocity(new Vector2(0, body.getLinearVelocity().y));
					}
					if(body.getPosition().x > camera.viewportWidth / 2) {
						body.setTransform(camera.viewportWidth / 2, pos.y, body.getAngle());
						body.setLinearVelocity(new Vector2(0, body.getLinearVelocity().y));
					}
					sprite.setRotation((float)Math.toDegrees(body.getAngle()));
					sprite.setCenter(pos.x, pos.y);
					sprite.draw(this.code.batch);
				}
				this.code.batch.end();
				accumulator += Math.min(delta, 0.25f);

				if (accumulator >= STEP_TIME) {
					accumulator -= STEP_TIME;
					physics.step(STEP_TIME, 6, 2);
				}
				ArrayList<Body> aux = new ArrayList<>();
				for(int i = 0; i < this.toDelete.size(); i++) {
					Body b = this.toDelete.get(i);
					UserData data = (UserData) b.getUserData();
					if(data.type == 3) {
						aux.add(b);
					} else if(data.type == 2) {
						powerUpsCount--;
					}
					physics.destroyBody(b);
				}
				obstacles.removeAll(aux);
				this.toDelete.clear();
				for(int i = 0; i < GameScreen.numObstacles - obstacles.size(); i++) {
					generateObstacle();
				}
				while(powerUpsCount < GameScreen.numPowerUps) {
					this.generatePowerUp();
				}
				break;
				
			case PAUSE:
				stage.getBatch().begin();
				stage.getBatch().draw(code.manager.get("Menus/Menu2.png", Texture.class), screenWidth * 36 / 100, screenHeight * 21 / 100, screenWidth * 30 / 100, screenHeight * 60 / 100);
				stage.getBatch().draw(code.manager.get("Menus/Combo.png", Texture.class), screenWidth * 40 / 100, screenHeight * 37 / 100, screenWidth * 20 / 100, screenHeight * 30 / 100);
				stage.getBatch().end();
				
				if(Gdx.input.isKeyPressed(Keys.ENTER)) {
					state = State.RUNNING;
				}
				if(Gdx.input.isKeyPressed(Keys.M)) {
					stage.dispose();
					code.setScreen(new MainMenuScreen(code));
				}
				if(Gdx.input.isKeyPressed(Keys.BACKSPACE)) {
					System.exit(0);
				}
				break;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public

 void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		this.code.music.dispose();
	}
}
