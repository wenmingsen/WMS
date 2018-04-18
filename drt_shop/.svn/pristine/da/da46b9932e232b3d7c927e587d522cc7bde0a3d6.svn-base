package com.csg.intshop.controller.mall;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.util.ResultMapHelper;


/**
 * DrtMallCart 积分商城未登录处理
 *
 * @author  李城
 * @since   1.8
 * @version 2018年01月24日 李城
 */
@RestController
@RequestMapping("/ins/mall/loginerro/*")
public class LoginerroController{
	
	//日志
	private Logger log = LoggerFactory.getLogger(LoginerroController.class);

	/**
	 * 处理当前登陆用户失败
	 * 
	 * @param session 会话
	 * @return 结果集
	 * @throws Exception 
	 */
	@RequestMapping("loginerroDeal/{type}")
	public Map<String,Object> loginerroDeal(HttpSession session,@PathVariable String type) throws Exception{
		if("fail".equals(type)){
			log.warn("未获取登陆信息：未登录或登陆超时");
			return ResultMapHelper.result(SystemConstants.LOGIN_FAIL, "未登录或登陆超时！");
			
		}else{
			log.warn("未获取登陆信息：在第三方设备登录");
			return ResultMapHelper.result(SystemConstants.LOGIN_SQUEEZE, "在第三方设备登录！");
		}
		
	}
	
}
	

