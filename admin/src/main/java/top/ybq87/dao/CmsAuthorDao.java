package top.ybq87.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsAuthor;

/**
 * 自定义作者查询
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/10 13:19
 */
public interface CmsAuthorDao {
    
    /**
     * 自定义查询sql
     *
     * @param keyword
     * @param status
     * @return
     */
    List<CmsAuthor> getList(@Param("keyword") String keyword, @Param("status") Integer status);
}
