package top.ybq87.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import top.ybq87.dto.CmsAuthorParam;
import top.ybq87.dto.CommonDeleteParam;
import top.ybq87.mbg.model.CmsAuthor;
import top.ybq87.service.CmsAuthorService;

/**
 * 博文作者管理
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/10 12:54
 */
@Api(tags = "CmsAuthorController", value = "博文作者管理")
@RestController
@RequestMapping("/author")
public class CmsAuthorController {
    
    @Autowired
    private CmsAuthorService authorService;
    
    @ApiOperation("获取博文作者列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<CmsAuthor>> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @ApiParam(value = "博文作者状态", required = false) @RequestParam(value = "status", required = false) Integer status
    ) {
        List<CmsAuthor> posts = authorService.list(keyword, status, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(posts));
    }
    
    
    @ApiOperation("添加博文作者")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody CmsAuthorParam authorParam) {
        int count = authorService.create(authorParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }
    
    @ApiOperation("更新博文作者")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody CmsAuthorParam authorParam) {
        int count = authorService.update(id, authorParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("更新失败");
    }
    
    @ApiOperation("批量删除博文作者信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody CommonDeleteParam deleteParam) {
        int count = authorService.delete(deleteParam.getIds());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    
}
