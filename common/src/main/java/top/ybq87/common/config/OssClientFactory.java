package top.ybq87.common.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云 oss 配置
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/10 21:48
 */
@Getter
@Configuration
public class OssClientFactory {
    
    @Value("${aliyun.oss.endpoint:oss-cn-shenzhen.aliyuncs.com}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.accessKeyId}")
    private String ALIYUN_OSS_ACCESSKEYID;
    @Value("${aliyun.oss.accessKeySecret}")
    private String ALIYUN_OSS_ACCESSKEYSECRET;
    
    private volatile static OSS client;
    
    public OSS getClient() {
        if (client == null) {
            synchronized (OssClientFactory.class) {
                if (client == null) {
                    client = new OSSClientBuilder().build(ALIYUN_OSS_ENDPOINT, ALIYUN_OSS_ACCESSKEYID, ALIYUN_OSS_ACCESSKEYSECRET);
                    // 服务器关闭, 停止 时,关闭ossclient
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> client.shutdown()));
                }
            }
        }
        return client;
    }
    
}
