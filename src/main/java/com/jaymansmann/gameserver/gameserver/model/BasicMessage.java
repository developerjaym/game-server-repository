package com.jaymansmann.gameserver.gameserver.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import com.jaymansmann.gameserver.gameserver.utility.UtilityClass;

public class BasicMessage {
	
	public static final String UNKNOWN = "UNKNOWN";
	private static final String NONE = "NONE";
	
	private String id;
	private String conversationId;
	private String type;
	private List<String> recipients;
	private String sender;
	private JaySon message;
	
	public BasicMessage() {
		super();
		this.id = UtilityClass.getRandomCodestring();
		this.type = NONE;
		this.recipients = new ArrayList<>();
		this.sender = UNKNOWN;
		this.message = null;
	}
	
	public BasicMessage(String type, String sender, JaySon message) {
		this(type, new ArrayList<>(), sender, message);
	}
	
	
	public BasicMessage(String type, List<String> recipients, String sender, JaySon message) {
		this();
		this.type = type;
		this.recipients = recipients;
		this.sender = sender;
		this.message = message;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getRecipients() {
		return recipients;
	}
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		if(UNKNOWN.equalsIgnoreCase(sender)) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
		this.sender = sender;
	}
	public JaySon getMessage() {
		return message;
	}
	public void setMessage(JaySon message) {
		this.message = message;
	}

}
