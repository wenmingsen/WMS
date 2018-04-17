package com.csg.statistics.job;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.csg.statistics.common.enums.BehaviorTypeEnum;
import com.csg.statistics.entity.DrtMonitorBehaviorRecord;
import com.csg.statistics.entity.DrtMonitorBehaviorRecordBean;
import com.csg.statistics.entity.DrtMonitorLoginRecord;
import com.csg.statistics.entity.DrtMonitorRetentionRecord;
import com.csg.statistics.service.DrtMonitorBehaviorRecordService;
import com.csg.statistics.service.DrtMonitorLoginRecordService;
import com.csg.statistics.service.DrtMonitorRetentionRecordService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.StringUtils;
import com.csg.statistics.util.UUIDUtil;
import com.github.pagehelper.PageHelper;

/**
 * 用户留存率 计算任务
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月19日 曾令鹏
 */
@Component
class UserRetentionRecordComputingJob {
	
	/**日志*/
	private Logger log = LoggerFactory.getLogger(UserRetentionRecordComputingJob.class);

	/**留存率drtMonitorRetentionRecordService*/
	@Autowired
	private DrtMonitorRetentionRecordService drtMonitorRetentionRecordService;
	
	/**用户登录记录表drtMonitorLoginRecordService*/
	@Autowired
	private DrtMonitorLoginRecordService drtMonitorLoginRecordService;
	
	/**用户行为记录表drtMonitorBehaviorRecordService*/
	@Autowired
	private DrtMonitorBehaviorRecordService drtMonitorBehaviorRecordService;
	
