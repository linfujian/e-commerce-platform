package git.com.postgraduate.bookstore.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public final class RedisUtil {
	
	@Autowired
	private RedisTemplate<Serializable,Object> redisTemplate;
	
	
	
	public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
