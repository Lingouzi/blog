package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsPostCategory;
import top.ybq87.mbg.model.CmsPostCategoryExample;

public interface CmsPostCategoryMapper {
    long countByExample(CmsPostCategoryExample example);

    int deleteByExample(CmsPostCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPostCategory record);

    int insertSelective(CmsPostCategory record);

    List<CmsPostCategory> selectByExample(CmsPostCategoryExample example);

    CmsPostCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsPostCategory record, @Param("example") CmsPostCategoryExample example);

    int updateByExample(@Param("record") CmsPostCategory record, @Param("example") CmsPostCategoryExample example);

    int updateByPrimaryKeySelective(CmsPostCategory record);

    int updateByPrimaryKey(CmsPostCategory record);
}