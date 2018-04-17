package com.csg.statistics.job;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.csg.statistics.common.enums.BehaviorTypeEnum;
import com.csg.statistics.entity.DrtMonitorBehaviorDuration;
import com.csg.statistics.entity.DrtMonitorBehaviorRecord;
import com.csg.statistics.entity.DrtMonitorBehaviorRecordBean;
import com.csg.statistics.entity.DrtMonitorLoginRecord;
import com.csg.statistics.entity.DrtMonitorLoginRecordBean;
import com.csg.statistics.entity.DrtMonitorRetentionRecord;
import com.csg.statistics.service.DrtMonitorBehaviorDurationService;
import com.csg.statistics.service.DrtMonitorBehaviorRecordService;
import com.csg.statistics.service.DrtMonitorLoginRecordService;
import com.csg.statistics.service.DrtMonitorRetentionRecordService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.GetUUID;
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
public class UserRetentionRecordComputingJob {
	
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
	
	/**用户行为间隔记录表drtMonitorBehaviorDurationService*/
	@Autowired
	DrtMonitorBehaviorDurationService drtMonitorBehaviorDurationService;
	
	/**
	 * 留存率计算
	 *
	 */
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
			
			//insert用户行为间隔List
			List<DrtMonitorBehaviorDuration> lstDrtMonitorBehaviorDuration = new ArrayList<DrtMonitorBehaviorDuration>();
			
