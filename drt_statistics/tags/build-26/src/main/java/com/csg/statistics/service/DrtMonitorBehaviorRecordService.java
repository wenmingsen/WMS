package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtMonitorBehaviorRecord;
import com.csg.statistics.entity.DrtMonitorBehaviorRecordBean;
import com.csg.statistics.mapper.DrtMonitorBehaviorRecordMapper;

/**
 * DrtMonitorBehaviorRecordService 用户行为记录表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月19日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMonitorBehaviorRecordService{
	
	/**用户行为记录表drtMonitorBehaviorRecordMapper接口*/
	@Autowired
	private DrtMonitorBehaviorRecordMapper drtMonitorBehaviorRecordMapper;

	/**
	 * 保存
	 * 
	 * @param drtMonitorBehaviorRecord 用户行为记录表
	 */
	public void insert(DrtMonitorBehaviorRecord drtMonitorBehaviorRecord) throws Exception{
		drtMonitorBehaviorRecordMapper.insert(drtMonitorBehaviorRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorBehaviorRecord 用户行为记录表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMonitorBehaviorRecord drtMonitorBehaviorRecord) throws Exception{
		drtMonitorBehaviorRecordMapper.update(drtMonitorBehaviorRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 用户行为记录表 主键ID
	 * @return 用户行为记录表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtMonitorBehaviorRecord selectByPrimaryKey(String id) throws Exception{
		return drtMonitorBehaviorRecordMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorBehaviorRecord 用户行为记录表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtMonitorBehaviorRecord> selectList(DrtMonitorBehaviorRecord drtMonitorBehaviorRecord) throws Exception{
		return drtMonitorBehaviorRecordMapper.selectList(drtMonitorBehaviorRecord);
	}
	
	/**
	 * 通过查询实体自定义非空字段获取记录集
	 * 
	 * @param drtMonitorBehaviorRecord 用户行为记录表-查询实体
	 * @return 用户行为记录表 记录集
	 */
	public List<DrtMonitorBehaviorRecord> selectListByDrtMonitorBehaviorRecordBean(DrtMonitorBehaviorRecordBean drtMonitorBehaviorRecordBean){
		return drtMonitorBehaviorRecordMapper.selectList(drtMonitorBehaviorRecordBean);
	}

	/**
	 * 批量插入用户行为记录
	 * 
	 * @param lstDrtMonitorBehaviorRecord 用户行为记录集合
	 * @return 影响条数
	 */
	public int insertMonitorBehaviorRecords(List<DrtMonitorBehaviorRecord> lstDrtMonitorBehaviorRecord){
		return drtMonitorBehaviorRecordMapper.insertMonitorBehaviorRecords(lstDrtMonitorBehaviorRecord);
	}
}