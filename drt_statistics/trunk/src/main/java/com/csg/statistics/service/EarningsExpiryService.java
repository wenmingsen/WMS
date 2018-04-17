package com.csg.statistics.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.bean.EarningsExpiryModel;
import com.csg.statistics.common.enums.NumberTypeEnum;
import com.csg.statistics.entity.DrtAccount;
import com.csg.statistics.entity.DrtFinAllEarningsRecord;
import com.csg.statistics.entity.DrtFinAllEarningsRecordHistory;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.EarningsUtils;
import com.csg.statistics.util.UUIDUtil;

/**
 * 到期积分清算任务
 *
 * @author  xuchen
 * @since   1.8
 * @version 2018-03-01 xuchen
 */
@Service
public class EarningsExpiryService {

	/** 登录账户信息表 */
	@Autowired
	private DrtAccountService drtAccountService;
	
	/** 积分明细历史表 */
	@Autowired
	private DrtFinAllEarningsRecordHistoryService drtFinAllEarningsRecordHistoryService;
	
	/** 积分明细表 */
	@Autowired
	private DrtFinAllEarningsRecordService drtFinAllEarningsRecordService;
	
	/**
	 * 扣除到期积分
	 * @author xuchen
	 * @return boolean flag 成功true 失败false
	 */
	public void deductExpiryEarnings() throws Exception{
		//插入明细记录
		List<DrtAccount> drtAccountList = drtAccountService.selectListEcpiryEarningsPositive(null);
		for(DrtAccount drtAccountTemp:drtAccountList){
			insertEarningsRecord(drtAccountTemp);
		}
		drtAccountService.deductExpiryEarnings();
	}
	
	/**
	 * 计算下次截止日期到期积分,更新drtAccount的EXPIRY_EARNINGS字段
	 * @author xuchen
	 * @return boolean flag 成功true 失败false
	 */
	public void calculateNextTimeExpiryEarnings() throws Exception{
		//当前日期
		Integer expiryDay = NumberUtils.toInt(DateTimeUtils.converDateToString(new Date(),"yyyyMMdd"));
		//下次截止日期
		expiryDay = EarningsUtils.getNextExpiryDay(expiryDay);
		//根据到期日期返回该过期积分获取的时间段
		Map<String, Integer> earningsCreateDurationMap = EarningsUtils.getEarningsCreateDuration(expiryDay);
		//封装查询条件
		EarningsExpiryModel earningsExpiryModel = new EarningsExpiryModel();
		earningsExpiryModel.setStartDate(earningsCreateDurationMap.get("startDate"));
		earningsExpiryModel.setEndDate(earningsCreateDurationMap.get("endDate"));
		
		//计算下次截止日期到期积分,更新drtAccount的EXPIRY_EARNINGS字段
		drtFinAllEarningsRecordHistoryService.calculateNextTimeExpiryEarnings(earningsExpiryModel);
	}
	
	/**
	 * 积分明细表插入数据
	 * @author xuchen
	 */
	public void insertEarningsRecord(DrtAccount drtAccount) throws Exception{
		DrtFinAllEarningsRecord drtFinAllEarningsRecord = new DrtFinAllEarningsRecord();
		drtFinAllEarningsRecord.setPoEarningsRecordId(UUIDUtil.generateUUID());
		drtFinAllEarningsRecord.setTotalEarnings(drtAccount.getTotalEarnings() - drtAccount.getExpiryEarnings().intValue());
		drtFinAllEarningsRecord.setAccountId(drtAccount.getAccountId());
		drtFinAllEarningsRecord.setEarnings(drtAccount.getExpiryEarnings().intValue());
		drtFinAllEarningsRecord.setRecordTime(DateTimeUtils.getCurrentTime());
		drtFinAllEarningsRecord.setPmType(NumberTypeEnum.MINUS.getValue());
		drtFinAllEarningsRecord.setFromType("8");
		drtFinAllEarningsRecord.setFromName("积分过期");
		drtFinAllEarningsRecord.setAuditState(2);
		
		//2018-02-06 添加当前天字段供app积分走势图使用
		drtFinAllEarningsRecord.setCurrDay(NumberUtils.toInt(DateTimeUtils.converDateToString(new Date(), "yyyyMMdd")));
		
		drtFinAllEarningsRecord.setLastModifyTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
		drtFinAllEarningsRecord.setCurrentEarnings(drtAccount.getTotalEarnings());
		
		DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory = drtFinAllEarningsRecordService.convertToDrtFinAllEarningsRecordHistory(drtFinAllEarningsRecord);
		//插入操作
		drtFinAllEarningsRecordHistoryService.insert(drtFinAllEarningsRecordHistory);
		drtFinAllEarningsRecordService.insert(drtFinAllEarningsRecord);
	}
}
