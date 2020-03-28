package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsAlbumPic;
import top.ybq87.mbg.model.CmsAlbumPicExample;

public interface CmsAlbumPicMapper {
    long countByExample(CmsAlbumPicExample example);

    int deleteByExample(CmsAlbumPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsAlbumPic record);

    int insertSelective(CmsAlbumPic record);

    List<CmsAlbumPic> selectByExample(CmsAlbumPicExample example);

    CmsAlbumPic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsAlbumPic record, @Param("example") CmsAlbumPicExample example);

    int updateByExample(@Param("record") CmsAlbumPic record, @Param("example") CmsAlbumPicExample example);

    int updateByPrimaryKeySelective(CmsAlbumPic record);

    int updateByPrimaryKey(CmsAlbumPic record);
}