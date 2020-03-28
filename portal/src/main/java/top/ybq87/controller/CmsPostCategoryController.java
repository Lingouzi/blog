package top.ybq87.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.ybq87.common.response.CommonResult;
import top.ybq87.mbg.model.CmsPostCategory;
import top.ybq87.service.CmsPostCategoryService;

/**
 * 博文分类控制类
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 22:38
 */
@Api(tags = "CmsPostCategoryController", value = "博文分类管理器")
@RestController
public class CmsPostCategoryController {
    
    @Autowired
    private CmsPostCategoryService categoryService;
    
    @ApiOperation(value = "获取所有博文分类",notes = "一次性加载全部生效的分类,排序")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public CommonResult<List<CmsPostCategory>> categories() {
        List<CmsPostCategory> categories = categoryService.categories();
        return CommonResult.success(categories);
    }
}
