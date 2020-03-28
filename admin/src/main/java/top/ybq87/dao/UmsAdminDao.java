package top.ybq87.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.dto.UmsAdminDto;

/**
 * 自定义实现数据库查询 umsAdmin
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/2 20:49
 */
public interface UmsAdminDao {
    
    /**
     * 查询分页
     *
     * @param keyword
     * @return
     */
    List<UmsAdminDto> getList(@Param("keyword") String keyword, @Param("status") Integer status);
}
