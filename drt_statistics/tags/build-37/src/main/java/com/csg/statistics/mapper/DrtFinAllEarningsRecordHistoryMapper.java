package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtFinAllEarningsRecordHistory;

/**
 * DrtFinAllEarningsRecordHistoryMapper 积分收支明细表(总表)
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月28日 曾令鹏
 */
public interface DrtFinAllEarningsRecordHistoryMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtFinAllEarningsRecordHistory 积分收支明细表(总表)
	 */
	void insert(DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinAllEarningsRecordHistory 积分收支明细表(总表)
	 */
	void update(DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poEarningsRecordId 积分收支明细表(总表) 主键ID
	 * @return 积分收支明细表(总表) 单条记录
	 */
	DrtFinAllEarningsRecordHistory selectByPrimaryKey(String poEarningsRecordId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinAllEarningsRecordHistory 积分收支明细表(总表)
	 * @return 积分收支明细表(总表) 记录集
	 */
	List<DrtFinAllEarningsRecordHistory> selectList(DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory);

}