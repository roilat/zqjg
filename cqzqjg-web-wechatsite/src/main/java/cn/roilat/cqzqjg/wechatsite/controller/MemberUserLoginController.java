package cn.roilat.cqzqjg.wechatsite.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

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
import cn.roilat.cqzqjg.common.vo.UserBean;
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
	private static final String WECHAT_PAGE_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=123";
	private static final String WECHAT_AUTHORIZE_TYPE_BASE = "snsapi_base";
	private static final String WECHAT_AUTHORIZE_TYPE_USERINFO = "snsapi_userinfo";
	@Value("${wechat.appId}")
	private String wechatAppId;
	@Value("${wechat.secretKey}")
	private String wechatSecretKey;
	@Value("${system.host}")
	private String hostUrl;
	@Value("${system.webPort}")
	private String webPort;
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
		log.info(text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	@RequestMapping(value = "/wechatLogin")
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
					JwtAuthenticatioToken result = new JwtAuthenticatioToken(null, null);
					openId = json.getString("openid");
					if (!StringUtils.isBlank(openId)) {
						result.setOpenId(openId);
						BizMemberUser user = bizMemberUserService.findByOpenId(openId);
						// 账号不存在、密码错误
						if (user == null) {
							return HttpResult.error("账号不存在").setData(result);
						}
						result = new JwtAuthenticatioToken(user);
						result.eraseCredentials();

						// 账号锁定
						if (user.getIfLocked() == "1") {
							return HttpResult.error("账号已被锁定,请联系管理员").setData(result);
						}

						// 账号不是审核已通过
						if (!"2".equals(user.getApproveStatus())) {
							result.setAccountAppStatus(user.getApproveStatus());
							result.setApproveDesc(user.getApproveDesc());
							return HttpResult.error().setData(result);
						}

						// 系统登录认证
						JwtAuthenticatioToken token = SecurityUtils.loginWechat(request, user, authenticationManager);
						token.eraseCredentials();
						token.setAccountAppStatus(user.getApproveStatus());
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
		if (kaptcha == null) {
			return HttpResult.error("验证码已失效");
		}
		if (!kaptcha.equals(captcha)) {
			return HttpResult.error("验证码不正确");
		}

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

	@PostMapping(value = "/newUser")
	public HttpResult newUser(@RequestBody UserBean userBean, HttpServletRequest request) throws IOException {
		log.info("收到注册用户请求： " + userBean.toString());
		if (StringUtils.isBlank(userBean.getAccount())) {
			return HttpResult.error("用户名不能为空");
		}
		if (StringUtils.isBlank(userBean.getCompanyName())) {
			return HttpResult.error("公司名不能为空");
		}
		if (StringUtils.isBlank(userBean.getPassword())) {
			return HttpResult.error("密码不能为空");
		}
		if (StringUtils.isBlank(userBean.getPhoneNumber())) {
			return HttpResult.error("手机号不能为空");
		}
		BizMemberUser user = bizMemberUserService.findByLoginName(userBean.getAccount());
		if (null != user) {
			return HttpResult.error("登录账号重复");
		}

		// 盐
		String salt = PasswordUtils.getSalt();
		// 密码加密
		String password = PasswordUtils.encode(userBean.getPassword(), salt);

		BizMemberUser bizMemberUser = new BizMemberUser();
		if (userBean.getUserId() != null) {
			try {
				bizMemberUser.setId(Long.parseLong(userBean.getUserId()));
			} catch (NumberFormatException e) {
			}
		}
		bizMemberUser.setLoginName(userBean.getAccount());
		bizMemberUser.setCompanyName(userBean.getCompanyName());
		bizMemberUser.setPassword(password);
		bizMemberUser.setPhoneNumber(userBean.getPhoneNumber());
		bizMemberUser.setApproveStatus("0");
		bizMemberUser.setIfLocked("0");
		bizMemberUser.setIfWechatLogin("0");
		bizMemberUser.setSalt(salt);
		bizMemberUser.setOpenId(userBean.getOpenId());
		bizMemberUser.setCreateTime(new Date());
		// 保存用户
		int i = bizMemberUserService.save(bizMemberUser);
		if (i > 0) {
			return HttpResult.ok("账号申请已发送成功，请等待后台管理员复核。");
		} else {
			log.error("数据保存失败");
			return HttpResult.error("申请失败，请重试");
		}
	}
	@RequestMapping("toWechatLogin")
	public void toWechatLogin(HttpServletResponse response) throws UnsupportedEncodingException, IOException {
		String redirectUrl = "http://%s/index.html#/wxAuth";
		String url = String.format(redirectUrl, hostUrl);
		response.sendRedirect(String.format(WECHAT_PAGE_AUTHORIZE_URL, wechatAppId,URLEncoder.encode(url, "utf-8"),WECHAT_AUTHORIZE_TYPE_BASE));
	}

	@RequestMapping("toBindWechat")
    public void toBindWechat(HttpServletResponse response) throws UnsupportedEncodingException, IOException {
		String redirectUrl = "http://%s/index.html#/wxAuth";
		String url = String.format(redirectUrl, hostUrl);
    	response.sendRedirect(String.format(WECHAT_PAGE_AUTHORIZE_URL, wechatAppId,URLEncoder.encode(url, "utf-8"),WECHAT_AUTHORIZE_TYPE_USERINFO));
    }
}
