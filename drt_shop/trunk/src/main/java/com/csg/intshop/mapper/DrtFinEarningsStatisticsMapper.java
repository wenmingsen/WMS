package com.csg.intshop.mapper;

import java.util.List;

import com.csg.intshop.entity.DrtFinEarningsStatistics;

/**
 * DrtFinEarningsStatisticsMapper 分类收益统计表
 *
 * @author  xuchen
 * @since   1.8
 * @version 2018年02月06日 xuchen
 */
public interface DrtFinEarningsStatisticsMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 */
	void insert(DrtFinEarningsStatistics drtFinEarningsStatistics);

	/**
	 * 批量保存
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 */
	void insertList(List<DrtFinEarningsStatistics> drtFinEarningsStatisticsList);

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 分类收益统计表 主键ID
	 */
	void delete(String id);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 */
	void update(DrtFinEarningsStatistics drtFinEarningsStatistics);

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 */
	void updateIfNotNull(DrtFinEarningsStatistics drtFinEarningsStatistics);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 分类收益统计表 主键ID
	 * @return 分类收益统计表 单条记录
	 */
	DrtFinEarningsStatistics selectByPrimaryKey(String id);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 * @return 分类收益统计表 记录集
	 */
	List<DrtFinEarningsStatistics> selectList(DrtFinEarningsStatistics drtFinEarningsStatistics);

}