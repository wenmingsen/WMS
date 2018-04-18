package com.csg.intshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtFinEarningsStatistics;
import com.csg.intshop.mapper.DrtFinEarningsStatisticsMapper;

/**
 * DrtFinEarningsStatisticsService 分类收益统计表
 *
 * @author  xuchen
 * @since   1.8
 * @version 2018年02月06日 xuchen
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtFinEarningsStatisticsService{
	
	/**分类收益统计表drtFinEarningsStatisticsMapper接口*/
	@Autowired
	private DrtFinEarningsStatisticsMapper drtFinEarningsStatisticsMapper;

	/**
	 * 保存
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 */
	public void insert(DrtFinEarningsStatistics drtFinEarningsStatistics) throws Exception{
		drtFinEarningsStatisticsMapper.insert(drtFinEarningsStatistics);
	}

	/**
	 * 批量保存
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 */
	public void insertList(List<DrtFinEarningsStatistics> drtFinEarningsStatisticsList) throws Exception{
		drtFinEarningsStatisticsMapper.insertList(drtFinEarningsStatisticsList);
	}

	/**
	 * 通过主键删除单条记录
	 * 
	 * @param id 分类收益统计表 主键ID
	 * @throws Exception 出错抛出异常
	 */
	public void delete(String id) throws Exception{
		drtFinEarningsStatisticsMapper.delete(id);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtFinEarningsStatistics drtFinEarningsStatistics) throws Exception{
		drtFinEarningsStatisticsMapper.update(drtFinEarningsStatistics);
	}

	/**
	 * 更新非空字段
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 * @throws Exception 出错抛出异常
	 */
	public void updateIfNotNull(DrtFinEarningsStatistics drtFinEarningsStatistics) throws Exception{
		drtFinEarningsStatisticsMapper.updateIfNotNull(drtFinEarningsStatistics);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 分类收益统计表 主键ID
	 * @return 分类收益统计表 单条记录
	 */
	public DrtFinEarningsStatistics selectByPrimaryKey(String id){
		return drtFinEarningsStatisticsMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinEarningsStatistics 分类收益统计表
	 */
	public List<DrtFinEarningsStatistics> selectList(DrtFinEarningsStatistics drtFinEarningsStatistics){
		return drtFinEarningsStatisticsMapper.selectList(drtFinEarningsStatistics);
	}

}