package com.csg.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.statistics.service.elec.ElecService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * Description: Api微服务调用测试 
 * @author 王东
 * @author 1.0
 * @date 2018年1月18日 上午10:36:54 
 *
 */
@RestController
@RequestMapping("/statistics")
public class ApiController {
	
	@Autowired
	private ElecService elecService;
	/** 测试统计调用电费微服务是否成功   */
	@RequestMapping("testStatisticsElec")
	@HystrixCommand(fallbackMethod="testStatisticsElecCallBack")
	public String testStatisticElec(){
		return elecService.testStatisticsElec();
	}
	public String testStatisticsElecCallBack(){
		return null;
	}

}
