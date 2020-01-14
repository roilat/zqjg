package cn.roilat.cqzqjg.common.util;

import com.alibaba.fastjson.JSONObject;

import cn.roilat.cqzqjg.common.utils.StringUtils;

public class WechatUtils {
	private static final int timeout = 5000;
	private static final String WECHAT_PAGE_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	private static final String WECHAT_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

	/**
	 * 获取微信网页访问token
	 * 
	 * @param appId
	 * @param secret
	 * @param code
	 * @throws Exception
	 */
	public static JSONObject getWechatPageAccessToken(String appId, String secret, String code) throws Exception {
		String url = String.format(WECHAT_PAGE_ACCESS_TOKEN_URL, appId, secret, code);
		String resultString = HttpUtils.sendGet(url, timeout, null);
		if (StringUtils.isBlank(resultString)) {
			return null;
		}
//		 JSONObject json = JSONObject.parseObject(resultString);
		JSONObject json = JSONObject.parseObject(
				"{	\"access_token\": \"ACCESS_TOKEN\",	\"expires_in\": 7200,	\"refresh_token\": \"REFRESH_TOKEN\",	\"openid\": \"OPENID\",	\"scope\": \"SCOPE\"}");
		return json;
	}

	/**
	 * 获取微信用户信息
	 * 
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getWechatUserInfo(String accessToken, String openId) throws Exception {
		String url = String.format(WECHAT_USER_INFO_URL, accessToken, openId);
		String resultString = HttpUtils.sendGet(url, timeout, null);
		if (StringUtils.isBlank(resultString)) {
			return null;
		}
//		JSONObject json = JSONObject.parseObject(resultString);
		JSONObject json = JSONObject.parseObject(
				"{\"openid\": \" OPENID\",\"nickname\": \"NICKNAME\",\"headimgurl\": \"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46\"}");
		return json;
	}
//	{   
//		  "openid":" OPENID",
//		  "nickname": NICKNAME,
//		  "sex":"1",
//		  "province":"PROVINCE",
//		  "city":"CITY",
//		  "country":"COUNTRY",
//		  "headimgurl":       "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
//		  "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
//		  "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
//		}
}
