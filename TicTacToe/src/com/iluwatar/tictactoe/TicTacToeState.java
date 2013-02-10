package com.iluwatar.tictactoe;

public class TicTacToeState {

	public enum SquareState {FREE, X, O};

	public static final int SQUARES_X = 3;
	public static final int SQUARES_Y = 3;
	public static final int ARRAY_COUNT = SQUARES_X * SQUARES_Y;
	
	private SquareState squares[] = new SquareState[ARRAY_COUNT];
	
	public TicTacToeState() {
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
	
	SquareState getSquare(int arrayPos) {
		return squares[arrayPos];
	}
	
	SquareState getSquare(int xPos, int yPos) {
		return squares[yPos*SQUARES_X+xPos];
	}
}
