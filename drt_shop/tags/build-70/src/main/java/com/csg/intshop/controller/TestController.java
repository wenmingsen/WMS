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
}
