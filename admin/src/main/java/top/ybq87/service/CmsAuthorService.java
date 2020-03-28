package top.ybq87.service;

import java.util.List;
import top.ybq87.dto.CmsAuthorParam;
import top.ybq87.mbg.model.CmsAuthor;

/**
 * 作者service
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/10 12:57
 */
public interface CmsAuthorService {
    
    /**
     * 分页
     *
     * @param keyword
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CmsAuthor> list(String keyword, Integer status, Integer pageNum, Integer pageSize);
    
    /**
     * 创建
     *
     * @param authorParam
     * @return
     */
    int create(CmsAuthorParam authorParam);
    
    /**
     * 更新
     *
     * @param id
     * @param authorParam
     * @return
     */
    int update(Long id, CmsAuthorParam authorParam);
    
    /**
     * 删除
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);
}
