package com.jaymansmann.gameserver.gameserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaymansmann.gameserver.gameserver.model.Game;
import com.jaymansmann.gameserver.gameserver.model.GameEvent;
import com.jaymansmann.gameserver.gameserver.model.GameOverEvent;
import com.jaymansmann.gameserver.gameserver.model.GameStartedEvent;
import com.jaymansmann.gameserver.gameserver.utility.JCache;
import com.jaymansmann.gameserver.gameserver.utility.UtilityClass;

@Service
public class GameService {

	@Autowired
	private JCache<String, Game> games;
	
	public String startGame(String playerName, int playerCount) {
		String gameId = UtilityClass.getRandomCodestring();
		createNewGame(playerName, playerCount, gameId);
		return gameId;
	}

	public void endGame(String gameId) {
		Game game = getGame(gameId);
		game.setActive(false);
		game.getGameEvents().add(new GameOverEvent());
	}

	public void receiveGameEvent(String gameId, GameEvent gameEvent) {
		Game game = getGame(gameId);
		game.getGameEvents().add(gameEvent);
	}

	public List<GameEvent> getGameEvent(String gameId, int mostRecentIndex) {
		Game game = getGame(gameId);
		return game.getGameEvents().subList(mostRecentIndex, game.getGameEvents().size());
	}

	private Game getGame(String gameId) {
		return games.get(gameId).orElseThrow(NoSuchElementException::new);
	}
	
	private void createNewGame(String playerName, int playerCount, String gameId) {
		Game game = new Game();
		game.setGameEvents(new ArrayList<>());
		GameStartedEvent event = new GameStartedEvent();
		event.setPlayer(playerName);
		game.getGameEvents().add(event);
		game.setId(gameId);
		game.setPlayerCount(playerCount);
		games.put(gameId, game);
	}
}
