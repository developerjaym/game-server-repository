package com.jaymansmann.gameserver.gameserver.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jaymansmann.gameserver.gameserver.model.BasicConversation;
import com.jaymansmann.gameserver.gameserver.utility.JCache;
import com.jaymansmann.gameserver.gameserver.utility.JCacheManager;

@EnableCaching
@Configuration
public class CacheConfig {
	
	@Bean
	public JCacheManager systemMessageCacheManager() {
		return new JCacheManager(1, 1);
	}
	
	@Bean("conversations")
	public JCache<String, BasicConversation> conversationSet() {
		return new JCache<String, BasicConversation>(2048, Duration.ofDays(7));
	}
}


