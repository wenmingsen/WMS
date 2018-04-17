package com.csg.statistics.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csg.statistics.service.DrtOprnBusinessStateService;

/**
 * 每天凌晨2点执行
 * @author Syni
 *
 */
@Component
public class OneDayJob {
	
	/**业务用户统计service*/
	@Autowired
	private  DrtOprnBusinessStateService drtOprnBusinessStateService;
	
	@Scheduled(cron="0 0/1 * * * ?")
	public void test(){
		System.out.println(1111);
	}

	/**
	 * 业务用户统计（新增用户数量，实名认证用户数量，绑定银行卡用户数量，活跃度用户数量）
	 * 
	 *
	 * */
	@Scheduled(cron="0 15 0 * * ?")
	public void statisticsBusinessUser(){
		drtOprnBusinessStateService.getDrtStateBusinessUser();
	}
}
