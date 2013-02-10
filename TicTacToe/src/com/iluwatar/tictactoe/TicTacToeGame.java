package com.iluwatar.tictactoe;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iluwatar.tictactoe.TicTacToeState.SquareState;

public class TicTacToeGame implements ApplicationListener, InputProcessor {
	
	private static final String LOG = TicTacToeGame.class.getSimpleName() + "." + TicTacToeUtils.getMethodName(0);
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	
	private Texture bgTexture;
	private Texture oTexture;
	private Texture xTexture;
	
	private TicTacToeState gameState;
	
	@Override
	public void create() {
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		bgTexture = new Texture(Gdx.files.internal("data/bg.png"));
		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		xTexture = new Texture(Gdx.files.internal("data/x.png"));
		xTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		oTexture = new Texture(Gdx.files.internal("data/o.png"));
		oTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		gameState = new TicTacToeState();
		gameState.setSquare(0, SquareState.X);
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void dispose() {
		batch.dispose();
		bgTexture.dispose();
		oTexture.dispose();
		xTexture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		final float xSize = Gdx.graphics.getWidth() / TicTacToeState.SQUARES_X;
		final float ySize = Gdx.graphics.getHeight() / TicTacToeState.SQUARES_Y;
		for (int xpos=0; xpos<TicTacToeState.SQUARES_X; xpos++) {
			for (int ypos=0; ypos<TicTacToeState.SQUARES_Y; ypos++) {
				batch.draw(getTextureForSquareState(gameState.getSquare(xpos, ypos)), xpos * xSize, ypos * ySize, xSize, ySize);
			}
		}
		
		batch.end();
	}
	
	private Texture getTextureForSquareState(SquareState ss) {
		if (ss == SquareState.FREE) {
			return bgTexture;
		} else if (ss == SquareState.X) {
			return xTexture;
		} else {
			return oTexture;
		}
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(LOG, "resize width=" + width + " height=" + height);
	}

	@Override
	public void pause() {
		Gdx.app.log(LOG, "pause");
	}

	@Override
	public void resume() {
		Gdx.app.log(LOG, "resume");
	}

	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log(LOG, "keyDown keycode=" + keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Gdx.app.log(LOG, "keyUp keycode=" + keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log(LOG, "touchDown screenX=" + screenX + " screenY=" + screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log(LOG, "touchUp");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Gdx.app.log(LOG, "touchDragged");
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		//Gdx.app.log(LOG, "mouseMoved");
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		Gdx.app.log(LOG, "scrolled");
		return false;
	}
}
