package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtMonitorVisitorRecord;

/**
 * DrtMonitorVisitorRecordMapper 访客记录
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月22日 曾令鹏
 */
public interface DrtMonitorVisitorRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMonitorVisitorRecord 访客记录
	 */
	void insert(DrtMonitorVisitorRecord drtMonitorVisitorRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorVisitorRecord 访客记录
	 */
	void update(DrtMonitorVisitorRecord drtMonitorVisitorRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 访客记录 主键ID
	 * @return 访客记录 单条记录
	 */
	DrtMonitorVisitorRecord selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorVisitorRecord 访客记录
	 * @return 访客记录 记录集
	 */
	List<DrtMonitorVisitorRecord> selectList(DrtMonitorVisitorRecord drtMonitorVisitorRecord);

}