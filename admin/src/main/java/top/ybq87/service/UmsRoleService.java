package top.ybq87.service;

import java.util.List;
import top.ybq87.dto.UmsRolePermissionRelationParam;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.mbg.model.UmsRole;

/**
 * 角色功能接口
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 15:42
 */
public interface UmsRoleService {
    
    /**
     * 角色分页
     *
     * @param keyword
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UmsRole> list(String keyword, Integer status, Integer pageNum, Integer pageSize);
    
    /**
     * 创建角色
     *
     * @param role
     * @return
     */
    int create(UmsRole role);
    
    /**
     * 更改角色
     *
     * @param id
     * @param role
     * @return
     */
    int update(Long id, UmsRole role);
    
    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);
    
    /**
     * 依据角色的id, 获取对应的权限列表.
     *
     * @param roleId
     * @return
     */
    List<UmsPermission> getPermissionList(Long roleId);
    
    /**
     * 更新角色的权限
     *
     * @param role
     * @return
     */
    int updatePermission(UmsRolePermissionRelationParam role);
}
