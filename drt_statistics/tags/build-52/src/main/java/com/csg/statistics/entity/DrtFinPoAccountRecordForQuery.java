package com.csg.statistics.entity;

/**
 * drt_fin_po_account_record-->DrtFinPoAccountRecord 理财账户信息交易明细表(预购)
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月19日 徐辰
 */
public class DrtFinPoAccountRecordForQuery extends DrtFinPoAccountRecord{
	
	/** 交易时间yyyyMMdd */
	private java.sql.Timestamp dealTimeStart;

	/** 交易时间yyyyMMdd */
	private java.sql.Timestamp dealTimeEnd;

	/**
	 * 交易时间yyyyMMdd
	 * @param dealTimeStart 设置 dealTimeStart 属性值为参数值 dealTimeStart
	 */
	public void setDealTimeStart(java.sql.Timestamp dealTimeStart) {
		this.dealTimeStart = dealTimeStart;
	}
	/**
	 * 交易时间yyyyMMdd
     * @return 获取dealTimeStart属性值
     */
	public java.sql.Timestamp getDealTimeStart() {
		return this.dealTimeStart;	
	}
	
	/**
	 * 交易时间yyyyMMdd
	 * @param dealTime 设置 dealTime 属性值为参数值 dealTime
	 */
	public void setDealTimeEnd(java.sql.Timestamp dealTimeEnd) {
		this.dealTimeEnd = dealTimeEnd;
	}
	/**
	 * 交易时间yyyyMMdd
     * @return 获取dealTime属性值
     */
	public java.sql.Timestamp getDealTimeEnd() {
		return this.dealTimeEnd;	
	}
	
}
	

	
