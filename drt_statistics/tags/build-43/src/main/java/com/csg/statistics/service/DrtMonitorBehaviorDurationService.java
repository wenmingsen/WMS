package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtMonitorBehaviorDuration;
import com.csg.statistics.mapper.DrtMonitorBehaviorDurationMapper;


/**
 * DrtMonitorBehaviorDurationService 用户行为间隔记录表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月20日 温明森
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMonitorBehaviorDurationService{
	
	/**用户行为间隔记录表drtMonitorBehaviorDurationMapper接口*/
	@Autowired
	private DrtMonitorBehaviorDurationMapper drtMonitorBehaviorDurationMapper;

	/**
	 * 保存
	 * 
	 * @param drtMonitorBehaviorDuration 用户行为间隔记录表
	 */
	public void insert(DrtMonitorBehaviorDuration drtMonitorBehaviorDuration) throws Exception{
		drtMonitorBehaviorDurationMapper.insert(drtMonitorBehaviorDuration);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorBehaviorDuration 用户行为间隔记录表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMonitorBehaviorDuration drtMonitorBehaviorDuration) throws Exception{
		drtMonitorBehaviorDurationMapper.update(drtMonitorBehaviorDuration);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 用户行为间隔记录表 主键ID
	 * @return 用户行为间隔记录表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtMonitorBehaviorDuration selectByPrimaryKey(String id) throws Exception{
		return drtMonitorBehaviorDurationMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorBehaviorDuration 用户行为间隔记录表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtMonitorBehaviorDuration> selectList(DrtMonitorBehaviorDuration drtMonitorBehaviorDuration) throws Exception{
		return drtMonitorBehaviorDurationMapper.selectList(drtMonitorBehaviorDuration);
	}
	
	/**
	 * 通过List批量插入用户行为间隔表
	 * 
	 * @param lstMonitorBehaviorDuration 用户行为间隔表集合
	 * @return int 插入条数
	 */
	public int insertMonitorBehaviorDurations(List<DrtMonitorBehaviorDuration> lstMonitorBehaviorDuration){
		return drtMonitorBehaviorDurationMapper.insertMonitorBehaviorDurations(lstMonitorBehaviorDuration);
	}


}