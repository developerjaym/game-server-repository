package com.jaymansmann.gameserver.gameserver.utility;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class JCache<K, V> {
	private int maxSize;
	private ConcurrentHashMap<K, JCacheItem<V>> map;
	private Duration time;
	public JCache(int maxSize, Duration time) {
		this.maxSize = maxSize;
		this.map = new ConcurrentHashMap<>(maxSize);
		this.time = time;
	}
	
	public void remove(K key) {
		this.map.remove(key);
	}
	
	public V getOrPut(K key, Supplier<V> orPut) {
		resize();
		return get(key).orElseGet(() -> put(key, orPut.get()));
	}
	
	public V put(K key, V value) {
		resize();
		this.map.put(key, new JCacheItem<V>(value, time));
		return value;
	}
	
	public V putIfAbsent(K key, V value) {
		resize();
		this.map.putIfAbsent(key, new JCacheItem<V>(value, time));
		return value;
	}
	
	/**
	 * Checks the item to see if it's expired.
	 * If it's expired, removes the item.
	 * If it's there and not expired, returns the item's value
	 * @param key
	 * @return
	 */
	public Optional<V> get(K key) {
		return Optional.ofNullable(this.map.get(key))
				.filter(item -> {
					if(!item.isValid()) {
						this.remove(key);
						return false;
					}
					return true;
				})
				.map(item -> item.getItem());
	}
	
	private void resize() {
		if(this.map.size() > this.maxSize) {
			this.map.clear();
		}
	}
}

class JCacheItem<V> {
	private V item;
	private ZonedDateTime expirationTime;
	
	public JCacheItem(V item, Duration durationTilExpiry) {
		this.item = item;
		this.expirationTime = ZonedDateTime.now().plus(durationTilExpiry);
	}
	
	public JCacheItem(V item) {
		this(item, Duration.ofDays(1L));
	}
	
	public boolean isValid() {
		return this.expirationTime.isAfter(ZonedDateTime.now());
	}
	
	public V getItem() {
		return item;
	}
}