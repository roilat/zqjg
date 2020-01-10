package cn.roilat.cqzqjg.common.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;

/**
 * 自定义令牌对象
 * 
 * @author Louis
 * @date Nov 21, 2018
 */
public class JwtAuthenticatioToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	private String token;
	private String companyName;
	private String wechat;
	private String avatar;
	private String openId;
	private String nickName;
	private Long userId;
	private String ifWechatLogin;
	private String accountAppStatus;

	public JwtAuthenticatioToken(BizMemberUser memberUser) {
		super(memberUser.getLoginName(), memberUser.getPassword());
		wechat = memberUser.getWechat();
		companyName = memberUser.getCompanyName();
		avatar = memberUser.getAvatar();
		nickName = memberUser.getNickName();
		openId = memberUser.getOpenId();
		userId = memberUser.getId();
		ifWechatLogin = memberUser.getIfWechatLogin();
	}

	public JwtAuthenticatioToken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public JwtAuthenticatioToken(Object principal, Object credentials, String token) {
		super(principal, credentials);
		this.token = token;
	}

	public JwtAuthenticatioToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities, String token) {
		super(principal, credentials, authorities);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIfWechatLogin() {
		return ifWechatLogin;
	}

	public void setIfWechatLogin(String ifWechatLogin) {
		this.ifWechatLogin = ifWechatLogin;
	}

	public String getAccountAppStatus() {
		return accountAppStatus;
	}

	public void setAccountAppStatus(String accountAppStatus) {
		this.accountAppStatus = accountAppStatus;
	}

}
