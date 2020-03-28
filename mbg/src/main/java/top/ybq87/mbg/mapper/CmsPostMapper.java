package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsPost;
import top.ybq87.mbg.model.CmsPostExample;

public interface CmsPostMapper {
    long countByExample(CmsPostExample example);

    int deleteByExample(CmsPostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPost record);

    int insertSelective(CmsPost record);

    List<CmsPost> selectByExample(CmsPostExample example);

    CmsPost selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsPost record, @Param("example") CmsPostExample example);

    int updateByExample(@Param("record") CmsPost record, @Param("example") CmsPostExample example);

    int updateByPrimaryKeySelective(CmsPost record);

    int updateByPrimaryKey(CmsPost record);
}