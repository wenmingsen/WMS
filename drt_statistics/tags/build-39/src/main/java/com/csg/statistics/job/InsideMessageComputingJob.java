package com.csg.statistics.job;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.csg.statistics.entity.DrtNews;
import com.csg.statistics.entity.DrtOprInsideMessage;
import com.csg.statistics.service.DrtAccountService;
import com.csg.statistics.service.DrtNewsService;
import com.csg.statistics.service.DrtOprInsideMessageService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.GetUUID;

/**
 * 站内任务消息任务
 *
 * @author 温明森
 * @since JDK1.8
 * @version 2018年01月06日 温明森
 */
@Component
public class InsideMessageComputingJob {

	/** 日志 */
	private Logger log = LoggerFactory
			.getLogger(InsideMessageComputingJob.class);

	// 注入消息service
	@Autowired
	DrtNewsService drtNewsService;

	// 注入站内消息Service
	@Autowired
	DrtOprInsideMessageService drtOprInsideMessageService;

	// 注入电融通Service
	@Autowired
	DrtAccountService drtAccountService;

	/**
	 * 定时发送消息插入
	 *
	 */
	@Scheduled(cron="0 0 0/1 * * ?")//每1个小时 
	public void insertTimingNewsComputingJob() {

		// 查询条件
		DrtOprInsideMessage objInsideMessageQuery = new DrtOprInsideMessage();
		// (0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕)
		objInsideMessageQuery.setMessageState(2);
		// 发送类型（1-定时 2- 循环）
		objInsideMessageQuery.setSendType(1);
		//是否删除（0.否1.是）
		objInsideMessageQuery.setIsDelete(0);
		try {
			// 所有站内消息
			List<DrtOprInsideMessage> lstDrtOprInsideMessage = drtOprInsideMessageService
					.selectList(objInsideMessageQuery);
			// 插人发送消息
			List<DrtNews> lstDrtNews = new ArrayList<DrtNews>();
			if (!CollectionUtils.isEmpty(lstDrtOprInsideMessage)) {
				// 获取当前时间
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyMMddHH");
				String strPresentDate = DateTimeUtils.getIntervalTime(
						System.currentTimeMillis(), 0, simpleDateFormat);
				int iPresentDate = NumberUtils.toInt(strPresentDate);
				for (DrtOprInsideMessage objInsideMessage : lstDrtOprInsideMessage) {
					//时间截取格式为yyyyMMddHH
					int iSendTime = 0;
					if(objInsideMessage.getSendTime() != null){
						String strSendTime = String.valueOf(objInsideMessage.getSendTime()).substring(0, 10);
						iSendTime = NumberUtils.toInt(strSendTime);
					}
					// 到定时时间插入
					if (objInsideMessage.getSendTime() != 0
							&& objInsideMessage.getSendTime() != null
							&& iPresentDate == iSendTime) {
							// 封装
							DrtNews objDrtNews = new DrtNews();
							objDrtNews.setTid(GetUUID.getUuuid());
							objDrtNews.setCreatTime(new Timestamp(System
									.currentTimeMillis()));
							// 类型(1,系统消息;2,电费账单消息3.平台消息)
							objDrtNews.setNewType("3");
							objDrtNews.setTheme(objInsideMessage
									.getMessageName());
							objDrtNews.setChangeTime(new Timestamp(System
									.currentTimeMillis()));
							objDrtNews
									.setContent(objInsideMessage.getMessage());
							objDrtNews.setKeyword("平台消息");
							lstDrtNews.add(objDrtNews);
					}
					if(iSendTime > iPresentDate){
						// 0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕
						objInsideMessage.setMessageState(4);
						// 更新站内消息状态
						drtOprInsideMessageService.update(objInsideMessage);
					}else if(iSendTime <= iPresentDate){
						// 0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕
						objInsideMessage.setMessageState(5);
						// 更新站内消息状态
						drtOprInsideMessageService.update(objInsideMessage);
					}
				}
			}
			if (!CollectionUtils.isEmpty(lstDrtNews)) {
				// 插入发送消息
				drtNewsService.insertDrtNews(lstDrtNews);
			}
		} catch (Exception e) {
			log.error("InsideMessageComputingJob-定时消息插入异常！", e);
		}
	}

