package top.ybq87.service;

import java.util.List;
import top.ybq87.dto.CmsPostDto;
import top.ybq87.mbg.model.CmsAuthor;
import top.ybq87.mbg.model.CmsPostCategory;

/**
 * 博文管理service
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/6 13:37
 */
public interface CmsPostService {
    
    /**
     * 获取分类
     *
     * @return
     */
    List<CmsPostCategory> getCategoryList();
    
    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);
    
    /**
     * 更新
     *
     * @param id
     * @param post
     * @return
     */
    int update(Long id, CmsPostDto post);
    
    /**
     * 新建
     *
     * @param post
     * @return
     */
    long create(CmsPostDto post);
    
    /**
     * 分页数据
     *
     * @param keyword
     * @param status
     * @param authorId
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CmsPostDto> list(String keyword, Integer status, Integer authorId, Integer categoryId, Integer pageNum, Integer pageSize);
    
    /**
     * 作者信息
     *
     * @param keyword
     * @return
     */
    List<CmsAuthor> getAuthors(String keyword);
    
    /**
     * 更新文章作者
     *
     * @param id
     * @param authorId
     * @return
     */
    int updateAuthor(Long id, Long authorId);
    
    /**
     * 更新分类
     *
     * @param id
     * @param categoryId
     * @return
     */
    int updateCategory(Long id, Long categoryId);
    
    /**
     * 获取单个博文内容
     *
     * @param id
     * @return
     */
    CmsPostDto getPost(Long id);
}
