package com.csg.statistics.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csg.statistics.common.enums.ElectronicMallApiUrlEnum;
import com.csg.statistics.common.enums.ErrorEnum;


/**
 * http工具类
 * 
 *
 * @author 曾令鹏
 * @since jdk1.8
 * @version 2018年1月22日 曾令鹏
 */
public class HttpUtil {
	
	private Logger log = LoggerFactory.getLogger(HttpUtil.class);

	private final static String DEFAULT_VALUE_JOSN = "{}";

	/** 处理超时 */
	public static String ERRORTYPE_SOCKETTIMEOUT = "1";
	/** 连接超时 */
	public static String ERRORTYPE_CONNECT = "2";
	/** 其他错误 */
	public static String ERRORTYPE_OTHER = "3";

	/** 默认连接超时时间：30秒 */
	private final static int DEFAULT_CONNECT_TIMEOUT = 30000;

	/** 默认处理超时时间：30秒 */
	private final static int DEFAULT_SOCKET_TIMEOUT = 30000;

	/** 连接超时时间 */
	private int connectTimeout;

	/** 连接超时时间 */
	private int socketTimeout;
	

	/**
	 * 
	 * 构造函数
	 * 
	 * @param connectTimeout
	 *            连接超时时间
	 * @param socketTimeout
	 *            处理超时时间
	 */
	private HttpUtil(int connectTimeout, int socketTimeout) {
		super();
		this.connectTimeout = connectTimeout;
		this.socketTimeout = socketTimeout;
	}

	public static HttpUtil build() {
		return build(DEFAULT_CONNECT_TIMEOUT, DEFAULT_SOCKET_TIMEOUT);
	}

	public static HttpUtil build(int connectTimeout, int socketTimeout) {
		return new HttpUtil(connectTimeout, socketTimeout);
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	/**
	 * post 提交 json数据
	 * 
	 * @param url
	 *            请求地址
	 * 
	 * @param paramMap
	 *            参数集
	 */
	public JSONObject postJson(String url, Map<String, String> paramMap) {
		String json = null;
		if (paramMap == null || paramMap.size() == 0) {
			json = DEFAULT_VALUE_JOSN;
		} else {
			json = JSONObject.fromObject(paramMap).toString();
		}
		HttpPost httpPost = new HttpPost();
		// 设置请求地址
		httpPost.setURI(URI.create(url));

		// 设置请求头
		httpPost.addHeader(HTTP.CONTENT_TYPE,
				ContentType.APPLICATION_JSON.getMimeType());
		// 设置请求参数配置
		setConfig(httpPost);

		// 设置参数
		HttpEntity httpEntity = new StringEntity(json,
				ContentType.APPLICATION_JSON);
		httpPost.setEntity(httpEntity);

		// 执行
		return execute(httpPost);
	}

	/**
	 * post 提交
	 * 
	 * @param url
	 *            请求地址
	 * @param paramMap
	 *            参数集
	 * @throws URISyntaxException
	 */
	public JSONObject post(String url, Map<String, String> paramMap)
			throws UnsupportedEncodingException {
		HttpPost httpPost = new HttpPost(url);

		// paramMap转为请求参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (paramMap != null && paramMap.size() > 0) {
			Set<Entry<String, String>> entrySet = paramMap.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}

		// 设置请求参数配置
		setConfig(httpPost);

		// 设置参数
		HttpEntity httpEntity = new UrlEncodedFormEntity(nvps);
		httpPost.setEntity(httpEntity);

		// 执行
		return execute(httpPost);
	}

	/**
	 * get 提交
	 * 
	 * @param url
	 *            请求地址
	 * @param paramMap
	 *            参数集
	 */
	public JSONObject get(String url, Map<String, String> paramMap) {
		HttpGet httpGet = new HttpGet();

		if (paramMap != null && paramMap.size() > 0) {
			String params = buildParam(paramMap);
			url = url + "?" + params;
		}

		// 设置请求地址
		httpGet.setURI(URI.create(url));

		// 设置请求参数配置
		setConfig(httpGet);

		// 执行
		return execute(httpGet);
	}

