package com.iluwatar.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.iluwatar.tictactoe.TicTacToeBoardState.SquareState;
import com.iluwatar.tictactoe.TicTacToeBoardState.WinningLine;
import com.iluwatar.tictactoe.TicTacToeGameState.GameResult;
import com.iluwatar.tictactoe.TicTacToeUtils.TicTacToeScreen;

public class TicTacToePlayScreen extends TicTacToeBaseScreen {

	private static final String LOG = TicTacToeGame.class.getSimpleName() + "." + TicTacToeUtils.getMethodName(0);
	
	private Texture bgTexture;
	private Texture oTexture;
	private Texture xTexture;
	
	private TicTacToeBoardState gameState;
	
	private int dx;
	private int dy;
	private float xSize;
	private float ySize;
	private ScreenCallback callback;
	
	BaseOpponent opponent = new EasyOpponent();
	
	MoveHelper moveHelper = this.new MoveHelper();
	
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
		super.dispose();
		bgTexture.dispose();
		oTexture.dispose();
		xTexture.dispose();
	}

	@Override
	public void render() {

		batch.begin();
		
		for (int xpos=0; xpos<TicTacToeBoardState.SQUARES_X; xpos++) {
			for (int ypos=0; ypos<TicTacToeBoardState.SQUARES_Y; ypos++) {
				batch.draw(getTextureForSquareState(gameState.getSquare(xpos, ypos)), xpos * xSize, ypos * ySize, xSize, ySize);
			}
		}
		
		batch.end();

		if (!gameState.isGameOver()) {
			if (moveHelper.isOpponentMove()) {
				makeMove();
			}
		} else {
			if (moveHelper.isTimeToEndGame()) {
				
				if (gameState.isFull()) {
					callback.getGameState().setLastResult(GameResult.TIE);
					callback.getGameState().incrementTies();
				} else if (gameState.isWinnerX(new WinningLine())) {
					callback.getGameState().setLastResult(GameResult.WIN);
					callback.getGameState().incrementWins();
				} else if (gameState.isWinnerO(new WinningLine())) {
					callback.getGameState().setLastResult(GameResult.LOSE);
					callback.getGameState().incrementLosses();
				}
				callback.endScreen(TicTacToeScreen.RESULTS);
				return;
				
			} else {

				WinningLine line = new WinningLine();
				if (gameState.isWinnerX(line) || gameState.isWinnerO(line)) {

					Gdx.gl10.glLineWidth(10);
					Gdx.gl10.glEnable(GL10.GL_BLEND);
					ShapeRenderer renderer = new ShapeRenderer();
					renderer.begin(ShapeType.Line);
					renderer.setColor(1, 0, 0, 0.5f);
//					Gdx.app.log(LOG, "(" + getSquareCenterX(line.start.x) + "," + 
//							getSquareCenterY(line.start.y) + "  " + 
//							getSquareCenterX(line.end.x) + "," + 
//							getSquareCenterY(line.end.y) + ")");
					renderer.line(getSquareCenterX(line.start.x), 
							getSquareCenterY(line.start.y), 
							getSquareCenterX(line.end.x), 
							getSquareCenterY(line.end.y));
					renderer.end();
					
				}
				
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		dx = width;
		dy = height;
		xSize = width / TicTacToeBoardState.SQUARES_X;
		ySize = height / TicTacToeBoardState.SQUARES_Y;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if (!gameState.isGameOver()) {
			if (moveHelper.isPlayerMove()) {
				int arrayPos = getArrayPos(screenX, screenY);
				if (gameState.getSquare(arrayPos) == SquareState.FREE) {
					gameState.setSquare(arrayPos, SquareState.X);
					moveHelper.setLastPlayerMove(elapsedTime);
				}
			}
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
		
		opponent.makeMove(gameState);
		moveHelper.setLastOpponentMove(elapsedTime);

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
		moveHelper.setLastPlayerMove(0);
		moveHelper.setLastOpponentMove(0);
	}
	
	public void initOpponent(int parameter) {
		
		Gdx.app.log(LOG, "opponent=" + parameter);
		
		switch (parameter) {
			case 0: opponent = new EasyOpponent(); break;
			case 1: opponent = new MediumOpponent(); break;
			case 2: opponent = new HardOpponent(); break;
		}
	}
	
	private int getSquareCenterX(int index) {
		return (int) (xSize/2 + xSize*index);
	}

	private int getSquareCenterY(int index) {
		return (int) (ySize/2 + ySize*index);
	}
	
	public class MoveHelper {
		
		private float lastPlayerMove;
		private float lastOpponentMove;
		
		public float getLastPlayerMove() {
			return lastPlayerMove;
		}
		
		public void setLastPlayerMove(float lastPlayerMove) {
			this.lastPlayerMove = lastPlayerMove;
		}

		public float getLastOpponentMove() {
			return lastOpponentMove;
		}

		public void setLastOpponentMove(float lastOpponentMove) {
			this.lastOpponentMove = lastOpponentMove;
		}
		
		public boolean isPlayerMove() {
			return (lastPlayerMove <= lastOpponentMove);
		}
		
		public boolean isOpponentMove() {
			if (isPlayerMove()) { 
				return false;
			}
			return ((elapsedTime-1) >= lastPlayerMove);
		}
		
		public boolean isTimeToEndGame() {
			float last = (lastPlayerMove > lastOpponentMove) ? lastPlayerMove : lastOpponentMove;
			return ((elapsedTime-3) >= last);
		}
	}
	
}
