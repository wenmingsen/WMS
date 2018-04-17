package com.csg.statistics.entity;

/**
 * 预购账户积分收益计算明细表-查询实体
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年01月10日 曾令鹏
 */
public class DrtFinPoEarningsComputingRecordQuery extends DrtFinPoEarningsComputingRecord {

	/** 收益开始计算时间：yyyyMMdd */
	private Integer earningsComputingTimeStart;
	
	/** 收益结束计算时间：yyyyMMdd */
	private Integer earningsComputingTimeEnd;
	
	
	/**
	 * 收益开始计算时间：yyyyMMdd
	 * @param earningsComputingTimeStart 设置 earningsComputingTimeStart 属性值为参数值 earningsComputingTimeStart
	 */
	public void setEarningsComputingTimeStart(Integer earningsComputingTimeStart) {
		this.earningsComputingTimeStart = earningsComputingTimeStart;
	}
	/**
	 * 收益开始计算时间：yyyyMMdd
     * @return 获取earningsComputingTimeStart属性值
     */
	public Integer getEarningsComputingTimeStart() {
		return this.earningsComputingTimeStart;	
	}
	
	/**
	 * 收益结束计算时间：yyyyMMdd
	 * @param earningsComputingTimeEnd 设置 earningsComputingTimeEnd 属性值为参数值 earningsComputingTimeEnd
	 */
	public void setEarningsComputingTimeEnd(Integer earningsComputingTimeEnd) {
		this.earningsComputingTimeEnd = earningsComputingTimeEnd;
	}
	/**
	 * 收益结束计算时间：yyyyMMdd
     * @return 获取earningsComputingTimeEnd属性值
     */
	public Integer getEarningsComputingTimeEnd() {
		return this.earningsComputingTimeEnd;	
	}
	
	
}