			// 设置查询 
			DrtMonitorBehaviorRecordBean queryDrtMonitorBehaviorRecordBean = new DrtMonitorBehaviorRecordBean();
			queryDrtMonitorBehaviorRecordBean.setBehaviorTimeStart(NumberUtils.toLong(presentDateStart + "000000"));
			queryDrtMonitorBehaviorRecordBean.setBehaviorTimeEnd(NumberUtils.toLong(presentDateEnd + "595959"));
			List<DrtMonitorBehaviorRecord> drtMonitorBehaviorRecordList = drtMonitorBehaviorRecordService.selectListByDrtMonitorBehaviorRecordBean(queryDrtMonitorBehaviorRecordBean);
			if(drtMonitorBehaviorRecordList != null && drtMonitorBehaviorRecordList.size() > 0){
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
									//设置用户行为间隔对象
									lstDrtMonitorBehaviorDuration = this.getDrtMonitorBehaviorRecord(groupByBehaviorType,lstDrtMonitorBehaviorDuration);
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
			try{
				if(!CollectionUtils.isEmpty(lstDrtMonitorBehaviorDuration)){
					drtMonitorBehaviorDurationService.insertMonitorBehaviorDurations(lstDrtMonitorBehaviorDuration);
				}
			}catch(Exception e){
				log.error("job-用户行为间隔批量插入失败:", e.getMessage());
			}
			/** pv数 */
			long pvCount = 0;
			
			// 设置查询数，以防数据量过大
			int pageSize = 10000;
			// 获取登录数 
			DrtMonitorLoginRecord queryDrtMonitorLoginRecord = new DrtMonitorLoginRecord();
			queryDrtMonitorLoginRecord.setPresentDate(NumberUtils.toInt(presentDateEnd));
			long loginCount = PageHelper.count(()->drtMonitorLoginRecordService.selectList(queryDrtMonitorLoginRecord));
			// 得到总页数，并遍历每一页
			int totalPageNum = (int) ((loginCount + pageSize - 1) / pageSize);
			for (int pageNum = 1; pageNum <= totalPageNum; pageNum++) {
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
	 * 设置用户行为间隔对象
	 *@param groupByBehaviorType 用户行为分组map
	 *@param lstDrtMonitorBehaviorDuration 集合
	 *@return List<DrtMonitorBehaviorDuration> 用户行为间隔记录表集合
	 *
	 */
	private List<DrtMonitorBehaviorDuration> getDrtMonitorBehaviorRecord(
			Map<Integer, List<DrtMonitorBehaviorRecord>> groupByBehaviorType, List<DrtMonitorBehaviorDuration> lstDrtMonitorBehaviorDuration) {
		
		
		try{
			
			//注册-绑定用户天数
			int iRegisterAndBindingUserDay = 0;
			//绑定用电户-绑定银行卡天数
			int iBindingUserAndCardDay = 0;
			//绑定银行卡-预购电费天数
			int iBindingCardAndPurchaseMoneyDay = 0;
			//注册创建时间
			long registerDate = 0;
			//绑定用电户创建时间
			long bindingUserDate = 0;
			//绑定银行卡创建时间
			long bindingCardDate = 0;
			//预购电费创建时间
			long purchaseMoneyDate = 0;
			
			if(groupByBehaviorType.containsKey(BehaviorTypeEnum.REGISTER.getValue())){	
				//获取注册时间
				List<DrtMonitorBehaviorRecord> lstRegister=groupByBehaviorType.get(BehaviorTypeEnum.REGISTER.getValue());
				DrtMonitorBehaviorRecord objMonitorRegister = lstRegister.get(0);
				registerDate = objMonitorRegister.getBehaviorTime();
				//1.注册-绑定用电户号
				if(groupByBehaviorType.containsKey(BehaviorTypeEnum.ELEC_NUMBER.getValue())){
					//获取绑定用户时间
					List<DrtMonitorBehaviorRecord> lstBindingUser=groupByBehaviorType.get(BehaviorTypeEnum.ELEC_NUMBER.getValue());
					DrtMonitorBehaviorRecord objBindingUser=lstBindingUser.get(0);
					bindingUserDate = objBindingUser.getBehaviorTime();
					if(registerDate != 0 && bindingUserDate != 0 && bindingUserDate > registerDate){
						iRegisterAndBindingUserDay = DateTimeUtils.differentDaysByMillisecond(bindingUserDate, registerDate);
						//用户行为间隔对象
						DrtMonitorBehaviorDuration objRegisterAndBinding = new DrtMonitorBehaviorDuration();
						//设置UID
						objRegisterAndBinding.setId(GetUUID.getUuuid());
						//用户ID
						objRegisterAndBinding.setAccountId(objMonitorRegister.getAccountId());
						//行为区间类型：1.注册-绑定用电户号2.绑定用电户号-绑定银行卡3.绑定银行卡-预购电费
						objRegisterAndBinding.setBehaviorSection(1);
						// 行为区间类型：1. 1-5天 2. 5-15天 3. 15-30天 4. 30天以上 
						if(iRegisterAndBindingUserDay >= 0 && iRegisterAndBindingUserDay <= 5){
							objRegisterAndBinding.setDurationSection(1);
						}else if(iRegisterAndBindingUserDay > 5 && iRegisterAndBindingUserDay <= 15){
							objRegisterAndBinding.setDurationSection(2);
						}else if(iRegisterAndBindingUserDay >15 && iRegisterAndBindingUserDay <= 30){
							objRegisterAndBinding.setDurationSection(3);
						}else if(iRegisterAndBindingUserDay > 30 || iRegisterAndBindingUserDay > 30){
							objRegisterAndBinding.setDurationSection(4);
						}
						//创建时间
						objRegisterAndBinding.setCreateTime(new Timestamp(System.currentTimeMillis()));
						if(objRegisterAndBinding != null && objRegisterAndBinding.getBehaviorSection() != null 
								&& objRegisterAndBinding.getDurationSection() != null){
							lstDrtMonitorBehaviorDuration.add(objRegisterAndBinding);
						}
					}
					
				}
				//2.绑定用电户号-绑定银行卡
				if(groupByBehaviorType.containsKey(BehaviorTypeEnum.BANK_CARD.getValue())){
					//获取绑定银行卡时间
					List<DrtMonitorBehaviorRecord> lstbindingCard=groupByBehaviorType.get(BehaviorTypeEnum.BANK_CARD.getValue());
					DrtMonitorBehaviorRecord objBindingCard = lstbindingCard.get(0);
					bindingCardDate = objBindingCard.getBehaviorTime();
					if(bindingUserDate != 0 && bindingCardDate != 0 ){
						iBindingUserAndCardDay = DateTimeUtils.differentDaysByMillisecond(bindingUserDate, bindingCardDate);
						//用户行为间隔对象
						DrtMonitorBehaviorDuration objBindingUserAndCard = new DrtMonitorBehaviorDuration();
						//设置UID
						objBindingUserAndCard.setId(GetUUID.getUuuid());
						//用户ID
						objBindingUserAndCard.setAccountId(objMonitorRegister.getAccountId());
						//行为区间类型：1.注册-绑定用电户号2.绑定用电户号-绑定银行卡3.绑定银行卡-预购电费
						objBindingUserAndCard.setBehaviorSection(2);
						// 行为区间类型：1. 1-5天 2. 5-15天 3. 15-30天 4. 30天以上 
						if(iBindingUserAndCardDay >= 0 && iBindingUserAndCardDay <= 5){
							objBindingUserAndCard.setDurationSection(1);
						}else if(iBindingUserAndCardDay > 5 && iBindingUserAndCardDay <= 15){
							objBindingUserAndCard.setDurationSection(2);
						}else if(iBindingUserAndCardDay >15 && iBindingUserAndCardDay <= 30){
							objBindingUserAndCard.setDurationSection(3);
						}else if(iBindingUserAndCardDay > 30 || iBindingUserAndCardDay > 30){
							objBindingUserAndCard.setDurationSection(4);
						}
						//创建时间
						objBindingUserAndCard.setCreateTime(new Timestamp(System.currentTimeMillis()));
						if(objBindingUserAndCard != null && objBindingUserAndCard.getBehaviorSection() != null 
								&& objBindingUserAndCard.getDurationSection() != null){
							lstDrtMonitorBehaviorDuration.add(objBindingUserAndCard);
						}
					}
				}
				//3.绑定银行卡-预购电费
				if(groupByBehaviorType.containsKey(BehaviorTypeEnum.PO_MONEY.getValue())){
					//获取预购电费信息时间
					List<DrtMonitorBehaviorRecord> list=groupByBehaviorType.get(BehaviorTypeEnum.PO_MONEY.getValue());
					DrtMonitorBehaviorRecord objPurchaseMoney = list.get(0);
					purchaseMoneyDate = objPurchaseMoney.getBehaviorTime();
					if(bindingCardDate != 0 && purchaseMoneyDate != 0 && purchaseMoneyDate > bindingCardDate){
						iBindingCardAndPurchaseMoneyDay = DateTimeUtils.differentDaysByMillisecond(bindingCardDate, purchaseMoneyDate);
						//用户行为间隔对象
						DrtMonitorBehaviorDuration objBindingCardAndPurchaseMoney = new DrtMonitorBehaviorDuration();
						//设置UID
						objBindingCardAndPurchaseMoney.setId(GetUUID.getUuuid());
						//用户ID
						objBindingCardAndPurchaseMoney.setAccountId(objMonitorRegister.getAccountId());
						//行为区间类型：1.注册-绑定用电户号2.绑定用电户号-绑定银行卡3.绑定银行卡-预购电费
						objBindingCardAndPurchaseMoney.setBehaviorSection(3);
						// 行为区间类型：1. 1-5天 2. 5-15天 3. 15-30天 4. 30天以上 
						if(iBindingCardAndPurchaseMoneyDay >= 0 && iBindingCardAndPurchaseMoneyDay <= 5){
							objBindingCardAndPurchaseMoney.setDurationSection(1);
						}else if(iBindingCardAndPurchaseMoneyDay > 5 && iBindingCardAndPurchaseMoneyDay <= 15){
							objBindingCardAndPurchaseMoney.setDurationSection(2);
						}else if(iBindingCardAndPurchaseMoneyDay >15 && iBindingCardAndPurchaseMoneyDay <= 30){
							objBindingCardAndPurchaseMoney.setDurationSection(3);
						}else if(iBindingCardAndPurchaseMoneyDay > 30 || iBindingCardAndPurchaseMoneyDay > 30){
							objBindingCardAndPurchaseMoney.setDurationSection(4);
						}
						
						//创建时间
						objBindingCardAndPurchaseMoney.setCreateTime(new Timestamp(System.currentTimeMillis()));
						if(objBindingCardAndPurchaseMoney != null && objBindingCardAndPurchaseMoney.getBehaviorSection() != null 
								&& objBindingCardAndPurchaseMoney.getDurationSection() != null){
							lstDrtMonitorBehaviorDuration.add(objBindingCardAndPurchaseMoney);
						}
					}
				}
			}
		}catch (Exception e){
			log.error("封装用户行为间隔List出错:", e.getMessage());
		}
		return lstDrtMonitorBehaviorDuration;
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
		String presentDateStart = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), 0-days, simpleDateFormat);
		String presentDate = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), -1, simpleDateFormat);
		DrtMonitorBehaviorRecordBean queryDrtMonitorBehaviorRecordBean = new DrtMonitorBehaviorRecordBean();
		queryDrtMonitorBehaviorRecordBean.setBehaviorTimeStart(NumberUtils.toLong(presentDateStart + "000000"));
		queryDrtMonitorBehaviorRecordBean.setBehaviorTimeEnd(NumberUtils.toLong(presentDateStart + "595959"));
		queryDrtMonitorBehaviorRecordBean.setBehaviorType(BehaviorTypeEnum.REGISTER.getValue());
		List<DrtMonitorBehaviorRecord> registerList = drtMonitorBehaviorRecordService.selectListByDrtMonitorBehaviorRecordBean(queryDrtMonitorBehaviorRecordBean);
		if(registerList != null && registerList.size() > 0){
			int retainedCount = 0;
			for (DrtMonitorBehaviorRecord drtMonitorBehaviorRecord : registerList) {
				// 获取每一用户
				DrtMonitorLoginRecordBean drtMonitorLoginRecordBean = new DrtMonitorLoginRecordBean();
				drtMonitorLoginRecordBean.setAccountId(drtMonitorBehaviorRecord.getAccountId());
				drtMonitorLoginRecordBean.setPresentDateStart(NumberUtils.toInt(presentDateStart));
				drtMonitorLoginRecordBean.setPresentDateEnd(NumberUtils.toInt(presentDate));
				PageHelper.startPage(1, 1);
				List<DrtMonitorLoginRecord> list = drtMonitorLoginRecordService.selectListByDrtMonitorLoginRecordBean(drtMonitorLoginRecordBean);
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
