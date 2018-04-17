package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtFinAllEarningsRecord;

/**
 * DrtFinAllEarningsRecordMapper 积分收支明细表(当前表)
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月28日 曾令鹏
 */
public interface DrtFinAllEarningsRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 */
	void insert(DrtFinAllEarningsRecord drtFinAllEarningsRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 */
	void update(DrtFinAllEarningsRecord drtFinAllEarningsRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poEarningsRecordId 积分收支明细表(当前表) 主键ID
	 * @return 积分收支明细表(当前表) 单条记录
	 */
	DrtFinAllEarningsRecord selectByPrimaryKey(String poEarningsRecordId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 * @return 积分收支明细表(当前表) 记录集
	 */
	List<DrtFinAllEarningsRecord> selectList(DrtFinAllEarningsRecord drtFinAllEarningsRecord);

}