package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtMonitorBehaviorRecord;
import com.csg.statistics.entity.DrtMonitorBehaviorRecordBean;

/**
 * DrtMonitorBehaviorRecordMapper 用户行为记录表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月19日 曾令鹏
 */
public interface DrtMonitorBehaviorRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMonitorBehaviorRecord 用户行为记录表
	 */
	void insert(DrtMonitorBehaviorRecord drtMonitorBehaviorRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorBehaviorRecord 用户行为记录表
	 */
	void update(DrtMonitorBehaviorRecord drtMonitorBehaviorRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 用户行为记录表 主键ID
	 * @return 用户行为记录表 单条记录
	 */
	DrtMonitorBehaviorRecord selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorBehaviorRecord 用户行为记录表
	 * @return 用户行为记录表 记录集
	 */
	List<DrtMonitorBehaviorRecord> selectList(DrtMonitorBehaviorRecord drtMonitorBehaviorRecord);
	
	/**
	 * 通过查询实体自定义非空字段获取记录集
	 * 
	 * @param drtMonitorBehaviorRecord 用户行为记录表-查询实体
	 * @return 用户行为记录表 记录集
	 */
	List<DrtMonitorBehaviorRecord> selectListByDrtMonitorBehaviorRecordBean(DrtMonitorBehaviorRecordBean drtMonitorBehaviorRecordBean);
	
	/**
	 * 批量插入用户行为记录
	 * 
	 * @param lstDrtMonitorBehaviorRecord 用户行为记录集合
	 * @return 影响条数
	 */
	int insertMonitorBehaviorRecords(List<DrtMonitorBehaviorRecord> lstDrtMonitorBehaviorRecord);

}