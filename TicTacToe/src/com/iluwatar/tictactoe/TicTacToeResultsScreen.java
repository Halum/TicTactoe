package com.iluwatar.tictactoe;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iluwatar.tictactoe.TicTacToeGameState.GameResult;
import com.iluwatar.tictactoe.TicTacToeUtils.TicTacToeScreen;

public class TicTacToeResultsScreen extends TicTacToeBaseScreen {

	private static final String LOG = TicTacToeGame.class.getSimpleName() + "." + TicTacToeUtils.getMethodName(0);

	private int x;
	private int y;
	private int dx;
	private int dy;
	
	private ScreenCallback callback;
	
	BitmapFont font = new BitmapFont();

	TicTacToeResultsScreen(ScreenCallback callback) {
		this.callback = callback;
	}

	@Override
	public void dispose() {
		font.dispose();
	}

	@Override
	public void render(SpriteBatch batch) {
		font.setColor(1, 1, 1, 1);
		font.setScale(this.dx / 240, this.dy / 160);

		String s = "YOU WIN!!!";
		if (callback.getGameState().getLastResult() == GameResult.TIE) {
			s = "DRAW!";
		} else if (callback.getGameState().getLastResult() == GameResult.LOSE) {
			s = "SORRY, YOU LOSE!";
		}
		renderStringCentered(batch, font, s, this.dy / 10 * 8, this.dx);
		renderStringCentered(batch, font, "WIN - DRAW - LOSE", this.dy / 10 * 6, this.dx);
		s = callback.getGameState().getWins() +	" - " + 
				callback.getGameState().getTies() + " - " + 
				callback.getGameState().getLosses();
		renderStringCentered(batch, font, s, this.dy / 10 * 4, this.dx);
	}
	
	private void renderStringCentered(SpriteBatch batch, BitmapFont font, String s, int yPos, int screenWidth) {
		float w = font.getBounds(s).width;
		int xPos = (int) ((screenWidth - w) / 2);
		font.draw(batch, s, xPos, yPos);
	}
	
	@Override
	public void resize(int width, int height) {
		this.x = 0;
		this.y = 0;
		this.dx = width;
		this.dy = height;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		callback.endScreen(TicTacToeScreen.MENU);
		return false;
	}
	
}