	/**
	 * 小时循环消息插入
	 *
	 */
	@Scheduled(cron="0 0 0/1 * * ?")//每1个小时 
	public void insertHourLoopNewsComputingJob() {
		// 查询条件
		DrtOprInsideMessage objInsideMessageQuery = new DrtOprInsideMessage();
		// (0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕)
		objInsideMessageQuery.setMessageState(2);
		// 发送类型（1-定时 2- 循环）
		objInsideMessageQuery.setSendType(2);
		//发送频率 3-小时 4-天 5- 月
		objInsideMessageQuery.setSendRate(3);
		//是否删除（0.否1.是）
		objInsideMessageQuery.setIsDelete(0);
		try {
			// 所有站内消息
			List<DrtOprInsideMessage> lstDrtOprInsideMessage = drtOprInsideMessageService
					.selectList(objInsideMessageQuery);
			// 插人发送消息
			List<DrtNews> lstDrtNews = new ArrayList<DrtNews>();
			if (!CollectionUtils.isEmpty(lstDrtOprInsideMessage)) {
				// 获取当前时间
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyMMddHH");
				String strPresentDate = DateTimeUtils.getIntervalTime(
						System.currentTimeMillis(), 0, simpleDateFormat);
				int iPresentDate = NumberUtils.toInt(strPresentDate);
				for (DrtOprInsideMessage objInsideMessage : lstDrtOprInsideMessage) {
					//开始时间截取格式为yyyyMMddHH
					int iStartTime = 0;
					if(objInsideMessage.getStartTime() != null){
						String strStartTime = String.valueOf(objInsideMessage.getStartTime()).substring(0, 10);
						iStartTime = NumberUtils.toInt(strStartTime);
					}
					//开始时间截取格式为yyyyMMddHH
					int iEndTime = 0;
					if(objInsideMessage.getEndTime() != null){
						String strEndTime = String.valueOf(objInsideMessage.getEndTime()).substring(0, 10);
						iEndTime = NumberUtils.toInt(strEndTime);
					}
					// 到定时时间插入
					if (objInsideMessage.getStartTime() != 0
							&&objInsideMessage.getEndTime() != 0
							&& objInsideMessage.getStartTime() != null
							&& objInsideMessage.getEndTime() != null
							&& iPresentDate >= iStartTime
							&& iPresentDate <= iEndTime) {
							// 封装
							DrtNews objDrtNews = new DrtNews();
							objDrtNews.setTid(GetUUID.getUuuid());
							objDrtNews.setCreatTime(new Timestamp(System
									.currentTimeMillis()));
							// 类型(1,系统消息;2,电费账单消息3.平台消息)
							objDrtNews.setNewType("3");
							objDrtNews.setTheme(objInsideMessage
									.getMessageName());
							objDrtNews.setChangeTime(new Timestamp(System
									.currentTimeMillis()));
							objDrtNews
									.setContent(objInsideMessage.getMessage());
							objDrtNews.setKeyword("平台消息");
							lstDrtNews.add(objDrtNews);
					}
					if(iPresentDate > iEndTime){
						// 0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕
						objInsideMessage.setMessageState(5);
						// 更新站内消息状态
						drtOprInsideMessageService.update(objInsideMessage);
					}else if(iPresentDate >= iStartTime && iPresentDate <= iEndTime){
						// 0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕
						objInsideMessage.setMessageState(4);
						// 更新站内消息状态
						drtOprInsideMessageService.update(objInsideMessage);
					}
				}
			}
			if (!CollectionUtils.isEmpty(lstDrtNews)) {
				// 插入发送消息
				drtNewsService.insertDrtNews(lstDrtNews);
			}
		} catch (Exception e) {
			log.error("InsideMessageComputingJob-小时循环消息插入异常", e);
		}
	}

