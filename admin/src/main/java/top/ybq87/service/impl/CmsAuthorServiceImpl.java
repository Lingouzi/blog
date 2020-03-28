package top.ybq87.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.github.pagehelper.PageHelper;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ybq87.dao.CmsAuthorDao;
import top.ybq87.dto.CmsAuthorParam;
import top.ybq87.mbg.mapper.CmsAuthorMapper;
import top.ybq87.mbg.model.CmsAuthor;
import top.ybq87.mbg.model.CmsAuthorExample;
import top.ybq87.service.CmsAuthorService;

/**
 * 作者service实现类
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/10 12:57
 */
@Service
public class CmsAuthorServiceImpl implements CmsAuthorService {
    
    @Autowired
    private CmsAuthorMapper authorMapper;
    
    @Autowired
    private CmsAuthorDao authorDao;
    
    @Override
    public List<CmsAuthor> list(String keyword, Integer status, Integer pageNum, Integer pageSize) {
        // 依据作者名称, 简介, 备注 搜索
        PageHelper.startPage(pageNum, pageSize);
        return authorDao.getList(keyword, status);
    }
    
    @Override
    public int create(CmsAuthorParam authorParam) {
        CmsAuthor author = new CmsAuthor();
        BeanUtil.copyProperties(authorParam, author, CopyOptions.create().ignoreNullValue());
        author.setCreateTime(new Date());
        author.setStatus(1);
        return authorMapper.insert(author);
    }
    
    @Override
    public int update(Long id, CmsAuthorParam authorParam) {
        CmsAuthor author = new CmsAuthor();
        BeanUtil.copyProperties(authorParam, author, CopyOptions.create().ignoreNullValue());
        author.setId(id);
        return authorMapper.updateByPrimaryKeySelective(author);
    }
    
    @Override
    public int delete(List<Long> ids) {
        CmsAuthorExample example = new CmsAuthorExample();
        example.createCriteria().andIdIn(ids);
        return authorMapper.deleteByExample(example);
    }
}
