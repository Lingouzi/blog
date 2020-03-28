package top.ybq87.dao;

import java.util.List;
import top.ybq87.mbg.model.UmsPermission;

/**
 * 自定义权限数据查询接口
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/4 20:02
 */
public interface UmsPermissionDao {
    
    /**
     * 自定义分页接口
     *
     * @param keyword
     * @param status
     * @return
     */
    List<UmsPermission> getList(String keyword, Integer status);
}
