package top.ybq87.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import top.ybq87.common.response.CommonResult;
import top.ybq87.dto.UmsAdminDto;
import top.ybq87.dto.UmsAdminParam;
import top.ybq87.dto.UmsAdminUpdateParam;
import top.ybq87.mbg.model.UmsAdmin;
import top.ybq87.mbg.model.UmsPermission;

/**
 * 类名：IUmsAdminService <p> 功能说明：<p> 后台管理员Service
 *
 * @author 创建人：ly 664162337@qq.com **************************修订记录*************************************
 * @date 创建日期：2020/1/10 17:16
 */
public interface UmsAdminService {
    
    /**
     * 注册功能
     *
     * @param umsAdminParam
     * @return
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);
    
    /**
     * 根据用户名获取后台管理员
     *
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);
    
    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     *
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(Long adminId);
    
    /**
     * 获取用户信息
     *
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);
    
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);
    
    /**
     * 刷新token
     *
     * @param token
     * @return
     */
    String refreshToken(String token);
    
    /**
     * 重置用户密码
     *
     * @param username
     * @return
     */
    boolean resetpwd(String username);
    
    /**
     * 通过token 获取到用户信息
     *
     * @param token
     * @return
     */
    UmsAdmin getAdminByToken(String token);
    
    /**
     * 后台用户分页
     *
     * @param keyword
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<UmsAdminDto> list(String keyword, Integer status, Integer pageNum, Integer pageSize);
    
    /**
     * 修改状态
     *
     * @param user
     * @return
     */
    CommonResult updateStatus(UmsAdminUpdateParam user);
    
    /**
     * 修改头像
     *
     * @param user
     * @return
     */
    CommonResult updateIcon(UmsAdminUpdateParam user);
    
    /**
     * 重置密码
     *
     * @param user
     * @return
     */
    CommonResult updatePassowrd(UmsAdminUpdateParam user);
    
    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);
}
