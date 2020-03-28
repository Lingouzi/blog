package top.ybq87.service;

import java.util.List;
import top.ybq87.common.response.CommonResult;
import top.ybq87.dto.UmsPermissionNode;
import top.ybq87.mbg.model.UmsPermission;

/**
 * 后台用户权限管理接口
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/4 19:43
 */
public interface UmsPermissionService {
    
    /**
     * 权限列表分页
     *
     * @param keyword
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UmsPermission> list(String keyword, Integer status, Integer pageNum, Integer pageSize);
    
    /**
     * 创建权限
     *
     * @param permission
     * @return
     */
    int create(UmsPermission permission);
    
    /**
     * 初始化系统现有权限
     */
    void init();
    
    /**
     * 返回树形结构
     *
     * @return
     */
    CommonResult<List<UmsPermissionNode>> treeList();
    
    /**
     * 更新权限字段
     *
     * @param id
     * @param permission
     * @return
     */
    int update(Long id, UmsPermission permission);
    
    /**
     * 批量删除权限数据
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);
}
