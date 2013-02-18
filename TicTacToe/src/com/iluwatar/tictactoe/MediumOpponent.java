package com.iluwatar.tictactoe;

public class MediumOpponent extends BaseOpponent {

	@Override
	public void makeMove(TicTacToeBoardState state) {
		
		BaseMove chain = new WinMove();
		BaseMove node = chain.setNextMove(new BlockMove());
		node.setNextMove(new RandomMove());
		chain.move(state);
		
	}

}
