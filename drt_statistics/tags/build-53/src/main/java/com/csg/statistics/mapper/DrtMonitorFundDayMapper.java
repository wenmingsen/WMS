package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtMonitorFundDay;

/**
 * DrtMonitorFundDayMapper 日资金监控表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月19日 徐辰
 */
public interface DrtMonitorFundDayMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMonitorFundDay 日资金监控表
	 */
	void insert(DrtMonitorFundDay drtMonitorFundDay);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorFundDay 日资金监控表
	 */
	void update(DrtMonitorFundDay drtMonitorFundDay);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 日资金监控表 主键ID
	 * @return 日资金监控表 单条记录
	 */
	DrtMonitorFundDay selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorFundDay 日资金监控表
	 * @return 日资金监控表 记录集
	 */
	List<DrtMonitorFundDay> selectList(DrtMonitorFundDay drtMonitorFundDay);

}