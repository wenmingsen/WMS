package com.csg.intshop.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {


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
		return "redirect:http://168.168.4.19:7777/fastdfs"+path;
	}
	
}
