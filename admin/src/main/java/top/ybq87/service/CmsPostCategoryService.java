package top.ybq87.service;

import java.util.List;
import top.ybq87.mbg.model.CmsPostCategory;

/**
 * 博文分类
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 22:42
 */
public interface CmsPostCategoryService {
    
    /**
     * 分类分页
     *
     * @param keyword
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CmsPostCategory> list(String keyword, Integer status, Integer pageNum, Integer pageSize);
    
    /**
     * 增加分类
     *
     * @param category
     * @return
     */
    int create(CmsPostCategory category);
    
    /**
     * 更新分类
     *
     * @param id
     * @param category
     * @return
     */
    int update(Long id, CmsPostCategory category);
    
    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);
}