	/**
	 * 留存率计算 每天01:00运行
	 *
	 */
	@Scheduled(cron="0 0 1 * * ?")
	public void start() {
		try{
			// T-1天 
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String presentDateStart = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), -7, simpleDateFormat);
			String presentDateEnd = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), -1, simpleDateFormat);
			
			// 获取注册数 
			long registerCount = 0;
			// 绑定用电户号数 
			long elecNumberCount = 0;
			// 绑定银行卡数 
			long bankCardCount = 0;
			// 预购电费数 
			long poMoneyCount = 0;
			
			// 设置查询 
			DrtMonitorBehaviorRecordBean queryDrtMonitorBehaviorRecordBean = new DrtMonitorBehaviorRecordBean();
			queryDrtMonitorBehaviorRecordBean.setBehaviorTimeStart(NumberUtils.toLong(presentDateStart + "000000"));
			queryDrtMonitorBehaviorRecordBean.setBehaviorTimeEnd(NumberUtils.toLong(presentDateEnd + "595959"));
			queryDrtMonitorBehaviorRecordBean.setIsDelete(1);
			List<DrtMonitorBehaviorRecord> drtMonitorBehaviorRecordList = drtMonitorBehaviorRecordService.selectListByDrtMonitorBehaviorRecordBean(queryDrtMonitorBehaviorRecordBean);
			if(drtMonitorBehaviorRecordList != null){
				// 按用户ID分组
				Map<String, List<DrtMonitorBehaviorRecord>> groupByAccountId = drtMonitorBehaviorRecordList.stream().collect(Collectors.groupingBy(DrtMonitorBehaviorRecord::getAccountId));
				if(groupByAccountId != null){
					Set<Map.Entry<String, List<DrtMonitorBehaviorRecord>>> entrySet = groupByAccountId.entrySet();
					for(Map.Entry<String, List<DrtMonitorBehaviorRecord>> entry : entrySet){
						if(entry.getValue() != null && entry.getValue().size() > 0){
							// 将用户所有行为按行为分组
							Map<Integer, List<DrtMonitorBehaviorRecord>> groupByBehaviorType = entry.getValue().stream().collect(Collectors.groupingBy(DrtMonitorBehaviorRecord::getBehaviorType));
							if(groupByBehaviorType != null && groupByBehaviorType.size() > 0){
								// 注册-->绑定用电户号-->绑定银行卡-->开通预购电费
								if(groupByBehaviorType.containsKey(BehaviorTypeEnum.REGISTER.getValue())){
									registerCount++;
									if(groupByBehaviorType.containsKey(BehaviorTypeEnum.ELEC_NUMBER.getValue())){
										elecNumberCount++;
									}
									if(groupByBehaviorType.containsKey(BehaviorTypeEnum.BANK_CARD.getValue())){
										bankCardCount++;
									}
									if(groupByBehaviorType.containsKey(BehaviorTypeEnum.PO_MONEY.getValue())){
										poMoneyCount++;
									}
								}
							}
						}
					}
				}
			}
			
			
			/** pv数 */
			long pvCount = 0;
			
			// 设置查询数，以防数据量过大
			int pageSize = 10000;
			// 获取登录数 
			DrtMonitorLoginRecord queryDrtMonitorLoginRecord = new DrtMonitorLoginRecord();
			queryDrtMonitorLoginRecord.setPresentDate(NumberUtils.toInt(presentDateStart));
			long loginCount = PageHelper.count(()->drtMonitorLoginRecordService.selectList(queryDrtMonitorLoginRecord));
			// 得到总页数，并遍历每一页
			int totalPageNum = (int) (loginCount + (pageSize - 1) / pageSize);
			for (int pageNum = 1; pageNum < totalPageNum; pageNum++) {
				PageHelper.startPage(pageNum, pageSize, false);
				List<DrtMonitorLoginRecord> drtMonitorLoginRecordList = drtMonitorLoginRecordService.selectList(queryDrtMonitorLoginRecord);
				if(drtMonitorLoginRecordList != null && drtMonitorLoginRecordList.size() > 0){
					for (DrtMonitorLoginRecord drtMonitorLoginRecord : drtMonitorLoginRecordList) {
						// 累加每人每天pv数
						pvCount += drtMonitorLoginRecord.getRequestCount();
					}
				}
			}
			
			// 插入今日统计留存率记录
			DrtMonitorRetentionRecord drtMonitorRetentionRecord = new DrtMonitorRetentionRecord();
			drtMonitorRetentionRecord.setId(UUIDUtil.generateUUID());
			drtMonitorRetentionRecord.setPresentDate(NumberUtils.toInt(presentDateEnd));
			drtMonitorRetentionRecord.setLoginCount(loginCount);
			drtMonitorRetentionRecord.setRegisterCount(registerCount);
			drtMonitorRetentionRecord.setElecNumberCount(elecNumberCount);
			drtMonitorRetentionRecord.setBankCardCount(bankCardCount);
			drtMonitorRetentionRecord.setPoMoneyCount(poMoneyCount);
			drtMonitorRetentionRecord.setPvCount(pvCount);
			drtMonitorRetentionRecord.setUvCount(loginCount);
			drtMonitorRetentionRecord.setRetainedCount1(getRetentionRate(1));
			drtMonitorRetentionRecord.setRetainedCount3(getRetentionRate(3));
			drtMonitorRetentionRecord.setRetainedCount7(getRetentionRate(7));
			drtMonitorRetentionRecord.setRetainedCount30(getRetentionRate(30));
			
			drtMonitorRetentionRecordService.insert(drtMonitorRetentionRecord);
		}catch(Exception e){
			log.error("留存率计算job - {}", e.getMessage());
		}
	}
	
	/**
	 * 返回指定天数的留存率
	 * 
	 * @param days 如：3日留存率，则days=3
	 * @return 返回比例。如：23.45%
	 */
	private String getRetentionRate(int days){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		/** T-n */
		String presentDate = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), 0-days, simpleDateFormat);
		DrtMonitorBehaviorRecordBean queryDrtMonitorBehaviorRecordBean = new DrtMonitorBehaviorRecordBean();
		queryDrtMonitorBehaviorRecordBean.setBehaviorTimeStart(NumberUtils.toLong(presentDate + "000000"));
		queryDrtMonitorBehaviorRecordBean.setBehaviorTimeEnd(NumberUtils.toLong(presentDate + "595959"));
		queryDrtMonitorBehaviorRecordBean.setIsDelete(1);
		queryDrtMonitorBehaviorRecordBean.setBehaviorType(BehaviorTypeEnum.REGISTER.getValue());
		List<DrtMonitorBehaviorRecord> registerList = drtMonitorBehaviorRecordService.selectListByDrtMonitorBehaviorRecordBean(queryDrtMonitorBehaviorRecordBean);
		if(registerList != null && registerList.size() > 0){
			int retainedCount = 0;
			for (DrtMonitorBehaviorRecord drtMonitorBehaviorRecord : registerList) {
				// 获取每一用户
				DrtMonitorLoginRecord drtMonitorLoginRecord = new DrtMonitorLoginRecord();
				drtMonitorLoginRecord.setAccountId(drtMonitorBehaviorRecord.getAccountId());
				drtMonitorLoginRecord.setPresentDate(NumberUtils.toInt(presentDate));
				PageHelper.startPage(1, 1);
				List<DrtMonitorLoginRecord> list = drtMonitorLoginRecordService.selectList(drtMonitorLoginRecord);
				if(list != null && list.size() > 0){
					retainedCount++;
				}
			}
			if(retainedCount > 0){
				return StringUtils.formattedDecimalToPercentage(retainedCount * 0.1 / registerList.size());
			}
		}
		return "0.00%";
	}
	
	
}
