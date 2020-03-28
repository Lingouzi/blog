package top.ybq87.service;

import java.util.List;
import top.ybq87.dto.CmsPostDto;

/**
 * 操作post的接口
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/17 14:23
 */
public interface CmsPostService {
    
    /**
     * @param keyword
     * @param authorId
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<CmsPostDto> list(String keyword, Integer authorId, Integer categoryId, Integer pageNum, Integer pageSize);
    
    /**
     * 通过id 获取博文
     * @param id 博文id
     * @return
     */
    CmsPostDto getPostById(Long id);
}
