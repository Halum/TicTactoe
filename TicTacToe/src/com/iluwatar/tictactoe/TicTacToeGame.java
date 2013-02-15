package com.iluwatar.tictactoe;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TicTacToeGame implements ApplicationListener, InputProcessor {
	
	private static final String LOG = TicTacToeGame.class.getSimpleName() + "." + TicTacToeUtils.getMethodName(0);
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private TicTacToePlayScreen playScreen;
	private TicTacToeMenuScreen menuScreen;
	
	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		menuScreen = new TicTacToeMenuScreen();
		playScreen  = new TicTacToePlayScreen();
	}

	@Override
	public void dispose() {
		menuScreen.dispose();
		playScreen.dispose();
		batch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		menuScreen.render(batch);
		//playScreen.render(batch);
		
		batch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		Gdx.app.log(LOG, "resize width=" + width + " height=" + height);
		menuScreen.resize(width, height);
		playScreen.resize(width, height);
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
		menuScreen.touchDown(screenX, screenY, pointer, button);
		//playScreen.touchDown(screenX, screenY, pointer, button);
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
