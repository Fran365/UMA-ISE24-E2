package package_bump_boat;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;

import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import com.badlogic.gdx.audio.Music;
import javax.sound.sampled.Mixer;

public class Code extends Game {
	SpriteBatch batch;
	AssetManager manager;
  	int difficulty;
	Music music;
	public float volumen = 0.5f;
	public int ended = 0;
	public boolean winner = false;
	public int wonLegs = 0;
	//private MyInputProcessor inputProcessor;
	//TipoBarco tipoBarcoSeleccionado;
	//ArrayList<TipoBarco> tipoBarcos = new ArrayList(); // Lista de tipos de barcos a mostrar
	

	//////

	public int moverIzquierda = Keys.A;
	public int moverDerecha = Keys.D;
	public int moverArriba = Keys.W;
	public int frenar = Keys.S;

	public int usarPowerUp = Keys.SPACE; // Provisional

	public Code() {

		super();
	}

	@Override
	public void create() {
		difficulty = 1;
		batch = new SpriteBatch();
		/**
		 * Quita el puntero del ratï¿½n
		 */
//		Gdx.input.setCursorCatched(true);
		/**
		 * Quita la interacciï¿½n con el ratï¿½n
		 */
//		inputProcessor = new MyInputProcessor();
//		Gdx.input.setInputProcessor(inputProcessor);

		manager = new AssetManager();
		manager.load("mainManuBackground.jpg", Texture.class);
		manager.finishLoading();
		//tipoBarcoSeleccionado = new TipoBarco(5f, 5f, "barcos/barcoNormal.png", 5f, 5f);
		//cargarTiposBarcos();
		//setScreen(new PantallaDeInicio(this));
	}
	
	// Método que añade todos los tipos de barcos al array

	/*private void cargarTiposBarcos() {
		tipoBarcos.add(new TipoBarco(5f, 5f, "barcos/barcoNormal.png", 5f, 5f));
		tipoBarcos.add(new TipoBarco(3f, 8f, "barcos/barcoMovilidad.png", 6f, 3f));
		tipoBarcos.add(new TipoBarco(2f, 5f, "barcos/barcoVida.png", 10f, 3f));
		tipoBarcos.add(new TipoBarco(4f, 3f, "barcos/barcoSpeed.png", 5f, 8f));
		tipoBarcos.add(new TipoBarco(8f, 6f, "barcos/barcoAceleracion.png", 3f, 3f));
		// TODO meter los barcos
	}*/
	
	@Override
	public void dispose() {
		batch.dispose();
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}