package top.ybq87.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.UmsRole;

/**
 * 自定义数据库查询接口
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 16:37
 */
public interface UmsRoleDao {
    
    /**
     * 自定义分页
     *
     * @param keyword
     * @param status
     * @return
     */
    List<UmsRole> getList(@Param("keyword") String keyword, @Param("status") Integer status);
}
