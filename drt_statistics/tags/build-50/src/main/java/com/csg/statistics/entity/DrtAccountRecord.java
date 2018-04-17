package com.csg.statistics.entity;

/**
 * drt_account_record-->DrtAccountRecord 登录账户操作记录表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月22日 温明森
 */
public class DrtAccountRecord{
	
	/** 主键ID */
	private String accountRecordId;

	/** 外键ID */
	private String accountId;

	/** 手机号 */
	private String phone;

	/** 记录类型,0:登录,1:登出,2:修改密码,3:修改手势密码,4:修改支付密码, */
	private String recordType;

	/** 时间 */
	private java.sql.Timestamp createTime;

	/** ip地址 */
	private String ipAddress;

	/** 备注 */
	private String remark;

	/**
	 * 主键ID
	 * @param accountRecordId 设置 accountRecordId 属性值为参数值 accountRecordId
	 */
	public void setAccountRecordId(String accountRecordId) {
		this.accountRecordId = accountRecordId == null ? null : accountRecordId.trim();
	}
	/**
	 * 主键ID
     * @return 获取accountRecordId属性值
     */
	public String getAccountRecordId() {
		return this.accountRecordId;	
	}
	
	/**
	 * 外键ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 外键ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 手机号
	 * @param phone 设置 phone 属性值为参数值 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}
	/**
	 * 手机号
     * @return 获取phone属性值
     */
	public String getPhone() {
		return this.phone;	
	}
	
	/**
	 * 记录类型,0:登录,1:登出,2:修改密码,3:修改手势密码,4:修改支付密码,
	 * @param recordType 设置 recordType 属性值为参数值 recordType
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType == null ? null : recordType.trim();
	}
	/**
	 * 记录类型,0:登录,1:登出,2:修改密码,3:修改手势密码,4:修改支付密码,
     * @return 获取recordType属性值
     */
	public String getRecordType() {
		return this.recordType;	
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
	 * ip地址
	 * @param ipAddress 设置 ipAddress 属性值为参数值 ipAddress
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress == null ? null : ipAddress.trim();
	}
	/**
	 * ip地址
     * @return 获取ipAddress属性值
     */
	public String getIpAddress() {
		return this.ipAddress;	
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
	

	
