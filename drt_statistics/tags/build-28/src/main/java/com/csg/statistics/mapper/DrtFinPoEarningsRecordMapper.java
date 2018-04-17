package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtFinPoEarningsRecord;

/**
 * DrtFinPoEarningsRecordMapper 积分收益明细表(预购)(当前表)
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月08日 曾令鹏
 */
public interface DrtFinPoEarningsRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
	 */
	void insert(DrtFinPoEarningsRecord drtFinPoEarningsRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
	 */
	void update(DrtFinPoEarningsRecord drtFinPoEarningsRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poEarningsRecordId 积分收益明细表(预购)(当前表) 主键ID
	 * @return 积分收益明细表(预购)(当前表) 单条记录
	 */
	DrtFinPoEarningsRecord selectByPrimaryKey(String poEarningsRecordId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoEarningsRecord 积分收益明细表(预购)(当前表)
	 * @return 积分收益明细表(预购)(当前表) 记录集
	 */
	List<DrtFinPoEarningsRecord> selectList(DrtFinPoEarningsRecord drtFinPoEarningsRecord);

}