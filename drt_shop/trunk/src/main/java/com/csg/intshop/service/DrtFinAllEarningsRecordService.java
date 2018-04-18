package com.csg.intshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtFinAllEarningsRecord;
import com.csg.intshop.mapper.DrtFinAllEarningsRecordMapper;

/**
 * DrtFinAllEarningsRecordService 积分收支明细表(当前表)
 *
 * @author  徐辰
 * @since   1.8
 * @version 2018年02月28日 徐辰
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtFinAllEarningsRecordService{
	
	/**积分收支明细表(当前表)drtFinAllEarningsRecordMapper接口*/
	@Autowired
	private DrtFinAllEarningsRecordMapper drtFinAllEarningsRecordMapper;

	/**
	 * 保存
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 */
	public void insert(DrtFinAllEarningsRecord drtFinAllEarningsRecord) throws Exception{
		drtFinAllEarningsRecordMapper.insert(drtFinAllEarningsRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtFinAllEarningsRecord drtFinAllEarningsRecord) throws Exception{
		drtFinAllEarningsRecordMapper.update(drtFinAllEarningsRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poEarningsRecordId 积分收支明细表(当前表) 主键ID
	 * @return 积分收支明细表(当前表) 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtFinAllEarningsRecord selectByPrimaryKey(String poEarningsRecordId) throws Exception{
		return drtFinAllEarningsRecordMapper.selectByPrimaryKey(poEarningsRecordId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinAllEarningsRecord 积分收支明细表(当前表)
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtFinAllEarningsRecord> selectList(DrtFinAllEarningsRecord drtFinAllEarningsRecord) throws Exception{
		return drtFinAllEarningsRecordMapper.selectList(drtFinAllEarningsRecord);
	}

}