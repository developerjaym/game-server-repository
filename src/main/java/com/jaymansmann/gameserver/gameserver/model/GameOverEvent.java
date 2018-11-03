package com.jaymansmann.gameserver.gameserver.model;

public class GameOverEvent extends GameEvent {

	@Override
	public String getType() {
		return "GAME_OVER";
	}
	@Override
	public String getMessage() {
		return "GAME_OVER";
	}
}
