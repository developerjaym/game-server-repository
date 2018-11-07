package com.jaymansmann.gameserver.gameserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaymansmann.gameserver.gameserver.model.GameEvent;
import com.jaymansmann.gameserver.gameserver.model.SystemMessage;
import com.jaymansmann.gameserver.gameserver.service.GameService;

@RequestMapping("/game")
@RestController
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("/start")
	public String startGame(@RequestParam(value = "player-name", required=true) String playerName, @RequestParam(value = "player-count", required = true) int playerCount) {
		String gameId = gameService.startGame(playerName, playerCount);
		return gameId;
	}
	
	@GetMapping("/{gameId}/end")
	public void endGame(@PathVariable String gameId) {
		gameService.endGame(gameId);
	}
	
	@GetMapping("/{gameId}/events")
	public List<GameEvent> getGameEvents(@PathVariable String gameId, @RequestParam(value = "most-recent-index", required = true) int mostRecentIndex) {
		return gameService.getGameEvent(gameId, mostRecentIndex);
	}
	
	@PostMapping("/{gameId}/events")
	public void receiveGameEvent(@PathVariable String gameId, @RequestBody GameEvent gameEvent) {
		gameService.receiveGameEvent(gameId, gameEvent);
	}
	
	@GetMapping("/system")
	public SystemMessage getSystemMessage() {
		return gameService.getSystemMessage();
	}
}
