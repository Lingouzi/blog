package top.ybq87.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * 类名：UmsAdminLoginParam <p> 功能说明：<p> 用户登录参数
 *
 * @author 创建人：ly 664162337@qq.com **************************修订记录*************************************
 * @date 创建日期：2020/1/11 10:02
 */
@Getter
@Setter
public class UmsAdminLoginParam {
    
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;
}
