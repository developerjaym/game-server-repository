package com.jaymansmann.gameserver.gameserver.model;

import java.time.ZonedDateTime;

public class SystemMessage {
	private String message;
	private ZonedDateTime timestamp;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(ZonedDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
}
