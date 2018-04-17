package com.csg.statistics.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
public class DemoController {

	@RequestMapping("demo")
	public String demo(){
		return "欢迎使用统计微服务！";
	}
	
}
