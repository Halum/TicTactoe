package com.iluwatar.tictactoe;

public class TicTacToeUtils {

	public static String getMethodName(final int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return ste[ste.length - 1 - depth].getMethodName();
	}	
	
}
