package top.ybq87.common.response;

/**
 * 枚举了一些常用API操作码
 * @author 创建人：ly
 * @version 版本号：V1.0
 * @date 创建日期：2019/12/21 15:31
 */
public enum ResultCode implements IErrorCode {
    /**
     * 操作成功, 200
     */
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败, 500
     */
    FAILED(500, "操作失败"),
    /**
     * 参数检验失败, 404
     */
    VALIDATE_FAILED(404, "参数检验失败"),
    /**
     * 暂未登录或token已经过期, 401
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    /**
     * 没有相关权限, 403
     */
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String message;
    
    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
    
    @Override
    public long getCode() {
        return code;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
}