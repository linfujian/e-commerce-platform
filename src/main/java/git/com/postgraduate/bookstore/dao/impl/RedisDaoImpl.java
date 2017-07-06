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

	
	/*����ɾ����Ӧ��value*/
	public void remove(String... keys) {
		for(String key : keys)
			remove(key);;
	}
	
	/*����ɾ��key*/
	public void removePattern(String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if(keys.size()>0)
			redisTemplate.delete(keys);
	}
	
	/*ɾ����Ӧ��value*/
	public void remove(String key) {
		if(exists(key)) {
			redisTemplate.delete(key);
		}
	}
	
	/*ɾ��map�е�key-value*/
	public void remove(String key, String field) {
		
		BoundHashOperations<Serializable, Object, Object> operations = redisTemplate.boundHashOps(key);
		operations.delete(field);
	}
	
	/*�жϻ������Ƿ��ж�Ӧ��value*/
	public boolean exists(String key) {
		return redisTemplate.hasKey(key);
	}
	
	public boolean exists(String key, String field) {
		
		return redisTemplate.opsForHash().hasKey(key, field);
	}
	
	/*��ȡ����*/
	public Object get(String key) {
		
		Object result = null;
		HashOperations<Serializable, Object, Object> operations = redisTemplate.opsForHash();
		result = operations.entries(key);
		return result;
	}
	
	
	/*д�뻺��*/
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
	
	/*д�뻺��*/
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
	
	/*��һ���ݶ�����*/
	public void incrBy(String key,String field, Long nm) {
	
		BoundHashOperations<Serializable, Object, Object> operations =  redisTemplate.boundHashOps(key);
		operations.increment(field, nm);
	}
}
