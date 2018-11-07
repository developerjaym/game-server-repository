package com.jaymansmann.gameserver.gameserver.model;

import java.util.List;
import java.util.SortedMap;

public class GameEvent {

	private List<List<String>> board;
	private SortedMap<String, String> namesToScores;
	private String type;
	private String message;
	private String player;
	
	public List<List<String>> getBoard() {
		return board;
	}
	public void setBoard(List<List<String>> board) {
		this.board = board;
	}
	public SortedMap<String, String> getNamesToScores() {
		return namesToScores;
	}
	public void setNamesToScores(SortedMap<String, String> namesToScores) {
		this.namesToScores = namesToScores;
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
