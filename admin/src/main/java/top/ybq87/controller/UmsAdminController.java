package top.ybq87.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.ybq87.common.response.CommonPage;
import top.ybq87.common.response.CommonResult;
import top.ybq87.dto.CommonDeleteParam;
import top.ybq87.dto.UmsAdminDto;
import top.ybq87.dto.UmsAdminLoginParam;
import top.ybq87.dto.UmsAdminParam;
import top.ybq87.dto.UmsAdminUpdateParam;
import top.ybq87.mbg.model.UmsAdmin;
import top.ybq87.service.UmsAdminService;

/**
 * 系统管理员控制类
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/10 17:15
 */
@RestController
@Api(tags = "UmsAdminController", value = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    
    @Autowired
    private UmsAdminService adminService;
    
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(
            @RequestBody UmsAdminParam umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed("用户名重复");
        }
        return CommonResult.success(umsAdmin);
    }
    
    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(
            @RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(4);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
    
    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>(4);
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }
    
    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public CommonResult getAdminInfo(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return CommonResult.unauthorized(null);
        }
        if (token.startsWith(tokenHead)) {
            token = token.substring(tokenHead.length());
        }
        // 获取用户信息
        UmsAdmin umsAdmin = adminService.getAdminByToken(token);
        if (umsAdmin == null) {
            return CommonResult.unauthorized(null);
        }
        if (umsAdmin.getStatus() == 0) {
            return CommonResult.unauthorized("用户已注销");
        }
        Map<String, Object> data = new HashMap<>(4);
        data.put("username", umsAdmin.getUsername());
        data.put("nickName", umsAdmin.getNickName());
        data.put("roles", new String[]{"TEST"});
        data.put("icon", umsAdmin.getIcon());
        return CommonResult.success(data);
    }
    
    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout() {
        return CommonResult.success(null);
    }
    
    @ApiOperation(value = "后台用户分页数据")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsAdminDto>> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "status", required = false) Integer status) {
        List<UmsAdminDto> admins = adminService.list(keyword, status, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(admins));
    }
    
    @ApiOperation("更新用户状态")
    @RequestMapping(value = "/update/status", method = RequestMethod.POST)
    public CommonResult updateStatus(@RequestBody UmsAdminUpdateParam user) {
        return adminService.updateStatus(user);
    }
    
    @ApiOperation("更新用户头像")
    @RequestMapping(value = "/update/icon", method = RequestMethod.POST)
    public CommonResult updateIcon(@RequestBody UmsAdminUpdateParam user) {
        return adminService.updateIcon(user);
    }
    
    @ApiOperation("修改密码")
    @RequestMapping(value = "/update/password", method = RequestMethod.POST)
    public CommonResult updatePassowrd(@RequestBody UmsAdminUpdateParam user) {
        return adminService.updatePassowrd(user);
    }
    
    @ApiOperation(value = "重置密码")
    @RequestMapping(value = "/resetpwd", method = RequestMethod.POST)
    public CommonResult<Object> resetpwd(String username) {
        boolean resetpwd = adminService.resetpwd(username);
        if (!resetpwd) {
            return CommonResult.failed();
        }
        return CommonResult.success(null);
    }
    
    @ApiOperation("批量删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody CommonDeleteParam deleteParam) {
        int count = adminService.delete(deleteParam.getIds());
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    
}
