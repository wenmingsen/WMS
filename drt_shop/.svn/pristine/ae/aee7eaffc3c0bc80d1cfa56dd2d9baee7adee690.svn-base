package com.csg.intshop.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csg.common.config.AppConfig;
import com.csg.common.config.BeanFactory;
import com.csg.common.config.CommonContants;
import com.csg.intshop.util.RedisUtil;

import java.io.IOException;

public class BaseFilter {
	/**
	 * 输出text数据
	 * @param response
	 * @param obj
	 * @throws java.io.IOException
	 * @throws java.io.IOException
	 */
	protected void writeText(HttpServletResponse response, String obj) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(obj);
		response.getWriter().close();
	}
	
	protected boolean loginTF(String accountId,HttpServletRequest req) throws IOException {
		RedisUtil redisUtil = (RedisUtil) BeanFactory.getBean("redisUtil");
		// redis 上sessionId
		Object obj = redisUtil.get(accountId+":sessionId");
		String reqSessionId = req.getSession().getId();
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
}
