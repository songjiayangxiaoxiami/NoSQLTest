package com.yt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Title: AbstractBaseRedisDao.java
 * Description: 
 * Company: HundSun
 * @author yangting
 * @date 2017年7月3日 上午11:01:34
 * @version 1.0
 */
public abstract class AbstractBaseRedisDao<K,V>{
	
	@Autowired
	protected RedisTemplate<K, V> redisTemplate;
	
	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	public RedisSerializer<String> getRedisSerializer() {
		return redisTemplate.getStringSerializer();
	}
}
