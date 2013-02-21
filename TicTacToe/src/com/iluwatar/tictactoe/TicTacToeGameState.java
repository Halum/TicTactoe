package com.iluwatar.tictactoe;

public class TicTacToeGameState {

	enum GameResult {WIN, TIE, LOSE};
	
	private int wins;
	private int ties;
	private int losses;
	
	private GameResult lastResult;
	
	public void incrementWins() {
		this.wins++;
	}
	
	public void incrementTies() {
		this.ties++;
	}
	
	public void incrementLosses() {
		this.losses++;
	}
	
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getTies() {
		return ties;
	}
	public void setTies(int ties) {
		this.ties = ties;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public GameResult getLastResult() {
		return lastResult;
	}
	public void setLastResult(GameResult lastResult) {
		this.lastResult = lastResult;
	}
	
}
