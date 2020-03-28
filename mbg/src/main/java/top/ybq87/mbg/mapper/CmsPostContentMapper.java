package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsPostContent;
import top.ybq87.mbg.model.CmsPostContentExample;

public interface CmsPostContentMapper {
    long countByExample(CmsPostContentExample example);

    int deleteByExample(CmsPostContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPostContent record);

    int insertSelective(CmsPostContent record);

    List<CmsPostContent> selectByExampleWithBLOBs(CmsPostContentExample example);

    List<CmsPostContent> selectByExample(CmsPostContentExample example);

    CmsPostContent selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsPostContent record, @Param("example") CmsPostContentExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsPostContent record, @Param("example") CmsPostContentExample example);

    int updateByExample(@Param("record") CmsPostContent record, @Param("example") CmsPostContentExample example);

    int updateByPrimaryKeySelective(CmsPostContent record);

    int updateByPrimaryKeyWithBLOBs(CmsPostContent record);

    int updateByPrimaryKey(CmsPostContent record);
}