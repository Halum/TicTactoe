package com.iluwatar.tictactoe;

import com.iluwatar.tictactoe.TicTacToeBoardState.SquareState;

public class RandomMove extends BaseMove {

	@Override
	public boolean makeMove(TicTacToeBoardState state) {
		while (true) {
			int x = TicTacToeUtils.randomInteger(0, TicTacToeBoardState.SQUARES_X-1);
			int y = TicTacToeUtils.randomInteger(0, TicTacToeBoardState.SQUARES_Y-1);
			if (state.getSquare(x, y) == SquareState.FREE) {
				state.setSquare(x, y, SquareState.O);
				return true;
			}
		}
	}

}
