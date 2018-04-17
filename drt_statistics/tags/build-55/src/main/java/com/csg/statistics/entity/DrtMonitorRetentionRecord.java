package com.csg.statistics.entity;

/**
 * drt_monitor_retention_record-->DrtMonitorRetentionRecord 留存率
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月20日 曾令鹏
 */
public class DrtMonitorRetentionRecord{
	
	/** 主键 */
	private String id;

	/** 当前日期：yyyyMMdd */
	private Integer presentDate;

	/** 登录用户数 */
	private Long loginCount;

	/** 注册用户数 */
	private Long registerCount;

	/** 绑定用电户号数 */
	private Long elecNumberCount;

	/** 绑定银行卡数 */
	private Long bankCardCount;

	/** 预购电费数 */
	private Long poMoneyCount;

	/** pv数 */
	private Long pvCount;

	/** uv数 */
	private Long uvCount;

	/** 1日留存数 */
	private String retainedCount1;

	/** 3日留存数 */
	private String retainedCount3;

	/** 7日留存数 */
	private String retainedCount7;

	/** 30日留存数 */
	private String retainedCount30;

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
	 * 当前日期：yyyyMMdd
	 * @param presentDate 设置 presentDate 属性值为参数值 presentDate
	 */
	public void setPresentDate(Integer presentDate) {
		this.presentDate = presentDate;
	}
	/**
	 * 当前日期：yyyyMMdd
     * @return 获取presentDate属性值
     */
	public Integer getPresentDate() {
		return this.presentDate;	
	}
	
	/**
	 * 登录用户数
	 * @param loginCount 设置 loginCount 属性值为参数值 loginCount
	 */
	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}
	/**
	 * 登录用户数
     * @return 获取loginCount属性值
     */
	public Long getLoginCount() {
		return this.loginCount;	
	}
	
	/**
	 * 注册用户数
	 * @param registerCount 设置 registerCount 属性值为参数值 registerCount
	 */
	public void setRegisterCount(Long registerCount) {
		this.registerCount = registerCount;
	}
	/**
	 * 注册用户数
     * @return 获取registerCount属性值
     */
	public Long getRegisterCount() {
		return this.registerCount;	
	}
	
	/**
	 * 绑定用电户号数
	 * @param elecNumberCount 设置 elecNumberCount 属性值为参数值 elecNumberCount
	 */
	public void setElecNumberCount(Long elecNumberCount) {
		this.elecNumberCount = elecNumberCount;
	}
	/**
	 * 绑定用电户号数
     * @return 获取elecNumberCount属性值
     */
	public Long getElecNumberCount() {
		return this.elecNumberCount;	
	}
	
	/**
	 * 绑定银行卡数
	 * @param bankCardCount 设置 bankCardCount 属性值为参数值 bankCardCount
	 */
	public void setBankCardCount(Long bankCardCount) {
		this.bankCardCount = bankCardCount;
	}
	/**
	 * 绑定银行卡数
     * @return 获取bankCardCount属性值
     */
	public Long getBankCardCount() {
		return this.bankCardCount;	
	}
	
	/**
	 * 预购电费数
	 * @param poMoneyCount 设置 poMoneyCount 属性值为参数值 poMoneyCount
	 */
	public void setPoMoneyCount(Long poMoneyCount) {
		this.poMoneyCount = poMoneyCount;
	}
	/**
	 * 预购电费数
     * @return 获取poMoneyCount属性值
     */
	public Long getPoMoneyCount() {
		return this.poMoneyCount;	
	}
	
	/**
	 * pv数
	 * @param pvCount 设置 pvCount 属性值为参数值 pvCount
	 */
	public void setPvCount(Long pvCount) {
		this.pvCount = pvCount;
	}
	/**
	 * pv数
     * @return 获取pvCount属性值
     */
	public Long getPvCount() {
		return this.pvCount;	
	}
	
	/**
	 * uv数
	 * @param uvCount 设置 uvCount 属性值为参数值 uvCount
	 */
	public void setUvCount(Long uvCount) {
		this.uvCount = uvCount;
	}
	/**
	 * uv数
     * @return 获取uvCount属性值
     */
	public Long getUvCount() {
		return this.uvCount;	
	}
	
	/**
	 * 1日留存数
	 * @param retainedCount1 设置 retainedCount1 属性值为参数值 retainedCount1
	 */
	public void setRetainedCount1(String retainedCount1) {
		this.retainedCount1 = retainedCount1 == null ? null : retainedCount1.trim();
	}
	/**
	 * 1日留存数
     * @return 获取retainedCount1属性值
     */
	public String getRetainedCount1() {
		return this.retainedCount1;	
	}
	
	/**
	 * 3日留存数
	 * @param retainedCount3 设置 retainedCount3 属性值为参数值 retainedCount3
	 */
	public void setRetainedCount3(String retainedCount3) {
		this.retainedCount3 = retainedCount3 == null ? null : retainedCount3.trim();
	}
	/**
	 * 3日留存数
     * @return 获取retainedCount3属性值
     */
	public String getRetainedCount3() {
		return this.retainedCount3;	
	}
	
	/**
	 * 7日留存数
	 * @param retainedCount7 设置 retainedCount7 属性值为参数值 retainedCount7
	 */
	public void setRetainedCount7(String retainedCount7) {
		this.retainedCount7 = retainedCount7 == null ? null : retainedCount7.trim();
	}
	/**
	 * 7日留存数
     * @return 获取retainedCount7属性值
     */
	public String getRetainedCount7() {
		return this.retainedCount7;	
	}
	
	/**
	 * 30日留存数
	 * @param retainedCount30 设置 retainedCount30 属性值为参数值 retainedCount30
	 */
	public void setRetainedCount30(String retainedCount30) {
		this.retainedCount30 = retainedCount30 == null ? null : retainedCount30.trim();
	}
	/**
	 * 30日留存数
     * @return 获取retainedCount30属性值
     */
	public String getRetainedCount30() {
		return this.retainedCount30;	
	}
	
	
}
	

	
