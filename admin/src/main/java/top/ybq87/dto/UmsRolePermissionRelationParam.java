package top.ybq87.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 处理角色和权限关系,请求参数
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 21:45
 */
@Getter
@Setter
public class UmsRolePermissionRelationParam {
    
    private Long roleId;
    
    private List<Long> permissionIds;
    
}
