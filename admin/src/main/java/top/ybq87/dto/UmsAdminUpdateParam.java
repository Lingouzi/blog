package top.ybq87.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * umsAdmin 更改属性
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/3 15:57
 */
@Setter
@Getter
public class UmsAdminUpdateParam {
    
    @ApiModelProperty("id")
    @NotEmpty(message = "id 不能为空")
    private Long id;
    
    @ApiModelProperty(value = "用户名")
    private String username;
    
    @ApiModelProperty(value = "密码")
    private String password;
    
    @ApiModelProperty(value = "用户头像")
    private String icon;
    
    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式不合法")
    private String email;
    
    @ApiModelProperty(value = "用户昵称")
    private String nickName;
    
    @ApiModelProperty(value = "备注")
    private String note;
    
    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;
    
}
