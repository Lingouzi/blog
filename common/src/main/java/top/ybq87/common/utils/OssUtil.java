package top.ybq87.common.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyun.oss.model.OSSObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.ybq87.common.config.OssClientFactory;

/**
 * 阿里云 oss 工具类
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/11 13:46
 */
@Component
public class OssUtil {
    
    /**
     * 存储的空间
     */
    private static String ALIYUN_OSS_BUCKETNAME;
    
    /**
     * 限制上传文件大小
     */
    private static String ALIYUN_OSS_MAX_FILE_SIZE;
    
    /**
     * 上传后的回调,
     */
    private static String ALIYUN_OSS_CALLBACK;
    
    /**
     * 存储的目录前缀, 后面加日期分目录
     */
    private static String ALIYUN_OSS_DIR_PREFIX;
    
    /**
     * 最后得到的图片的前缀,
     */
    private static String IMG_PREFIX;
    
    /**
     * 如果是注入到static 属性中, 那么必须使用 set的方式.
     * @param aliyunOssBucketname
     */
    @Value("${aliyun.oss.bucketName:ybq87}")
    public void setAliyunOssBucketname(String aliyunOssBucketname) {
        ALIYUN_OSS_BUCKETNAME = aliyunOssBucketname;
    }
    
    @Value("${aliyun.oss.maxSize}")
    public void setAliyunOssMaxFileSize(String aliyunOssMaxFileSize) {
        ALIYUN_OSS_MAX_FILE_SIZE = aliyunOssMaxFileSize;
    }
    
    @Value("${aliyun.oss.callback}")
    public void setAliyunOssCallback(String aliyunOssCallback) {
        ALIYUN_OSS_CALLBACK = aliyunOssCallback;
    }
    
    @Value("${aliyun.oss.dir.prefix:blog/images/}")
    public void setAliyunOssDirPrefix(String aliyunOssDirPrefix) {
        ALIYUN_OSS_DIR_PREFIX = aliyunOssDirPrefix;
    }
    
    @Value("${aliyun.oss.img.prefix:http://img.ybq87.top}")
    public void setImgPrefix(String imgPrefix) {
        IMG_PREFIX = imgPrefix;
    }
    
    private static OssClientFactory config;
    
    @Autowired
    private void setConfig(OssClientFactory config) {
        OssUtil.config = config;
    }
    
    /**
     * 通过网络流上传文件
     * @param url        URL
     * @param objectName 上传文件目录和（包括文件名）例如“test/index.html”
     * @return 文件路径
     */
    public static String uploadByNetworkStream(String url, String objectName) {
        try {
            InputStream inputStream = new URL(url).openStream();
            objectName = validateObjectName(objectName);
            config.getClient().putObject(ALIYUN_OSS_BUCKETNAME, objectName, inputStream);
            boolean exist = config.getClient().doesObjectExist(ALIYUN_OSS_BUCKETNAME, objectName);
            if (exist) {
                return IMG_PREFIX + objectName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 判断是否是在规定的目录
     * @param objectName
     */
    private static String validateObjectName(String objectName) {
        if (!objectName.startsWith(ALIYUN_OSS_DIR_PREFIX)) {
            // 区分日期
            objectName =
                    ALIYUN_OSS_DIR_PREFIX + (ALIYUN_OSS_DIR_PREFIX.endsWith("/") ? "" : "/") + DateUtil.format(new Date(), "yyyy/MMdd")
                            + "/" + objectName;
        }
        return objectName;
    }
    
    /**
     * 通过输入流上传文件
     * @param inputStream 输入流
     * @param objectName  上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return 文件路径
     */
    public static String uploadByInputStream(InputStream inputStream, String objectName) {
        try {
            objectName = validateObjectName(objectName);
            config.getClient().putObject(ALIYUN_OSS_BUCKETNAME, objectName, inputStream);
            boolean exist = config.getClient().doesObjectExist(ALIYUN_OSS_BUCKETNAME, objectName);
            if (exist) {
                return IMG_PREFIX + objectName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 通过file上传文件
     * @param file       上传的文件
     * @param objectName 上传文件目录和（包括文件名） 例如“test/a.jpg”
     * @return 文件路径
     */
    public static String uploadByFile(File file, String objectName) {
        try {
            objectName = validateObjectName(objectName);
            config.getClient().putObject(ALIYUN_OSS_BUCKETNAME, objectName, file);
            boolean exist = config.getClient().doesObjectExist(ALIYUN_OSS_BUCKETNAME, objectName);
            if (exist) {
                return IMG_PREFIX + objectName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * 根据key删除oss服务器上的文件
     * @param objectName 文件路径/名称，例如“test/a.txt”
     * @return void            返回类型
     */
    public static void deleteFile(String objectName) {
        config.getClient().deleteObject(ALIYUN_OSS_BUCKETNAME, objectName);
    }
    
    /**
     * 根据key获取服务器上的文件的输入流
     * @param key 文件路径和名称
     * @return InputStream    文件输入流
     */
    public static InputStream getInputStreamByOss(String key) {
        InputStream content = null;
        try {
            OSSObject ossObj = config.getClient().getObject(ALIYUN_OSS_BUCKETNAME, key);
            content = ossObj.getObjectContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
    
    /**
     * 获取文件详细信息
     * @param url object的地址
     * @return
     */
    public static JSONObject imageInfo(String url) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("x-oss-process", "image/info");
        String response = HttpUtil.get(url, map);
        // 请求阿里云的接口返回object相关的属性
        // {
        //    "FileSize": {"value": "91197"},
        //    "Format": {"value": "jpg"},
        //    "ImageHeight": {"value": "700"},
        //    "ImageWidth": {"value": "700"},
        //    "ResolutionUnit": {"value": "1"},
        //    "XResolution": {"value": "1/1"},
        //    "YResolution": {"value": "1/1"}}
        JSONObject resp = JSONUtil.parseObj(response);
        String fileSize = resp.getJSONObject("FileSize").getStr("value");
        String imageHeight = resp.getJSONObject("ImageHeight").getStr("value");
        String imageWidth = resp.getJSONObject("ImageWidth").getStr("value");
        String format = resp.getJSONObject("Format").getStr("value");
        return JSONUtil.createObj().put("url", url)
                .put("fileSize", fileSize)
                .put("height", imageHeight)
                .put("width", imageWidth)
                .put("format", format);
    }
    
    /**
     * 获取到文件名称
     * @param source
     * @return
     */
    public static String getObjectName(String source) {
        source = source.contains("?") ? source.split("\\?")[0] : source;
        // 去掉 / 符号
        return source.substring(source.lastIndexOf("/") + 1);
    }
}
