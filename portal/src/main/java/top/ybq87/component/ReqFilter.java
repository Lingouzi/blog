package top.ybq87.component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * 添加filter, 多个filter ,使用@order(index) 指定顺序
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/31 13:28
 */
@Component
public class ReqFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("uri:" + req.getRequestURI());
        chain.doFilter(request, response);
    }
}
