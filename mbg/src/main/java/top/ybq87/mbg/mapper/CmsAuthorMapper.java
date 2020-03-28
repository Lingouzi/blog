package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsAuthor;
import top.ybq87.mbg.model.CmsAuthorExample;

public interface CmsAuthorMapper {
    long countByExample(CmsAuthorExample example);

    int deleteByExample(CmsAuthorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsAuthor record);

    int insertSelective(CmsAuthor record);

    List<CmsAuthor> selectByExample(CmsAuthorExample example);

    CmsAuthor selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsAuthor record, @Param("example") CmsAuthorExample example);

    int updateByExample(@Param("record") CmsAuthor record, @Param("example") CmsAuthorExample example);

    int updateByPrimaryKeySelective(CmsAuthor record);

    int updateByPrimaryKey(CmsAuthor record);
}