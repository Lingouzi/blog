package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsUserSearchLog;
import top.ybq87.mbg.model.CmsUserSearchLogExample;

public interface CmsUserSearchLogMapper {
    long countByExample(CmsUserSearchLogExample example);

    int deleteByExample(CmsUserSearchLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsUserSearchLog record);

    int insertSelective(CmsUserSearchLog record);

    List<CmsUserSearchLog> selectByExample(CmsUserSearchLogExample example);

    CmsUserSearchLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsUserSearchLog record, @Param("example") CmsUserSearchLogExample example);

    int updateByExample(@Param("record") CmsUserSearchLog record, @Param("example") CmsUserSearchLogExample example);

    int updateByPrimaryKeySelective(CmsUserSearchLog record);

    int updateByPrimaryKey(CmsUserSearchLog record);
}