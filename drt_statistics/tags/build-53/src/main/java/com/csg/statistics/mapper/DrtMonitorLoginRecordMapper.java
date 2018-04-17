package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtMonitorLoginRecord;
import com.csg.statistics.entity.DrtMonitorLoginRecordBean;

/**
 * DrtMonitorLoginRecordMapper 用户登录记录表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月19日 曾令鹏
 */
public interface DrtMonitorLoginRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtMonitorLoginRecord 用户登录记录表
	 */
	void insert(DrtMonitorLoginRecord drtMonitorLoginRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorLoginRecord 用户登录记录表
	 */
	void update(DrtMonitorLoginRecord drtMonitorLoginRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 用户登录记录表 主键ID
	 * @return 用户登录记录表 单条记录
	 */
	DrtMonitorLoginRecord selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorLoginRecord 用户登录记录表
	 * @return 用户登录记录表 记录集
	 */
	List<DrtMonitorLoginRecord> selectList(DrtMonitorLoginRecord drtMonitorLoginRecord);
	
	
	/**
	 * 通过查询实体自定义非空字段获取记录集
	 * 
	 * @param DrtMonitorLoginRecordBean 用户登录记录表-查询实体
	 * @return 用户登录记录表 记录集
	 */
	List<DrtMonitorLoginRecord> selectListByDrtMonitorLoginRecordBean(DrtMonitorLoginRecordBean drtMonitorLoginRecordBean);
	
	/**
	 *批量插入
	 * 
	 * @param lstDrtMonitorLoginRecord 用户登录记录表集合
	 * @return 影响条数
	 */
	int insertMonitorLoginRecords(List<DrtMonitorLoginRecord> lstDrtMonitorLoginRecord);
	
	/**
	 * 通过省份分组查询数据
	 *	
	 *	<pre>
	 *		结果集数据：provinceName、count
	 *	</pre>
	 *
	 * @param drtMonitorLoginRecordBean 查询实体
	 * @return 查询成功返回结果集
	 */
	List<DrtMonitorLoginRecordBean> selectGroupCountByProvinceName(DrtMonitorLoginRecordBean drtMonitorLoginRecordBean);
	
	/**
	 * 通过城市分组查询数据
	 *
	 * <pre>
	 * 	结果集数据：cityName、count
	 * </pre>
	 * @param drtMonitorLoginRecordBean 查询实体
	 * @return 查询成功返回结果集
	 */
	List<DrtMonitorLoginRecordBean> selectGroupCountByCityName(DrtMonitorLoginRecordBean drtMonitorLoginRecordBean);

}