package top.ybq87.redis.service;

import java.util.List;

/**
 * redis对外提供的访问接口
 * @author ly
 * @web http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @QQ 664162337@qq.com
 * @date 2020/3/28
 */
public interface RedisService {
    
    /**
     * 保存属性
     * @param key   存储的key
     * @param value 存储的值
     * @param time  设置过期时间,单位s
     */
    void set(String key, Object value, long time);
    
    /**
     * 保存属性
     * @param key
     * @param value
     */
    void set(String key, Object value);
    
    /**
     * 获取属性
     * @param key
     * @return
     */
    Object get(String key);
    
    /**
     * 删除属性
     * @param key
     * @return
     */
    Boolean del(String key);
    
    /**
     * 批量删除属性
     * @param keys
     * @return
     */
    Long del(List<String> keys);
    
    /**
     * 设置过期时间
     * @param key
     * @param time 设置过期时间,单位 s
     * @return
     */
    Boolean expire(String key, long time);
    
    /**
     * 获取过期时间
     * @param key
     * @return
     */
    Long getExpire(String key);
    
    /**
     * 判断是否有该属性
     * @param key
     * @return
     */
    Boolean hasKey(String key);
    
}
