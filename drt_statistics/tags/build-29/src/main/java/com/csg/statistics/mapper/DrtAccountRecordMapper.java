package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtAccountRecord;

/**
 * DrtAccountRecordMapper 登录账户操作记录表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月22日 温明森
 */
public interface DrtAccountRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtAccountRecord 登录账户操作记录表
	 */
	void insert(DrtAccountRecord drtAccountRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtAccountRecord 登录账户操作记录表
	 */
	void update(DrtAccountRecord drtAccountRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param accountRecordId 登录账户操作记录表 主键ID
	 * @return 登录账户操作记录表 单条记录
	 */
	DrtAccountRecord selectByPrimaryKey(String accountRecordId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtAccountRecord 登录账户操作记录表
	 * @return 登录账户操作记录表 记录集
	 */
	List<DrtAccountRecord> selectList(DrtAccountRecord drtAccountRecord);
	
	/**
	 * 通过查询实体自定义非空字段获取记录集
	 * 
	 * @param drtMonitorLoginRecordBean 用户登录记录表-查询实体
	 * @return 用户登录记录表 记录集
	 */
	List<DrtAccountRecord> selectListByDrtAccountRecord(DrtAccountRecord drtMonitorLoginRecord);

}