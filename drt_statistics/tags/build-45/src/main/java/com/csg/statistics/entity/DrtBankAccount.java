package com.csg.statistics.entity;

/**
 * drt_bank_account-->DrtBankAccount 银行账户信息表（绑卡信息）
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
public class DrtBankAccount{
	
	/** 主键ID */
	private String bankAccountId;

	/** 时间 */
	private java.sql.Timestamp createTime;

	/** 银行帐号,银行卡号 */
	private String account;

	/** 户主,持卡人 */
	private String accountName;

	/** 户主手机 */
	private String accountPhone;

	/** 开户银行 */
	private String accountType;

	/** 账户状态 */
	private String accountStatus;

	/** 关联account表ID */
	private String accountId;

	/** 身份证号 */
	private String idCard;

	/**
	 * 主键ID
	 * @param bankAccountId 设置 bankAccountId 属性值为参数值 bankAccountId
	 */
	public void setBankAccountId(String bankAccountId) {
		this.bankAccountId = bankAccountId == null ? null : bankAccountId.trim();
	}
	/**
	 * 主键ID
     * @return 获取bankAccountId属性值
     */
	public String getBankAccountId() {
		return this.bankAccountId;	
	}
	
	/**
	 * 时间
	 * @param createTime 设置 createTime 属性值为参数值 createTime
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 时间
     * @return 获取createTime属性值
     */
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;	
	}
	
	/**
	 * 银行帐号,银行卡号
	 * @param account 设置 account 属性值为参数值 account
	 */
	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}
	/**
	 * 银行帐号,银行卡号
     * @return 获取account属性值
     */
	public String getAccount() {
		return this.account;	
	}
	
	/**
	 * 户主,持卡人
	 * @param accountName 设置 accountName 属性值为参数值 accountName
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName == null ? null : accountName.trim();
	}
	/**
	 * 户主,持卡人
     * @return 获取accountName属性值
     */
	public String getAccountName() {
		return this.accountName;	
	}
	
	/**
	 * 户主手机
	 * @param accountPhone 设置 accountPhone 属性值为参数值 accountPhone
	 */
	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone == null ? null : accountPhone.trim();
	}
	/**
	 * 户主手机
     * @return 获取accountPhone属性值
     */
	public String getAccountPhone() {
		return this.accountPhone;	
	}
	
	/**
	 * 开户银行
	 * @param accountType 设置 accountType 属性值为参数值 accountType
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType == null ? null : accountType.trim();
	}
	/**
	 * 开户银行
     * @return 获取accountType属性值
     */
	public String getAccountType() {
		return this.accountType;	
	}
	
	/**
	 * 账户状态
	 * @param accountStatus 设置 accountStatus 属性值为参数值 accountStatus
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus == null ? null : accountStatus.trim();
	}
	/**
	 * 账户状态
     * @return 获取accountStatus属性值
     */
	public String getAccountStatus() {
		return this.accountStatus;	
	}
	
	/**
	 * 关联account表ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 关联account表ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 身份证号
	 * @param idCard 设置 idCard 属性值为参数值 idCard
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard == null ? null : idCard.trim();
	}
	/**
	 * 身份证号
     * @return 获取idCard属性值
     */
	public String getIdCard() {
		return this.idCard;	
	}
	
	
}
	

	
