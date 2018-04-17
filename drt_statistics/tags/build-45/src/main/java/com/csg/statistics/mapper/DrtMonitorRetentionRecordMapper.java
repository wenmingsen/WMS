package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtMonitorRetentionRecord;

/**
 * DrtMonitorRetentionRecordMapper 留存率
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月19日 曾令鹏
 */
public interface DrtMonitorRetentionRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMonitorRetentionRecord 留存率
	 */
	void insert(DrtMonitorRetentionRecord drtMonitorRetentionRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorRetentionRecord 留存率
	 */
	void update(DrtMonitorRetentionRecord drtMonitorRetentionRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 留存率 主键ID
	 * @return 留存率 单条记录
	 */
	DrtMonitorRetentionRecord selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorRetentionRecord 留存率
	 * @return 留存率 记录集
	 */
	List<DrtMonitorRetentionRecord> selectList(DrtMonitorRetentionRecord drtMonitorRetentionRecord);

}