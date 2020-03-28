package top.ybq87.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class UmsUserAuth implements Serializable {
    private Long id;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "注册类型：1：微信，2：qq，3：手机，4：email，5：用户名，6：github，7：微博。")
    private Integer type;

    @ApiModelProperty(value = "身份的唯一标识，登录账号、微信号openid，微博号，qq号等")
    private String appid;

    @ApiModelProperty(value = "站内密码，第三方为token")
    private String credential;

    @ApiModelProperty(value = "1：验证账号，0：未验证")
    private Integer verified;

    @ApiModelProperty(value = "微信的unionid，如果微信有开发平台，就有unionid")
    private String unionid;

    @ApiModelProperty(value = "对应的用户的id")
    private Long userId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", type=").append(type);
        sb.append(", appid=").append(appid);
        sb.append(", credential=").append(credential);
        sb.append(", verified=").append(verified);
        sb.append(", unionid=").append(unionid);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}