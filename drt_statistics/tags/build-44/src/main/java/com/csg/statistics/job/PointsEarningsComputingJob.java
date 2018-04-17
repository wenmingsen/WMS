package com.csg.statistics.job;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.statistics.common.enums.NumberTypeEnum;
import com.csg.statistics.entity.DrtFinAllEarningsRecord;
import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.entity.DrtFinPoAccountRecord;
import com.csg.statistics.entity.DrtFinPoEarningsComputingRecord;
import com.csg.statistics.entity.DrtFinPoEarningsComputingRecordQuery;
import com.csg.statistics.entity.DrtOprPointsRule;
import com.csg.statistics.entity.DrtOprPointsRuleBean;
import com.csg.statistics.service.DrtFinAllEarningsRecordService;
import com.csg.statistics.service.DrtFinPoAccountRecordService;
import com.csg.statistics.service.DrtFinPoAccountService;
import com.csg.statistics.service.DrtFinPoEarningsComputingRecordService;
import com.csg.statistics.service.DrtOprPointsRuleService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;
import com.github.pagehelper.PageHelper;

/**
 * 积分收益计算任务
 *
 * @author 曾令鹏
 * @since 1.8
 * @version 2017年12月08日 曾令鹏
 */
@Component
public class PointsEarningsComputingJob {
	
	/**日志*/
	private Logger log = LoggerFactory.getLogger(PointsEarningsComputingJob.class);

	/** 理财账户信息表（预购） */
	@Autowired
	private DrtFinPoAccountService drtFinPoAccountService;
	
	/** 理财账户信息交易明细表(预购) */
	@Autowired
	private DrtFinPoAccountRecordService drtFinPoAccountRecordService;

	/** 积分规则 */
	@Autowired
	private DrtOprPointsRuleService drtOprPointsRuleService;

	/** 积分收益明细表(预购)(当前表) */
	@Autowired
	private DrtFinAllEarningsRecordService drtFinAllEarningsRecordService;

	/** 预购账户积分收益计算明细表 */
	@Autowired
	private DrtFinPoEarningsComputingRecordService drtFinPoEarningsComputingRecordService;

