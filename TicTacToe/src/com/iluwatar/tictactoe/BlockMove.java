package com.iluwatar.tictactoe;

import java.awt.Point;

import com.iluwatar.tictactoe.TicTacToeBoardState.SquareState;

public class BlockMove extends BaseMove {

	@Override
	public boolean makeMove(TicTacToeBoardState state) {
		Point freePoint = new Point();
		
		for (int y=0; y<3; y++) {
			if (rowHasTwoOfTheseAndOneFree(state, SquareState.X, 0, y, 1, 0, freePoint)) {
				state.setSquare(freePoint.x, freePoint.y, SquareState.O);
				return false;
			}
		}

		for (int x=0; x<3; x++) {
			if (rowHasTwoOfTheseAndOneFree(state, SquareState.X, x, 0, 0, 1, freePoint)) {
				state.setSquare(freePoint.x, freePoint.y, SquareState.O);
				return false;
			}
		}

		if (rowHasTwoOfTheseAndOneFree(state, SquareState.X, 0, 0, 1, 1, freePoint)) {
			state.setSquare(freePoint.x, freePoint.y, SquareState.O);
			return false;
		}

		if (rowHasTwoOfTheseAndOneFree(state, SquareState.X, 0, 2, 1, -1, freePoint)) {
			state.setSquare(freePoint.x, freePoint.y, SquareState.O);
			return false;
		}
		
		return true;
	}

}
