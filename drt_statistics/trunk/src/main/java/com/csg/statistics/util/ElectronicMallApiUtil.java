package com.csg.statistics.util;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.csg.statistics.common.enums.ElectronicMallApiUrlEnum;
import com.csg.statistics.common.enums.ErrorEnum;
import com.csg.statistics.util.HttpUtil.JsonResult;

public class ElectronicMallApiUtil {

	public final static String BASE_URL = "http://121.15.129.226:8290/gateway/interface/acintegral";

	/**
	 * 获取电子商城认证token
	 * 
	 * @return
	 */
	public static String getToken() {
		return getToken(true);
	}
	
	/**
	 * 获取电子商城认证token
	 * 
	 * @param isReadCache 是否读取缓存
	 * @return
	 */
	public static String getToken(boolean isReadCache) {
		// 获取token
		Map<String, String> paramMap = new HashMap<>();
		String username = "JFSC";
		String password = "a7a0bd9ef71d8cf9";
		String requestTime = DateTimeUtils.converDateToString(new Date(),
				"yyyy-MM-dd HH:mm:ss");
		String authSign = Md5Utils.encodeMd5ToLowerCase(username + password
				+ requestTime + password);

		paramMap.put("username", username);
		paramMap.put("password", password);
		paramMap.put("requestTime", requestTime);
		paramMap.put("authSign", authSign);
		JSONObject jsonObject = HttpUtil.build()
				.postJson(
						BASE_URL + ElectronicMallApiUrlEnum.GET_TOKEN.getValue(),
						paramMap);
		System.out.println(jsonObject.toString());
		if (ErrorEnum.SUCCESS.getResultCode().equals(
				String.valueOf(jsonObject.getString("statusCode")))) {
			String token = String.valueOf(jsonObject.getString("token"));
			return token;
		}
		return null;
	}
	
	/**
	 * 签名
	 * 
	 * @param paramMap 待签名参数
	 * @return
	 */
	public static String sign(LinkedHashMap<String, String> paramMap){
		String content = "";
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			if(!"authSign".equals(entry.getKey())){
				content += entry.getValue();
			}
		}
		return Md5Utils.encodeMd5ToLowerCase(content);
	}
	
	/**
	 * post提交 json数据
	 * 
	 * @param urlEnum 电子商城接口地址 枚举
	 * @param paramMap 请求参数，参数顺序严格按照接口文档字段顺序（无须传递timestamp、token、authSign字段）
	 * @return 返回net.sf.json.JSONObject对象，该对象不可能为空，具体值请参照接口文档
	 */
	public static JSONObject postJson(ElectronicMallApiUrlEnum urlEnum, LinkedHashMap<String, String> paramMap) {
		String timestamp = DateTimeUtils.converDateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		paramMap.put("timestamp", timestamp);
		paramMap.put("token", getToken());
		paramMap.put("authSign", sign(paramMap));
		// 请求地址
		String url = BASE_URL + urlEnum.getValue();
		JSONObject jsonObject = HttpUtil.build().postJson(url, paramMap);
		// 若是token失效，则重试一次
		if(jsonObject == null || ErrorEnum.TOKEN_INVALID.getResultCode().equals(String.valueOf(jsonObject.getString("resultCode")))){
			paramMap.put("token", getToken(false));
			paramMap.put("authSign", sign(paramMap));
			jsonObject = HttpUtil.build().postJson(url, paramMap);
		}
		if(jsonObject == null){
			JsonResult jsonResult = JsonResult.build(ErrorEnum.OTHER_EXCEPTION);
			jsonObject = JSONObject.fromObject(jsonResult);
		}
		return jsonObject;
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
		paramMap.put("skuCode", "888856874521488888");
		JSONObject postJson = HttpUtil.build().postJson(ElectronicMallApiUtil.BASE_URL + ElectronicMallApiUrlEnum.GET_ORDER_WAYBILL_DETAIL.getValue(), paramMap);
		System.out.println(postJson.toString());
		
	}

}
