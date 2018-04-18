package com.csg.intshop.filter;

import java.io.IOException;

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

import org.apache.commons.lang.ArrayUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.csg.common.config.BeanFactory;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.util.RedisUtil;
import com.csg.personcenter.entity.mybatis.DrtAccount;



@WebFilter(filterName = "filter0_ShopInfo", urlPatterns = "/ins/*", initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.html,*.jpg,*.bmp,*.png,*.css,*.ico") // 忽略资源
})
public class Filter0_ShopInfo extends BaseFilter implements Filter {
	private String exclusions;
	private  String stateLoser=SystemConstants.RESULT_CODE_FAILED;
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
		//过滤掉微服务调用
		
		Boolean isApi = requestURI.contains("/api/");
		Boolean isToMian = requestURI.contains("/toMain");
		Boolean isQueryshop = requestURI.contains("/ins/drtShopItem/queryshop");
		Boolean isQueryOrderGoods = requestURI.contains("/ins/drtShopItem/queryOrderGoods");
		//Cookie[] cookies = req.getCookies();
		if(isToMian||isQueryshop||isApi||isQueryOrderGoods){
			chain.doFilter(request, response);
			return;
		}
		String[] exclusions = this.exclusions.split(",");
		String servletPath = req.getServletPath();
		DrtAccount drtAccount = (DrtAccount)req.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		if(exclusions!=null&&ArrayUtils.contains(exclusions, servletPath)){
			chain.doFilter(request, response);
			return;
		}
		if(drtAccount==null){//没登陆的
			// String responseStr = jsonResponse.toJSONString(SystemConstants.LOGIN_TIME_OUT, "登录超时，请重新登录!", "");
			String responseStr =stateLoser;
			 writeText(rsp,responseStr);
			 return;
		}
		String reqSessionId = req.getSession().getId();
		String accountId = drtAccount.getAccountId();
		if(accountId == null || "".equals(accountId)){
			 //String responseStr = jsonResponse.toJSONString(SystemConstants.RESULT_CODE_FAILED, "accountId不存在!", "");
			String responseStr =stateLoser; 
			writeText(rsp,responseStr);
			 return;
		}
		//
		RedisUtil redisUtil = (RedisUtil) BeanFactory.getBean("redisUtil");
		//redis 上sessionId
		if(redisUtil!=null){
			Object obj = redisUtil.get(accountId);
			if(obj != null && reqSessionId != null){
				String redisSessionId = (String)obj ;
				//session 上sessionId
				//如果上面两个sessionId相同,则是同一个源头用户,如果不同,则是有新源头用户上线了,旧源头用户要重新登陆;
				if(!reqSessionId.equals(redisSessionId)){
					// 如果不同,则是新用户把旧用户挤出去
					//String responseStr = jsonResponse.toJSONString(SystemConstants.RESULT_CODE_UNUSUAL, "您的帐号已在其它设备登录,请重新登录!", "");
					String responseStr =stateLoser;
					writeText(rsp,responseStr);
					return;
				}
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

}
