package com.csg.intshop.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.csg.common.config.AppConfig;
import com.csg.common.config.CommonContants;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.util.RedisUtil;
import com.csg.personcenter.entity.mybatis.DrtAccount;



@WebFilter(filterName = "filter0_ShopInfo", urlPatterns = "/ins/*", initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.html,*.jpg,*.bmp,*.png,*.css,*.ico") // 忽略资源
})
public class Filter0_ShopInfo extends BaseFilter implements Filter {
	
	/** 防止重复提交地址 */
	private final static String[] sameUrlDataArr = {"/ins/mall/order/exchangeOrder", "/ins/mall/order/saveOrder"};
	
	private String exclusions;
	private Logger log = LoggerFactory.getLogger(Filter0_ShopInfo.class);
	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,		
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rsp = (HttpServletResponse)response;
		String requestURI = req.getRequestURI();
		String url=req.getContextPath()+"/ins/mall/loginerro/loginerroDeal";
		//过滤掉微服务调用
		
		Boolean isApi = requestURI.contains("/api/");
		Boolean isToMian = requestURI.contains("/toMain");
		Boolean isQueryshop = requestURI.contains("/ins/drtShopItem/queryshop");
		Boolean isQueryOrderGoods = requestURI.contains("/ins/drtShopItem/queryOrderGoods");
		Boolean isQueryMallItem = requestURI.contains("/ins/mall/item/");
		Boolean isLoginerro = requestURI.contains("/ins/mall/loginerro/loginerroDeal");
		//Cookie[] cookies = req.getCookies();
		if(isToMian||isQueryshop||isApi||isQueryOrderGoods||isQueryMallItem||isLoginerro){
			chain.doFilter(request, response);
			return;
		}
		String[] exclusions = this.exclusions.split(",");
		String servletPath = req.getServletPath();
		HttpSession session=req.getSession();
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		//JSonResponse jsonResponse = new JSonResponse();
		if(exclusions!=null&&ArrayUtils.contains(exclusions, servletPath)){
			chain.doFilter(request, response);
			return;
		}
		if(drtAccount==null){//没登陆的
			// String responseStr = jsonResponse.toJSONString(SystemConstants.LOGIN_TIME_OUT, "登录超时，请重新登录!", "");
			/*String responseStr =stateLoser;
			 writeText(rsp,responseStr);*/
			log.info("登录超时，请重新登录!");
			String type="fail";
			rsp.sendRedirect(url+"/"+type);
		}
		String accountId = drtAccount.getAccountId();
		if(accountId == null || "".equals(accountId)){
			 //String responseStr = jsonResponse.toJSONString(SystemConstants.RESULT_CODE_FAILED, "accountId不存在!", "");
			//String responseStr =stateLoser; 
			log.info("用户不存在！");
			String type="fail";
			rsp.sendRedirect(url+"/"+type);
		}
		//判断是否多设备登陆
		boolean loginTF = loginTF(accountId, req);
		if (loginTF) {
			//String responseStr = jsonResponse.toJSONString(SystemConstants.RESULT_CODE_UNUSUAL,"您的帐号已在其它设备登录,请重新登录!", "");
			/*String responseStr =stateLoser; 
			writeText(rsp, responseStr);*/
			log.info("您的帐号已在其它设备登录,请重新登录!");
			String type="squeeze";
			rsp.sendRedirect(url+"/"+type);
		}
		// 防止重复提交
		if(isContains(sameUrlDataArr, req.getServletPath())){
			if(repeatDataValidator(req, accountId)){
				return;
			}
		}
		
		chain.doFilter(request, response);
		
	}

	ApplicationContext ctx =null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		 ctx = WebApplicationContextUtils
			.getWebApplicationContext(servletContext);
		 this.exclusions = filterConfig.getInitParameter("exclusions");
		
	}
	
	protected boolean loginTF(String accountId,HttpServletRequest req) throws IOException {
		String reqSessionId = req.getSession().getId();
		
		RedisUtil redisUtil = (RedisUtil) ctx.getBean("redisUtil");
		
		// redis 上sessionId
		Object obj = redisUtil.get(accountId+":sessionId");
		if (obj == null || obj == "") {
			redisUtil.set(accountId+":sessionId", reqSessionId,(long) CommonContants.springRedisSessionTimeOut);
			obj = redisUtil.get(accountId+":sessionId");
		}
		
		if (obj != null && reqSessionId != null&&!AppConfig.getProperty("isOpenYC", true)) {
			String redisSessionId = (String) obj;
			// session 上sessionId
			// 如果上面两个sessionId相同,则是同一个源头用户,如果不同,则是有新源头用户上线了,旧源头用户要重新登陆;
			if (!reqSessionId.equals(redisSessionId)) {
				// 如果不同,则是新用户把旧用户挤出去
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证同一个url数据是否重复提交，重复返回true
	 * 
	 * @param request 当期请求
	 * @param accountId 当前用户ID
	 * @return 重复返回true，反之false
	 */
	private synchronized boolean repeatDataValidator(HttpServletRequest request, String accountId) {
		RedisUtil redisUtil = (RedisUtil) ctx.getBean("redisUtil");
		String key = accountId + ":" + request.getServletPath() + "repeatData";
		String params = JSONObject.fromObject(
				request.getParameterMap()).toString();
		String url = request.getRequestURI();
		Map<String, String> map = new HashMap<String, String>();
		map.put(url, params);
		String nowUrlParams = map.toString();//
		Object preUrlParams = redisUtil.get(key);
		if (preUrlParams == null){
			// 如果上一个数据为null,表示还没有访问页面
			redisUtil.set(key, nowUrlParams);
			return false;
		} else {
			// 否则，已经访问过页面
			if (preUrlParams.toString().equals(nowUrlParams)) {
				// 如果上次url+数据和本次url+数据相同，则表示重复提交数据
				return true;
			} else {
				// 如果上次 url+数据 和本次url加数据不同，则不是重复提交
				redisUtil.set(key, nowUrlParams);
				return false;
			}

		}
	}
	
	/**
	 * 判断数组是否包含指定串
	 * @param strArr 数组
	 * @param value 指定串
	 * @return 存在返回true，反之false
	 */
	private static boolean isContains(String[] strArr, String value){
		if(value != null && strArr != null & strArr.length > 0){
			for(int i = 0; i < strArr.length; i++){
				if(value.equals(strArr[i])){
					return true;
				}
			}
		}
		return false;
	}

}