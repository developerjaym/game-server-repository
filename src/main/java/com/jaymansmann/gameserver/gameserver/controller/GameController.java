package com.jaymansmann.gameserver.gameserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaymansmann.gameserver.gameserver.service.GameService;

@RestController
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping
	public String test() {
		System.out.println("In test");
		gameService.test();
		return "Success?";
	}
}
