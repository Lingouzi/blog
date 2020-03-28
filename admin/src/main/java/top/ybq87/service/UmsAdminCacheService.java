package top.ybq87.service;

import java.util.List;
import top.ybq87.mbg.model.UmsAdmin;
import top.ybq87.mbg.model.UmsPermission;

/**
 * 用户操作redis接口
 * @author ly
 * @web http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @QQ 664162337@qq.com
 * @date 2020/3/27
 */
public interface UmsAdminCacheService {
    
    /**
     * 从缓存获取umsadmin
     * @param username
     * @return
     */
    UmsAdmin getAdmin(String username);
    
    /**
     * 用过用户id ,得到他的权限 缓存
     * @param id
     * @return
     */
    List<UmsPermission> getPermissionListByAdminId(Long id);
    
    /**
     * 设置用户信息到redis
     * @param admin
     */
    void setAdmin(UmsAdmin admin);
    
    /**
     * 保存权限到redis
     * @param id
     * @param permissionList
     */
    void setPermissionListByAdminId(Long id, List<UmsPermission> permissionList);
}
