package top.ybq87.common.response;

/**
 * 封装API的错误码
 * @author 创建人：ly
 * @version 版本号：V1.0
 * @date 创建日期：2019/12/21 15:31
 */
public interface IErrorCode {
    
    /**
     * 状态码
     * @return
     */
    long getCode();
    
    /**
     * 返回说明文字
     * @return
     */
    String getMessage();
}
