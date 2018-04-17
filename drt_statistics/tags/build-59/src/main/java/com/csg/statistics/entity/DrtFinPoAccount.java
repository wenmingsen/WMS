package com.csg.statistics.entity;

/**
 * drt_fin_po_account-->DrtFinPoAccount 理财账户信息表（预购）
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月11日 曾令鹏
 */
public class DrtFinPoAccount{
	
	/** 主键ID */
	private String poAccountId;

	/** 预购帐号 */
	private String poAccount;

	/** 预购账户总金额 */
	private Integer poTotalMoney;

	/** 预购收益计算金额 */
	private Long poEarningsComputingMoney;

	/** 预购创建时间 */
	private java.sql.Timestamp createTime;

	/** 电融通账号ID */
	private String accountId;

	/** 账户状态(0,关闭;1开通) */
	private String poAccountStatus;

	/** 预购开通电费代扣(0,未开通;1开通) */
	private String isAutoPay;

	/** 预购开通电费代扣时间 */
	private java.sql.Timestamp autoPayTime;

	/** 本预购账户总积分 */
	private Integer totalEarnings;

	/** 绑定用电户口ID */
	private String elecUserId;

	/**
	 * 主键ID
	 * @param poAccountId 设置 poAccountId 属性值为参数值 poAccountId
	 */
	public void setPoAccountId(String poAccountId) {
		this.poAccountId = poAccountId == null ? null : poAccountId.trim();
	}
	/**
	 * 主键ID
     * @return 获取poAccountId属性值
     */
	public String getPoAccountId() {
		return this.poAccountId;	
	}
	
	/**
	 * 预购帐号
	 * @param poAccount 设置 poAccount 属性值为参数值 poAccount
	 */
	public void setPoAccount(String poAccount) {
		this.poAccount = poAccount == null ? null : poAccount.trim();
	}
	/**
	 * 预购帐号
     * @return 获取poAccount属性值
     */
	public String getPoAccount() {
		return this.poAccount;	
	}
	
	/**
	 * 预购账户总金额
	 * @param poTotalMoney 设置 poTotalMoney 属性值为参数值 poTotalMoney
	 */
	public void setPoTotalMoney(Integer poTotalMoney) {
		this.poTotalMoney = poTotalMoney;
	}
	/**
	 * 预购账户总金额
     * @return 获取poTotalMoney属性值
     */
	public Integer getPoTotalMoney() {
		return this.poTotalMoney;	
	}
	
	/**
	 * 预购收益计算金额
	 * @param poEarningsComputingMoney 设置 poEarningsComputingMoney 属性值为参数值 poEarningsComputingMoney
	 */
	public void setPoEarningsComputingMoney(Long poEarningsComputingMoney) {
		this.poEarningsComputingMoney = poEarningsComputingMoney;
	}
	/**
	 * 预购收益计算金额
     * @return 获取poEarningsComputingMoney属性值
     */
	public Long getPoEarningsComputingMoney() {
		return this.poEarningsComputingMoney;	
	}
	
	/**
	 * 预购创建时间
	 * @param createTime 设置 createTime 属性值为参数值 createTime
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 预购创建时间
     * @return 获取createTime属性值
     */
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;	
	}
	
	/**
	 * 电融通账号ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 电融通账号ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 账户状态(0,关闭;1开通)
	 * @param poAccountStatus 设置 poAccountStatus 属性值为参数值 poAccountStatus
	 */
	public void setPoAccountStatus(String poAccountStatus) {
		this.poAccountStatus = poAccountStatus == null ? null : poAccountStatus.trim();
	}
	/**
	 * 账户状态(0,关闭;1开通)
     * @return 获取poAccountStatus属性值
     */
	public String getPoAccountStatus() {
		return this.poAccountStatus;	
	}
	
	/**
	 * 预购开通电费代扣(0,未开通;1开通)
	 * @param isAutoPay 设置 isAutoPay 属性值为参数值 isAutoPay
	 */
	public void setIsAutoPay(String isAutoPay) {
		this.isAutoPay = isAutoPay == null ? null : isAutoPay.trim();
	}
	/**
	 * 预购开通电费代扣(0,未开通;1开通)
     * @return 获取isAutoPay属性值
     */
	public String getIsAutoPay() {
		return this.isAutoPay;	
	}
	
	/**
	 * 预购开通电费代扣时间
	 * @param autoPayTime 设置 autoPayTime 属性值为参数值 autoPayTime
	 */
	public void setAutoPayTime(java.sql.Timestamp autoPayTime) {
		this.autoPayTime = autoPayTime;
	}
	/**
	 * 预购开通电费代扣时间
     * @return 获取autoPayTime属性值
     */
	public java.sql.Timestamp getAutoPayTime() {
		return this.autoPayTime;	
	}
	
	/**
	 * 本预购账户总积分
	 * @param totalEarnings 设置 totalEarnings 属性值为参数值 totalEarnings
	 */
	public void setTotalEarnings(Integer totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	/**
	 * 本预购账户总积分
     * @return 获取totalEarnings属性值
     */
	public Integer getTotalEarnings() {
		return this.totalEarnings;	
	}
	
	/**
	 * 绑定用电户口ID
	 * @param elecUserId 设置 elecUserId 属性值为参数值 elecUserId
	 */
	public void setElecUserId(String elecUserId) {
		this.elecUserId = elecUserId == null ? null : elecUserId.trim();
	}
	/**
	 * 绑定用电户口ID
     * @return 获取elecUserId属性值
     */
	public String getElecUserId() {
		return this.elecUserId;	
	}
	
	
}
	

	
