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

	public TicTacToeBoardState(TicTacToeBoardState state) {
		System.arraycopy(state.squares, 0, this.squares, 0, state.squares.length);
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
	
	public boolean isFull() {
		for (int i=0; i<ARRAY_COUNT; i++) {
			if (getSquare(i) == SquareState.FREE) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isGameOver() {
		WinningLine line = new WinningLine();
		return (isFull() || isWinnerX(line) || isWinnerO(line));
	}
	
	public boolean isWinnerX(WinningLine line) {
		return checkForWin(SquareState.X, line);
	}
	
	public boolean isWinnerO(WinningLine line) {
		return checkForWin(SquareState.O, line);
	}
	
	private boolean checkForWin(SquareState ss, WinningLine line) {

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
				line.start.x = i;
				line.start.y = 0;
				line.end.x = i;
				line.end.y = SQUARES_Y-1;
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
				line.start.x = 0;
				line.start.y = j;
				line.end.x = SQUARES_X-1;
				line.end.y = j;
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
			line.start.x = 0;
			line.start.y = 0;
			line.end.x = SQUARES_X-1;
			line.end.y = SQUARES_Y-1;
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
			line.start.x = 0;
			line.start.y = SQUARES_Y-1;
			line.end.x = SQUARES_X-1;
			line.end.y = 0;
			return true;
		}
		
		return false;
	}
	
	public static class WinningLine {
		public Point start = new Point();
		public Point end = new Point();
	}
}
