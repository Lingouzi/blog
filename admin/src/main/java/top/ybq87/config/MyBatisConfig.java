package top.ybq87.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 类名：MyBatisConfig <p>
 * 功能说明：<p>
 * MyBatis配置类
 *
 * @author 创建人：ly 664162337@qq.com
 * **************************修订记录*************************************
 * @date 创建日期：2020/1/10 17:46
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"top.ybq87.mbg.mapper", "top.ybq87.dao"})
public class MyBatisConfig {
}
