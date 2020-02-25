package cn.roilat.cqzqjg.wechatsite.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.roilat.cqzqjg.common.util.PasswordUtils;
import cn.roilat.cqzqjg.common.util.WechatUtils;
import cn.roilat.cqzqjg.common.utils.StringUtils;
import cn.roilat.cqzqjg.common.vo.ModifyPasswordBean;
import cn.roilat.cqzqjg.core.http.HttpResult;
import cn.roilat.cqzqjg.services.biz.model.BizMemberUser;
import cn.roilat.cqzqjg.services.biz.service.BizMemberCompanyService;
import cn.roilat.cqzqjg.services.biz.service.BizMemberUserService;
import cn.roilat.cqzqjg.services.biz.vo.BizMemberCompanyResp;

/**
 * @program: zqjg
 * @description: 我的页面
 * @author: liujing
 * @create: 2020-01-07 16:41
 **/
@RestController
@RequestMapping("my")
public class MyPageController {
	private Logger log = LoggerFactory.getLogger(getClass());
	@Value("${wx.mp.configs.appid}")
	private String wechatAppId;
	@Value("${wx.mp.configs.secret}")
	private String wechatSecretKey;

	@Autowired
	private BizMemberUserService bizMemberUserService;
	@Autowired
	private BizMemberCompanyService bizMemberCompanyService;

	@GetMapping(value = "/company/{id}")
	public HttpResult companyDetail(@PathVariable Long id) throws IOException {
		BizMemberUser bizMemberUser = bizMemberUserService.findById(id);
		if (null == bizMemberUser) {
			return HttpResult.error("用户不存在");
		}
		if (null == bizMemberUser.getCompanyId()) {
			return HttpResult.error("用户绑定单位信息为空");
		}
		BizMemberCompanyResp bizMemberCompanyResp = bizMemberCompanyService
				.findByIdResp(Long.valueOf(bizMemberUser.getCompanyId()));
		if (null == bizMemberCompanyResp) {
			return HttpResult.error("单位不存在");
		}
		return HttpResult.ok("查询成功", bizMemberCompanyResp);
	}

	/*
	 * @GetMapping(value = "/removeWechat/{id}") public HttpResult
	 * removeWechat(@PathVariable Long id) throws IOException { Boolean result =
	 * bizMemberUserService.removeWechat(id); if (result) { return
	 * HttpResult.ok("解绑成功"); } else { return HttpResult.ok("解绑失败"); } }
	 */

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
	@RequestMapping(value = "/unbindWechatLogin")
	public HttpResult unbindWechatLogin(HttpServletRequest request) {
		String account = request.getParameter("account");
		if (account == null) {
			return HttpResult.error("输入参数错误！");
		}
		// 用户信息
		BizMemberUser user = bizMemberUserService.findByLoginName(account);
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
		Map<String, Object> map = new HashMap<String, Object>();
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
							memberUser.setWechat(nickname);
							memberUser.setOpenId(openId);
							memberUser.setIfWechatLogin("1");
							map.put("avatar", headimgurl);
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
			return HttpResult.ok("绑定成功！", map);
		} else {
			log.error(errorInfo);
			return HttpResult.error(String.format("绑定失败【%s】！", errorInfo));
		}

	}

}
