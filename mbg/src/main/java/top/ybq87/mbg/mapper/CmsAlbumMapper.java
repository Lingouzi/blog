package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsAlbum;
import top.ybq87.mbg.model.CmsAlbumExample;

public interface CmsAlbumMapper {
    long countByExample(CmsAlbumExample example);

    int deleteByExample(CmsAlbumExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsAlbum record);

    int insertSelective(CmsAlbum record);

    List<CmsAlbum> selectByExample(CmsAlbumExample example);

    CmsAlbum selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsAlbum record, @Param("example") CmsAlbumExample example);

    int updateByExample(@Param("record") CmsAlbum record, @Param("example") CmsAlbumExample example);

    int updateByPrimaryKeySelective(CmsAlbum record);

    int updateByPrimaryKey(CmsAlbum record);
}