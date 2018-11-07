package com.jaymansmann.gameserver.gameserver.config;

import java.time.ZonedDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jaymansmann.gameserver.gameserver.model.SystemMessage;

@Configuration
public class SpringConfig {

	@Bean
	public SystemMessage lastTimeDown() {
		SystemMessage message = new SystemMessage();
		message.setTimestamp(ZonedDateTime.now());
		message.setMessage("SYSTEM_RESTART");
		return message;
	}
	
}