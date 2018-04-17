package com.csg.statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.statistics.job.FundComputingJob;
import com.csg.statistics.job.InsideMessageComputingJob;
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
	private InsideMessageComputingJob nsideMessageComputingJob;
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
	
	@RequestMapping("userMonitorLoginJob")
	public String user(){
		userMonitorComputingJob.userMonitorLoginComputing();
		return "用户监控登陆次数统计";
	}

	
	@RequestMapping("insertTimingNews")
	public String insertTimingNewsComputingJob() throws Exception{
		nsideMessageComputingJob.insertTimingNewsComputingJob();
		return "定时发送消息插入成功";
	}
	
	@RequestMapping("insertHourLoopNews")
	public String insertHourLoopNewsComputingJob() throws Exception{
		nsideMessageComputingJob.insertHourLoopNewsComputingJob();
		return "小时循环消息插入成功";
	}
	
	@RequestMapping("insertDayLoopNews")
	public String insertDayLoopNewsComputingJob() throws Exception{
		nsideMessageComputingJob.insertDayLoopNewsComputingJob();
		return "天循环消息插入成功";
	}
	
	@RequestMapping("insertMonthLoopNews")
	public String insertMonthLoopNewsComputingJob() throws Exception{
		nsideMessageComputingJob.insertMonthLoopNewsComputingJob();
		return "月循环消息插入成功";
	}
	
	@RequestMapping("deleteWeekNews")
	public String deleteWeekNewsComputingJob() throws Exception{
		nsideMessageComputingJob.deleteWeekNewsComputingJob();
		return "删除最近一周站内消息记录成功";
	}
}
