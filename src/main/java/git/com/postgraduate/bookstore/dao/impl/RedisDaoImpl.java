package git.com.postgraduate.bookstore.dao.impl;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Repository;
import git.com.postgraduate.bookstore.dao.AbstractRedisDao;

@Repository
public class RedisDaoImpl extends AbstractRedisDao {

	
	/*批量删除对应的value*/
	public void remove(String... keys) {
		for(String key : keys)
			remove(key);;
	}
	
	/*批量删除key*/
	public void removePattern(String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if(keys.size()>0)
			redisTemplate.delete(keys);
	}
	
	/*删除对应的value*/
	public void remove(String key) {
		if(exists(key)) {
			redisTemplate.delete(key);
		}
	}
	
	/*删除map中的key-value*/
	public void remove(String key, String field) {
		
		BoundHashOperations<Serializable, Object, Object> operations = redisTemplate.boundHashOps(key);
		operations.delete(field);
	}
	
	/*判断缓存中是否有对应的value*/
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public boolean exists(String key, String field) {
		
		return redisTemplate.opsForHash().hasKey(key, field);
	}
	
	/*读取缓存*/
	public Object get(String key) {
		
		Object result = null;
		HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
		result = operations.entries(key);
		return result;
	}
	
	
	/*写入缓存*/
	public boolean set(String key,HashMap<String, String> value) {
		boolean result = false;
		try {
			HashOperations<Serializable, String, String> operations = redisTemplate.opsForHash();
			operations.putAll(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*写入缓存*/
	public boolean set(String key, HashMap<String, Long> value, long expireTime) {
		
		boolean result = false;
		try {
			HashOperations<Serializable, String, Long> operations = redisTemplate.opsForHash();
			operations.putAll(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*以一个梯度增加*/
	public void incrBy(String key,String field, Long nm) {
	
		BoundHashOperations<Serializable, Object, Object> operations =  redisTemplate.boundHashOps(key);
		operations.increment(field, nm);
	}
}
