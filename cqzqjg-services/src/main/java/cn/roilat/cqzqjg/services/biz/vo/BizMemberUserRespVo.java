package cn.roilat.cqzqjg.services.biz.vo;

/**
 * @program: zqjg
 * @description: 用户vo
 * @author: liujing
 * @create: 2020-01-07 17:00
 **/
public class BizMemberUserRespVo {
    /** 主键 */
    private Long id;
    /** 会员单位名称 */
    private String companyName;
    /** 会员单位ID */
    private Integer companyId;
    /** 登录名 */
    private String loginName;
    /** 昵称 */
    private String nickName;
    /** 头像 */
    private String avatar;
    /** 微信号 */
    private String wechat;
    /** 是否绑定微信 */
    private String ifWechatLogin;
    /** 审批状态(0待审核,1审核不通过2审核通过) */
    private String approveStatus;
    /** 是否已锁定 */
    private String ifLocked;
    /** 创建时间 */
    private String createTime;
    /** 更新时间 */
    private String lastUpdateTime;
    /** 是否删除 -1：已删除 0：正常 */
    private Integer delFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getIfWechatLogin() {
        return ifWechatLogin;
    }

    public void setIfWechatLogin(String ifWechatLogin) {
        this.ifWechatLogin = ifWechatLogin;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getIfLocked() {
        return ifLocked;
    }

    public void setIfLocked(String ifLocked) {
        this.ifLocked = ifLocked;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
