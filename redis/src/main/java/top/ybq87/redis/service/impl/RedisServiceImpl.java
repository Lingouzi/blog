package top.ybq87.redis.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.ybq87.redis.service.RedisService;

/**
 * redisservice 实现类
 * <p></p>
 * 注意这里需要注入到项目中才能使用,需要被spring扫包扫到,具体的配置说明在admin和portal的入口处说明
 * @author ly
 * @web http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @QQ 664162337@qq.com
 * @date 2020/3/28
 */
@Service
public class RedisServiceImpl implements RedisService {
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    @Override
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }
    
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }
    
    @Override
    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }
    
    @Override
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
    
    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    
    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
