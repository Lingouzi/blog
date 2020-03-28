package top.ybq87.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 作者请求类
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/10 12:59
 */
@Getter
@Setter
public class CmsAuthorParam {
    
    private Long id;
    
    @ApiModelProperty(value = "作者名称,最多50个字符，必填")
    private String name;
    
    @ApiModelProperty(value = "作者头像缩略图，必填")
    private String icon;
    
    @ApiModelProperty(value = "简介")
    private String summary;
    
    @ApiModelProperty(value = "手机号码")
    private String phone;
    
    @ApiModelProperty(value = "帐号启用状态:0->禁用；1->启用")
    private Integer status;
    
    @ApiModelProperty(value = "备注")
    private String note;
}
