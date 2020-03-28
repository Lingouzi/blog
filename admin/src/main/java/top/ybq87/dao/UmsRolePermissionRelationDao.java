package top.ybq87.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.mbg.model.UmsRolePermissionRelation;

/**
 * 自定义角色权限关系查询
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 20:33
 */
public interface UmsRolePermissionRelationDao {
    
    /**
     * 依据角色的id, 查询关联的权限信息
     *
     * @param roleId
     * @return
     */
    List<UmsPermission> getPermissionList(@Param("roleId") Long roleId);
    
    /**
     * 批量插入
     *
     * @param relations
     * @return
     */
    int insertList(@Param("list") List<UmsRolePermissionRelation> list);
}
