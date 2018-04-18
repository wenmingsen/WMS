/**
 * 
 */
package com.csg.intshop.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csg.common.config.AppConfig;

/**
 * @author 李城
 *
 */
@Controller
@RequestMapping("/ins/home")
public class DrtShopHomeController {

	/**
	 * 进入商城首页
	 * 
	 * @return 商城首页地址
	 * */
	@RequestMapping(value = "/toMain")
	public String toMyBidMessage(HttpServletRequest request,HttpServletResponse respone) {
		String cookieName="photoIp";
		String photoIp=AppConfig.getProperty("fileServer");
		Cookie[] cookies = request.getCookies();
		boolean isPhotoIp=false;
		if(cookies!=null){
			for(Cookie cookie: cookies){
				if(cookie.getName().equals(cookieName)){
					cookie.setValue(photoIp);
					cookie.setPath("/");
					//cookie.setMaxAge(0);
					//respone.addCookie(cookie);
					isPhotoIp=true;
				}
			}
		}else{
			Cookie newCookie=new Cookie(cookieName,photoIp);
			newCookie.setPath("/");
			respone.addCookie(newCookie);
			isPhotoIp=true;
		}
		if(!isPhotoIp){
			Cookie newCookie=new Cookie(cookieName,photoIp);
			newCookie.setPath("/");
			respone.addCookie(newCookie);
		}
		return "redirect:/pages/mall_index.html";
	}
}
