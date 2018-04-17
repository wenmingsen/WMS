package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtMonitorBehaviorDuration;


/**
 * DrtMonitorBehaviorDurationMapper 用户行为间隔记录表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月20日 温明森
 */
public interface DrtMonitorBehaviorDurationMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMonitorBehaviorDuration 用户行为间隔记录表
	 */
	void insert(DrtMonitorBehaviorDuration drtMonitorBehaviorDuration);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorBehaviorDuration 用户行为间隔记录表
	 */
	void update(DrtMonitorBehaviorDuration drtMonitorBehaviorDuration);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 用户行为间隔记录表 主键ID
	 * @return 用户行为间隔记录表 单条记录
	 */
	DrtMonitorBehaviorDuration selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorBehaviorDuration 用户行为间隔记录表
	 * @return 用户行为间隔记录表 记录集
	 */
	List<DrtMonitorBehaviorDuration> selectList(DrtMonitorBehaviorDuration drtMonitorBehaviorDuration);
	
	/**
	 * 通过List批量插入用户行为间隔表
	 * 
	 * @param lstMonitorBehaviorDuration 用户行为间隔表集合
	 * @return int 插入条数
	 */
	int insertMonitorBehaviorDurations(List<DrtMonitorBehaviorDuration> lstMonitorBehaviorDuration);

}