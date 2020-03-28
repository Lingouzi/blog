package top.ybq87.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.model.v20151123.SingleSendMailResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里邮件服务工具类 每日免费额度是200,所以要做一个限制,如果超过200个发送,那么就关闭邮件功能
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/2/23 15:08
 */
@Slf4j
@Component
public class EmailUtil {
    
    private static String ALIYUN_DM_ACCESSKEYID;
    
    private static String ALIYUN_DM_ACCESSKEYSECRET;
    
    
    @Value("${aliyun.oss.accessKeyId}")
    public void setALIYUN_DM_ACCESSKEYID(String ALIYUN_DM_ACCESSKEYID) {
        EmailUtil.ALIYUN_DM_ACCESSKEYID = ALIYUN_DM_ACCESSKEYID;
    }
    
    @Value("${aliyun.oss.accessKeySecret}")
    public void setALIYUN_DM_ACCESSKEYSECRET(String ALIYUN_DM_ACCESSKEYSECRET) {
        EmailUtil.ALIYUN_DM_ACCESSKEYSECRET = ALIYUN_DM_ACCESSKEYSECRET;
    }
    
    /**
     * 发送邮件给注册用户,
     *
     * @param toEmail 注册用户的邮箱地址
     * @param code    生成的 验证码
     * @return 是否发送成功, true/false
     */
    public static boolean sendRegistCodeMailToRegister(String toEmail, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ALIYUN_DM_ACCESSKEYID, ALIYUN_DM_ACCESSKEYSECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        
        SingleSendMailRequest request = new SingleSendMailRequest();
        request.setSysRegionId("cn-hangzhou");
        request.setAccountName("no-reply@email.ybq87.top");
        request.setAddressType(1);
        request.setReplyToAddress(false);
        request.setToAddress(toEmail);
        request.setSubject("【ybq87】邮箱验证码消息");
        String html =
                "<div style=\"color: rgb(51, 51, 51); font-family: &quot;lucida Grande&quot;, Verdana; white-space: normal; margin: 6px 0px;\" data-spm-anchor-id=\"5176.2020520150.112.i12.6fc07528yRYiWf\">\n"
                        + "    <p style=\"font-size: 14px; line-height: 23.8px;\">\n"
                        + "        朋友:\n"
                        + "    </p>\n"
                        + "    <p style=\"font-size: 14px; line-height: 23.8px;\" data-spm-anchor-id=\"5176.2020520150.112.i6.6fc07528yRYiWf\">\n"
                        + "        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您好！感谢您注册我的个人网站！\n"
                        + "    </p>\n"
                        + "    <p style=\"font-size: 14px; line-height: 23.8px;\" data-spm-anchor-id=\"5176.2020520150.112.i7.6fc07528yRYiWf\">\n"
                        + "        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您正在进行邮箱注册个人账号，本次请求的验证码为：\n"
                        + "    </p>\n"
                        + "    <p style=\"line-height: 23.8px;\" data-spm-anchor-id=\"5176.2020520150.112.i7.6fc07528yRYiWf\">\n"
                        + "        <strong style=\"color: rgb(51, 102, 255);\" data-spm-anchor-id=\"5176.2020520150.112.i5.6fc07528yRYiWf\"><span style=\"font-size: 14px;\">&nbsp; &nbsp; &nbsp;</span><span style=\"font-size:24px\"> </span><span style=\"font-size:18px\">"
                        + code + "</span></strong>\n"
                        + "    </p>\n"
                        + "</div>\n"
                        + "<div style=\"color: rgb(51, 51, 51); font-family: &quot;lucida Grande&quot;, Verdana; font-size: 14px; white-space: normal; margin: 6px 0px;\">\n"
                        + "    <p style=\"line-height: 23.8px;\" data-spm-anchor-id=\"5176.2020520150.112.i11.6fc07528yRYiWf\">\n"
                        + "        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请在验证码输入框输入此验证码，以完成验证。\n"
                        + "    </p>\n"
                        + "    <p style=\"line-height: 23.8px;\" data-spm-anchor-id=\"5176.2020520150.113.i3.5c4775288rhdM7\">\n"
                        + "        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您不执行这项服务，请忽略此邮件，由此给您带来的不便请谅解！\n"
                        + "    </p>\n"
                        + "</div>\n"
                        + "<p style=\"line-height: 20px; color: rgb(51, 51, 51); font-family: &quot;lucida Grande&quot;, Verdana; font-size: 14px; white-space: normal; margin-top: 0px; margin-bottom: 0px;\">\n"
                        + "    此致\n"
                        + "</p>\n"
                        + "<p style=\"line-height: 20px; color: rgb(51, 51, 51); font-family: &quot;lucida Grande&quot;, Verdana; font-size: 14px; white-space: normal; margin-top: 0px; margin-bottom: 18px;\" data-spm-anchor-id=\"5176.2020520150.112.i4.6fc07528yRYiWf\">\n"
                        + "    YBQ &amp; LY\n"
                        + "</p>\n"
                        + "<p style=\"line-height: 20px; color: rgb(51, 51, 51); font-family: &quot;lucida Grande&quot;, Verdana; font-size: 14px; white-space: normal; margin-top: 0px; margin-bottom: 18px;\">\n"
                        + "    （系统邮件，请勿回复。)\n"
                        + "</p>";
        request.setHtmlBody(html);
        // 发件人名称, 用户在收到邮件的时候会显示:
        request.setFromAlias("林二狗");
        try {
            SingleSendMailResponse response = client.getAcsResponse(request);
            JSONObject obj = JSONUtil.parseObj(response);
            log.info("验证码邮件已经成功发送, 返回:" + obj.toString());
            return true;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            log.error("ErrCode:" + e.getErrCode());
            log.error("ErrMsg:" + e.getErrMsg());
            log.error("RequestId:" + e.getRequestId());
        }
        return false;
    }
    
}
