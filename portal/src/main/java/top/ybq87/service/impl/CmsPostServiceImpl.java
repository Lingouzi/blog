package top.ybq87.service.impl;

import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ybq87.dao.CmsPostDao;
import top.ybq87.dto.CmsPostDto;
import top.ybq87.mbg.mapper.CmsPostMapper;
import top.ybq87.service.CmsPostService;

/**
 * 博文接口实现类
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/17 14:25
 */
@Service
public class CmsPostServiceImpl implements CmsPostService {
    
    @Autowired
    private CmsPostMapper postMapper;
    
    @Autowired
    private CmsPostDao postDao;
    
    @Override
    public List<CmsPostDto> list(String keyword, Integer authorId, Integer categoryId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return postDao.getList(keyword, authorId, categoryId);
    }
    
    @Override
    public CmsPostDto getPostById(Long id) {
        CmsPostDto post = postDao.getPostById(id);
        if (post == null || post.getStatus() != 1) {
            return null;
        }
        return post;
    }
}
