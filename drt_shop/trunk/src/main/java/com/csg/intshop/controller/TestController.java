package com.csg.intshop.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csg.intshop.service.core.AddressCore;
import com.csg.intshop.service.core.EarningsCore;
import com.csg.intshop.util.HttpUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class TestController {
	
	
	@Autowired
	private EarningsCore earningsCore;
	@Autowired
	private AddressCore addressCore;
	
	/** 测试调用电费服务是否成功测试  */
	@RequestMapping("/testShopElec")
	@HystrixCommand(fallbackMethod = "testShopElecCallBack")
	public @ResponseBody String testShopElec(){
		return earningsCore.testShopElec();
	}
	public String testShopElecCallBack(){
		return "失败";
	}
	
	/** 商城调用资产服务是否成功测试  */
	@RequestMapping("/testShopFinance")
	@HystrixCommand(fallbackMethod = "testShopFinanceCallBack")
	public @ResponseBody String testShopFinance(){
		return addressCore.testShopFinance();
	}
	public String testShopFinanceCallBack(){
		return "失败";
	}
	

	@RequestMapping(value = "/index")
	public String toMyBidMessage(HttpServletRequest request ) {
		Cookie[] cookie = request.getCookies();
		System.out.print(cookie);
		return "redirect:/pages/mall_index.html";
	}
	
	@RequestMapping(value={"/fastdfs/**", "/pages/null/fastdfs/**"})
	public String fastdfs(HttpServletRequest request){
		
		String path = request.getServletPath();
		String[] split = path.split("fastdfs");
		if(split.length == 2){
			path = split[1];
		}
		String ipAddress = HttpUtil.getIpAddress(request);
		if(ipAddress != null && ipAddress.startsWith("192.168")){
			return "redirect:http://168.168.4.19:7777/fastdfs"+path;
		}
		return "redirect:http://119.29.100.104:7777/fastdfs"+path;
	}
	
	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request ) {
		return "redirect:/pages/mallGoods/main.html";
	}
	
}
