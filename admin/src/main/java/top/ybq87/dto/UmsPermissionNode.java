package top.ybq87.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import top.ybq87.mbg.model.UmsPermission;

/**
 * 拓展,树形结构
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/4 21:02
 */
@Getter
@Setter
public class UmsPermissionNode extends UmsPermission {
    
    List<UmsPermissionNode> children;
}
