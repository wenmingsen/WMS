package com.csg.statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.statistics.entity.DrtFinPoAccountRecord;
import com.csg.statistics.entity.DrtFinPoAccountRecordForQuery;
import com.csg.statistics.mapper.DrtFinPoAccountRecordMapper;

/**
 * DrtFinPoAccountRecordService 理财账户信息交易明细表(预购)
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月19日 徐辰
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtFinPoAccountRecordService{
	
	/**理财账户信息交易明细表(预购)drtFinPoAccountRecordMapper接口*/
	@Autowired
	private DrtFinPoAccountRecordMapper drtFinPoAccountRecordMapper;

	/**
	 * 保存
	 * 
	 * @param drtFinPoAccountRecord 理财账户信息交易明细表(预购)
	 */
	public void insert(DrtFinPoAccountRecord drtFinPoAccountRecord) throws Exception{
		drtFinPoAccountRecordMapper.insert(drtFinPoAccountRecord);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinPoAccountRecord 理财账户信息交易明细表(预购)
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtFinPoAccountRecord drtFinPoAccountRecord) throws Exception{
		drtFinPoAccountRecordMapper.update(drtFinPoAccountRecord);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poAccountRecordId 理财账户信息交易明细表(预购) 主键ID
	 * @return 理财账户信息交易明细表(预购) 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtFinPoAccountRecord selectByPrimaryKey(String poAccountRecordId) throws Exception{
		return drtFinPoAccountRecordMapper.selectByPrimaryKey(poAccountRecordId);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoAccountRecord 理财账户信息交易明细表(预购)
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtFinPoAccountRecord> selectList(DrtFinPoAccountRecord drtFinPoAccountRecord) throws Exception{
		return drtFinPoAccountRecordMapper.selectList(drtFinPoAccountRecord);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoAccountRecordForQuery 理财账户信息交易明细表(预购)
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtFinPoAccountRecord> selectListYesterday(DrtFinPoAccountRecordForQuery drtFinPoAccountRecordForQuery) throws Exception{
		return drtFinPoAccountRecordMapper.selectListYesterday(drtFinPoAccountRecordForQuery);
	}
}