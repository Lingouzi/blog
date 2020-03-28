package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.UmsUserAuth;
import top.ybq87.mbg.model.UmsUserAuthExample;

public interface UmsUserAuthMapper {
    long countByExample(UmsUserAuthExample example);

    int deleteByExample(UmsUserAuthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsUserAuth record);

    int insertSelective(UmsUserAuth record);

    List<UmsUserAuth> selectByExample(UmsUserAuthExample example);

    UmsUserAuth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsUserAuth record, @Param("example") UmsUserAuthExample example);

    int updateByExample(@Param("record") UmsUserAuth record, @Param("example") UmsUserAuthExample example);

    int updateByPrimaryKeySelective(UmsUserAuth record);

    int updateByPrimaryKey(UmsUserAuth record);
}