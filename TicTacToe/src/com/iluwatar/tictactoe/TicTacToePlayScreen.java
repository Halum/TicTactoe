package com.iluwatar.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iluwatar.tictactoe.TicTacToeBoardState.SquareState;
import com.iluwatar.tictactoe.TicTacToeUtils.TicTacToeScreen;

public class TicTacToePlayScreen extends TicTacToeBaseScreen {

	private static final String LOG = TicTacToeGame.class.getSimpleName() + "." + TicTacToeUtils.getMethodName(0);
	
	private Texture bgTexture;
	private Texture oTexture;
	private Texture xTexture;
	
	private TicTacToeBoardState gameState;
	
	float xSize;
	float ySize;
	private ScreenCallback callback;
	
	BaseOpponent opponent = new EasyOpponent();
	
	public TicTacToePlayScreen(ScreenCallback callback) {
		this.callback = callback;
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
			callback.endScreen(TicTacToeScreen.MENU);
			return;
		}
		
		opponent.makeMove(gameState);
		
		if (gameState.isFinished() || gameState.isWinnerX() || gameState.isWinnerO()) {
			callback.endScreen(TicTacToeScreen.MENU);
			return;
		}
	}
	
	private int getArrayPos(int screenX, int screenY) {
		int x = screenX / (int)xSize;
		int y = screenY / (int)ySize;
		y = TicTacToeBoardState.SQUARES_Y - 1 - y;
		int result = y * TicTacToeBoardState.SQUARES_X + x;
		return result;
	}
	
	public void initBoard() {
		gameState = new TicTacToeBoardState();
	}
	
	public void initOpponent(int parameter) {
		
		Gdx.app.log(LOG, "opponent=" + parameter);
		
		switch (parameter) {
			case 0: opponent = new EasyOpponent(); break;
			case 1: opponent = new MediumOpponent(); break;
			case 2: opponent = new EasyOpponent(); break;
		}
	}
}
