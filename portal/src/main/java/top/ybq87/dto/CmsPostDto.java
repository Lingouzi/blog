package top.ybq87.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import top.ybq87.mbg.model.CmsAuthor;
import top.ybq87.mbg.model.CmsPost;
import top.ybq87.mbg.model.CmsPostCategory;

/**
 * 博文展示, 用于列表的展示
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/17 15:35
 */
@Setter
@Getter
public class CmsPostDto extends CmsPost {
    
    @ApiModelProperty("作者")
    private CmsAuthor author;
    
    @ApiModelProperty("分类")
    private CmsPostCategory category;
    
    @ApiModelProperty(value = "正文部分,必填")
    private String content;
    
    @ApiModelProperty(value = "1:html,2:md格式")
    private Integer contentType;
    
}
