package top.ybq87.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 公共删除
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/10 13:01
 */
@Getter
@Setter
public class CommonDeleteParam {
    
    @ApiModelProperty("id")
    private List<Long> ids;
}
