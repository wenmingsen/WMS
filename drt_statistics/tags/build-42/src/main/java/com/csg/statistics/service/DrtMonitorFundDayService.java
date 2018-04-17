package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtMonitorFundDay;
import com.csg.statistics.mapper.DrtMonitorFundDayMapper;

/**
 * DrtMonitorFundDayService 日资金监控表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月19日 徐辰
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtMonitorFundDayService{
	
	/**日资金监控表drtMonitorFundDayMapper接口*/
	@Autowired
	private DrtMonitorFundDayMapper drtMonitorFundDayMapper;

	/**
	 * 保存
	 * 
	 * @param drtMonitorFundDay 日资金监控表
	 */
	public void insert(DrtMonitorFundDay drtMonitorFundDay) throws Exception{
		drtMonitorFundDayMapper.insert(drtMonitorFundDay);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtMonitorFundDay 日资金监控表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtMonitorFundDay drtMonitorFundDay) throws Exception{
		drtMonitorFundDayMapper.update(drtMonitorFundDay);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 日资金监控表 主键ID
	 * @return 日资金监控表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtMonitorFundDay selectByPrimaryKey(String id) throws Exception{
		return drtMonitorFundDayMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtMonitorFundDay 日资金监控表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtMonitorFundDay> selectList(DrtMonitorFundDay drtMonitorFundDay) throws Exception{
		return drtMonitorFundDayMapper.selectList(drtMonitorFundDay);
	}

}