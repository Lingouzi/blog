package top.ybq87.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ybq87.dao.CmsPostDao;
import top.ybq87.dto.CmsPostDto;
import top.ybq87.mbg.mapper.CmsAuthorMapper;
import top.ybq87.mbg.mapper.CmsPostCategoryMapper;
import top.ybq87.mbg.mapper.CmsPostContentMapper;
import top.ybq87.mbg.mapper.CmsPostMapper;
import top.ybq87.mbg.model.CmsAuthor;
import top.ybq87.mbg.model.CmsAuthorExample;
import top.ybq87.mbg.model.CmsPost;
import top.ybq87.mbg.model.CmsPostCategory;
import top.ybq87.mbg.model.CmsPostCategoryExample;
import top.ybq87.mbg.model.CmsPostContent;
import top.ybq87.service.CmsPostService;

/**
 * 博文实现类
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/6 13:37
 */
@Service
public class CmsPostServiceImpl implements CmsPostService {
    
    @Autowired
    private CmsPostMapper postMapper;
    
    @Autowired
    private CmsPostContentMapper cmsPostContentMapper;
    
    @Autowired
    private CmsPostDao postDao;
    
    @Autowired
    private CmsAuthorMapper authorMapper;
    
    @Autowired
    private CmsPostCategoryMapper categoryMapper;
    
    @Override
    public List<CmsPostCategory> getCategoryList() {
        CmsPostCategoryExample example = new CmsPostCategoryExample();
        example.createCriteria().andStatusEqualTo(1);
        return categoryMapper.selectByExample(example);
    }
    
    @Override
    public int delete(List<Long> ids) {
        return 0;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(Long id, CmsPostDto postParam) {
        // 依据id从数据库查询是否有记录, 没有返回0
        // 有数据, 设置更新的字段.
        CmsPost post = new CmsPost();
        BeanUtil.copyProperties(postParam, post, CopyOptions.create().ignoreNullValue());
        
        CmsPost p = postMapper.selectByPrimaryKey(id);
        if (p == null) {
            return 0;
        }
        
        // 设置分类, 设置作者 , 且传递过来的作者id 和 之前的作者id不一样,才更新作者信息,
        if (postParam.getAuthor() != null && postParam.getAuthor().getId() != null && postParam.getAuthor().getId().equals(p.getAuthorId())) {
            CmsAuthor author = authorMapper.selectByPrimaryKey(postParam.getAuthor().getId());
            if (author != null) {
                post.setAuthorId(postParam.getAuthor().getId());
                // 得到作者信息.
                post.setAuthorName(author.getName());
                post.setAuthorIcon(author.getIcon());
            }
        }
        if (postParam.getCategory() != null && postParam.getCategory().getId() != null && postParam.getCategory().getId().equals(p.getCategoryId())) {
            post.setCategoryId(postParam.getCategory().getId());
        }
        
        // 最后修改时间.
        post.setModifyTime(new Date());
        
        // 内容不为空的时候, 更新
        if (!Strings.isNullOrEmpty(postParam.getContent())) {
            // 用于插入数据库或者更新的content
            CmsPostContent cmsPostContent = new CmsPostContent();
            
            // 先查询得到原始内容
            CmsPostContent content = postDao.getContentByPostId(id);
            if (content == null) {
                cmsPostContent.setPostId(id);
                cmsPostContent.setContent(postParam.getContent());
                cmsPostContent.setType(postParam.getContentType());
                cmsPostContentMapper.insertSelective(cmsPostContent);
            } else {
                // 更新依据id
                cmsPostContent.setId(content.getId());
                cmsPostContent.setContent(postParam.getContent());
                cmsPostContent.setType(postParam.getContentType());
                cmsPostContentMapper.updateByPrimaryKeySelective(cmsPostContent);
            }
        }
        int update = postMapper.updateByPrimaryKeySelective(post);
        return update;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long create(CmsPostDto postDto) {
        CmsPost post = new CmsPost();
        BeanUtil.copyProperties(postDto, post, CopyOptions.create().ignoreNullValue());
        
        // 设置分类, 设置作者
        if (postDto.getAuthor() != null && postDto.getAuthor().getId() != null) {
            CmsAuthor author = authorMapper.selectByPrimaryKey(postDto.getAuthor().getId());
            if (author != null) {
                post.setAuthorId(postDto.getAuthor().getId());
                // 得到作者信息.
                post.setAuthorName(author.getName());
                post.setAuthorIcon(author.getIcon());
            }
        }
        if (postDto.getCategory() != null && postDto.getCategory().getId() != null) {
            post.setCategoryId(postDto.getCategory().getId());
        }
        
        post.setCreateTime(new Date());
        post.setModifyTime(new Date());
        // 默认先不上线 , 2: 草稿
        post.setStatus(2);
        post.setReadCount(0);
        
        int insert = postMapper.insert(post);
        // 正文部分
        CmsPostContent cmsPostContent = new CmsPostContent();
        cmsPostContent.setContent(postDto.getContent());
        cmsPostContent.setType(postDto.getContentType());
        cmsPostContent.setPostId(post.getId());
        int count = cmsPostContentMapper.insert(cmsPostContent);
        
        if (insert > 0 && count > 0) {
            return post.getId();
        }
        return 0;
    }
    
    @Override
    public List<CmsPostDto> list(String keyword, Integer status, Integer authorId, Integer categoryId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return postDao.getList(keyword, status, authorId, categoryId);
    }
    
    @Override
    public List<CmsAuthor> getAuthors(String keyword) {
        CmsAuthorExample example = new CmsAuthorExample();
        if (Strings.isNullOrEmpty(keyword)) {
            example.createCriteria().andNameLike("%" + keyword + "%")
                    .andStatusEqualTo(1);
        } else {
            example.createCriteria().andStatusEqualTo(1);
        }
        return authorMapper.selectByExample(example);
    }
    
    @Override
    public int updateAuthor(Long id, Long authorId) {
        // 查询作者
        CmsAuthor author = authorMapper.selectByPrimaryKey(authorId);
        if (author != null && author.getStatus() == 1) {
            CmsPost post = new CmsPost();
            post.setId(id);
            post.setAuthorId(authorId);
            post.setAuthorIcon(author.getIcon());
            post.setAuthorName(author.getName());
            return postMapper.updateByPrimaryKeySelective(post);
        }
        return 0;
    }
    
    @Override
    public int updateCategory(Long id, Long categoryId) {
        CmsPostCategory category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null && category.getStatus() == 1) {
            CmsPost post = new CmsPost();
            post.setId(id);
            post.setCategoryId(categoryId);
            return postMapper.updateByPrimaryKeySelective(post);
        }
        return 0;
    }
    
    @Override
    public CmsPostDto getPost(Long id) {
        // 加载博文信息
        // 加载作者信息
        // 加载分类信息
        return postDao.getPost(id);
    }
    
}
