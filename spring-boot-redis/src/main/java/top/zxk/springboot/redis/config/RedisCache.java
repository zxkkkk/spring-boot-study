package top.zxk.springboot.redis.config;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L; // 24小时
    public final static long HOUR_ONE_EXPIRE = 60 * 60 * 1L; // 1小时
    public final static long HOUR_SIX_EXPIRE = 60 * 60 * 6L; // 6小时
    public final static long NOT_EXPIRE = -1L; // 不设置过期时长

    public void set(String key, Object value, long expire) {
        redisTemplate.opsForValue().set(key, value);
        if (expire != NOT_EXPIRE) {
            expire(key, expire);
        }
    }

    public void expire(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public Object get(String key, long expire) {
        Object value = redisTemplate.opsForValue().get(key);
        if (value != null && expire != NOT_EXPIRE) {
            expire(key, expire);
        }
        return value;
    }

    public Object get(String key) {
        return get(key, NOT_EXPIRE);
    }

    // 添加 delete 方法
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // 批量删除
    public void delete(List<String> keys) {
        redisTemplate.delete(keys);
    }
}
