package com.csg.statistics.util;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csg.statistics.common.config.AppConfig;

public class HttpUtil {
	private Logger log = LoggerFactory.getLogger(HttpUtil.class);
	public static String ERRORTYPE_SOCKETTIMEOUT = "1";// 处理超时
	public static String ERRORTYPE_CONNECT = "2";// 连接超时
	public static String ERRORTYPE_OTHER = "3";// 其他错误

	private Integer connectionTimeOut;
	private Integer socketTimeOut;

	public Integer getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(Integer connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public Integer getSocketTimeOut() {
		return socketTimeOut;
	}

	public void setSocketTimeOut(Integer socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
	}

	public HttpUtil() {
		// TODO Auto-generated constructor stub
	}

	public HttpUtil(Integer connectionTimeOut, Integer socketTimeOut) {
		super();
		this.connectionTimeOut = connectionTimeOut;
		this.socketTimeOut = socketTimeOut;
	}

	public String send(String json, String url) {
		String result = null;
		HttpPost httpClient = new HttpPost();
		JSONObject errJson = new JSONObject();

		Integer connectionTimeOut = this.connectionTimeOut == null ? Integer
				.parseInt(AppConfig.getProperty("http.connection.timeout",
						"10000")) : this.connectionTimeOut;// 链接超时
		Integer socketTimeOut = this.socketTimeOut == null ? Integer
				.parseInt(AppConfig.getProperty("http.socket.timeout", "10000"))
				: this.socketTimeOut;// 处理超时

		Long startTime = new Date().getTime();
		try {
			httpClient.setURI(new URI(url));
			httpClient.addHeader("Content-Type", "application/json");
			String str = json;
			HttpEntity entity = new StringEntity(str, "UTF-8");
			httpClient.setEntity(entity);
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeOut);// 链接超时
			httpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, socketTimeOut);// 处理超时
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpClient);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				result = EntityUtils.toString(httpEntity, "utf-8");// 取出应答字符串
			} else {
				log.info("httpResponse statuscode !=200");
				errJson.put("key", "接口调用出错!!!");
				result = errJson.toString();
				errJson.put("errType", ERRORTYPE_OTHER);
			}
		} catch (Exception e) {
			if (e instanceof java.net.SocketTimeoutException) {
				errJson.put("key", "处理超时");
				errJson.put("errType", ERRORTYPE_SOCKETTIMEOUT);
			} else if (e instanceof ConnectException) {
				errJson.put("key", "连接超时");
				errJson.put("errType", ERRORTYPE_CONNECT);
			} else {
				errJson.put("key", "接口调用出错!");
				errJson.put("errType", ERRORTYPE_OTHER);
			}
			log.error(e.getMessage());
			// e.printStackTrace();
			result = errJson.toString();
		}
		Long endTime = new Date().getTime();
		log.info("url[" + url + "].time[" + (endTime - startTime) + "]");
		return result;
	}

	public static void main(String[] args) throws Exception {
		HttpUtil httpUtil = new HttpUtil();
		String ps = "{\"state\":\"0\",\"message\":\"获取成功\",\"data\":[{\"orderNumber\":\"6\",\"pictureUrl\":\"/operationPic/homePage/photo/2017/11/28/20171128151959\",\"name\":\"好看1\",\"url\":\"http://www.360doc.com/content/16/0128/22/4330887_531347275.shtml\"},{\"orderNumber\":\"7\",\"pictureUrl\":\"/operationPic/appFind/photo/2017/11/27/20171127105111\",\"name\":\"lisi2222\",\"url\":\"http://www.360doc.com/content/16/0128/22/4330887_531347275.shtml\"},{\"orderNumber\":\"10\",\"pictureUrl\":null,\"name\":\"看修改时间\",\"url\":\"http://wenwen.sogou.com/z/q760808195.htm\"},{\"orderNumber\":\"10\",\"pictureUrl\":\"/operationPic/appFind/photo/2017/11/29/20171129102340\",\"name\":\"修改时间\",\"url\":\"http://www.360doc.com/content/16/0128/22/4330887_531347275.shtml\"}]}";
		System.out.println(httpUtil.send(ps,
				"http://localhost:8080/drtPersonCenter/yx/yhxxtb"));
		/*
		 * String str =
		 * "{\"comm_type\":\"0\",\"sys_code\":\"DRT\",\"req_date\":\"20171113\",\"req_time\":\"174801\",\"req_ssn\":\"20171113000000085\",\"tx_code\":\"PAY003\",\"user_id\":\"10086\",\"user_name\":\"赵晓晓\",\"is_bind\":\"0\",\"user_id_no\":\"440506199701080947\",\"user_phone\":\"13834522568\",\"user_account\":\"6284880086839407279\",\"bank_name\":\"中国农业银行\",\"remark\":\"\"}"
		 * ; JSONObject jsonObject = new JSONObject();
		 * jsonObject.put("comm_type", "0");// 通讯类型 jsonObject.put("sys_code",
		 * "DRT");// 系统标识 Date date = new Date(); String reqDate =
		 * DateUtils.formatDate(date, "yyyyMMdd"); String reqTime =
		 * DateUtils.formatDate(date, "HHmmss"); String reqssn =
		 * DateUtils.formatDate(date, "yyyyMMddHHmmssSSS"); Random random = new
		 * Random(); int next = random.nextInt(100); jsonObject.put("req_date",
		 * reqDate);// 请求日期
		 * 
		 * jsonObject.put("req_time", reqTime);// 请求时间 jsonObject.put("req_ssn",
		 * reqssn + next);// 请求流水 jsonObject.put("tx_code", "PAY003");// 交易代码
		 * jsonObject.put("user_id", "123123123");// 客户编号
		 * jsonObject.put("user_name", "陈于谦");// 客户名称 jsonObject.put("is_bind",
		 * "0");// 绑卡操作 jsonObject.put("user_id_no", "1");// 用户身份证号码
		 * jsonObject.put("user_phone", "1");// 用户手机号
		 * jsonObject.put("user_account", "1");// 用户银行卡号
		 * jsonObject.put("bank_name", "1");// 银行名称 jsonObject.put("remark",
		 * ""); HttpUtil http = new HttpUtil(); String str2 =
		 * http.send(jsonObject.toString(),
		 * "http://168.168.4.23:8080/drt/unifyPay/pay003.gpay");
		 * System.out.println(jsonObject.fromObject(str2).get("err_code"));
		 */
	}

	public static String getData(String url, String username, String password)
			throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials(
				username, password);
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader(BasicScheme.authenticate(creds, "UTF-8", false));
		httpGet.setHeader("Content-Type", "application/json");
		CloseableHttpResponse response = httpClient.execute(httpGet);

		try {
			if (response.getStatusLine().getStatusCode() != 200) {
				System.out
						.println("call http api to get rabbitmq data return code: "
								+ response.getStatusLine().getStatusCode()
								+ ", url: " + url);
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity);
			}
		} finally {
			response.close();
		}

		return "";
	}

}
