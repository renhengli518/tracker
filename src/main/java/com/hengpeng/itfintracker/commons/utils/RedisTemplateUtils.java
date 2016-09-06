package com.hengpeng.itfintracker.commons.utils;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTemplateUtils {
	@SuppressWarnings("unchecked")
	private static RedisTemplate<Serializable, Serializable> redisTemplate = 
				(RedisTemplate<Serializable, Serializable>) ApplicationContextUtil
						.getBean("redisTemplate");
	/**
	 * @author renhengli
	 * @param key
	 * @param value
	 * @param timeout 缓存时间
	 */
	public static void save(final String key, Object value , final long timeout) {

		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
				if(timeout > 0){
					connection.expire(redisTemplate.getStringSerializer().serialize(key), timeout);
				}
				return null;
			}
		});
	}

	public static <T> T get(final String key) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
				if (connection.exists(keybytes)) {
					byte[] valuebytes = connection.get(keybytes);
					@SuppressWarnings("unchecked")
					T value = (T) SerializeUtil.unserialize(valuebytes);
					return value;
				}
				return null;
			}
		});
	}
}
