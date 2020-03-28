package top.ybq87.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * oss 相关请求参数
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/6 10:20
 */
@Getter
@Setter
public class OssParam {
    
    /**
     * 删除时,传入objectName, 图片完整地址中剔除域名和参数信息.
     */
    private String objectName;
}
