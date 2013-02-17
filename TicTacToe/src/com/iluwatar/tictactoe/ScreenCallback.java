package com.iluwatar.tictactoe;

import com.iluwatar.tictactoe.TicTacToeUtils.TicTacToeScreen;

public interface ScreenCallback {

	public void endScreen(TicTacToeScreen nextScreen);
	public void endScreen(TicTacToeScreen nextScreen, int parameter);
	
}
