package com.iluwatar.tictactoe;

public class TicTacToeUtils {

	enum TicTacToeScreen {MENU, PLAY, EXIT};
	
	public static String getMethodName(final int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return ste[ste.length - 1 - depth].getMethodName();
	}	
	
}
