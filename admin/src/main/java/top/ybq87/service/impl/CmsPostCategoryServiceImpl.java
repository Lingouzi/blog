package top.ybq87.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ybq87.mbg.mapper.CmsPostCategoryMapper;
import top.ybq87.mbg.model.CmsPostCategory;
import top.ybq87.mbg.model.CmsPostCategoryExample;
import top.ybq87.mbg.model.CmsPostCategoryExample.Criteria;
import top.ybq87.service.CmsPostCategoryService;

/**
 * 博文分类service实现类
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 22:43
 */
@Service
public class CmsPostCategoryServiceImpl implements CmsPostCategoryService {
    
    @Autowired
    private CmsPostCategoryMapper cmsPostCategoryMapper;
    
    @Override
    public List<CmsPostCategory> list(String keyword, Integer status, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        CmsPostCategoryExample example = new CmsPostCategoryExample();
        Criteria criteria = example.createCriteria();
        if (!Strings.isNullOrEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
        }
        if (status != null) {
            criteria.andStatusEqualTo(status);
        }
        return cmsPostCategoryMapper.selectByExample(example);
    }
    
    @Override
    public int create(CmsPostCategory category) {
        category.setStatus(0);
        category.setIcon("");
        category.setPostCount(0);
        category.setSort(0);
        return cmsPostCategoryMapper.insertSelective(category);
    }
    
    @Override
    public int update(Long id, CmsPostCategory category) {
        category.setId(id);
        return cmsPostCategoryMapper.updateByPrimaryKeySelective(category);
    }
    
    @Override
    public int delete(List<Long> ids) {
        CmsPostCategoryExample example = new CmsPostCategoryExample();
        example.createCriteria().andIdIn(ids);
        return cmsPostCategoryMapper.deleteByExample(example);
    }
    
}
