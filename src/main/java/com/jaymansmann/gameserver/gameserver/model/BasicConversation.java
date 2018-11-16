package com.jaymansmann.gameserver.gameserver.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import com.jaymansmann.gameserver.gameserver.utility.UtilityClass;

public class BasicConversation {
	private String id;
	private LinkedHashSet<String> participants;
	private List<BasicMessage> messages;
	
	public BasicConversation() {
		super();
		this.id = UtilityClass.getRandomCodestring();
		this.participants = new LinkedHashSet<>();
		this.messages = new ArrayList<>();
		
	}
	
	public BasicConversation(LinkedHashSet<String> participants, List<BasicMessage> messages) {
		super();
		this.participants = participants;
		this.messages = messages;
	}

	public BasicConversation(LinkedHashSet<String> participants) {
		super();
		this.participants = participants;
	}
	
	

	public BasicConversation(List<BasicMessage> messages) {
		super();
		this.messages = messages;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LinkedHashSet<String> getParticipants() {
		return participants;
	}
	public void setParticipants(LinkedHashSet<String> participants) {
		this.participants = participants;
	}
	public List<BasicMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<BasicMessage> messages) {
		this.messages = messages;
	}
	
	
}
