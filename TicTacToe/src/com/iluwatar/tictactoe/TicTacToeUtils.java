package com.iluwatar.tictactoe;

import java.util.Random;

public class TicTacToeUtils {

	enum TicTacToeScreen {MENU, PLAY, EXIT};
	
	public static String getMethodName(final int depth)
	{
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		return ste[ste.length - 1 - depth].getMethodName();
	}	

	public static int randomInteger(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;
		return randomNum;
	}
	
}
