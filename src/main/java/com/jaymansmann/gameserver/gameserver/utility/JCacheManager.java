package com.jaymansmann.gameserver.gameserver.utility;

import java.time.Duration;

public class JCacheManager extends JCache<String, JCache<Object, Object>>{

	private final int NORMAL_MAX_SIZE;
	public JCacheManager(int numberOfCaches, int normalMaxSize) {
		super(numberOfCaches, Duration.ofDays(7));//keep my caches for a week?//TODO verify this works
		this.NORMAL_MAX_SIZE = normalMaxSize;
	}
	
	/**
	 * 
	 * @param key
	 * @param duration
	 * @return
	 */
	public JCache<Object, Object> getOrPutCache(String key, Duration duration) {
		return getOrPut(key, () -> new JCache<Object, Object>(NORMAL_MAX_SIZE, duration));
	}
}
