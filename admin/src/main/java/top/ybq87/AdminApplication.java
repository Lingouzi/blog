package top.ybq87;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动入口 注意和 portal 项目的 路径对比
 * <p></p>
 * admin  项目包路径是 top.ybq87;
 * <p></p>
 * portal 项目路径是 top.ybq87.protal;
 * <p></p>
 * 2个项目都引用了redis模块,但是 protal 的注解是:@SpringBootApplication(scanBasePackages = "top.ybq87")
 * <p></p>
 * 因为redis在 top.ybq87.redis包下.RedisServiceImpl 注入的话必须要被扫到.
 * <p></p>
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/10 17:29
 */
@SpringBootApplication
public class AdminApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
