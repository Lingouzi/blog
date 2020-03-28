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
import top.ybq87.dto.UmsPermissionNode;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.service.UmsPermissionService;

/**
 * 后台用户权限管理
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/4 19:41
 */
@Api(tags = "UmsPermissionController", value = "后台用户权限管理")
@RestController
@RequestMapping("/permission")
public class UmsPermissionController {
    
    @Autowired
    private UmsPermissionService permissionService;
    
    @ApiOperation("获取权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsPermission>> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "status", required = false) Integer status) {
        List<UmsPermission> permissions = permissionService.list(keyword, status, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(permissions));
    }
    
    @ApiOperation("查询全部权限, 树形结构")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public CommonResult<List<UmsPermissionNode>> treeList() {
        return permissionService.treeList();
    }
    
    @ApiOperation("添加权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsPermission permission) {
        int count = permissionService.create(permission);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    
    @ApiOperation("更新权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsPermission permission) {
        int count = permissionService.update(id, permission);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    
    @ApiOperation("批量删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody CommonDeleteParam deleteParam) {
        int count = permissionService.delete(deleteParam.getIds());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败");
    }
    
    @ApiOperation("初始化权限")
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public CommonResult init() {
        permissionService.init();
        return CommonResult.success("");
    }
    
}
