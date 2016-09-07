package com.hengpeng.itfintracker.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * 
 * @author renhengli
 *
 */
public class RedisCacheTransfer {

	@Autowired
	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}

}