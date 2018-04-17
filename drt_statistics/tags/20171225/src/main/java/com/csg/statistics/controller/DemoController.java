package com.csg.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.statistics.job.PointsEarningsComputingJob;
import com.csg.statistics.service.DrtFinPoEarningsComputingRecordService;
import com.csg.statistics.service.DrtOprPointsRuleService;

@RestController
@RequestMapping("statistics")
public class DemoController {
	
	@Autowired
	private DrtOprPointsRuleService drtOprPointsRuleService;
	
	@Autowired
	private PointsEarningsComputingJob pointsEarningsComputingJob;
	
	@Autowired
	private DrtFinPoEarningsComputingRecordService drtFinPoEarningsComputingRecordService;

	@RequestMapping("demo")
	public String demo() throws Exception{
		pointsEarningsComputingJob.pointsEarningsComputing();
		return "欢迎使用统计微服务！";
	}
	
	@RequestMapping("moneyConsume")
	public String moneyConsume(Long money) throws Exception{
		String poAccountId = "00ad042b3ade43ada66f8d38506e1e36";
		drtFinPoEarningsComputingRecordService.moneyConsume(poAccountId, money);
		return "扣款成功";
	}
	
}
