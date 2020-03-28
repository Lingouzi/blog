package top.ybq87.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.UmsAdminRoleRelation;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.mbg.model.UmsRole;

/**
 * 接口名：UmsAdminRoleRelationDao <p>
 * 功能说明：<p>
 * 后台用户与角色管理自定义Dao
 *
 * @author 创建人：ly 664162337@qq.com
 * **********************************************************************
 * @date 创建日期：2020/1/10 22:30
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
