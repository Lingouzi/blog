package top.ybq87.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.ybq87.common.response.CommonResult;
import top.ybq87.mbg.model.CmsPostTag;
import top.ybq87.service.CmsPostTagService;

/**
 * @author ly
 * @web http://www.ybq87.top
 * @github https://github.com/Lingouzi
 * @QQ 664162337@qq.com
 * @date 2020/3/26
 */
@Api(tags = "CmsPostTagController", value = "热门标签控制器")
@RestController
public class CmsPostTagController {
    
    @Autowired
    private CmsPostTagService tagService;
    
    @ApiOperation(value = "加载热门标签", notes = "按照热度列出tag")
    @RequestMapping(value = "/popularTags", method = RequestMethod.GET)
    public CommonResult<List<CmsPostTag>> popularTags() {
        List<CmsPostTag> tags = tagService.popularTags();
        return CommonResult.success(tags);
    }
    
}
