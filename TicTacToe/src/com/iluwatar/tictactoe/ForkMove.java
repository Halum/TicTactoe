package com.iluwatar.tictactoe;

import java.awt.Point;

import com.iluwatar.tictactoe.TicTacToeBoardState.SquareState;

public class ForkMove extends BaseMove {

	@Override
	public boolean makeMove(TicTacToeBoardState state) {

		for (int x=0; x<TicTacToeBoardState.SQUARES_X; x++) {
			for (int y=0; y<TicTacToeBoardState.SQUARES_Y; y++) {
				if (state.getSquare(x, y) == SquareState.FREE) {
					if (moveCreatesFork(new TicTacToeBoardState(state), SquareState.O, new Point(x, y))) {
						state.setSquare(x, y, SquareState.O);
						return false;
					}
				}
			}
		}
		return true;
	}

}
