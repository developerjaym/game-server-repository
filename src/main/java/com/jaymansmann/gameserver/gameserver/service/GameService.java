package com.jaymansmann.gameserver.gameserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class GameService {
	@Autowired
	private JedisPool pool;
	public void test() {
		Jedis jedis = pool.getResource();
		try {
			String testValue = "testValueSpring";

			jedis.set("testKeySpring", testValue);
			System.out.println("Value set into Redis is: " + testValue);

			System.out.println("Value retrieved from Redis is: " + jedis.get("testKeySpring"));
		} finally {
			pool.returnResource(jedis);
		}
	}
}
