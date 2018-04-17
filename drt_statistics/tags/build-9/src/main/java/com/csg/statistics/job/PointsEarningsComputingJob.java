package com.csg.statistics.job;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.statistics.common.enums.NumberTypeEnum;
import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.entity.DrtFinPoEarningsComputingRecord;
import com.csg.statistics.entity.DrtFinPoEarningsRecord;
import com.csg.statistics.entity.DrtOprPointsRule;
import com.csg.statistics.entity.DrtOprPointsRuleBean;
import com.csg.statistics.service.DrtFinPoAccountService;
import com.csg.statistics.service.DrtFinPoEarningsComputingRecordService;
import com.csg.statistics.service.DrtFinPoEarningsRecordService;
import com.csg.statistics.service.DrtOprPointsRuleService;
import com.csg.statistics.util.DateTimeUtils;
import com.csg.statistics.util.UUIDUtil;

/**
 * 积分收益计算任务
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月08日 曾令鹏
 */
@Component
public class PointsEarningsComputingJob {

	/** 理财账户信息表（预购） */
	@Autowired
	private DrtFinPoAccountService drtFinPoAccountService;
	
	/** 积分规则 */
	@Autowired
	private DrtOprPointsRuleService drtOprPointsRuleService;
	
	/** 积分收益明细表(预购)(当前表) */
	@Autowired
	private DrtFinPoEarningsRecordService drtFinPoEarningsRecordService;
	
	/** 预购账户积分收益计算明细表 */
	@Autowired
	private DrtFinPoEarningsComputingRecordService drtFinPoEarningsComputingRecordService;
	
	/**
	 * 积分收益计算
	 *
	 * @throws Exception
	 */
//	@Scheduled(cron = "0 15 17 * * ? ")
	public void pointsEarningsComputing() throws Exception{
		System.err.println("----------------开始--------------------");
		pointsEarningsMoneyComputing();
		DrtFinPoAccount queryDrtFinPoAccount = new DrtFinPoAccount();
		// 账户状态(0,关闭;1开通)
		queryDrtFinPoAccount.setPoAccountStatus("1");
		// 获取预购账号列表
		List<DrtFinPoAccount> drtFinPoAccountList = drtFinPoAccountService.selectList(queryDrtFinPoAccount);
		if(drtFinPoAccountList != null && drtFinPoAccountList.size() > 0){
			for (DrtFinPoAccount drtFinPoAccount : drtFinPoAccountList) {
				// 获取用户预购收益计算金额
				Long poEarningsComputingMoney = drtFinPoAccount.getPoEarningsComputingMoney();
				if(poEarningsComputingMoney != null){
					// 获取该金额对应的规则
					DrtOprPointsRuleBean drtOprPointsRuleBean = new DrtOprPointsRuleBean();
					drtOprPointsRuleBean.setPoEarningsComputingMoney(poEarningsComputingMoney);
					// 状态（0.停用 1.启用）
					drtOprPointsRuleBean.setStatus("1");
					// 是否删除,0是，1否
					drtOprPointsRuleBean.setIsDelete("1");
					// 积分规则类型（1.余额积分 2.交费积分 3.推广积分）
					drtOprPointsRuleBean.setPointsRuleType("1");
					DrtOprPointsRule uniquePointsRule = drtOprPointsRuleService.selectOneDrtOprPointsRule(drtOprPointsRuleBean);
					if(uniquePointsRule != null){
						// 计算积分收益
						// 日收益积分为q=账户余额为k（元）*财务公司日收益率为r（万分之）*分成比例为p（百分之）*积分基准率为j（积分）*激励系数为f(倍)
						float dayIncome = NumberUtils.toFloat(uniquePointsRule.getDayIncome());
						float coefficient = NumberUtils.toFloat(uniquePointsRule.getCoefficient());
						float bonusColumn = NumberUtils.toFloat(uniquePointsRule.getBonusColumn());
						float pointsDatumRate = NumberUtils.toFloat(uniquePointsRule.getPointsDatumRate());
						float earningsPoints = poEarningsComputingMoney.longValue() / 100.0f * dayIncome * bonusColumn * pointsDatumRate * coefficient;
						int points = Math.round(earningsPoints * 100);
						if(points > 0){
							// 积分收益记录
							DrtFinPoEarningsRecord drtFinPoEarningsRecord = new DrtFinPoEarningsRecord();
							drtFinPoEarningsRecord.setPoEarningsRecordId(UUIDUtil.generateUUID());
							drtFinPoEarningsRecord.setAccountId(drtFinPoAccount.getAccountId());
							drtFinPoEarningsRecord.setPoAccountId(drtFinPoAccount.getPoAccountId());
							drtFinPoEarningsRecord.setElecUserId(drtFinPoAccount.getElecUserId());
							// 正负值（0为负，1为正）
							drtFinPoEarningsRecord.setType(NumberTypeEnum.PLUS.getValue());
							drtFinPoEarningsRecord.setEarnings(points);
							// 积分来源类型：0-动态收益
							drtFinPoEarningsRecord.setFromType("0");
							drtFinPoEarningsRecord.setTime(new Timestamp(System.currentTimeMillis()));
							// 插入记录并更新积分
							drtFinPoEarningsRecordService.insertAndUpdatePoints(drtFinPoEarningsRecord);
						}
					}
				}
			}
		}
	}
	/**
	 * 积分收益金额计算
	 *
	 * 
	 * @throws Exception
	 */
	public void pointsEarningsMoneyComputing() throws Exception{
		// 预购账户积分收益计算明细汇总
		DrtFinPoEarningsComputingRecord objQueryDrtFinPoEarningsComputingRecord = new DrtFinPoEarningsComputingRecord();
		String strCurrentDate = DateTimeUtils.converDateToString(new Date(), "yyyyMMdd");
		objQueryDrtFinPoEarningsComputingRecord.setEarningsComputingTime(NumberUtils.toLong(strCurrentDate));
		// 是否加入计算：0是 1否
		objQueryDrtFinPoEarningsComputingRecord.setIsJoinComputing(0);
		// 是否已经计算：0-否 1-是
		objQueryDrtFinPoEarningsComputingRecord.setIsComputed(0);
		// 是否删除：0-是 1-否
		objQueryDrtFinPoEarningsComputingRecord.setIsDelete(1);
		// 预购账户积分收益计算明细集合
		List<DrtFinPoEarningsComputingRecord> lstDrtFinPoEarningsComputingRecord = drtFinPoEarningsComputingRecordService.selectList(objQueryDrtFinPoEarningsComputingRecord);
		for (DrtFinPoEarningsComputingRecord objDrtFinPoEarningsComputingRecord : lstDrtFinPoEarningsComputingRecord) {
			if(objDrtFinPoEarningsComputingRecord != null && objDrtFinPoEarningsComputingRecord.getPoMoney() != null){
				drtFinPoEarningsComputingRecordService.updateAndUpdateMoneyComputing(objDrtFinPoEarningsComputingRecord);
			}
		}
	}
	
	
}
