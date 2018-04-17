package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtMonitorRetentionRecord;
import com.csg.statistics.mapper.DrtMonitorRetentionRecordMapper;

/**
 * DrtMonitorRetentionRecordService 留存率
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月19日 曾令鹏
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMonitorRetentionRecordService{
	
	/**留存率drtMonitorRetentionRecordMapper接口*/
	@Autowired
	private DrtMonitorRetentionRecordMapper drtMonitorRetentionRecordMapper;

	/**
	 * 保存
	 * 
	 * @param drtMonitorRetentionRecord 留存率
	 */
	public void insert(DrtMonitorRetentionRecord drtMonitorRetentionRecord) throws Exception{
		drtMonitorRetentionRecordMapper.insert(drtMonitorRetentionRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorRetentionRecord 留存率
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMonitorRetentionRecord drtMonitorRetentionRecord) throws Exception{
		drtMonitorRetentionRecordMapper.update(drtMonitorRetentionRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 留存率 主键ID
	 * @return 留存率 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtMonitorRetentionRecord selectByPrimaryKey(String id) throws Exception{
		return drtMonitorRetentionRecordMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorRetentionRecord 留存率
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtMonitorRetentionRecord> selectList(DrtMonitorRetentionRecord drtMonitorRetentionRecord) throws Exception{
		return drtMonitorRetentionRecordMapper.selectList(drtMonitorRetentionRecord);
	}
	
}