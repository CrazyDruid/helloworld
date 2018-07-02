package Test.Service;

import KeyPrefix.RedisKeyPrefix;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/5/31.
 */

@Service
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public void set(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj, 600, TimeUnit.SECONDS);
    }

    public Object get(String key) {
        key = RedisKeyPrefix.USER + key;
//        System.out.println("|||||" + redisTemplate.opsForValue().get(key));
        return redisTemplate.opsForValue().get(key);
    }
}
