package com.hengpeng.itfintracker.commons.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author renhengli
 * @date 2016年9月7日
 * @version 1.0
 */
@Transactional
public class RedisTemplateUtils {
	private static final Logger logger = Logger.getLogger(RedisTemplateUtils.class);
	@SuppressWarnings("unchecked")
	private static RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) ApplicationContextUtil.getBean("redisTemplate");

	// @SuppressWarnings("unchecked")
	// private static RedisTemplate<Serializable, Serializable>
	// stringRedisTemplate = (RedisTemplate<Serializable, Serializable>)
	// ApplicationContextUtil
	// .getBean("stringRedisTemplate");
	/**
	 * @author renhengli
	 * @param key
	 * @param value
	 * @param timeout
	 *            缓存时间
	 */
	public static void set(final String key, Object value, final long timeout) {
		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
				if (timeout > 0) {
					connection.expire(redisTemplate.getStringSerializer().serialize(key), timeout);
				}
				return null;
			}
		});
	}

	public static <T> T get(final String key) {
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection) throws DataAccessException {
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

	public static Long delete(final String... keys) {
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				long result = 0;
				for (int i = 0; i < keys.length; i++) {
					byte[] keybytes = redisTemplate.getStringSerializer().serialize(keys[i]);
					if (connection.exists(keybytes)) {
						result = connection.del(keybytes);
					}
				}
				return result;
			}
		});
	}

	/**
	 * Set集合添加
	 * 
	 * @author renhengli
	 * @date 2016年9月7日
	 * @param key
	 * @param value
	 * @return
	 */
	public static int sadd(String key, HashSet<?> value, long timeout) {
		try {
			SetOperations<String, Object> s = redisTemplate.opsForSet();
			if (s.isMember(key, value)) {
				return 1;
			}
			s.add(key, value);
			if (timeout > 0) {
				redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis->:add set collection failed!errorMsg:" + e.getMessage());
			return 0;
		}
	}

	/**
	 * set集合获取
	 * 
	 * @author renhengli
	 * @date 2016年9月7日
	 * @param key
	 * @param value
	 * @return
	 */
	public static Set<?> sget(String key) {
		try {
			SetOperations<String, Object> s = redisTemplate.opsForSet();
			Set<Object> result = s.members(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis->:get set collection failed!errorMsg:" + e.getMessage());
			return null;
		}
	}

	/**
	 * 添加list集合
	 * 
	 * @author renhengli
	 * @date 2016年9月7日
	 * @param key
	 * @param list
	 * @return
	 */
	public static int lpush(String key, List<?> list, long timeout) {
		try {
			ListOperations<String, Object> s = redisTemplate.opsForList();
			for (int i = 0; i < list.size(); i++) {
				s.leftPush(key, list.get(i));
			}
			if (timeout > 0) {
				redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis->:add list collection failed!errorMsg:" + e.getMessage());
			return 0;
		}
	}

	/**
	 * 获取list集合
	 * 
	 * @author renhengli
	 * @date 2016年9月7日
	 * @param key
	 * @return
	 */
	public static List<?> lget(String key) {
		try {
			ListOperations<String, Object> s = redisTemplate.opsForList();
			List<?> result = s.range(key, 0, s.size(key) - 1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("redis->:get list collection failed!errorMsg:" + e.getMessage());
			return null;
		}
	}
}
