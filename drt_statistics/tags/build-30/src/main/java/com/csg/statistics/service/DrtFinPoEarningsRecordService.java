package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csg.statistics.common.enums.AccountStatusEnum;
import com.csg.statistics.common.enums.NumberTypeEnum;
import com.csg.statistics.entity.DrtAccount;
import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.entity.DrtFinPoEarningsRecord;
import com.csg.statistics.mapper.DrtFinPoEarningsRecordMapper;
import com.google.common.base.Objects;

/**
 * DrtFinPoEarningsRecordService 积分收益明细表(预购)(当前表)
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月08日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtFinPoEarningsRecordService{
	
	/**积分收益明细表(预购)(当前表)drtFinPoEarningsRecordMapper接口*/
	@Autowired
	private DrtFinPoEarningsRecordMapper drtFinPoEarningsRecordMapper;
	
	/** 理财账户信息表（预购） */
	@Autowired
	private DrtFinPoAccountService drtFinPoAccountService;
	
	/** 登录账户信息表 */
	@Autowired
	private DrtAccountService drtAccountService;

	/**
	 * 保存
	 * 
	 * @param drtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
	 */
	public void insert(DrtFinPoEarningsRecord drtFinPoEarningsRecord) throws Exception{
		drtFinPoEarningsRecordMapper.insert(drtFinPoEarningsRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtFinPoEarningsRecord drtFinPoEarningsRecord) throws Exception{
		drtFinPoEarningsRecordMapper.update(drtFinPoEarningsRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poEarningsRecordId 积分收益明细表(预购)(当前表) 主键ID
	 * @return 积分收益明细表(预购)(当前表) 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtFinPoEarningsRecord selectByPrimaryKey(String poEarningsRecordId) throws Exception{
		return drtFinPoEarningsRecordMapper.selectByPrimaryKey(poEarningsRecordId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtFinPoEarningsRecord> selectList(DrtFinPoEarningsRecord drtFinPoEarningsRecord) throws Exception{
		return drtFinPoEarningsRecordMapper.selectList(drtFinPoEarningsRecord);
	}
	
	/**
	 * 保存记录并更新积分
	 * 
	 * @param drtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
	 * @return 成功返回1，反之0
	 * @throws Exception 出错抛出异常
	 */
	public int insertAndUpdatePoints(DrtFinPoEarningsRecord drtFinPoEarningsRecord) throws Exception{
		if(drtFinPoEarningsRecord != null && drtFinPoEarningsRecord.getEarnings() != null && drtFinPoEarningsRecord.getEarnings().intValue() != 0){
			// 插入积分收益明细表(预购)
			this.insert(drtFinPoEarningsRecord);
			// 更新预购账户总积分
			DrtFinPoAccount drtFinPoAccount = drtFinPoAccountService.selectByPrimaryKey(drtFinPoEarningsRecord.getPoAccountId());
			if(drtFinPoAccount != null && drtFinPoAccount.getTotalEarnings() != null){
				if(Objects.equal(NumberTypeEnum.PLUS.getValue(), drtFinPoEarningsRecord.getType())){
					drtFinPoAccount.setTotalEarnings(drtFinPoAccount.getTotalEarnings().intValue() + drtFinPoEarningsRecord.getEarnings().intValue());
				}else{
					drtFinPoAccount.setTotalEarnings(drtFinPoAccount.getTotalEarnings().intValue() - drtFinPoEarningsRecord.getEarnings().intValue());
				}
				drtFinPoAccountService.update(drtFinPoAccount);
				
				// 更新电融通账户总积分
				DrtAccount drtAccount = drtAccountService.selectByPrimaryKey(drtFinPoEarningsRecord.getAccountId());
				if(drtAccount != null && drtAccount.getAccountStatus() != null && Objects.equal(AccountStatusEnum.NORMAL.getValue(), drtAccount.getAccountStatus())
						&& drtAccount.getTotalEarnings() != null){
					// 正常账户
					if(Objects.equal(NumberTypeEnum.PLUS.getValue(), drtFinPoEarningsRecord.getType())){
						drtAccount.setTotalEarnings(drtAccount.getTotalEarnings().intValue() + drtFinPoEarningsRecord.getEarnings().intValue());
					}else{
						drtAccount.setTotalEarnings(drtAccount.getTotalEarnings().intValue() - drtFinPoEarningsRecord.getEarnings().intValue());
					}
					drtAccountService.update(drtAccount);
					return 1;
				}
			}
		}
		// 手动回滚事务
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}

}