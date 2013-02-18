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
}