	/**
	 * 天循环消息插入
	 *
	 */
	@Scheduled(cron="0 15 10 * * ?")//每天上午10:15 触发
	public void insertDayLoopNewsComputingJob() {

		// 查询条件
		DrtOprInsideMessage objInsideMessageQuery = new DrtOprInsideMessage();
		// (0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕)
		objInsideMessageQuery.setMessageState(2);
		// 发送类型（1-定时 2- 循环）
		objInsideMessageQuery.setSendType(2);
		//发送频率 3-小时 4-天 5- 月
		objInsideMessageQuery.setSendRate(4);
		//是否删除（0.否1.是）
		objInsideMessageQuery.setIsDelete(0);
		try {
			// 所有站内消息
			List<DrtOprInsideMessage> lstDrtOprInsideMessage = drtOprInsideMessageService
					.selectList(objInsideMessageQuery);
			// 插人发送消息
			List<DrtNews> lstDrtNews = new ArrayList<DrtNews>();
			if (!CollectionUtils.isEmpty(lstDrtOprInsideMessage)) {
				// 获取当前时间
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyMMddHH");
				String strPresentDate = DateTimeUtils.getIntervalTime(
						System.currentTimeMillis(), 0, simpleDateFormat);
				int iPresentDate = NumberUtils.toInt(strPresentDate);
				for (DrtOprInsideMessage objInsideMessage : lstDrtOprInsideMessage) {
					//开始时间截取格式为yyyyMMddHH
					int iStartTime = 0;
					if(objInsideMessage.getStartTime() != null){
						String strStartTime = String.valueOf(objInsideMessage.getStartTime()).substring(0, 10);
						iStartTime = NumberUtils.toInt(strStartTime);
					}
					//开始时间截取格式为yyyyMMddHH
					int iEndTime = 0;
					if(objInsideMessage.getEndTime() != null){
						String strEndTime = String.valueOf(objInsideMessage.getEndTime()).substring(0, 10);
						iEndTime = NumberUtils.toInt(strEndTime);
					}
					// 到定时时间插入
					if (objInsideMessage.getStartTime() != 0
							&&objInsideMessage.getEndTime() != 0
							&& objInsideMessage.getStartTime() != null
							&& objInsideMessage.getEndTime() != null
							&& iPresentDate >= objInsideMessage.getStartTime()
							&& iPresentDate <= objInsideMessage.getEndTime()) {
							// 封装
							DrtNews objDrtNews = new DrtNews();
							objDrtNews.setTid(GetUUID.getUuuid());
							objDrtNews.setCreatTime(new Timestamp(System
									.currentTimeMillis()));
							// 类型(1,系统消息;2,电费账单消息3.平台消息)
							objDrtNews.setNewType("3");
							objDrtNews.setTheme(objInsideMessage
									.getMessageName());
							objDrtNews.setChangeTime(new Timestamp(System
									.currentTimeMillis()));
							objDrtNews
									.setContent(objInsideMessage.getMessage());
							objDrtNews.setKeyword("平台消息");
							lstDrtNews.add(objDrtNews);
					}
					if(iPresentDate > iEndTime){
						// 0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕
						objInsideMessage.setMessageState(5);
						// 更新站内消息状态
						drtOprInsideMessageService.update(objInsideMessage);
					}else if(iPresentDate >= iStartTime && iPresentDate <= iEndTime){
						// 0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕
						objInsideMessage.setMessageState(4);
						// 更新站内消息状态
						drtOprInsideMessageService.update(objInsideMessage);
					}
				}
			}
			if (!CollectionUtils.isEmpty(lstDrtNews)) {
				// 插入发送消息
				drtNewsService.insertDrtNews(lstDrtNews);
			}
		} catch (Exception e) {
			log.error("InsideMessageComputingJob-天循环消息插入异常", e);
		}

	}

