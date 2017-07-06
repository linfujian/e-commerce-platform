package git.com.postgraduate.bookstore.dao;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public  abstract class AbstractRedisDao {

	@Autowired
	protected RedisTemplate<Serializable, Object> redisTemplate;
	

	
	
	
}
