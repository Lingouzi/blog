package top.ybq87.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import top.ybq87.common.response.CommonResult;
import top.ybq87.common.utils.OssUtil;
import top.ybq87.dto.OssParam;

/**
 * oss 文件上传控制器
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/4 10:08
 */
@Api(tags = "OssController", value = "Oss对象存储管理")
@Slf4j
@RestController
@RequestMapping("/oss")
public class OssController {
    
    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult upload(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            String url = OssUtil.uploadByInputStream(file.getInputStream(), filename);
            // 得到图片的高宽等信息.
            return CommonResult.success(OssUtil.imageInfo(url));
        } catch (Exception e) {
            log.info("上传发生错误: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }
    
    @ApiOperation("百度编辑器的配置文件config.json加载.")
    @RequestMapping(value = "/ue/upload", method = RequestMethod.GET)
    public Object ueditorConfig(HttpServletRequest req) {
        // 这里返回config.json 内容, 本系统在前端加载,所以这里只做演示.
        return JSONUtil.createObj().put("loadConfig", "SUCCESS");
    }
    
    @ApiOperation("兼容百度编辑器ueditor的上传")
    @RequestMapping(value = "/ue/upload", method = RequestMethod.POST)
    public Object uploadForUeditor(@RequestParam("action") String action, HttpServletRequest req) {
        try {
            if ("catchimage".equalsIgnoreCase(action)) {
                // 从网址粘贴的图片上传
                String[] sources = req.getParameterValues("source[]");
                // 使用有序列表存储,
                JSONArray array = JSONUtil.createArray();
                JSONObject object;
                for (String source : sources) {
                    // 处理图片,获取到图片名称,
                    String objectName = OssUtil.getObjectName(source);
                    // 通过网址上传图片到阿里云.
                    String upload = OssUtil.uploadByNetworkStream(source, objectName);
                    object = JSONUtil.createObj().put("source", source).put("url", upload).put("state", "SUCCESS");
                    array.add(object);
                }
                return JSONUtil.createObj()
                        .put("list", array)
                        .put("state", "SUCCESS");
            } else if ("uploadimage".equalsIgnoreCase(action)) {
                // 图片选择上传 直接接受单文件上传
                List<MultipartFile> files = new ArrayList<>();
                if (req instanceof MultipartHttpServletRequest) {
                    MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) req;
                    //返回的数量与前端input数量相同, 返回的字符串即为前端input标签的name
                    MultipartFile file = multipartHttpServletRequest.getFile("file");
                    String filename = file.getOriginalFilename();
                    String url = OssUtil.uploadByInputStream(file.getInputStream(), filename);
                    return JSONUtil.createObj()
                            .put("state", "SUCCESS")
                            .put("url", url)
                            .put("title", filename)
                            .put("original", filename);
                }
            } else if ("listimage".equalsIgnoreCase(action)) {
                // 获取图片列表, 支持分页; 本项目业务不提供此功能, 所以这里直接返回空
                String start = req.getParameter("start");
                String size = req.getParameter("size");
                //{
                //    "state": "SUCCESS",
                //    "list": [{
                //        "url": "upload/1.jpg"
                //    }, {
                //        "url": "upload/2.jpg"
                //    }, ],
                //    "start": 20,
                //    "total": 100
                //}
                return JSONUtil.createObj()
                        .put("state", "SUCCESS")
                        .put("start", "0")
                        .put("list", "[]")
                        .put("total", "0");
            }
        } catch (Exception e) {
            log.info("上传发生错误: {}！", e.getMessage());
        }
        // 百度接口返回值规范: http://fex.baidu.com/ueditor/#dev-request_specification
        // {
        //    "state": "SUCCESS",
        //    "url": "upload/demo.jpg",
        //    "title": "demo.jpg",
        //    "original": "demo.jpg"
        //}
        return JSONUtil.createObj().put("state", "图片上传失败");
    }
    
    @ApiOperation("文件删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestBody OssParam param) {
        try {
            OssUtil.deleteFile(param.getObjectName());
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }
}
