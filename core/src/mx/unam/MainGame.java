package mx.unam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import mx.unam.States.GameStateManager;
import mx.unam.States.Menu;
import mx.unam.States.PlayState;
import mx.unam.States.Preferencess;

public class MainGame extends ApplicationAdapter {
	public static int WIDTH;
	public static int HEIGHT;
	private GameStateManager gsm;
	private SpriteBatch batch;
	Preferencess prefs;
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		prefs = new Preferencess();
		prefs.flushChafa();
		gsm.push(new Menu(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
