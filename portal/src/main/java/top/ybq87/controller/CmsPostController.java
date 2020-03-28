package top.ybq87.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ybq87.common.response.CommonPage;
import top.ybq87.common.response.CommonResult;
import top.ybq87.dto.CmsPostDto;
import top.ybq87.service.CmsPostService;

/**
 * 博文管理
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/17 14:15
 */
@Api(tags = "CmsPostController", value = "博文管理controller")
@RestController
public class CmsPostController {
    
    @Autowired
    private CmsPostService postService;
    
    @ApiOperation(value = "获取博文列表", notes = "按照关键词搜索,分页,或者按照作者的id搜索作者的所有文章,或者按照文章的大分类搜索分类的文章")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public CommonResult<CommonPage<CmsPostDto>> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @ApiParam(value = "作者id", required = false) @RequestParam(value = "authorId", required = false) Integer authorId,
            @ApiParam(value = "分类id", required = false) @RequestParam(value = "categoryId", required = false) Integer categoryId
    ) {
        List<CmsPostDto> posts = postService.list(keyword, authorId, categoryId, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(posts));
    }
    
    @ApiOperation(value = "获取单个博文的详情", notes = "包含博文信息,作者信息,分类信息")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public CommonResult<CmsPostDto> posts(@PathVariable Long id) {
        CmsPostDto post = postService.getPostById(id);
        if (post == null) {
            return CommonResult.failed("内容不存在");
        }
        return CommonResult.success(post);
    }
}
