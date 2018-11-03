package com.jaymansmann.gameserver.gameserver.model;

import java.util.List;

public class GameEvent {

	private List<List<String>> board;
	private String type;
	private String message;
	private String player;
	
	public List<List<String>> getBoard() {
		return board;
	}
	public void setBoard(List<List<String>> board) {
		this.board = board;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	
	
}
