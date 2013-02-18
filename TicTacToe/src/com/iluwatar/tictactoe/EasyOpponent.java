package com.iluwatar.tictactoe;


public class EasyOpponent extends BaseOpponent {

	public void makeMove(TicTacToeBoardState state) {
		BaseMove move = new RandomMove();
		move.move(state);
	}
	
}
