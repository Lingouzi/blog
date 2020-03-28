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
import top.ybq87.dto.CmsPostDto;
import top.ybq87.dto.CommonDeleteParam;
import top.ybq87.mbg.model.CmsAuthor;
import top.ybq87.mbg.model.CmsPostCategory;
import top.ybq87.service.CmsPostService;

/**
 * 博文管理控制器
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/6 13:35
 */
@Api(tags = "CmsPostController", value = "博文管理控制器")
@RequestMapping("/post")
@RestController
public class CmsPostController {
    
    @Autowired
    private CmsPostService postService;
    
    @ApiOperation("获取博文列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<CmsPostDto>> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @ApiParam(value = "博文状态", required = false) @RequestParam(value = "status", required = false) Integer status,
            @ApiParam(value = "作者id", required = false) @RequestParam(value = "authorId", required = false) Integer authorId,
            @ApiParam(value = "分类id", required = false) @RequestParam(value = "categoryId", required = false) Integer categoryId
    ) {
        List<CmsPostDto> posts = postService.list(keyword, status, authorId, categoryId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(posts));
    }
    
    @ApiOperation("获取单个博文")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<CmsPostDto> getPost(@PathVariable(value = "id", required = true) Long id) {
        CmsPostDto post = postService.getPost(id);
        if (post != null) {
            return CommonResult.success(post);
        }
        return CommonResult.failed("获取失败");
    }
    
    
    @ApiOperation("添加博文")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody CmsPostDto post) {
        long postId = postService.create(post);
        if (postId > 0) {
            return CommonResult.success(postId);
        }
        return CommonResult.failed("创建失败");
    }
    
    @ApiOperation("更新博文")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody CmsPostDto post) {
        int count = postService.update(id, post);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("更新失败");
    }
    
    @ApiOperation("更新作者")
    @RequestMapping(value = "/update/author", method = RequestMethod.POST)
    public CommonResult updateAuthor(@RequestBody CmsPostDto param) {
        int count = postService.updateAuthor(param.getId(), param.getAuthorId());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("更新失败");
    }
    
    @ApiOperation("更新分类")
    @RequestMapping(value = "/update/category", method = RequestMethod.POST)
    public CommonResult updateCategory(@RequestBody CmsPostDto param) {
        int count = postService.updateCategory(param.getId(), param.getCategoryId());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("更新失败");
    }
    
    @ApiOperation("批量删除博文信息")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody CommonDeleteParam deleteParam) {
        int count = postService.delete(deleteParam.getIds());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    
    @ApiOperation(value = "获取分类信息", notes = "博文选择归属分类")
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public CommonResult<List<CmsPostCategory>> getCategoryList() {
        List<CmsPostCategory> categories = postService.getCategoryList();
        return CommonResult.success(categories);
    }
    
    @ApiOperation(value = "获取作者信息", notes = "博文选择作者")
    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public CommonResult<List<CmsAuthor>> getAuthors(@RequestParam("keyword") String keyword) {
        List<CmsAuthor> authors = postService.getAuthors(keyword);
        return CommonResult.success(authors);
    }
    
}
