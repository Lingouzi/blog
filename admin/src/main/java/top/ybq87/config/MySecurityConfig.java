package top.ybq87.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.ybq87.security.config.SecurityConfig;
import top.ybq87.service.UmsAdminService;

/**
 * security 配置 重写父类的 userDetailsService方法,从数据库查询用户信息组装成 UserDetailsService 返回
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/10 22:12
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends SecurityConfig {
    
    @Autowired
    private UmsAdminService adminService;
    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);
    }
}