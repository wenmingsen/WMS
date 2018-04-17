package com.csg.statistics.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.entity.DrtFinPoEarningsComputingRecord;
import com.csg.statistics.mapper.DrtFinPoEarningsComputingRecordMapper;
import com.csg.statistics.util.UUIDUtil;
import com.github.pagehelper.PageHelper;

/**
 * DrtFinPoEarningsComputingRecordService 预购账户积分收益计算明细表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月11日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtFinPoEarningsComputingRecordService{
	
	/**预购账户积分收益计算明细表drtFinPoEarningsComputingRecordMapper接口*/
	@Autowired
	private DrtFinPoEarningsComputingRecordMapper drtFinPoEarningsComputingRecordMapper;
	
	/** 理财账户信息表（预购） */
	@Autowired
	private DrtFinPoAccountService drtFinPoAccountService;

	/**
	 * 保存
	 * 
	 * @param drtFinPoEarningsComputingRecord 预购账户积分收益计算明细表
	 */
	public void insert(DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord) throws Exception{
		drtFinPoEarningsComputingRecordMapper.insert(drtFinPoEarningsComputingRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinPoEarningsComputingRecord 预购账户积分收益计算明细表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord) throws Exception{
		drtFinPoEarningsComputingRecordMapper.update(drtFinPoEarningsComputingRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 预购账户积分收益计算明细表 主键ID
	 * @return 预购账户积分收益计算明细表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtFinPoEarningsComputingRecord selectByPrimaryKey(String id) throws Exception{
		return drtFinPoEarningsComputingRecordMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoEarningsComputingRecord 预购账户积分收益计算明细表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtFinPoEarningsComputingRecord> selectList(DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord) throws Exception{
		return drtFinPoEarningsComputingRecordMapper.selectList(drtFinPoEarningsComputingRecord);
	}
	
	/**
	 * 更新记录并更新积分计算金额
	 * 
	 * @param drtFinPoEarningsComputingRecord 预购账户积分收益计算明细表
	 * @return 成功返回1，反之0
	 * @throws Exception 出错抛出异常
	 */
	public int updateAndUpdateMoneyComputing(DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord) throws Exception{
		if(drtFinPoEarningsComputingRecord != null && drtFinPoEarningsComputingRecord.getPoMoney() != null){
			DrtFinPoAccount drtFinPoAccount = drtFinPoAccountService.selectByPrimaryKey(drtFinPoEarningsComputingRecord.getPoAccountId());
			if(drtFinPoAccount != null){
				// 更新收益金额
				drtFinPoAccount.setPoEarningsComputingMoney(drtFinPoAccount.getPoEarningsComputingMoney().longValue() + drtFinPoEarningsComputingRecord.getPoMoney().longValue());
				drtFinPoAccountService.update(drtFinPoAccount);
				
				// 标记该记录已加入计算
				// 是否已经计算：0-否 1-是
				drtFinPoEarningsComputingRecord.setIsComputed(1);
				drtFinPoEarningsComputingRecord.setLastModifyTime(new Timestamp(System.currentTimeMillis()));
				this.update(drtFinPoEarningsComputingRecord);
				return 1;
			}
		}
		// 手动回滚事务
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}

	/**
	 * 消费时更新收益余额
	 * 
	 * @param updateList 待更新的记录集
	 * @param insertList 待插入的记录集
	 * @param drtFinPoAccount 待更新的预购账户记录
	 * @throws Exception 出错抛出异常
	 */
	public void updateMoney(List<DrtFinPoEarningsComputingRecord> updateList,
			List<DrtFinPoEarningsComputingRecord> insertList,
			DrtFinPoAccount drtFinPoAccount) throws Exception{
		for (DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord : updateList) {
			this.update(drtFinPoEarningsComputingRecord);
		}
		for (DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord : insertList) {
			this.insert(drtFinPoEarningsComputingRecord);
		}
		
		drtFinPoAccountService.update(drtFinPoAccount);
	}
	
	/**
	 * 通过预购账户ID消费金额
	 * 
	 * @param poAccountId  预购账户ID
	 * @param money 消费的金额，单位：分
	 * @return 成功返回1，反之0
	 * @throws Exception 出错抛出异常
	 */
	public int moneyConsume(String poAccountId, long money) throws Exception{
		DrtFinPoAccount drtFinPoAccount = drtFinPoAccountService.selectByPrimaryKey(poAccountId);
		if(drtFinPoAccount != null && drtFinPoAccount.getPoTotalMoney() != null && drtFinPoAccount.getPoTotalMoney().longValue() >= money){
			DrtFinPoEarningsComputingRecord queryDrtFinPoEarningsComputingRecord = new DrtFinPoEarningsComputingRecord();
			// 是否加入计算：0是 1否
			queryDrtFinPoEarningsComputingRecord.setIsJoinComputing(0);
			// 是否已经计算：0-否 1-是
			queryDrtFinPoEarningsComputingRecord.setIsComputed(0);
			// 是否删除：0-是 1-否
			queryDrtFinPoEarningsComputingRecord.setIsDelete(1);
			PageHelper.orderBy("earnings_computing_time desc");
			// 该用户收益金额明细
			List<DrtFinPoEarningsComputingRecord> drtFinPoEarningsComputingRecordList = this.selectList(queryDrtFinPoEarningsComputingRecord);
			
			// 待更新、待插入
			List<DrtFinPoEarningsComputingRecord> updateList = new ArrayList<DrtFinPoEarningsComputingRecord>();
			List<DrtFinPoEarningsComputingRecord> insertList = new ArrayList<DrtFinPoEarningsComputingRecord>();
			
			if(drtFinPoEarningsComputingRecordList != null){
				// 一、优先减去没有加入收益的金额
				for (DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord : drtFinPoEarningsComputingRecordList) {
					if(drtFinPoEarningsComputingRecord != null && drtFinPoEarningsComputingRecord.getPoMoney() != null){
						if(money >= drtFinPoEarningsComputingRecord.getPoMoney().longValue()){
							// 1.减
							money -= drtFinPoEarningsComputingRecord.getPoMoney().longValue();
							// 2.更新状态
							drtFinPoEarningsComputingRecord.setIsJoinComputing(1);
							// 3.添加至待更新集
							updateList.add(drtFinPoEarningsComputingRecord);
						}else{
							// 剩余
							String destId = UUIDUtil.generateUUID();
							DrtFinPoEarningsComputingRecord dest = new DrtFinPoEarningsComputingRecord();
							BeanUtils.copyProperties(dest, drtFinPoEarningsComputingRecord);
							dest.setPoMoney(drtFinPoEarningsComputingRecord.getPoMoney().longValue() - money);
							dest.setId(destId);
							insertList.add(dest);
							
							// 减去
							String dest2Id = UUIDUtil.generateUUID();
							DrtFinPoEarningsComputingRecord dest2 = new DrtFinPoEarningsComputingRecord();
							BeanUtils.copyProperties(dest2, drtFinPoEarningsComputingRecord);
							dest2.setPoMoney(money);
							dest2.setIsJoinComputing(1);
							dest2.setId(dest2Id);
							insertList.add(dest2);
							
							// 1.减
							money -= drtFinPoEarningsComputingRecord.getPoMoney().longValue();
							// 2.更新状态
							drtFinPoEarningsComputingRecord.setIsJoinComputing(1);
							drtFinPoEarningsComputingRecord.setRemark("分裂为消费" + dest2.getPoMoney() + "：" + dest2Id + "， 剩余" + dest.getPoMoney() + "：" + destId);
							// 3.添加至待更新集
							updateList.add(drtFinPoEarningsComputingRecord);
							break;
						}
					}
				}
			}
			if(money > 0){
				// 二、减余额
				drtFinPoAccount.setPoTotalMoney((int)(drtFinPoAccount.getPoTotalMoney().longValue()-money));
			}
			// 执行
			this.updateMoney(updateList, insertList, drtFinPoAccount);
			return 1;
		}
		return 0;
	}

}