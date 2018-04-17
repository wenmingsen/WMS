package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtFinPoEarningsComputingRecord;

/**
 * DrtFinPoEarningsComputingRecordMapper 预购账户积分收益计算明细表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月11日 曾令鹏
 */
public interface DrtFinPoEarningsComputingRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtFinPoEarningsComputingRecord 预购账户积分收益计算明细表
	 */
	void insert(DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinPoEarningsComputingRecord 预购账户积分收益计算明细表
	 */
	void update(DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 预购账户积分收益计算明细表 主键ID
	 * @return 预购账户积分收益计算明细表 单条记录
	 */
	DrtFinPoEarningsComputingRecord selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoEarningsComputingRecord 预购账户积分收益计算明细表
	 * @return 预购账户积分收益计算明细表 记录集
	 */
	List<DrtFinPoEarningsComputingRecord> selectList(DrtFinPoEarningsComputingRecord drtFinPoEarningsComputingRecord);

}