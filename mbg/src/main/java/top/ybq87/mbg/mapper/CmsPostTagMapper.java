package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsPostTag;
import top.ybq87.mbg.model.CmsPostTagExample;

public interface CmsPostTagMapper {
    long countByExample(CmsPostTagExample example);

    int deleteByExample(CmsPostTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPostTag record);

    int insertSelective(CmsPostTag record);

    List<CmsPostTag> selectByExample(CmsPostTagExample example);

    CmsPostTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsPostTag record, @Param("example") CmsPostTagExample example);

    int updateByExample(@Param("record") CmsPostTag record, @Param("example") CmsPostTagExample example);

    int updateByPrimaryKeySelective(CmsPostTag record);

    int updateByPrimaryKey(CmsPostTag record);
}