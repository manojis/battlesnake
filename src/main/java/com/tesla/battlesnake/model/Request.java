package com.tesla.battlesnake.model;

public class Request {
	
	private Game game;
	private int turn;
	private Board board;
	private You you;
	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}
	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}
	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	/**
	 * @return the you
	 */
	public You getYou() {
		return you;
	}
	/**
	 * @param you the you to set
	 */
	public void setYou(You you) {
		this.you = you;
	}

}