	/**
	 * 月循环消息插入
	 *
	 */
	@Scheduled(cron="0 15 03 1 * ?")//  每月1日上午03:15触发
	public void insertMonthLoopNewsComputingJob() {

		// 查询条件
		DrtOprInsideMessage objInsideMessageQuery = new DrtOprInsideMessage();
		// (0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕)
		objInsideMessageQuery.setMessageState(2);
		// 发送类型（1-定时 2- 循环）
		objInsideMessageQuery.setSendType(2);
		//发送频率 3-小时 4-天 5- 月
		objInsideMessageQuery.setSendRate(5);
		//是否删除（0.否1.是）
		objInsideMessageQuery.setIsDelete(0);
		try {
			// 所有站内消息
			List<DrtOprInsideMessage> lstDrtOprInsideMessage = drtOprInsideMessageService
					.selectList(objInsideMessageQuery);
			// 插人发送消息
			List<DrtNews> lstDrtNews = new ArrayList<DrtNews>();
			if (!CollectionUtils.isEmpty(lstDrtOprInsideMessage)) {
				// 获取当前时间
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyMMddHH");
				String strPresentDate = DateTimeUtils.getIntervalTime(
						System.currentTimeMillis(), 0, simpleDateFormat);
				int iPresentDate = NumberUtils.toInt(strPresentDate);
				for (DrtOprInsideMessage objInsideMessage : lstDrtOprInsideMessage) {
					//开始时间截取格式为yyyyMMddHH
					int iStartTime = 0;
					if(objInsideMessage.getStartTime() != null){
						String strStartTime = String.valueOf(objInsideMessage.getStartTime()).substring(0, 10);
						iStartTime = NumberUtils.toInt(strStartTime);
					}
					//开始时间截取格式为yyyyMMddHH
					int iEndTime = 0;
					if(objInsideMessage.getEndTime() != null){
						String strEndTime = String.valueOf(objInsideMessage.getEndTime()).substring(0, 10);
						iEndTime = NumberUtils.toInt(strEndTime);
					}
					// 到定时时间插入
					if (objInsideMessage.getStartTime() != 0
							&&objInsideMessage.getEndTime() != 0
							&& objInsideMessage.getStartTime() != null
							&& objInsideMessage.getEndTime() != null
							&& iPresentDate >= objInsideMessage.getStartTime()
							&& iPresentDate <= objInsideMessage.getEndTime()) {
							// 封装
							DrtNews objDrtNews = new DrtNews();
							objDrtNews.setTid(GetUUID.getUuuid());
							objDrtNews.setCreatTime(new Timestamp(System
									.currentTimeMillis()));
							// 类型(1,系统消息;2,电费账单消息3.平台消息)
							objDrtNews.setNewType("3");
							objDrtNews.setTheme(objInsideMessage
									.getMessageName());
							objDrtNews.setChangeTime(new Timestamp(System
									.currentTimeMillis()));
							objDrtNews
									.setContent(objInsideMessage.getMessage());
							objDrtNews.setKeyword("平台消息");
							lstDrtNews.add(objDrtNews);
					}
					if(iPresentDate > iEndTime){
						// 0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕
						objInsideMessage.setMessageState(5);
						// 更新站内消息状态
						drtOprInsideMessageService.update(objInsideMessage);
					}else if(iPresentDate >= iStartTime && iPresentDate <= iEndTime){
						// 0.待上报1.审批中2.审批完成3.不通过4.执行中5.执行完毕
						objInsideMessage.setMessageState(4);
						// 更新站内消息状态
						drtOprInsideMessageService.update(objInsideMessage);
					}
				}
			}
			if (!CollectionUtils.isEmpty(lstDrtNews)) {
				// 插入发送消息
				drtNewsService.insertDrtNews(lstDrtNews);
			}
		} catch (Exception e) {
			log.error("InsideMessageComputingJob-月循环消息插入异常", e);
		}
	}
	
	/**
	 * 删除最近一周站内消息记录
	 *
	 */
	@Scheduled(cron="0 30 0 * * ?") //每天凌晨30分
	public void deleteWeekNewsComputingJob() {
		try{
			drtNewsService.deleteWeekNews();
		}catch(Exception e){
			log.error("InsideMessageComputingJob-删除最近一周站内消息记录异常", e);
		}
	}
}
