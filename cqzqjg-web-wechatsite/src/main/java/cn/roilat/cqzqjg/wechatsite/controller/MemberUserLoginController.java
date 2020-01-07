package cn.roilat.cqzqjg.wechatsite.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

import cn.roilat.cqzqjg.common.security.JwtAuthenticatioToken;
import cn.roilat.cqzqjg.common.util.PasswordUtils;
import cn.roilat.cqzqjg.common.util.SecurityUtils;
import cn.roilat.cqzqjg.common.util.WechatUtils;
import cn.roilat.cqzqjg.common.utils.IOUtils;
import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.common.vo.LoginBean;
import cn.roilat.cqzqjg.common.vo.ModifyPasswordBean;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;

/**
 * 登录控制器
 * 
 * @author Louis
 * @date Oct 29, 2018
 */
@RestController
@RequestMapping("auth")
public class MemberUserLoginController {

	private Logger log = LoggerFactory.getLogger(getClass());
	@Value("wechat.appId")
	private String wechatAppId;
	@Value("wechat.secretKey")
	private String wechatSecretKey;

	// test

	@Autowired
	private Producer producer;
	@Autowired
	private BizMemberUserService bizMemberUserService;
	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到验证码到 session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	@PostMapping(value = "/wechatLogin")
	public HttpResult wechatLogin(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		if (!StringUtils.isBlank(code)) {
			String openId = null;
			try {
				JSONObject json = WechatUtils.getWechatPageAccessToken(wechatAppId, wechatSecretKey, code);
				if (json != null) {
					if (json.getString("errcode") != null) {
						log.error("根据code获取openid和access_token错误：" + json.getString("errmsg"));
					}
					openId = json.getString("openid");
					if (!StringUtils.isBlank(openId)) {
						BizMemberUser user = bizMemberUserService.findByOpenId(openId);
						// 账号不存在、密码错误
						if (user == null) {
							return HttpResult.error("账号不存在");
						}

						// 账号锁定
						if (user.getIfLocked() == "1") {
							return HttpResult.error("账号已被锁定,请联系管理员");
						}
						// 系统登录认证
						JwtAuthenticatioToken token = SecurityUtils.loginWechat(request, user, authenticationManager);
						token.eraseCredentials();
						return HttpResult.ok(token);
					}
				}
			} catch (Exception e) {
				log.error("登录失败！", e);
			}
		}
		return HttpResult.error("登录失败！");
	}

	/**
	 * 密码登录
	 * 
	 * @param loginBean
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@PostMapping(value = "/login")
	public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
		if (loginBean == null) {
			return HttpResult.error("输入参数错误！");
		}
		String username = loginBean.getAccount();
		String password = loginBean.getPassword();
		String captcha = loginBean.getCaptcha();

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return HttpResult.error("用户名或密码为空！");
		}
		// 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
		Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		/*
		 * if (kaptcha == null) { return HttpResult.error("验证码已失效"); } if
		 * (!kaptcha.equals(captcha)) { return HttpResult.error("验证码不正确"); }
		 */

		// 用户信息
		BizMemberUser user = bizMemberUserService.findByLoginName(username);

		// 账号不存在、密码错误
		if (user == null) {
			return HttpResult.error("账号不存在");
		}

