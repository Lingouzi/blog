package top.ybq87.mbg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.ybq87.mbg.model.CmsCommentReply;
import top.ybq87.mbg.model.CmsCommentReplyExample;

public interface CmsCommentReplyMapper {
    long countByExample(CmsCommentReplyExample example);

    int deleteByExample(CmsCommentReplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsCommentReply record);

    int insertSelective(CmsCommentReply record);

    List<CmsCommentReply> selectByExample(CmsCommentReplyExample example);

    CmsCommentReply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsCommentReply record, @Param("example") CmsCommentReplyExample example);

    int updateByExample(@Param("record") CmsCommentReply record, @Param("example") CmsCommentReplyExample example);

    int updateByPrimaryKeySelective(CmsCommentReply record);

    int updateByPrimaryKey(CmsCommentReply record);
}