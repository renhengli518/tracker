package com.hengpeng.itfintracker.commons.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * redis 二级缓存中间类（用于静态注入）
 * @author renhengli
 *
 */
public class RedisCacheTransfer {

	@Autowired
	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}

}