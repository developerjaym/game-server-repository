package com.jaymansmann.gameserver.gameserver.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jaymansmann.gameserver.gameserver.model.Game;
import com.jaymansmann.gameserver.gameserver.utility.JCache;
import com.jaymansmann.gameserver.gameserver.utility.JCacheManager;

@EnableCaching
@Configuration
public class CacheConfig {
	
	@Bean
	public JCacheManager jCacheManager() {
		return new JCacheManager(1, 1024);
	}//probably won't use
	
	@Bean
	public JCache<String, Game> gameSet() {
		return new JCache<String, Game>(2048, Duration.ofDays(7));
	}
}


