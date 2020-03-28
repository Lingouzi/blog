package top.ybq87.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类名：IndexContrller
 * <p>
 * 首页管理
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/11 11:39
 */
@Slf4j
@Controller
@Api(tags = "IndexContrller", value = "前端首页")
public class IndexContrller {
    
    @ApiOperation("跳转到首页, 如果部署到正式环境,首页是静态的,直接走nginx所以实际上这里应该没有访问到")
    @RequestMapping(value = {"/", "/index.html"})
    public String index() {
        return "index";
    }
    
}
