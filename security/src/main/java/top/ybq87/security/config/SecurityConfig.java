package top.ybq87.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.ybq87.security.component.JwtAuthenticationTokenFilter;
import top.ybq87.security.component.RestAuthenticationEntryPoint;
import top.ybq87.security.component.RestfulAccessDeniedHandler;

/**
 * 对SpringSecurity的配置的扩展，支持自定义白名单资源路径和查询用户逻辑
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/10 22:01
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        // 不需要保护的资源路径允许访问
        for (String url : ignoreUrlsConfig().getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        //允许跨域请求的OPTIONS请求
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        // 任何请求需要身份认证
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                // 关闭跨站请求防护及不使用session
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                // 自定义权限拦截器JWT过滤器
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        
        // 记住我 和 注销登录
        // registry.and()
        //         //开启cookie储存用户信息，并设置有效期为14天，指定cookie中的密钥
        //         .rememberMe().tokenValiditySeconds(1209600).key("mykey")
        //         .and()
        //         .logout()
        //         //指定登出的url
        //         .logoutUrl("/login/logout")
        //         //指定登场成功之后跳转的url
        //         .logoutSuccessUrl("/index")
        //         .permitAll();
    }
    
    /**
     * 重写 configure(AuthenticationManagerBuilder auth) 方法
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 这里需要 传入 一个 实现了 UserDetailsService 接口的 认证方法, 也就是我们一般会设置的 UserDetailsService
        // 接口有一个 loadUserByUsername 方法, 返回 UserDetails
        // 在 security 模块我们先不实现它,调用父级的方法, 我们将这个交给 子类实现; 是不是有点类似模板方法设计模式, 实现交给子类
        auth.userDetailsService(userDetailsService())
                // 密码使用 passwordEncoder() 方法验证, 注意登录的时候处理用户的账号密码
                .passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    /**
     * 需要自己注入 authenticationManagerBean
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }
    
}