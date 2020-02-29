package cn.roilat.cqzqjg.common.vo;

/**
 * @program: zqjg
 * @description: 用户注册
 * @author: liujing
 * @create: 2020-01-07 09:38
 **/
public class UserBean {
	private Long id;
	private Integer companyId;
	private String companyName;
	private String loginName;
	private String password;
	private String phoneNumber;
	// 当前登录管理员
	private String adminUser;
	private String nickName;
	private String openId;
	private String userId;
	private String createBy;
	private String lastUpdateBy;
	/**
	 * 是否绑定微信
	 */
	private String ifWechatLogin;
	// 头像
	private String avatar;
	// 微信号
	private String wechat;
	private String ifLocked;

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getIfWechatLogin() {
		return ifWechatLogin;
	}

	public void setIfWechatLogin(String ifWechatLogin) {
		this.ifWechatLogin = ifWechatLogin;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(String adminUser) {
		this.adminUser = adminUser;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getIfLocked() {
		return ifLocked;
	}

	public void setIfLocked(String ifLocked) {
		this.ifLocked = ifLocked;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

}
