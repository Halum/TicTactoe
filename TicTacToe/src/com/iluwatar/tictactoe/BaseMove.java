package com.iluwatar.tictactoe;

import java.awt.Point;

import com.iluwatar.tictactoe.TicTacToeBoardState.SquareState;

public abstract class BaseMove {

	private BaseMove nextMove;
	
	/**
	 * The inherited class shall return true if the next move in the chain should be called.
	 * @param state Board state
	 * @return true if the move was not made and the next move should be called
	 */
	public abstract boolean makeMove(TicTacToeBoardState state);

	/**
	 * Calls makeMove and if necessary traverses to next node in the chain.
	 * @param state Board state
	 */
	public void move(TicTacToeBoardState state) {
		if (makeMove(state) && (nextMove != null)) {
			nextMove.move(state);
		}
	}
	
	/**
	 * Set the next node in the chain
	 * @param nextMove Next node in the chain
	 * @return Next node
	 */
	public BaseMove setNextMove(BaseMove nextMove) {
		this.nextMove = nextMove;
		return this.nextMove;
	}

	/**
	 * Check if row has two squares with same markers and one free.
	 * @param state Board state
	 * @param ss Square state to check for
	 * @param x Start x-coordinate
	 * @param y Start y-coordinate
	 * @param incX X-increment
	 * @param incY Y-increment
	 * @param free If return value is true contains the coordinates of the free square.
	 * @return true If row has two of the same and one free.
	 */
	protected boolean rowHasTwoOfTheseAndOneFree(TicTacToeBoardState state, SquareState ss, int x, int y, int incX, int incY, Point free) {
		int numTarget = 0;
		int numFree = 0;
		for (int i=0; i<3; i++) {
			if (state.getSquare(x, y) == ss) {
				numTarget++;
			} else if (state.getSquare(x, y) == SquareState.FREE) {
				numFree++;
				free.x = x;
				free.y = y;
			}
			x += incX;
			y += incY;
		}
		return ((numTarget == 2) && (numFree == 1));
	}

	/**
	 * Tests if the given move creates a fork
	 * @param state
	 * @param ss
	 * @param move
	 * @return true if fork is created with this move
	 */
	protected boolean moveCreatesFork(TicTacToeBoardState state, SquareState ss, Point move) {
		
		state.setSquare(move.x, move.y, ss);

		int numFound = 0;
		
		for (int y=0; y<3; y++) {
			if (rowHasTwoOfTheseAndOneFree(state, ss, 0, y, 1, 0, new Point())) {
				numFound++;
			}
		}

		for (int x=0; x<3; x++) {
			if (rowHasTwoOfTheseAndOneFree(state, SquareState.O, x, 0, 0, 1, new Point())) {
				numFound++;
			}
		}

		if (rowHasTwoOfTheseAndOneFree(state, SquareState.O, 0, 0, 1, 1, new Point())) {
			numFound++;
		}

		if (rowHasTwoOfTheseAndOneFree(state, SquareState.O, 0, 2, 1, -1, new Point())) {
			numFound++;
		}
		
		return (numFound>=2);
	}
	
}
