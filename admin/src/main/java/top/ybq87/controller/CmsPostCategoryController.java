package top.ybq87.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ybq87.common.response.CommonPage;
import top.ybq87.common.response.CommonResult;
import top.ybq87.dto.CommonDeleteParam;
import top.ybq87.mbg.model.CmsPostCategory;
import top.ybq87.service.CmsPostCategoryService;

/**
 * 博文分类控制类
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 22:38
 */
@Api(tags = "CmsPostCategoryController", value = "后台博文分类控制器")
@RestController
@RequestMapping("/category")
public class CmsPostCategoryController {
    
    @Autowired
    private CmsPostCategoryService categoryService;
    
    @ApiOperation("获取分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<CmsPostCategory>> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "status", required = false) Integer status) {
        List<CmsPostCategory> categories = categoryService.list(keyword, status, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(categories));
    }
    
    
    @ApiOperation("添加分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody CmsPostCategory category) {
        int count = categoryService.create(category);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }
    
    @ApiOperation("更新分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody CmsPostCategory category) {
        int count = categoryService.update(id, category);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("更新失败");
    }
    
    @ApiOperation("批量删除分类信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody CommonDeleteParam deleteParam) {
        int count = categoryService.delete(deleteParam.getIds());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
