package com.csg.intshop.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.common.enums.ErrorEnum;
import com.csg.intshop.entity.DrtMallApplication;
import com.csg.intshop.service.DrtMallApplicationService;
import com.csg.intshop.util.HttpUtil.JsonResult;
import com.github.pagehelper.StringUtil;

/**
 * 	电子商城接口 工具类
 *
 * @author  曾令鹏
 * @since   jdk1.8
 * @version 2018年1月23日 曾令鹏
 */
@Component
public class ElectronicMallApiUtil {

	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private DrtMallApplicationService drtMallApplicationService;
	
	
	private final static String TOKEN_KEY = "SHOP_AUTH_TOKEN";

	public final static String BASE_URL = "http://121.15.129.226:8290/gateway/interface/acintegral";

	/**
	 * 获取电子商城认证token
	 * 
	 * @return
	 */
	public String getToken() {
		return getToken(true);
	}
	
	/**
	 * 获取电子商城认证token
	 * 
	 * @param isReadCache 是否读取缓存
	 * @return
	 */
	public String getToken(boolean isReadCache) {
		if(isReadCache && redisUtil != null){
			// 读取redis
			Object objToken = redisUtil.get(TOKEN_KEY);
			if (objToken != null) {
				return objToken.toString();
			}
		}
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
				String.valueOf(jsonObject.get("statusCode")))) {
			String token = String.valueOf(jsonObject.get("token"));
			long expire = NumberUtils.toLong(String.valueOf(jsonObject.get("expire")), 0);
			String currTime = String.valueOf(jsonObject.get("currTime"));
			// 远程南网商城生成token时间
			Date currTimeDate = DateTimeUtils.converStringToDate(currTime, "yyyy-MM-dd HH:mm:ss");
			// 系统时间
			Date systemTimeDate = new Date();
			// 时间差
			long timeDifference = systemTimeDate.getTime() - currTimeDate.getTime();
			
			if (StringUtils.isNotBlank(token) && expire > timeDifference / 1000) {
				// 设置redis
				if(redisUtil != null){
					redisUtil.set(TOKEN_KEY, token, expire);
				}
			}
			return token;
		}
		return null;
	}
	
	/**
	 * 校验token是否正确
	 * @param token token
	 * @param requestTime 请求时间  格式：yyyy-MM-dd HH:mm:ss
	 * @return 成功返回true，反之false
	 */
	public boolean checkToken(String token,String requestTime){
		try{
			if(StringUtil.isNotEmpty(token)){
				Date requestDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(requestTime);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("token", token);
				map.put("invalidTime", Long.valueOf(new SimpleDateFormat("yyyyMMddHHmmss").format(requestDate)));
				List<DrtMallApplication> drtMallApplicationList = drtMallApplicationService.selectListByinterface(map);
				if(!CollectionUtils.isEmpty(drtMallApplicationList)){
					return true;
				}
			}
		}catch(Exception e){
			//服务器异常。
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 签名
	 * 
	 * @param paramMap 待签名参数
	 * @return
	 */
	public String sign(LinkedHashMap<String, String> paramMap){
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
	public JSONObject postJson(ElectronicMallApiUrlEnum urlEnum, LinkedHashMap<String, String> paramMap) {
		String timestamp = DateTimeUtils.converDateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
		paramMap.put("timestamp", timestamp);
		paramMap.put("token", getToken());
		paramMap.put("authSign", sign(paramMap));
		// 请求地址
		String url = BASE_URL + urlEnum.getValue();
		JSONObject jsonObject = HttpUtil.build().postJson(url, paramMap);
		// 若是token失效，则重试一次
		if(jsonObject == null || ErrorEnum.TOKEN_INVALID.getResultCode().equals(String.valueOf(jsonObject.get("resultCode")))){
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
//		// 0000054662,0000054660,0000054661
//		ElectronicMallApiUtil electronicMallApiUtil = new ElectronicMallApiUtil();
//		LinkedHashMap<String, String> linked = new LinkedHashMap<>();
//		linked.put("poolNum", "POOL000005");
//		linked.put("skuNum", "100");
//		linked.put("pageNum", "1");
//		//linked.put("integralOrderCode", "1");
//		//linked.put("skuCode", "0000054662,0000054660,0000054661");
//		JSONObject postJson2 = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.GET_SKU_CODE, linked);
//		System.out.println(postJson2.toString());
		HttpUtil httpUtil = HttpUtil.build();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("username", "DZSCTest");
		paramMap.put("password", "62c728b7407c54ae");
		paramMap.put("requestTime", "2018-01-30 15:18:01");
		paramMap.put("authSign", "55177171584926c8cb6be39fe0bd334b");
		JSONObject post = httpUtil.postJson("http://176i27z104.imwork.net:25749/drtShop/ins/api/getToken", paramMap);
		System.out.println(post.toString());
		
		
	}

}
