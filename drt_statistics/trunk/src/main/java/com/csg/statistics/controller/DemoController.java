package com.csg.statistics.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.statistics.job.FundComputingJob;
import com.csg.statistics.job.InsideMessageComputingJob;
import com.csg.statistics.job.JobRegister;
import com.csg.statistics.job.PointsEarningsComputingJob;
import com.csg.statistics.job.UserMonitorComputingJob;
import com.csg.statistics.job.UserRetentionRecordComputingJob;
import com.csg.statistics.job.VisitorRegionJob;
import com.csg.statistics.service.DrtElecDealPkwjService;
import com.csg.statistics.service.DrtFinPoEarningsComputingRecordService;
import com.csg.statistics.service.DrtOprPointsRuleService;
import com.csg.statistics.service.EarningsExpiryService;
import com.csg.statistics.service.elec.ElecService;
import com.csg.statistics.service.shop.ShopService;

@RestController
@RequestMapping("/statistics")
public class DemoController {
	
	@Autowired
	private DrtOprPointsRuleService drtOprPointsRuleService;
	
	@Autowired
	private PointsEarningsComputingJob pointsEarningsComputingJob;
	
	@Autowired
	private EarningsExpiryService earningsExpiryJob;
	
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
	
	@Autowired
	private ElecService elecService;
	
	@Autowired
	private  DrtElecDealPkwjService drtElecDealPkwjService;
	
	@Autowired
	private JobRegister jobRegister;
	
	@Autowired
	private  ShopService shopService;
//	@RequestMapping("/demo/test")
//	public void demos() throws Exception{
//		drtElecDealPkwjService.dealPktz();
//		
//		
//	}
	
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
	
	@RequestMapping("getMallItemComputingJob")
	public String getMallItemComputingJob() throws Exception{
		Map<String, Object> resultMap = jobRegister.getMallItemComputingJob();
		return "获取南网商城商品池商品信息成功,resultMap:" + resultMap.toString();
	}
	
	@RequestMapping("test")
	public String test() throws Exception{
		String strInfo = shopService.test();
		return strInfo;
	}
	
	@RequestMapping("earningsExpiryJob")
	public String earningsExpiryJob() throws Exception{
		earningsExpiryJob.deductExpiryEarnings();
		earningsExpiryJob.calculateNextTimeExpiryEarnings();
		return "积分到期处理成功";
	}
}
