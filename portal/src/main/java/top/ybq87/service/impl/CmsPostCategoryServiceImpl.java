package top.ybq87.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ybq87.mbg.mapper.CmsPostCategoryMapper;
import top.ybq87.mbg.model.CmsPostCategory;
import top.ybq87.mbg.model.CmsPostCategoryExample;
import top.ybq87.service.CmsPostCategoryService;

/**
 * @author ly
 */
@Service
public class CmsPostCategoryServiceImpl implements CmsPostCategoryService {
    
    @Autowired
    private CmsPostCategoryMapper categoryMapper;
    
    @Override
    public List<CmsPostCategory> categories() {
        CmsPostCategoryExample example = new CmsPostCategoryExample();
        example.createCriteria().andStatusEqualTo(1);
        example.setOrderByClause("sort ASC");
        List<CmsPostCategory> categories = categoryMapper.selectByExample(example);
        return categories;
    }
}
