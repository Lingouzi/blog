package top.ybq87.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.ybq87.mbg.model.UmsAdmin;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.redis.service.RedisService;
import top.ybq87.service.UmsAdminCacheService;

/**
 * @author ly
 * @web http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @QQ 664162337@qq.com
 * @date 2020/3/27
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {
    
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    
    @Value("${redis.key.permissionList}")
    private String REDIS_KEY_PERMISSION_LIST;
    
    @Autowired
    private RedisService redisService;
    
    
    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + username;
        return (UmsAdmin) redisService.get(key);
    }
    
    @Override
    public List<UmsPermission> getPermissionListByAdminId(Long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_PERMISSION_LIST + ":" + id;
        return (List<UmsPermission>) redisService.get(key);
    }
    
    @Override
    public void setAdmin(UmsAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
        redisService.set(key, admin, REDIS_EXPIRE);
    }
    
    @Override
    public void setPermissionListByAdminId(Long id, List<UmsPermission> permissionList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + id;
        redisService.set(key, permissionList, REDIS_EXPIRE);
    }
}
