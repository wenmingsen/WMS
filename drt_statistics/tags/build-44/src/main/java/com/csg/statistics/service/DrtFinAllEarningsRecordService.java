package com.csg.statistics.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csg.statistics.common.enums.AccountStatusEnum;
import com.csg.statistics.common.enums.NumberTypeEnum;
import com.csg.statistics.entity.DrtAccount;
import com.csg.statistics.entity.DrtFinAllEarningsRecord;
import com.csg.statistics.entity.DrtFinAllEarningsRecordHistory;
import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.mapper.DrtFinAllEarningsRecordMapper;
import com.csg.statistics.util.DateTimeUtils;
import com.google.common.base.Objects;

/**
 * DrtFinAllEarningsRecordService 积分收支明细表(当前表)
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月28日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtFinAllEarningsRecordService{
	
	/** 日志 */
	private Logger log = LoggerFactory.getLogger(DrtFinAllEarningsRecordService.class);
	
	/**积分收支明细表(当前表)drtFinAllEarningsRecordMapper接口*/
	@Autowired
	private DrtFinAllEarningsRecordMapper drtFinAllEarningsRecordMapper;
	
	/** 理财账户信息表（预购） */
	@Autowired
	private DrtFinPoAccountService drtFinPoAccountService;
	
	/** 登录账户信息表 */
	@Autowired
	private DrtAccountService drtAccountService;
	
	/** 积分收支明细表(总表) */
	@Autowired
	private DrtFinAllEarningsRecordHistoryService drtFinAllEarningsRecordHistoryService;

	/**
	 * 保存
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 */
	public void insert(DrtFinAllEarningsRecord drtFinAllEarningsRecord) throws Exception{
		drtFinAllEarningsRecordMapper.insert(drtFinAllEarningsRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtFinAllEarningsRecord drtFinAllEarningsRecord) throws Exception{
		drtFinAllEarningsRecordMapper.update(drtFinAllEarningsRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poEarningsRecordId 积分收支明细表(当前表) 主键ID
	 * @return 积分收支明细表(当前表) 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtFinAllEarningsRecord selectByPrimaryKey(String poEarningsRecordId) throws Exception{
		return drtFinAllEarningsRecordMapper.selectByPrimaryKey(poEarningsRecordId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtFinAllEarningsRecord> selectList(DrtFinAllEarningsRecord drtFinAllEarningsRecord) throws Exception{
		return drtFinAllEarningsRecordMapper.selectList(drtFinAllEarningsRecord);
	}
	
	/**
	 * 保存记录并更新总积分
	 * 
	 * @param drtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
	 * @return 成功返回1，反之0
	 * @throws Exception 出错抛出异常
	 */
	public int insertAndUpdateTotalPoints(DrtFinAllEarningsRecord drtFinAllEarningsRecord) throws Exception{
		if(drtFinAllEarningsRecord != null && drtFinAllEarningsRecord.getEarnings() != null && drtFinAllEarningsRecord.getEarnings().intValue() != 0){
			// 更新预购账户总积分
			DrtFinPoAccount drtFinPoAccount = drtFinPoAccountService.selectByPrimaryKey(drtFinAllEarningsRecord.getPoAccountId());
			if(drtFinPoAccount != null && drtFinPoAccount.getTotalEarnings() != null){
				// 预购账户积分只增不减
				if(Objects.equal(NumberTypeEnum.PLUS.getValue(), drtFinAllEarningsRecord.getPmType())){
					drtFinPoAccount.setTotalEarnings(NumberUtils.toInt(drtFinPoAccount.getTotalEarnings() + "") + drtFinAllEarningsRecord.getEarnings().intValue());
					// 赋值总积分
					drtFinAllEarningsRecord.setTotalEarnings(drtFinPoAccount.getTotalEarnings());
					
					// 更新预购账户总积分
					drtFinPoAccountService.update(drtFinPoAccount);
				}
				
				// 更新电融通账户总积分
				DrtAccount drtAccount = drtAccountService.selectByPrimaryKey(drtFinAllEarningsRecord.getAccountId());
				if(drtAccount != null && drtAccount.getAccountStatus() != null && Objects.equal(AccountStatusEnum.NORMAL.getValue(), drtAccount.getAccountStatus())
						&& drtAccount.getTotalEarnings() != null){
					// 正常账户
					if(Objects.equal(NumberTypeEnum.PLUS.getValue(), drtFinAllEarningsRecord.getPmType())){
						drtAccount.setTotalEarnings(NumberUtils.toInt(drtAccount.getTotalEarnings() + "") + drtFinAllEarningsRecord.getEarnings().intValue());
					}else{
						drtAccount.setTotalEarnings(NumberUtils.toInt(drtAccount.getTotalEarnings() + "") - drtFinAllEarningsRecord.getEarnings().intValue());
					}
					// 更新电融通账户总积分
					drtAccountService.update(drtAccount);
					
					// 插入积分收益明细表(预购)
					// 设置积分记录当前电融通账户积分余额
					drtFinAllEarningsRecord.setCurrentEarnings(drtAccount.getTotalEarnings());
					drtFinAllEarningsRecord.setLastModifyTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
					this.insert(drtFinAllEarningsRecord);
					// 积分收支明细表(总表)
					DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory = convertToDrtFinAllEarningsRecordHistory(drtFinAllEarningsRecord);
					drtFinAllEarningsRecordHistoryService.insert(drtFinAllEarningsRecordHistory);
					return 1;
				}
			}
		}
		log.info("com.csg.statistics.service.DrtFinAllEarningsRecordService.insertAndUpdateTotalPoints方法回滚：" + drtFinAllEarningsRecord.getPoEarningsRecordId());
		// 手动回滚事务
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}
	
	/**
	 * 积分收支明细表(当前表)--转换成-->积分收支明细表(当前表)
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 * @return 积分收支明细表(当前表)
	 */
	private DrtFinAllEarningsRecordHistory convertToDrtFinAllEarningsRecordHistory(DrtFinAllEarningsRecord drtFinAllEarningsRecord){
		DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory = new DrtFinAllEarningsRecordHistory();
		drtFinAllEarningsRecordHistory.setPoEarningsRecordId(drtFinAllEarningsRecord.getPoEarningsRecordId());
		drtFinAllEarningsRecordHistory.setPoAccountId(drtFinAllEarningsRecord.getPoAccountId());
		drtFinAllEarningsRecordHistory.setAccountId(drtFinAllEarningsRecord.getAccountId());
		drtFinAllEarningsRecordHistory.setElecUserId(drtFinAllEarningsRecord.getElecUserId());
		drtFinAllEarningsRecordHistory.setEarnings(drtFinAllEarningsRecord.getEarnings());
		drtFinAllEarningsRecordHistory.setTotalEarnings(drtFinAllEarningsRecord.getTotalEarnings());
		drtFinAllEarningsRecordHistory.setRecordTime(drtFinAllEarningsRecord.getRecordTime());
		drtFinAllEarningsRecordHistory.setPmType(drtFinAllEarningsRecord.getPmType());
		drtFinAllEarningsRecordHistory.setFromType(drtFinAllEarningsRecord.getFromType());
		drtFinAllEarningsRecordHistory.setFromName(drtFinAllEarningsRecord.getFromName());
		drtFinAllEarningsRecordHistory.setOrderNo(drtFinAllEarningsRecord.getOrderNo());
		drtFinAllEarningsRecordHistory.setAuditState(drtFinAllEarningsRecord.getAuditState());
		drtFinAllEarningsRecordHistory.setCurrentEarnings(drtFinAllEarningsRecord.getCurrentEarnings());
		drtFinAllEarningsRecordHistory.setLastModifyTime(drtFinAllEarningsRecord.getLastModifyTime());
		return drtFinAllEarningsRecordHistory;
	}
	
}