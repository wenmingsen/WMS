package com.csg.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.statistics.job.FundComputingJob;
import com.csg.statistics.job.PointsEarningsComputingJob;
import com.csg.statistics.job.UserMonitorComputingJob;
import com.csg.statistics.job.UserRetentionRecordComputingJob;
import com.csg.statistics.job.VisitorRegionJob;
import com.csg.statistics.service.DrtFinPoEarningsComputingRecordService;
import com.csg.statistics.service.DrtOprPointsRuleService;

@RestController
@RequestMapping("/statistics")
public class DemoController {
	
	@Autowired
	private DrtOprPointsRuleService drtOprPointsRuleService;
	
	@Autowired
	private PointsEarningsComputingJob pointsEarningsComputingJob;
	
	@Autowired
	private UserMonitorComputingJob userMonitorComputingJob;
	
	@Autowired
	private UserRetentionRecordComputingJob userRetentionRecordComputingJob;
	
	@Autowired
	private DrtFinPoEarningsComputingRecordService drtFinPoEarningsComputingRecordService;
	
	@Autowired
	private VisitorRegionJob visitorRegionJob;

	@Autowired
	private FundComputingJob fundComputingJob;
	
	@RequestMapping("/demo")
	public String demo() throws Exception{
		return "欢迎使用统计微服务！";
	}
	
	@RequestMapping("userRetentionRecordComputingJob")
	public String userRetentionRecordComputingJob(){
		userRetentionRecordComputingJob.start();
		return "用户留存率计算任务开始";
	}
	
	@RequestMapping("pointsEarningsComputingJob")
	public String pointsEarningsComputing(){
		pointsEarningsComputingJob.pointsEarningsComputing();
		return "积分收益计算任务开始";
	}
	
	@RequestMapping("fundComputingJob")
	public String fundComputingJob() throws Exception{
		fundComputingJob.fundComputing();
		return "资金计算任务开始";
	}
	
	@RequestMapping("visitorRegionJob")
	public String ss(){
		visitorRegionJob.start();
		return "访客地域分布计算任务开始";
	}
	
	@RequestMapping("moneyConsume")
	public String moneyConsume(Long money) throws Exception{
		String poAccountId = "00ad042b3ade43ada66f8d38506e1e36";
		drtFinPoEarningsComputingRecordService.moneyConsume(poAccountId, money);
		return "扣款成功";
	}
	
}
