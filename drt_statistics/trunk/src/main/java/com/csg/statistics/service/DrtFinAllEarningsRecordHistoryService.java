package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.bean.EarningsExpiryModel;
import com.csg.statistics.entity.DrtFinAllEarningsRecordHistory;
import com.csg.statistics.mapper.DrtFinAllEarningsRecordHistoryMapper;

/**
 * DrtFinAllEarningsRecordHistoryService 积分收支明细表(总表)
 *
 * @author  徐辰
 * @since   1.8
 * @version 2018年02月28日 徐辰
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtFinAllEarningsRecordHistoryService{
	
	/**积分收支明细表(总表)drtFinAllEarningsRecordHistoryMapper接口*/
	@Autowired
	private DrtFinAllEarningsRecordHistoryMapper drtFinAllEarningsRecordHistoryMapper;

	/**
	 * 保存
	 * 
	 * @param drtFinAllEarningsRecordHistory 积分收支明细表(总表)
	 */
	public void insert(DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory) throws Exception{
		drtFinAllEarningsRecordHistoryMapper.insert(drtFinAllEarningsRecordHistory);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinAllEarningsRecordHistory 积分收支明细表(总表)
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory) throws Exception{
		drtFinAllEarningsRecordHistoryMapper.update(drtFinAllEarningsRecordHistory);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poEarningsRecordId 积分收支明细表(总表) 主键ID
	 * @return 积分收支明细表(总表) 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtFinAllEarningsRecordHistory selectByPrimaryKey(String poEarningsRecordId) throws Exception{
		return drtFinAllEarningsRecordHistoryMapper.selectByPrimaryKey(poEarningsRecordId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinAllEarningsRecordHistory 积分收支明细表(总表)
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtFinAllEarningsRecordHistory> selectList(DrtFinAllEarningsRecordHistory drtFinAllEarningsRecordHistory) throws Exception{
		return drtFinAllEarningsRecordHistoryMapper.selectList(drtFinAllEarningsRecordHistory);
	}

	/**
	 * 计算下次截止日期到期积分
	 * 
	 * @param drtFinAllEarningsRecordHistory 积分到期查询模型
	 * @throws Exception 出错抛出异常
	 */
	public void calculateNextTimeExpiryEarnings(EarningsExpiryModel earningsExpiryModel) throws Exception{
		drtFinAllEarningsRecordHistoryMapper.calculateNextTimeExpiryEarnings(earningsExpiryModel);
	}
}