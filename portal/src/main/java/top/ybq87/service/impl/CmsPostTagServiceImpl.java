package top.ybq87.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ybq87.mbg.mapper.CmsPostTagMapper;
import top.ybq87.mbg.model.CmsPostTag;
import top.ybq87.mbg.model.CmsPostTagExample;
import top.ybq87.service.CmsPostTagService;

/**
 * @author ly
 * @web http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @QQ 664162337@qq.com
 * @date 2020/3/26
 */
@Service
public class CmsPostTagServiceImpl implements CmsPostTagService {
    
    @Autowired
    private CmsPostTagMapper tagMapper;
    
    @Override
    public List<CmsPostTag> popularTags() {
        CmsPostTagExample example = new CmsPostTagExample();
        example.createCriteria().andStatusEqualTo(1);
        example.setOrderByClause("post_count DESC limit 0,10");
        List<CmsPostTag> tags = tagMapper.selectByExample(example);
        return tags;
    }
}
