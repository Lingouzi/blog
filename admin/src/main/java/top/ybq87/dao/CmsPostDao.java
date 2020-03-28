package top.ybq87.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.dto.CmsPostDto;
import top.ybq87.mbg.model.CmsPostContent;

/**
 * 自定义博文查询
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/6 13:54
 */
public interface CmsPostDao {
    
    /**
     * 自定义搜索分页
     * @param keyword
     * @param status     状态
     * @param authorId   作者id
     * @param categoryId 分类id
     * @return
     */
    List<CmsPostDto> getList(@Param("keyword") String keyword, @Param("status") Integer status, @Param("authorId") Integer authorId,
            @Param("categoryId") Integer categoryId);
    
    /**
     * 获取博文信息.
     * @param id
     * @return
     */
    CmsPostDto getPost(@Param("id") Long id);
    
    /**
     * 通过博文id 获取博文的内容
     * @param id
     * @return
     */
    CmsPostContent getContentByPostId(@Param("id") Long id);
}
