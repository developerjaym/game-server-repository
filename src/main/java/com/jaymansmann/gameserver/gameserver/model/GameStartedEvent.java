package com.jaymansmann.gameserver.gameserver.model;

public class GameStartedEvent extends GameEvent {

	@Override
	public String getType() {
		return "GAME_STARTED";
	}
	@Override
	public String getMessage() {
		return "GAME_STARTED";
	}
}
