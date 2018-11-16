package com.jaymansmann.gameserver.gameserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaymansmann.gameserver.gameserver.model.BasicMessage;
import com.jaymansmann.gameserver.gameserver.model.SystemMessage;
import com.jaymansmann.gameserver.gameserver.service.BasicConversationService;

@RequestMapping("/conversations")
@RestController
public class BasicController {
	
	@Autowired
	private BasicConversationService service;
	
	@PostMapping(value = "/create", produces="application/json")
	public BasicMessage create(@RequestBody BasicMessage message) {
		return this.service.create(message);
	}
	
	@GetMapping(value = "/{id}", produces="application/json")
	public List<BasicMessage> readMessagesFrom(@PathVariable String id, @RequestParam int startingIndex){
		return service.readMessagesFrom(id, startingIndex);
	}
	
	@PostMapping(value = "/{id}", produces="application/json")
	public BasicMessage update(@PathVariable String id, @RequestBody BasicMessage message) {
		return service.update(id, message);
	}
	
	@DeleteMapping("/{id}")
	public void destroy(@PathVariable String id) {
		service.destroy(id);
	}

	@GetMapping("/system")
	public SystemMessage getSystemMessage() {
		return service.getSystemMessage();
	}
}