		if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
			return HttpResult.error("密码不正确");
		}

		// 账号锁定
		if (user.getIfLocked() == "1") {
			return HttpResult.error("账号已被锁定,请联系管理员");
		}

		// 系统登录认证
		user.setPassword(password);
		JwtAuthenticatioToken token = SecurityUtils.loginWechat(request, user, authenticationManager);
		token.eraseCredentials();
		return HttpResult.ok(token);
	}

	/**
	 * 修改密码接口
	 */
	@PostMapping(value = "/modifyPassword")
	public HttpResult modifyPassword(@RequestBody ModifyPasswordBean modifyPasswordBean, HttpServletRequest request) {
		if (modifyPasswordBean == null) {
			return HttpResult.error("输入参数错误！");
		}
		String username = modifyPasswordBean.getAccount();
		String oldPassword = modifyPasswordBean.getOldPassword();
		String newPassword = modifyPasswordBean.getNewPassword();
		String newPasswordConfirm = modifyPasswordBean.getNewPasswordConfirm();
		if (StringUtils.isBlank(username)) {
			return HttpResult.error("用户必须登录后，才能进行该操作！");
		}
		if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)
				|| StringUtils.isBlank(newPasswordConfirm)) {
			return HttpResult.error("原密码和新密码输入不能为空！");
		}
		if (!newPassword.equals(newPasswordConfirm)) {
			return HttpResult.error("新输入的密码不一致！");
		}
		// 用户信息
		BizMemberUser user = bizMemberUserService.findByLoginName(username);

		// 账号不存在、密码错误
		if (user == null) {
			return HttpResult.error("账号不存在");
		}
		if (!PasswordUtils.matches(user.getSalt(), oldPassword, user.getPassword())) {
			return HttpResult.error("原密码不正确");
		}
		String salt = user.getSalt();
		String password = PasswordUtils.encode(newPassword, salt);
		user.setPassword(password);
		return HttpResult.ok(bizMemberUserService.save(user));
	}

	/**
	 * 解除微信绑定接口
	 */
	@PostMapping(value = "/unbindWechatLogin")
	public HttpResult unbindWechatLogin(@RequestBody LoginBean loginBean, HttpServletRequest request) {
		if (loginBean == null) {
			return HttpResult.error("输入参数错误！");
		}
		String username = loginBean.getAccount();
		if (StringUtils.isBlank(username)) {
			return HttpResult.error("用户必须登录后，才能进行该操作！");
		}
		// 用户信息
		BizMemberUser user = bizMemberUserService.findByLoginName(username);
		// 账号不存在、密码错误
		if (user == null) {
			return HttpResult.error("账号不存在");
		}
		user.setIfWechatLogin("0");
		return HttpResult.ok(bizMemberUserService.save(user));
	}

	/**
	 * 微信绑定接口
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/bindWechatLogin")
	public HttpResult bindWechatLogin(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String userId = request.getParameter("userId");
		String errorInfo = null;
		if (!StringUtils.isBlank(code) && !StringUtils.isBlank(userId)) {
			String accessToken = null;
			String openId = null;
			try {
				JSONObject json = WechatUtils.getWechatPageAccessToken(wechatAppId, wechatSecretKey, code);
				if (json != null) {
					if (json.getString("errcode") != null) {
						log.error("根据code获取openid和access_token错误：" + json.getString("errmsg"));
					}
					accessToken = json.getString("access_token");
					openId = json.getString("openid");
				}
			} catch (Exception e) {
				errorInfo = "根据code获取openid和access_token失败。";
			}
			if (!StringUtils.isBlank(openId) && !StringUtils.isBlank(accessToken)) {
				try {
					JSONObject json = WechatUtils.getWechatUserInfo(accessToken, openId);
					if (json != null) {
						if (json.getString("errcode") != null) {
							log.error("根据openid和access_token获取用户信息错误：" + json.getString("errmsg"));
						}
						String nickname = json.getString("nickname");
						String headimgurl = json.getString("headimgurl");
						// 如果全部为空，则不行
						if (StringUtils.isBlank(nickname) && StringUtils.isBlank(headimgurl)) {
							errorInfo = "获取微信信息失败！";
						} else {
							BizMemberUser memberUser = new BizMemberUser();
							memberUser.setId(Long.parseLong(userId));
							memberUser.setAvatar(headimgurl);
							memberUser.setNickName(nickname);
							memberUser.setOpenId(openId);
							memberUser.setIfWechatLogin("1");
							int n = bizMemberUserService.save(memberUser);
							if (n <= 0) {
								errorInfo = "未查询到当前用户！";
							}
						}
					} else {
						errorInfo = "获取微信信息失败！";
					}
				} catch (Exception e) {
					errorInfo = "根据code获取openid和access_token失败。";
				}
			} else {
				errorInfo = "根据code获取openid和access_token失败。";
			}
		} else {
			errorInfo = "code或者userId为空！";
		}
		if (StringUtils.isBlank(errorInfo)) {
			return HttpResult.ok("绑定成功！");
		} else {
			log.error(errorInfo);
			return HttpResult.error(String.format("绑定失败【%s】！", errorInfo));
		}

	}
}
