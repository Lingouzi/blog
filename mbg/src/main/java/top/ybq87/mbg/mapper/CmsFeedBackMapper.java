package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsFeedBack;
import top.ybq87.mbg.model.CmsFeedBackExample;

public interface CmsFeedBackMapper {
    long countByExample(CmsFeedBackExample example);

    int deleteByExample(CmsFeedBackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsFeedBack record);

    int insertSelective(CmsFeedBack record);

    List<CmsFeedBack> selectByExample(CmsFeedBackExample example);

    CmsFeedBack selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsFeedBack record, @Param("example") CmsFeedBackExample example);

    int updateByExample(@Param("record") CmsFeedBack record, @Param("example") CmsFeedBackExample example);

    int updateByPrimaryKeySelective(CmsFeedBack record);

    int updateByPrimaryKey(CmsFeedBack record);
}