package top.ybq87.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.dto.CmsPostDto;

/**
 * 自定义实现数据库查询cmsPost
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/17 15:29
 */
public interface CmsPostDao {
    
    /**
     * 自定义搜索分页
     *
     * @param keyword
     * @param status     状态
     * @param authorId   作者id
     * @param categoryId 分类id
     * @return
     */
    List<CmsPostDto> getList(@Param("keyword") String keyword, @Param("authorId") Integer authorId, @Param("categoryId") Integer categoryId);
    
    /**
     * 获取单个
     * @param id
     * @return
     */
    CmsPostDto getPostById(Long id);
}
