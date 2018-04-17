package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtMonitorVisitDuration;
import com.csg.statistics.mapper.DrtMonitorVisitDurationMapper;


/**
 * DrtMonitorVisitDurationService 用户访问集中时间段表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMonitorVisitDurationService{
	
	/**用户访问集中时间段表drtMonitorVisitDurationMapper接口*/
	@Autowired
	private DrtMonitorVisitDurationMapper drtMonitorVisitDurationMapper;

	/**
	 * 保存
	 * 
	 * @param drtMonitorVisitDuration 用户访问集中时间段表
	 */
	public void insert(DrtMonitorVisitDuration drtMonitorVisitDuration) throws Exception{
		drtMonitorVisitDurationMapper.insert(drtMonitorVisitDuration);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorVisitDuration 用户访问集中时间段表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMonitorVisitDuration drtMonitorVisitDuration) throws Exception{
		drtMonitorVisitDurationMapper.update(drtMonitorVisitDuration);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 用户访问集中时间段表 主键ID
	 * @return 用户访问集中时间段表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtMonitorVisitDuration selectByPrimaryKey(String id) throws Exception{
		return drtMonitorVisitDurationMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorVisitDuration 用户访问集中时间段表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtMonitorVisitDuration> selectList(DrtMonitorVisitDuration drtMonitorVisitDuration) throws Exception{
		return drtMonitorVisitDurationMapper.selectList(drtMonitorVisitDuration);
	}
	
	/**
	 *批量插入
	 * 
	 * @param lstDrtMonitorVisitDuration 用户访问集中时间统计集合
	 * @throws Exception 出错抛出异常
	 */
	public int insertDrtMonitorVisitDurations(List<DrtMonitorVisitDuration> lstDrtMonitorVisitDuration)throws Exception{
		return drtMonitorVisitDurationMapper.insertDrtMonitorVisitDurations(lstDrtMonitorVisitDuration);
	}
}