	/**
	 * 设置请求
	 * 
	 * @param request
	 *            请求对象
	 */
	public void setConfig(HttpRequestBase request) {
		// 设置请求
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(connectTimeout)
				.setSocketTimeout(socketTimeout).build();
		// 设置其他一些参数配置
		request.setConfig(config);
	}

	/**
	 * 执行请求
	 * 
	 * @param request
	 */
	private JSONObject execute(HttpUriRequest request) {
		JSONObject jsonObject = null;
		CloseableHttpResponse response = null;
		HttpEntity httpEntity = null;
		try {
			// 创建连接对象
			CloseableHttpClient httpclient = HttpClients.createDefault();
			// 执行
			response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				httpEntity = response.getEntity();
				// 取出应答字符串
				String result = EntityUtils.toString(httpEntity, Consts.UTF_8);
				jsonObject = JSONObject.fromObject(result);
			}
		} catch (Exception e) {
			log.error("执行失败--{}", e);
			if (e instanceof java.net.SocketTimeoutException) {
				// 处理超时
				JsonResult jsonResult = JsonResult.build(ErrorEnum.SOCKET_TIMEOUT);
				jsonObject = JSONObject.fromObject(jsonResult);
			} else if (e instanceof ConnectException) {
				// 连接超时
				JsonResult jsonResult = JsonResult.build(ErrorEnum.CONNECT_TIMEOUT);
				jsonObject = JSONObject.fromObject(jsonResult);
			}else{
				// 接口调用出错
				JsonResult jsonResult = JsonResult.build(ErrorEnum.SOCKET_TIMEOUT);
				jsonObject = JSONObject.fromObject(jsonResult);
			}
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if(httpEntity != null){
					// 确保完全消耗
					EntityUtils.consume(httpEntity);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(jsonObject == null){
			JsonResult jsonResult = JsonResult.build(ErrorEnum.SERVER_EXCEPTION);
			jsonObject = JSONObject.fromObject(jsonResult);
		}
		return jsonObject;
	}

	/**
	 * 将参数拼接后返回
	 * 
	 * @param param
	 *            参数
	 * @return 如：a=1&b=2&c=3
	 */
	private static String buildParam(Map<String, String> param) {
		List<String> keys = new ArrayList<String>(param.keySet());
		//Collections.sort(keys);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = param.get(key);
			sb.append(buildKeyValue(key, value, false));
			sb.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = param.get(tailKey);
		sb.append(buildKeyValue(tailKey, tailValue, false));

		return sb.toString();
	}

	/**
	 * 拼接键值对
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param isEncode
	 *            是否URL编码
	 * @return
	 */
	private static String buildKeyValue(String key, String value,
			boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}
	
	 public static class JsonResult {

		/** 状态码（Y;N） */
		private String success;
		
		/** 异常信息提示 */
		private String resultMessage;
		
		/** 异常代码KEY */
		private String resultCode;
		
		private JsonResult(String success, String resultCode, String resultMessage){
			this.success = success;
			this.resultCode = resultCode;
			this.resultMessage = resultMessage;
		}
		
		public static JsonResult build(ErrorEnum errorEnum){
			return new JsonResult("N", errorEnum.getResultCode(), errorEnum.getResultMessage());
		}

		public String getSuccess() {
			return success;
		}

		public void setSuccess(String success) {
			this.success = success;
		}

		public String getResultMessage() {
			return resultMessage;
		}

		public void setResultMessage(String resultMessage) {
			this.resultMessage = resultMessage;
		}

		public String getResultCode() {
			return resultCode;
		}

		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}
		
	}
	

	public static void main(String[] args) {

		Map<String, String> paramMap = new HashMap<>();
		String username = "JFSC";
		String password = "a7a0bd9ef71d8cf9";
		String requestTime = DateTimeUtils.converDateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		String authSign = Md5Utils.encodeMd5ToLowerCase(username + password + requestTime + password);
		
		paramMap.put("username", username);
		paramMap.put("password", password);
		paramMap.put("requestTime", requestTime);
		paramMap.put("authSign", authSign);
		JSONObject postJson = HttpUtil.build().postJson(ElectronicMallApiUtil.BASE_URL + ElectronicMallApiUrlEnum.GET_TOKEN.getValue(), paramMap);
		System.out.println(postJson.toString());

	}

}
