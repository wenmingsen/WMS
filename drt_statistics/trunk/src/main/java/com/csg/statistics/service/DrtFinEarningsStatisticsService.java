package com.csg.statistics.service;

import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csg.statistics.entity.DrtFinEarningsStatistics;
import com.csg.statistics.mapper.DrtFinEarningsStatisticsMapper;
import com.csg.statistics.util.GetUUID;
import com.github.pagehelper.PageHelper;

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

	/**
	 * 保存或更新，一个电融通accountId,对应一条记录
	 * @param drtFinEarningsStatistics 分类收益统计表
	 */
	public void insertOrUpdate(DrtFinEarningsStatistics drtFinEarningsStatistics) {
		try {
			DrtFinEarningsStatistics queryDrtFinEarningsStatistics = new DrtFinEarningsStatistics();
			queryDrtFinEarningsStatistics.setAccountId(drtFinEarningsStatistics.getAccountId());
			PageHelper.startPage(1, 1);
			List<DrtFinEarningsStatistics> drtFinEarningsStatisticsList = this.selectList(queryDrtFinEarningsStatistics);
			if(CollectionUtils.isEmpty(drtFinEarningsStatisticsList)){
				drtFinEarningsStatistics.setId(GetUUID.getUuuid());
				drtFinEarningsStatistics.setDynamicEarnings(NumberUtils.toInt(drtFinEarningsStatistics.getDynamicEarnings() + "", 0));
				drtFinEarningsStatistics.setPayElecEarnings(NumberUtils.toInt(drtFinEarningsStatistics.getPayElecEarnings() + "", 0));
				drtFinEarningsStatistics.setActivityEarnings(NumberUtils.toInt(drtFinEarningsStatistics.getActivityEarnings() + "", 0));
				drtFinEarningsStatistics.setExceptionEarnings(NumberUtils.toInt(drtFinEarningsStatistics.getExceptionEarnings() + "", 0));
				drtFinEarningsStatistics.setExchangeEarnings(NumberUtils.toInt(drtFinEarningsStatistics.getExchangeEarnings() + "", 0));
				drtFinEarningsStatistics.setTotalEarnings(drtFinEarningsStatistics.getTotalEarnings());
				this.insert(drtFinEarningsStatistics);
			}else {
				DrtFinEarningsStatistics oldDrtFinEarningsStatistics = drtFinEarningsStatisticsList.get(0);
				oldDrtFinEarningsStatistics.setDynamicEarnings(NumberUtils.toInt(oldDrtFinEarningsStatistics.getDynamicEarnings() + "", 0) + NumberUtils.toInt(drtFinEarningsStatistics.getDynamicEarnings() + "", 0));
				oldDrtFinEarningsStatistics.setPayElecEarnings(NumberUtils.toInt(oldDrtFinEarningsStatistics.getPayElecEarnings() + "", 0) + NumberUtils.toInt(drtFinEarningsStatistics.getPayElecEarnings() + "", 0));
				oldDrtFinEarningsStatistics.setActivityEarnings(NumberUtils.toInt(oldDrtFinEarningsStatistics.getActivityEarnings() + "", 0) + NumberUtils.toInt(drtFinEarningsStatistics.getActivityEarnings() + "", 0));
				oldDrtFinEarningsStatistics.setExceptionEarnings(NumberUtils.toInt(oldDrtFinEarningsStatistics.getExceptionEarnings() + "", 0) + NumberUtils.toInt(drtFinEarningsStatistics.getExceptionEarnings() + "", 0));
				oldDrtFinEarningsStatistics.setExchangeEarnings(NumberUtils.toInt(oldDrtFinEarningsStatistics.getExchangeEarnings() + "", 0) + NumberUtils.toInt(drtFinEarningsStatistics.getExchangeEarnings() + "", 0));
				oldDrtFinEarningsStatistics.setTotalEarnings(drtFinEarningsStatistics.getTotalEarnings());
				this.update(oldDrtFinEarningsStatistics);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}