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
import top.ybq87.dto.UmsRolePermissionRelationParam;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.mbg.model.UmsRole;
import top.ybq87.service.UmsRoleService;

/**
 * 后台角色管理控制器
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/5 15:41
 */

@Api(tags = "UmsRoleController", value = "后台角色管理控制器")
@RestController
@RequestMapping("/role")
public class UmsRoleController {
    
    @Autowired
    private UmsRoleService roleService;
    
    @ApiOperation("获取权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsRole>> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "status", required = false) Integer status) {
        List<UmsRole> roles = roleService.list(keyword, status, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(roles));
    }
    
    
    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsRole role) {
        int count = roleService.create(role);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("创建失败");
    }
    
    @ApiOperation("更新角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsRole role) {
        int count = roleService.update(id, role);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("更新失败");
    }
    
    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody CommonDeleteParam deleteParam) {
        int count = roleService.delete(deleteParam.getIds());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败");
    }
    
    @ApiOperation("依据角色id, 获取角色的权限")
    @RequestMapping(value = "/permission/{id}", method = RequestMethod.GET)
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long id) {
        List<UmsPermission> permissionList = roleService.getPermissionList(id);
        return CommonResult.success(permissionList);
    }
    
    @ApiOperation(value = "更改角色的权限", notes = "先删除旧有的关联关系,然后插入新的关联关系.")
    @RequestMapping(value = "/permission/update", method = RequestMethod.POST)
    public CommonResult updatePermission(@RequestBody UmsRolePermissionRelationParam rolePermissionRelationParam) {
        int count = roleService.updatePermission(rolePermissionRelationParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    
}
