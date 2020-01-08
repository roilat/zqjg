package cn.roilat.cqzqjg.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.roilat.cqzqjg.core.http.HttpResult;

/**
 * HTTP工具类
 * 
 * @author Louis
 * @date Oct 29, 2018
 */
public class HttpUtils {

	/**
	 * 获取HttpServletRequest对象
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * 输出信息到浏览器
	 * 
	 * @param response
	 * @param message
	 * @throws IOException
	 */
	public static void print(HttpServletResponse response, int code, String msg) throws IOException {
		response.setContentType("application/json; charset=utf-8");
		HttpResult result = HttpResult.error(code, msg);
		String json = JSONObject.toJSONString(result);
		response.getWriter().print(json);
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 发送post请求
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendPost(String url, int timeout, Map<String, ?> params) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).build();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				nameValuePairs.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
			}
		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		post.setConfig(requestConfig);
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = HttpClients.createDefault();
			response = client.execute(post);
			String responseText = EntityUtils.toString(response.getEntity(), "UTF-8");
			return responseText;
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
	}

	/**
	 * 发送post请求
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String sendGet(String url, int timeout, Map<String, ?> params) throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout).build();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				nameValuePairs.add(new BasicNameValuePair(key, String.valueOf(params.get(key))));
			}
		}

		String uri = EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

		HttpGet post = new HttpGet(url.contains("?") ? (url + "&" + uri) : (url + "?" + uri));

		post.setConfig(requestConfig);
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = HttpClients.createDefault();
			response = client.execute(post);
			String responseText = EntityUtils.toString(response.getEntity(), "UTF-8");
			return responseText;
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
	}
}
