package com.iluwatar.tictactoe;

import java.util.Random;

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
	
	float xSize;
	float ySize;
	
	@Override
	public void create() {
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
//		camera.setToOrtho(false, w, h);
		batch = new SpriteBatch();
		
		bgTexture = new Texture(Gdx.files.internal("data/bg.png"));
		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		xTexture = new Texture(Gdx.files.internal("data/x.png"));
		xTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		oTexture = new Texture(Gdx.files.internal("data/o.png"));
		oTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		gameState = new TicTacToeState();
		
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
		xSize = width / TicTacToeState.SQUARES_X;
		ySize = height / TicTacToeState.SQUARES_Y;
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
		int arrayPos = getArrayPos(screenX, screenY);
		if (gameState.getSquare(arrayPos) == SquareState.FREE) {
			gameState.setSquare(arrayPos, SquareState.X);
			makeMove();
		}
		return false;
	}
	
	private void makeMove() {
		if (gameState.isFinished() || gameState.isWinnerX() || gameState.isWinnerO()) {
			Gdx.app.exit();
			return;
		}
		while (true) {
			int x = randomInteger(0, TicTacToeState.SQUARES_X-1);
			int y = randomInteger(0, TicTacToeState.SQUARES_Y-1);
			if (gameState.getSquare(x, y) == SquareState.FREE) {
				gameState.setSquare(x, y, SquareState.O);
				break;
			}
		}
		if (gameState.isFinished() || gameState.isWinnerX() || gameState.isWinnerO()) {
			Gdx.app.exit();
			return;
		}
	}
	
	private int randomInteger(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;
		return randomNum;
	}

	private int getArrayPos(int screenX, int screenY) {
		int x = screenX / (int)xSize;
		int y = screenY / (int)ySize;
		y = TicTacToeState.SQUARES_Y - 1 - y;
		int result = y * TicTacToeState.SQUARES_X + x;
		Gdx.app.log(LOG, "getArrayPos return=" + result);
		return result;
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
