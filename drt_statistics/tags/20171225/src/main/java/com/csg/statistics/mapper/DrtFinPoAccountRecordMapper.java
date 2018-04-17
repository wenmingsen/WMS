package com.csg.statistics.mapper;

import java.util.List;

import com.csg.statistics.entity.DrtFinPoAccountRecord;
import com.csg.statistics.entity.DrtFinPoAccountRecordForQuery;

/**
 * DrtFinPoAccountRecordMapper 理财账户信息交易明细表(预购)
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月19日 徐辰
 */
public interface DrtFinPoAccountRecordMapper{
	
	/**
	 * 保存
	 * 
	 * @param drtFinPoAccountRecord 理财账户信息交易明细表(预购)
	 */
	void insert(DrtFinPoAccountRecord drtFinPoAccountRecord);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtFinPoAccountRecord 理财账户信息交易明细表(预购)
	 */
	void update(DrtFinPoAccountRecord drtFinPoAccountRecord);
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param poAccountRecordId 理财账户信息交易明细表(预购) 主键ID
	 * @return 理财账户信息交易明细表(预购) 单条记录
	 */
	DrtFinPoAccountRecord selectByPrimaryKey(String poAccountRecordId);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoAccountRecord 理财账户信息交易明细表(预购)
	 * @return 理财账户信息交易明细表(预购) 记录集
	 */
	List<DrtFinPoAccountRecord> selectList(DrtFinPoAccountRecord drtFinPoAccountRecord);

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtFinPoAccountRecordForQuery 理财账户信息交易明细表(预购)
	 * @return 理财账户信息交易明细表(预购) 记录集
	 */
	List<DrtFinPoAccountRecord> selectListYesterday(DrtFinPoAccountRecordForQuery drtFinPoAccountRecordForQuery);
}