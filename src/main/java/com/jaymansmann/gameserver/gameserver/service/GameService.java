package com.jaymansmann.gameserver.gameserver.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.jaymansmann.gameserver.gameserver.config.SpringConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class GameService {
//	@Autowired
//	private JedisPool pool;
	public void test() {
		System.out.println("Launching Redis sample. Configured with Spring");
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		//ApplicationContext ctx = new GenericXmlApplicationContext("applicationContext.xml");

		JedisPool pool = ctx.getBean(JedisPool.class);
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
