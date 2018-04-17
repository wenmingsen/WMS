package com.csg.statistics.entity;

/**
 * drt_fin_po_earnings_computing_record-->DrtFinPoEarningsComputingRecord 预购账户积分收益计算明细表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月25日 曾令鹏
 */
public class DrtFinPoEarningsComputingRecord{
	
	/** 主键 */
	private String id;

	/** 预购账户ID */
	private String poAccountId;

	/** 预购金额 */
	private Long poMoney;

	/** 预购时间：yyyyMMddHHmmss */
	private Long poTime;

	/** 收益开始计算时间：yyyyMMdd */
	private Integer earningsComputingTime;

	/** 是否加入计算：0是 1否 */
	private Integer isJoinComputing;

	/** 是否已经计算：0-否 1-是 */
	private Integer isComputed;

	/** 备注 */
	private String remark;

	/**
	 * 主键
	 * @param id 设置 id 属性值为参数值 id
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}
	/**
	 * 主键
     * @return 获取id属性值
     */
	public String getId() {
		return this.id;	
	}
	
	/**
	 * 预购账户ID
	 * @param poAccountId 设置 poAccountId 属性值为参数值 poAccountId
	 */
	public void setPoAccountId(String poAccountId) {
		this.poAccountId = poAccountId == null ? null : poAccountId.trim();
	}
	/**
	 * 预购账户ID
     * @return 获取poAccountId属性值
     */
	public String getPoAccountId() {
		return this.poAccountId;	
	}
	
	/**
	 * 预购金额
	 * @param poMoney 设置 poMoney 属性值为参数值 poMoney
	 */
	public void setPoMoney(Long poMoney) {
		this.poMoney = poMoney;
	}
	/**
	 * 预购金额
     * @return 获取poMoney属性值
     */
	public Long getPoMoney() {
		return this.poMoney;	
	}
	
	/**
	 * 预购时间：yyyyMMddHHmmss
	 * @param poTime 设置 poTime 属性值为参数值 poTime
	 */
	public void setPoTime(Long poTime) {
		this.poTime = poTime;
	}
	/**
	 * 预购时间：yyyyMMddHHmmss
     * @return 获取poTime属性值
     */
	public Long getPoTime() {
		return this.poTime;	
	}
	
	/**
	 * 收益开始计算时间：yyyyMMdd
	 * @param earningsComputingTime 设置 earningsComputingTime 属性值为参数值 earningsComputingTime
	 */
	public void setEarningsComputingTime(Integer earningsComputingTime) {
		this.earningsComputingTime = earningsComputingTime;
	}
	/**
	 * 收益开始计算时间：yyyyMMdd
     * @return 获取earningsComputingTime属性值
     */
	public Integer getEarningsComputingTime() {
		return this.earningsComputingTime;	
	}
	
	/**
	 * 是否加入计算：0是 1否
	 * @param isJoinComputing 设置 isJoinComputing 属性值为参数值 isJoinComputing
	 */
	public void setIsJoinComputing(Integer isJoinComputing) {
		this.isJoinComputing = isJoinComputing;
	}
	/**
	 * 是否加入计算：0是 1否
     * @return 获取isJoinComputing属性值
     */
	public Integer getIsJoinComputing() {
		return this.isJoinComputing;	
	}
	
	/**
	 * 是否已经计算：0-否 1-是
	 * @param isComputed 设置 isComputed 属性值为参数值 isComputed
	 */
	public void setIsComputed(Integer isComputed) {
		this.isComputed = isComputed;
	}
	/**
	 * 是否已经计算：0-否 1-是
     * @return 获取isComputed属性值
     */
	public Integer getIsComputed() {
		return this.isComputed;	
	}
	
	/**
	 * 备注
	 * @param remark 设置 remark 属性值为参数值 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
	/**
	 * 备注
     * @return 获取remark属性值
     */
	public String getRemark() {
		return this.remark;	
	}
	
	
}
	

	
