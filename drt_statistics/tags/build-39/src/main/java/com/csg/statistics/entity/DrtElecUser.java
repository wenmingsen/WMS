package com.csg.statistics.entity;

/**
 * drt_elec_user-->DrtElecUser 用电户信息表（绑定信息）
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
public class DrtElecUser{
	
	/** 主键ID */
	private String elecUserId;

	/** 帐号 */
	private String accountId;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 用电户号（营销） */
	private String ydhh;

	/** 用电名称（营销） */
	private String ydmc;

	/** 结算户号（营销） */
	private String jshh;

	/** 用电地址（营销） */
	private String yddz;

	/** 用户状态（营销） */
	private String yhzt;

	/** 状态:0绑定，1待校验，2解绑 */
	private String elecUserStatus;

	/** 绑定验证时间 */
	private java.sql.Timestamp bindTime;

	/** 解绑时间 */
	private java.sql.Timestamp unbindTime;

	/** 用电区域 */
	private String areaId;

	/** 用电户手机号 */
	private String mobile;

	/** 供电单位编码 */
	private String gddwbm;

	/** 营销绑定状态1:绑定，2:解绑 */
	private String yxBindStatus;

	/** 营销改变时间 */
	private java.sql.Timestamp yxChangeTime;

	/** 营销调用结果0成功，-1失败 */
	private String yxResult;

	/**
	 * 主键ID
	 * @param elecUserId 设置 elecUserId 属性值为参数值 elecUserId
	 */
	public void setElecUserId(String elecUserId) {
		this.elecUserId = elecUserId == null ? null : elecUserId.trim();
	}
	/**
	 * 主键ID
     * @return 获取elecUserId属性值
     */
	public String getElecUserId() {
		return this.elecUserId;	
	}
	
	/**
	 * 帐号
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 帐号
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 创建时间
	 * @param createTime 设置 createTime 属性值为参数值 createTime
	 */
	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}
	/**
	 * 创建时间
     * @return 获取createTime属性值
     */
	public java.sql.Timestamp getCreateTime() {
		return this.createTime;	
	}
	
	/**
	 * 用电户号（营销）
	 * @param ydhh 设置 ydhh 属性值为参数值 ydhh
	 */
	public void setYdhh(String ydhh) {
		this.ydhh = ydhh == null ? null : ydhh.trim();
	}
	/**
	 * 用电户号（营销）
     * @return 获取ydhh属性值
     */
	public String getYdhh() {
		return this.ydhh;	
	}
	
	/**
	 * 用电名称（营销）
	 * @param ydmc 设置 ydmc 属性值为参数值 ydmc
	 */
	public void setYdmc(String ydmc) {
		this.ydmc = ydmc == null ? null : ydmc.trim();
	}
	/**
	 * 用电名称（营销）
     * @return 获取ydmc属性值
     */
	public String getYdmc() {
		return this.ydmc;	
	}
	
	/**
	 * 结算户号（营销）
	 * @param jshh 设置 jshh 属性值为参数值 jshh
	 */
	public void setJshh(String jshh) {
		this.jshh = jshh == null ? null : jshh.trim();
	}
	/**
	 * 结算户号（营销）
     * @return 获取jshh属性值
     */
	public String getJshh() {
		return this.jshh;	
	}
	
	/**
	 * 用电地址（营销）
	 * @param yddz 设置 yddz 属性值为参数值 yddz
	 */
	public void setYddz(String yddz) {
		this.yddz = yddz == null ? null : yddz.trim();
	}
	/**
	 * 用电地址（营销）
     * @return 获取yddz属性值
     */
	public String getYddz() {
		return this.yddz;	
	}
	
	/**
	 * 用户状态（营销）
	 * @param yhzt 设置 yhzt 属性值为参数值 yhzt
	 */
	public void setYhzt(String yhzt) {
		this.yhzt = yhzt == null ? null : yhzt.trim();
	}
	/**
	 * 用户状态（营销）
     * @return 获取yhzt属性值
     */
	public String getYhzt() {
		return this.yhzt;	
	}
	
	/**
	 * 状态:0绑定，1待校验，2解绑
	 * @param elecUserStatus 设置 elecUserStatus 属性值为参数值 elecUserStatus
	 */
	public void setElecUserStatus(String elecUserStatus) {
		this.elecUserStatus = elecUserStatus == null ? null : elecUserStatus.trim();
	}
	/**
	 * 状态:0绑定，1待校验，2解绑
     * @return 获取elecUserStatus属性值
     */
	public String getElecUserStatus() {
		return this.elecUserStatus;	
	}
	
	/**
	 * 绑定验证时间
	 * @param bindTime 设置 bindTime 属性值为参数值 bindTime
	 */
	public void setBindTime(java.sql.Timestamp bindTime) {
		this.bindTime = bindTime;
	}
	/**
	 * 绑定验证时间
     * @return 获取bindTime属性值
     */
	public java.sql.Timestamp getBindTime() {
		return this.bindTime;	
	}
	
	/**
	 * 解绑时间
	 * @param unbindTime 设置 unbindTime 属性值为参数值 unbindTime
	 */
	public void setUnbindTime(java.sql.Timestamp unbindTime) {
		this.unbindTime = unbindTime;
	}
	/**
	 * 解绑时间
     * @return 获取unbindTime属性值
     */
	public java.sql.Timestamp getUnbindTime() {
		return this.unbindTime;	
	}
	
	/**
	 * 用电区域
	 * @param areaId 设置 areaId 属性值为参数值 areaId
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId == null ? null : areaId.trim();
	}
	/**
	 * 用电区域
     * @return 获取areaId属性值
     */
	public String getAreaId() {
		return this.areaId;	
	}
	
	/**
	 * 用电户手机号
	 * @param mobile 设置 mobile 属性值为参数值 mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}
	/**
	 * 用电户手机号
     * @return 获取mobile属性值
     */
	public String getMobile() {
		return this.mobile;	
	}
	
	/**
	 * 供电单位编码
	 * @param gddwbm 设置 gddwbm 属性值为参数值 gddwbm
	 */
	public void setGddwbm(String gddwbm) {
		this.gddwbm = gddwbm == null ? null : gddwbm.trim();
	}
	/**
	 * 供电单位编码
     * @return 获取gddwbm属性值
     */
	public String getGddwbm() {
		return this.gddwbm;	
	}
	
	/**
	 * 营销绑定状态1:绑定，2:解绑
	 * @param yxBindStatus 设置 yxBindStatus 属性值为参数值 yxBindStatus
	 */
	public void setYxBindStatus(String yxBindStatus) {
		this.yxBindStatus = yxBindStatus == null ? null : yxBindStatus.trim();
	}
	/**
	 * 营销绑定状态1:绑定，2:解绑
     * @return 获取yxBindStatus属性值
     */
	public String getYxBindStatus() {
		return this.yxBindStatus;	
	}
	
	/**
	 * 营销改变时间
	 * @param yxChangeTime 设置 yxChangeTime 属性值为参数值 yxChangeTime
	 */
	public void setYxChangeTime(java.sql.Timestamp yxChangeTime) {
		this.yxChangeTime = yxChangeTime;
	}
	/**
	 * 营销改变时间
     * @return 获取yxChangeTime属性值
     */
	public java.sql.Timestamp getYxChangeTime() {
		return this.yxChangeTime;	
	}
	
	/**
	 * 营销调用结果0成功，-1失败
	 * @param yxResult 设置 yxResult 属性值为参数值 yxResult
	 */
	public void setYxResult(String yxResult) {
		this.yxResult = yxResult == null ? null : yxResult.trim();
	}
	/**
	 * 营销调用结果0成功，-1失败
     * @return 获取yxResult属性值
     */
	public String getYxResult() {
		return this.yxResult;	
	}
	
	
}
	

	
