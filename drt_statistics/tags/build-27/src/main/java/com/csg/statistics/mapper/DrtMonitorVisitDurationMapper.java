package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtMonitorVisitDuration;


/**
 * DrtMonitorVisitDurationMapper 用户访问集中时间段表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
public interface DrtMonitorVisitDurationMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMonitorVisitDuration 用户访问集中时间段表
	 */
	void insert(DrtMonitorVisitDuration drtMonitorVisitDuration);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorVisitDuration 用户访问集中时间段表
	 */
	void update(DrtMonitorVisitDuration drtMonitorVisitDuration);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 用户访问集中时间段表 主键ID
	 * @return 用户访问集中时间段表 单条记录
	 */
	DrtMonitorVisitDuration selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorVisitDuration 用户访问集中时间段表
	 * @return 用户访问集中时间段表 记录集
	 */
	List<DrtMonitorVisitDuration> selectList(DrtMonitorVisitDuration drtMonitorVisitDuration);
	
	/**
	 *批量插入
	 * 
	 * @param lstDrtMonitorVisitDuration 用户访问集中时间统计集合
	 * @throws Exception 出错抛出异常
	 */
	public int insertDrtMonitorVisitDurations(List<DrtMonitorVisitDuration> lstDrtMonitorVisitDuration);

}