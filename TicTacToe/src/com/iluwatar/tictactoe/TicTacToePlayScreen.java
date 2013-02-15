package com.iluwatar.tictactoe;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iluwatar.tictactoe.TicTacToeBoardState.SquareState;

public class TicTacToePlayScreen extends TicTacToeBaseScreen {

	private Texture bgTexture;
	private Texture oTexture;
	private Texture xTexture;
	
	private TicTacToeBoardState gameState;
	
	float xSize;
	float ySize;
	
	public TicTacToePlayScreen() {
		bgTexture = new Texture(Gdx.files.internal("data/bg.png"));
		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		xTexture = new Texture(Gdx.files.internal("data/x.png"));
		xTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		oTexture = new Texture(Gdx.files.internal("data/o.png"));
		oTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		gameState = new TicTacToeBoardState();
	}
	
	@Override
	public void dispose() {
		bgTexture.dispose();
		oTexture.dispose();
		xTexture.dispose();
	}

	@Override
	public void render(SpriteBatch batch) {
		
		for (int xpos=0; xpos<TicTacToeBoardState.SQUARES_X; xpos++) {
			for (int ypos=0; ypos<TicTacToeBoardState.SQUARES_Y; ypos++) {
				batch.draw(getTextureForSquareState(gameState.getSquare(xpos, ypos)), xpos * xSize, ypos * ySize, xSize, ySize);
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		xSize = width / TicTacToeBoardState.SQUARES_X;
		ySize = height / TicTacToeBoardState.SQUARES_Y;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int arrayPos = getArrayPos(screenX, screenY);
		if (gameState.getSquare(arrayPos) == SquareState.FREE) {
			gameState.setSquare(arrayPos, SquareState.X);
			makeMove();
		}
		return false;
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

	private void makeMove() {
		if (gameState.isFinished() || gameState.isWinnerX() || gameState.isWinnerO()) {
			Gdx.app.exit();
			return;
		}
		while (true) {
			int x = randomInteger(0, TicTacToeBoardState.SQUARES_X-1);
			int y = randomInteger(0, TicTacToeBoardState.SQUARES_Y-1);
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
		y = TicTacToeBoardState.SQUARES_Y - 1 - y;
		int result = y * TicTacToeBoardState.SQUARES_X + x;
		return result;
	}
	
	
}
