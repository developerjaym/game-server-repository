package com.jaymansmann.gameserver.gameserver.model;

import java.util.List;

public class Game {

	private String id;
	private int playerCount;
	private List<GameEvent> gameEvents;
	private boolean active;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	public List<GameEvent> getGameEvents() {
		return gameEvents;
	}
	public void setGameEvents(List<GameEvent> gameEvents) {
		this.gameEvents = gameEvents;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
