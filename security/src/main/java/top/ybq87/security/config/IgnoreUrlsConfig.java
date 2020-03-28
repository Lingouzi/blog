package top.ybq87.security.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 用于配置不需要保护的资源路径
 * @author 创建人：ly 664162337@qq.com
 */
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    
    private List<String> urls = new ArrayList<>();
    
    public List<String> getUrls() {
        return urls;
    }
    
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}