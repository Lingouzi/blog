package top.ybq87.service;

import java.util.List;
import top.ybq87.mbg.model.CmsPostTag;

/**
 * @author ly
 * @web http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @QQ 664162337@qq.com
 * @date 2020/3/26
 */
public interface CmsPostTagService {
    
    /**
     * 列出热门标签
     * @return
     */
    List<CmsPostTag> popularTags();
}
