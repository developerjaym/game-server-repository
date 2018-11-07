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
import com.jaymansmann.gameserver.gameserver.model.SystemMessage;
import com.jaymansmann.gameserver.gameserver.utility.JCache;
import com.jaymansmann.gameserver.gameserver.utility.JCacheable;
import com.jaymansmann.gameserver.gameserver.utility.UtilityClass;

@Service
public class GameService {

	@Autowired
	private JCache<String, Game> games;
	
	@Autowired
	private SystemMessage lastTimeDown;
	
	/**
	 * This method starts the game.
	 * 
	 * @param playerName the name of the player starting the game
	 * @param playerCount the number of players to be in the game
	 * @return the game's randomly generated ID
	 */
	public String startGame(String playerName, int playerCount) {
		String gameId = UtilityClass.getRandomCodestring();
		createNewGame(playerName, playerCount, gameId);
		return gameId;
	}

	/**
	 * This method ends the game.
	 * @param gameId the id o the game
	 */
	public void endGame(String gameId) {
		Game game = getGame(gameId);
		game.setActive(false);
		game.getGameEvents().add(new GameOverEvent());
	}

	/**
	 * 
	 * @param gameId the id of the game
	 * @param gameEvent the game event to add to the game
	 */
	public void receiveGameEvent(String gameId, GameEvent gameEvent) {
		Game game = getGame(gameId);
		game.getGameEvents().add(gameEvent);
	}

	/**
	 * 
	 * @param gameId the id of the game
	 * @param mostRecentIndex the index of the event most recently seen
	 * @return a list of all events in the game starting from the given index
	 */
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

	/**
	 * This method returns some message from the system.
	 * This app currently loses all data on shutdown,
	 * so this method provides a convenient way of informing clients
	 * that their data may be gone if they last sent a message before
	 * the system message's timestamp.
	 * 
	 * @return
	 */
	@JCacheable(time = 3_600_000)
	public SystemMessage getSystemMessage() {
		return this.lastTimeDown;
	}
}
