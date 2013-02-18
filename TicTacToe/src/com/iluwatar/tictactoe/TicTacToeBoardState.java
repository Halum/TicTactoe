package com.iluwatar.tictactoe;

/**
 * Holds board state.
 * @author Ilkka
 *
 */
public class TicTacToeBoardState {

	public enum SquareState {FREE, X, O};

	public static final int SQUARES_X = 3;
	public static final int SQUARES_Y = 3;
	public static final int ARRAY_COUNT = SQUARES_X * SQUARES_Y;
	
	private SquareState squares[] = new SquareState[ARRAY_COUNT];
	
	public TicTacToeBoardState() {
		for (int i=0; i<ARRAY_COUNT; i++) {
			setSquare(i, SquareState.FREE);
		}
	}
	
	public void setSquare(int arrayPos, SquareState state) {
		squares[arrayPos] = state;
	}
	
	public void setSquare(int xPos, int yPos, SquareState state) {
		squares[yPos*SQUARES_X+xPos] = state;
	}
	
	public SquareState getSquare(int arrayPos) {
		return squares[arrayPos];
	}
	
	public SquareState getSquare(int xPos, int yPos) {
		return squares[yPos*SQUARES_X+xPos];
	}
	
	public boolean isFinished() {
		for (int i=0; i<ARRAY_COUNT; i++) {
			if (getSquare(i) == SquareState.FREE) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isWinnerX() {
		return checkForWin(SquareState.X);
	}
	
	public boolean isWinnerO() {
		return checkForWin(SquareState.O);
	}
	
	private boolean checkForWin(SquareState ss) {

		boolean found;
		
		for (int i=0; i<SQUARES_X; i++) {
			found = true;
			for (int j=0; j<SQUARES_Y; j++) {
				if (getSquare(i, j) != ss) {
					found = false;
					break;
				}
			}
			if (found) {
				return true;
			}
		}
		
		for (int j=0; j<SQUARES_Y; j++) {
			found = true;
			for (int i=0; i<SQUARES_X; i++) {
				if (getSquare(i, j) != ss) {
					found = false;
					break;
				}
			}
			if (found) {
				return true;
			}
		}

		found = true;
		for (int i=0; i<SQUARES_X; i++) {
			if (getSquare(i, i) != ss) {
				found = false;
				break;
			}
		}
		if (found) {
			return true;
		}

		found = true;
		for (int i=0; i<SQUARES_X; i++) {
			if (getSquare(i, SQUARES_Y-1-i) != ss) {
				found = false;
				break;
			}
		}
		if (found) {
			return true;
		}
		
		return false;
	}
	
}
