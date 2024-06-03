package bump.boat;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img1;
	Texture img2;
	Texture img3;
	Texture img4;
	Texture img5;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img1 = new Texture("duck.jpg");
		img2 = new Texture("log.jpg");
		img3 = new Texture("rock.jpg");
		img4 = new Texture("boat.jpg");
		img5 = new Texture("mainMenuBackground.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 2, 3, 1);
		batch.begin();
		batch.draw(img5,0, 0, 700, 500);
		batch.draw(img1, 0, 0, 90, 90);
		batch.draw(img2,200, 200, 90, 50);
		batch.draw(img3,100, 250, 80, 80);
		batch.draw(img4,100, 100, 150, 100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img5.dispose();
		img1.dispose();
		img2.dispose();
		img3.dispose();
		img4.dispose();
	}
}
