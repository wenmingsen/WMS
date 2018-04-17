package com.csg.statistics.util;

import java.net.ConnectException;
import java.net.URI;
import java.util.Date;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.DateUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csg.statistics.common.config.AppConfig;

public class HttpUtil {
	private Logger log = LoggerFactory.getLogger(HttpUtil.class);

	public String send(String json, String url) {
		String result = null;
		HttpPost httpClient = new HttpPost();
		JSONObject errJson = new JSONObject();
		Integer readimeOunt = Integer.parseInt(AppConfig.getProperty(
				"threeApiTimeOut", "10000"));
		try {
			httpClient.setURI(new URI(url));
			httpClient.addHeader("Content-Type", "application/json");
			String str = json;
			HttpEntity entity = new StringEntity(str, "UTF-8");
			httpClient.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom()
					.setSocketTimeout(readimeOunt)
					.setConnectTimeout(readimeOunt).build();// 设置请求和传输超时时间
			httpClient.setConfig(requestConfig);
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpClient);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity, "utf-8");// 取出应答字符串
			}
		} catch (Exception e) {
			if (e instanceof ConnectException) {
				errJson.put("key", "链接出错");
			} else {
				errJson.put("key", "接口调用出错!");
			}
			log.error(e.getMessage());
			// e.printStackTrace();
			result = errJson.toString();
		}
		return result;

	}

	public static void main(String[] args) {
		String str = "{\"comm_type\":\"0\",\"sys_code\":\"DRT\",\"req_date\":\"20171113\",\"req_time\":\"174801\",\"req_ssn\":\"20171113000000085\",\"tx_code\":\"PAY003\",\"user_id\":\"10086\",\"user_name\":\"赵晓晓\",\"is_bind\":\"0\",\"user_id_no\":\"440506199701080947\",\"user_phone\":\"13834522568\",\"user_account\":\"6284880086839407279\",\"bank_name\":\"中国农业银行\",\"remark\":\"\"}";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("comm_type", "0");// 通讯类型
		jsonObject.put("sys_code", "DRT");// 系统标识
		Date date = new Date();
		String reqDate = DateUtils.formatDate(date, "yyyyMMdd");
		String reqTime = DateUtils.formatDate(date, "HHmmss");
		String reqssn = DateUtils.formatDate(date, "yyyyMMddHHmmssSSS");
		Random random = new Random();
		int next = random.nextInt(100);
		jsonObject.put("req_date", reqDate);// 请求日期

		jsonObject.put("req_time", reqTime);// 请求时间
		jsonObject.put("req_ssn", reqssn + next);// 请求流水
		jsonObject.put("tx_code", "PAY003");// 交易代码
		jsonObject.put("user_id", "123123123");// 客户编号
		jsonObject.put("user_name", "陈于谦");// 客户名称
		jsonObject.put("is_bind", "0");// 绑卡操作
		jsonObject.put("user_id_no", "1");// 用户身份证号码
		jsonObject.put("user_phone", "1");// 用户手机号
		jsonObject.put("user_account", "1");// 用户银行卡号
		jsonObject.put("bank_name", "1");// 银行名称
		jsonObject.put("remark", "");
		HttpUtil http = new HttpUtil();
		String str2 = http.send(jsonObject.toString(),
				"http://168.168.4.23:8080/drt/unifyPay/pay003.gpay");
		System.out.println(jsonObject.fromObject(str2).get("err_code"));
	}

}