	/**
	 * 积分收益计算
	 *
	 */
	public void pointsEarningsComputing() {
		System.err.println("----------------开始--------------------");
		// 积分收益时间点计算
		pointsEarningsTimePoint();
		// 积分收益金额计算
		pointsEarningsMoneyComputing();
		DrtFinPoAccount queryDrtFinPoAccount = new DrtFinPoAccount();
		// 账户状态(0,关闭;1开通)
		queryDrtFinPoAccount.setPoAccountStatus("1");
		// 获取预购账号列表
		int pageSize = 10000;
		long totalCount = PageHelper.count(() -> drtFinPoAccountService
				.selectList(queryDrtFinPoAccount));
		int totalPageNum = (int) ((totalCount + pageSize - 1) / pageSize);
		for (int pageNum = 1; pageNum <= totalPageNum; pageNum++) {
			try {
				PageHelper.startPage(pageNum, pageSize, false);
				List<DrtFinPoAccount> drtFinPoAccountList = drtFinPoAccountService
						.selectList(queryDrtFinPoAccount);
				if (drtFinPoAccountList != null
						&& drtFinPoAccountList.size() > 0) {
					for (DrtFinPoAccount drtFinPoAccount : drtFinPoAccountList) {
						// 获取用户预购收益计算金额
						Long poEarningsComputingMoney = drtFinPoAccount
								.getPoEarningsComputingMoney();
						if (poEarningsComputingMoney != null && poEarningsComputingMoney.longValue() > 0) {
							// 获取该金额对应的规则
							DrtOprPointsRuleBean drtOprPointsRuleBean = new DrtOprPointsRuleBean();
							drtOprPointsRuleBean
									.setPoEarningsComputingMoney(poEarningsComputingMoney);
							// 状态（0.停用 1.启用）
							drtOprPointsRuleBean.setStatus("1");
							// 是否删除,0是，1否
							drtOprPointsRuleBean.setIsDelete("1");
							// 积分规则类型（1.余额积分 2.交费积分 3.推广积分）
							drtOprPointsRuleBean.setPointsRuleType("1");
							DrtOprPointsRule uniquePointsRule = drtOprPointsRuleService
									.selectOneDrtOprPointsRule(drtOprPointsRuleBean);
							if (uniquePointsRule != null) {
								// 计算积分收益
								// 日收益积分为q=账户余额为k（元）*财务公司日收益率为r（万分之）*分成比例为p（百分之）*积分基准率为j（积分）*激励系数为f(倍)
								float dayIncome = NumberUtils
										.toFloat(uniquePointsRule
												.getDayIncome());
								float coefficient = NumberUtils
										.toFloat(uniquePointsRule
												.getCoefficient());
								float bonusColumn = NumberUtils
										.toFloat(uniquePointsRule
												.getBonusColumn());
								float pointsDatumRate = NumberUtils
										.toFloat(uniquePointsRule
												.getPointsDatumRate());
								float earningsPoints = poEarningsComputingMoney
										.longValue()
										/ 100.0f
										* dayIncome
										* bonusColumn
										* pointsDatumRate
										* coefficient;
								int points = Math.round(earningsPoints * 100);
								if (points > 0) {
									// 积分收益记录
									DrtFinAllEarningsRecord drtFinPoEarningsRecord = new DrtFinAllEarningsRecord();
									drtFinPoEarningsRecord
											.setPoEarningsRecordId(UUIDUtil
													.generateUUID());
									drtFinPoEarningsRecord
											.setAccountId(drtFinPoAccount
													.getAccountId());
									drtFinPoEarningsRecord
											.setPoAccountId(drtFinPoAccount
													.getPoAccountId());
									drtFinPoEarningsRecord
											.setElecUserId(drtFinPoAccount
													.getElecUserId());
									// 正负值（0为负，1为正）
									drtFinPoEarningsRecord
											.setPmType(NumberTypeEnum.PLUS
													.getValue());
									drtFinPoEarningsRecord.setEarnings(points);
									// 来源类型(0预购电费收益,1交电费收益,2活动奖励收益,9积分兑换)
									drtFinPoEarningsRecord.setFromType("0");
									drtFinPoEarningsRecord.setFromName("预购电费收益");
									// 0-待上报 1-审批中 2-审批通过 3-不通过
									drtFinPoEarningsRecord.setAuditState(2);
									drtFinPoEarningsRecord
											.setRecordTime(new Timestamp(System
													.currentTimeMillis()));
									// 插入记录并更新积分
									drtFinAllEarningsRecordService
											.insertAndUpdateTotalPoints(drtFinPoEarningsRecord);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				log.error("积分收益计算job - {}", e.getMessage());
			}
		}
	}

	/**
	 * 积分收益金额计算
	 *
	 * 
	 */
	private void pointsEarningsMoneyComputing() {
		try {
			// 预购账户积分收益计算明细汇总
			DrtFinPoEarningsComputingRecordQuery objQueryDrtFinPoEarningsComputingRecord = new DrtFinPoEarningsComputingRecordQuery();
			String strCurrentDate = DateTimeUtils.converDateToString(
					new Date(), "yyyyMMdd");
			objQueryDrtFinPoEarningsComputingRecord
					.setEarningsComputingTimeEnd(NumberUtils
							.toInt(strCurrentDate));
			// 是否加入计算：0是 1否
			objQueryDrtFinPoEarningsComputingRecord.setIsJoinComputing(0);
			// 是否已经计算：0-否 1-是
			objQueryDrtFinPoEarningsComputingRecord.setIsComputed(0);
			// 预购账户积分收益计算明细集合
			List<DrtFinPoEarningsComputingRecord> lstDrtFinPoEarningsComputingRecord = drtFinPoEarningsComputingRecordService
					.selectListByDrtFinPoEarningsComputingRecordQuery(objQueryDrtFinPoEarningsComputingRecord);
			for (DrtFinPoEarningsComputingRecord objDrtFinPoEarningsComputingRecord : lstDrtFinPoEarningsComputingRecord) {
				if (objDrtFinPoEarningsComputingRecord != null
						&& objDrtFinPoEarningsComputingRecord.getPoMoney() != null) {
					drtFinPoEarningsComputingRecordService
							.updateAndUpdateMoneyComputing(objDrtFinPoEarningsComputingRecord);
				}
			}
		} catch (Exception e) {
			log.error("积分收益计算job_积分收益金额计算 - {}", e.getMessage());
		}
	}
	
	/**
	 * 积分收益时间点计算
	 *
	 */
	private void pointsEarningsTimePoint(){
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			// 昨天
			/*String yesterday = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), -1, dateFormat);
			long yesterdayTime = DateTimeUtils.converStringToDate(yesterday, "yyyyMMdd").getTime();
			// 今天
			String today = DateTimeUtils.getIntervalTime(System.currentTimeMillis(), 0, dateFormat);
			long todayTime = DateTimeUtils.converStringToDate(today, "yyyyMMdd").getTime();
			//设置查询条件
			DrtFinPoAccountRecordForQuery drtFinPoAccountRecordForQuery = new DrtFinPoAccountRecordForQuery();
			drtFinPoAccountRecordForQuery.setDealTimeStart(new Timestamp(yesterdayTime));
			drtFinPoAccountRecordForQuery.setDealTimeEnd(new Timestamp(todayTime));*/
			DrtFinPoAccountRecord queryDrtFinPoAccountRecord = new DrtFinPoAccountRecord();
			/** 0-待上报 1-审批中 2-审批通过 3-不通过 */
			queryDrtFinPoAccountRecord.setAuditState(2);
			// 是否已经计算：0-否 1-是
			queryDrtFinPoAccountRecord.setIsComputed(0);
			List<DrtFinPoAccountRecord> finPoAccountRecordList = drtFinPoAccountRecordService.selectList(queryDrtFinPoAccountRecord);
			DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord = new DrtFinPoEarningsComputingRecord();
			for (DrtFinPoAccountRecord drtFinPoAccountRecord : finPoAccountRecordList) {
				try{
					if(drtFinPoAccountRecord.getPoDealMoney().longValue() < 0){
						drtFinPoEarningsComputingRecordService.moneyConsume(drtFinPoAccountRecord.getPoAccountId(), Math.abs(drtFinPoAccountRecord.getPoDealMoney().longValue()));
					}else{
						drtFinPoEarningsComputingRecord.setId(UUIDUtil.generateUUID());
						// 是否已经计算：0-否 1-是
						drtFinPoEarningsComputingRecord.setIsComputed(0);
						// 是否加入计算：0是 1否
						drtFinPoEarningsComputingRecord.setIsJoinComputing(0);
						drtFinPoEarningsComputingRecord.setRemark("");
						drtFinPoEarningsComputingRecord.setPoAccountRecordId(drtFinPoAccountRecord.getPoAccountRecordId());
						drtFinPoEarningsComputingRecord.setPoAccountId(drtFinPoAccountRecord.getPoAccountId());
						drtFinPoEarningsComputingRecord.setPoMoney(drtFinPoAccountRecord.getPoDealMoney().longValue());
						String poTime = DateTimeUtils.converTimestampToString(drtFinPoAccountRecord.getDealTime(), "yyyyMMddHHmmss");
						drtFinPoEarningsComputingRecord.setPoTime(NumberUtils.toLong(poTime));
						// 积分获取时间规则如下：客户电费资金到账时间为T，当T在15:00之前，积分到账时间为T+1；当T在15:00之后，积分到账时间为T+2。
						String strEarningsComputingTime = null;
						if(NumberUtils.toInt(poTime.substring(8)) > 150000){
							strEarningsComputingTime = DateTimeUtils.getIntervalTime(drtFinPoAccountRecord.getDealTime().getTime(), 2, dateFormat);
						}else{
							strEarningsComputingTime = DateTimeUtils.getIntervalTime(drtFinPoAccountRecord.getDealTime().getTime(), 1, dateFormat);
						}
						drtFinPoEarningsComputingRecord.setEarningsComputingTime(NumberUtils.toInt(strEarningsComputingTime));
						// 插入
						drtFinPoEarningsComputingRecord.setCreateTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
						drtFinPoEarningsComputingRecordService.insert(drtFinPoEarningsComputingRecord);
					}
				}catch(Exception e){
					log.error("积分收益时间点计算出现错误：预购账号ID - {}", drtFinPoAccountRecord.getPoAccountRecordId());
				}
			}
		}catch(Exception e){
			log.error("积分收益时间点计算 - {}", e.getMessage());
		}
	}
	
}
