package top.ybq87.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.ybq87.bo.AdminUserDetails;
import top.ybq87.common.response.CommonResult;
import top.ybq87.dao.UmsAdminDao;
import top.ybq87.dao.UmsAdminRoleRelationDao;
import top.ybq87.dto.UmsAdminDto;
import top.ybq87.dto.UmsAdminParam;
import top.ybq87.dto.UmsAdminUpdateParam;
import top.ybq87.mbg.mapper.UmsAdminLoginLogMapper;
import top.ybq87.mbg.mapper.UmsAdminMapper;
import top.ybq87.mbg.model.UmsAdmin;
import top.ybq87.mbg.model.UmsAdminExample;
import top.ybq87.mbg.model.UmsAdminLoginLog;
import top.ybq87.mbg.model.UmsPermission;
import top.ybq87.redis.service.RedisService;
import top.ybq87.security.util.JwtTokenUtil;
import top.ybq87.service.UmsAdminCacheService;
import top.ybq87.service.UmsAdminService;

/**
 * 类名：UmsAdminServiceImpl <p> 功能说明：<p> 后台管理员Service 实现类
 * @author 创建人：ly 664162337@qq.com **************************修订记录*************************************
 * @date 创建日期：2020/1/10 17:26
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    
    @Autowired
    private UmsAdminMapper adminMapper;
    
    @Autowired
    private UmsAdminDao adminDao;
    
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    
    @Autowired
    private UmsAdminCacheService adminCacheService;
    
    @Autowired
    private RedisService redisService;
    
    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(umsAdmin.getPassword());
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }
    
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }
    
    
    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if (admin != null) {
            List<UmsPermission> permissionList = adminCacheService.getPermissionListByAdminId(admin.getId());
            if (permissionList == null) {
                permissionList = new ArrayList<>();
            }
            return new AdminUserDetails(admin, permissionList);
        }
        //获取用户信息
        admin = getAdminByUsername(username);
        if (admin != null) {
            // 获取权限.
            List<UmsPermission> permissionList = getPermissionList(admin.getId());
            adminCacheService.setAdmin(admin);
            adminCacheService.setPermissionListByAdminId(admin.getId(), permissionList);
            return new AdminUserDetails(admin, permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
    
    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            // TODO 更新用户最后登录时间
            // updateLoginTimeByUsername(username);
            
            // 记录用户的登录行为.
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
    
    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }
    
    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }
    
    @Override
    public boolean resetpwd(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            UmsAdmin update = new UmsAdmin();
            String encodePassword = passwordEncoder.encode("123456");
            update.setId(admin.getId());
            update.setPassword(encodePassword);
            adminMapper.updateByPrimaryKeySelective(update);
            return true;
        }
        return false;
    }
    
    @Override
    public UmsAdmin getAdminByToken(String token) {
        String username = jwtTokenUtil.getUserNameFromToken(token);
        if (Strings.isNullOrEmpty(username)) {
            return null;
        }
        // 找到用户
        return getAdminByUsername(username);
    }
    
    @Override
    public List<UmsAdminDto> list(String keyword, Integer status, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return adminDao.getList(keyword, status);
    }
    
    @Override
    public CommonResult updateStatus(UmsAdminUpdateParam user) {
        UmsAdmin admin = new UmsAdmin();
        admin.setId(user.getId());
        admin.setStatus(user.getStatus());
        int count = adminMapper.updateByPrimaryKeySelective(admin);
        if (count > 0) {
            return CommonResult.success("");
        }
        return CommonResult.failed("更新失败");
    }
    
    @Override
    public CommonResult updateIcon(UmsAdminUpdateParam user) {
        UmsAdmin admin = new UmsAdmin();
        admin.setId(user.getId());
        admin.setIcon(user.getIcon());
        int count = adminMapper.updateByPrimaryKeySelective(admin);
        if (count > 0) {
            return CommonResult.success("");
        }
        return CommonResult.failed("更新失败");
    }
    
    @Override
    public CommonResult updatePassowrd(UmsAdminUpdateParam user) {
        UmsAdmin admin = new UmsAdmin();
        admin.setId(user.getId());
        String encodePassword = passwordEncoder.encode(user.getPassword());
        admin.setPassword(encodePassword);
        int update = adminMapper.updateByPrimaryKeySelective(admin);
        if (update > 0) {
            // 重置redis 缓存
            ThreadUtil.execute(() -> {
                UmsAdmin umsAdmin = adminMapper.selectByPrimaryKey(user.getId());
                if (umsAdmin != null) {
                    // TODO 清除用户缓存
                    // redisUtil.del("jwt:cache:" + umsAdmin.getUsername());
                }
            });
            
            return CommonResult.success("");
        }
        return CommonResult.failed();
    }
    
    @Override
    public int delete(List<Long> ids) {
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andIdIn(ids);
        return adminMapper.deleteByExample(example);
    }
    
}
