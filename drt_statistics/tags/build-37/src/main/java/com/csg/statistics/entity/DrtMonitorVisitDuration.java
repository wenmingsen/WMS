package com.csg.statistics.entity;

/**
 * drt_monitor_visit_duration-->DrtMonitorVisitDuration 用户访问集中时间段表
 *
 * @author  温明森
 * @since   1.8
 * @version 2017年12月25日 温明森
 */
public class DrtMonitorVisitDuration{
	
	/** 主键 */
	private String id;

	/** 用户ID */
	private String accountId;

	/** 当前日期：yyyyMMdd */
	private Integer presentDate;

	/** 00:00-01:00 */
	private Integer h0;

	/** 01:00-02:00 */
	private Integer h1;

	/** 02:00-03:00 */
	private Integer h2;

	/** 03:00-04:00 */
	private Integer h3;

	/** 04:00-05:00 */
	private Integer h4;

	/** 05:00-06:00 */
	private Integer h5;

	/** 06:00-07:00 */
	private Integer h6;

	/** 07:00-08:00 */
	private Integer h7;

	/** 08:00-09:00 */
	private Integer h8;

	/** 09:00-10:00 */
	private Integer h9;

	/** 10:00-11:00 */
	private Integer h10;

	/** 11:00-12:00 */
	private Integer h11;

	/** 12:00-13:00 */
	private Integer h12;

	/** 13:00-14:00 */
	private Integer h13;

	/** 14:00-15:00 */
	private Integer h14;

	/** 15:00-16:00 */
	private Integer h15;

	/** 16:00-17:00 */
	private Integer h16;

	/** 17:00-18:00 */
	private Integer h17;

	/** 18:00-19:00 */
	private Integer h18;

	/** 19:00-20:00 */
	private Integer h19;

	/** 20:00-21:00 */
	private Integer h20;

	/** 21:00-22:00 */
	private Integer h21;

	/** 22:00-23:00 */
	private Integer h22;

	/** 23:00-00:00 */
	private Integer h23;

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
	 * 用户ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 用户ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
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
	 * 00:00-01:00
	 * @param h0 设置 h0 属性值为参数值 h0
	 */
	public void setH0(Integer h0) {
		this.h0 = h0;
	}
	/**
	 * 00:00-01:00
     * @return 获取h0属性值
     */
	public Integer getH0() {
		return this.h0;	
	}
	
	/**
	 * 01:00-02:00
	 * @param h1 设置 h1 属性值为参数值 h1
	 */
	public void setH1(Integer h1) {
		this.h1 = h1;
	}
	/**
	 * 01:00-02:00
     * @return 获取h1属性值
     */
	public Integer getH1() {
		return this.h1;	
	}
	
	/**
	 * 02:00-03:00
	 * @param h2 设置 h2 属性值为参数值 h2
	 */
	public void setH2(Integer h2) {
		this.h2 = h2;
	}
	/**
	 * 02:00-03:00
     * @return 获取h2属性值
     */
	public Integer getH2() {
		return this.h2;	
	}
	
	/**
	 * 03:00-04:00
	 * @param h3 设置 h3 属性值为参数值 h3
	 */
	public void setH3(Integer h3) {
		this.h3 = h3;
	}
	/**
	 * 03:00-04:00
     * @return 获取h3属性值
     */
	public Integer getH3() {
		return this.h3;	
	}
	
	/**
	 * 04:00-05:00
	 * @param h4 设置 h4 属性值为参数值 h4
	 */
	public void setH4(Integer h4) {
		this.h4 = h4;
	}
	/**
	 * 04:00-05:00
     * @return 获取h4属性值
     */
	public Integer getH4() {
		return this.h4;	
	}
	
	/**
	 * 05:00-06:00
	 * @param h5 设置 h5 属性值为参数值 h5
	 */
	public void setH5(Integer h5) {
		this.h5 = h5;
	}
	/**
	 * 05:00-06:00
     * @return 获取h5属性值
     */
	public Integer getH5() {
		return this.h5;	
	}
	
	/**
	 * 06:00-07:00
	 * @param h6 设置 h6 属性值为参数值 h6
	 */
	public void setH6(Integer h6) {
		this.h6 = h6;
	}
	/**
	 * 06:00-07:00
     * @return 获取h6属性值
     */
	public Integer getH6() {
		return this.h6;	
	}
	
	/**
	 * 07:00-08:00
	 * @param h7 设置 h7 属性值为参数值 h7
	 */
	public void setH7(Integer h7) {
		this.h7 = h7;
	}
	/**
	 * 07:00-08:00
     * @return 获取h7属性值
     */
	public Integer getH7() {
		return this.h7;	
	}
	
	/**
	 * 08:00-09:00
	 * @param h8 设置 h8 属性值为参数值 h8
	 */
	public void setH8(Integer h8) {
		this.h8 = h8;
	}
	/**
	 * 08:00-09:00
     * @return 获取h8属性值
     */
	public Integer getH8() {
		return this.h8;	
	}
	
	/**
	 * 09:00-10:00
	 * @param h9 设置 h9 属性值为参数值 h9
	 */
	public void setH9(Integer h9) {
		this.h9 = h9;
	}
	/**
	 * 09:00-10:00
     * @return 获取h9属性值
     */
	public Integer getH9() {
		return this.h9;	
	}
	
	/**
	 * 10:00-11:00
	 * @param h10 设置 h10 属性值为参数值 h10
	 */
	public void setH10(Integer h10) {
		this.h10 = h10;
	}
	/**
	 * 10:00-11:00
     * @return 获取h10属性值
     */
	public Integer getH10() {
		return this.h10;	
	}
	
	/**
	 * 11:00-12:00
	 * @param h11 设置 h11 属性值为参数值 h11
	 */
	public void setH11(Integer h11) {
		this.h11 = h11;
	}
	/**
	 * 11:00-12:00
     * @return 获取h11属性值
     */
	public Integer getH11() {
		return this.h11;	
	}
	
	/**
	 * 12:00-13:00
	 * @param h12 设置 h12 属性值为参数值 h12
	 */
	public void setH12(Integer h12) {
		this.h12 = h12;
	}
	/**
	 * 12:00-13:00
     * @return 获取h12属性值
     */
	public Integer getH12() {
		return this.h12;	
	}
	
	/**
	 * 13:00-14:00
	 * @param h13 设置 h13 属性值为参数值 h13
	 */
	public void setH13(Integer h13) {
		this.h13 = h13;
	}
	/**
	 * 13:00-14:00
     * @return 获取h13属性值
     */
	public Integer getH13() {
		return this.h13;	
	}
	
	/**
	 * 14:00-15:00
	 * @param h14 设置 h14 属性值为参数值 h14
	 */
	public void setH14(Integer h14) {
		this.h14 = h14;
	}
	/**
	 * 14:00-15:00
     * @return 获取h14属性值
     */
	public Integer getH14() {
		return this.h14;	
	}
	
	/**
	 * 15:00-16:00
	 * @param h15 设置 h15 属性值为参数值 h15
	 */
	public void setH15(Integer h15) {
		this.h15 = h15;
	}
	/**
	 * 15:00-16:00
     * @return 获取h15属性值
     */
	public Integer getH15() {
		return this.h15;	
	}
	
	/**
	 * 16:00-17:00
	 * @param h16 设置 h16 属性值为参数值 h16
	 */
	public void setH16(Integer h16) {
		this.h16 = h16;
	}
	/**
	 * 16:00-17:00
     * @return 获取h16属性值
     */
	public Integer getH16() {
		return this.h16;	
	}
	
	/**
	 * 17:00-18:00
	 * @param h17 设置 h17 属性值为参数值 h17
	 */
	public void setH17(Integer h17) {
		this.h17 = h17;
	}
	/**
	 * 17:00-18:00
     * @return 获取h17属性值
     */
	public Integer getH17() {
		return this.h17;	
	}
	
	/**
	 * 18:00-19:00
	 * @param h18 设置 h18 属性值为参数值 h18
	 */
	public void setH18(Integer h18) {
		this.h18 = h18;
	}
	/**
	 * 18:00-19:00
     * @return 获取h18属性值
     */
	public Integer getH18() {
		return this.h18;	
	}
	
	/**
	 * 19:00-20:00
	 * @param h19 设置 h19 属性值为参数值 h19
	 */
	public void setH19(Integer h19) {
		this.h19 = h19;
	}
	/**
	 * 19:00-20:00
     * @return 获取h19属性值
     */
	public Integer getH19() {
		return this.h19;	
	}
	
	/**
	 * 20:00-21:00
	 * @param h20 设置 h20 属性值为参数值 h20
	 */
	public void setH20(Integer h20) {
		this.h20 = h20;
	}
	/**
	 * 20:00-21:00
     * @return 获取h20属性值
     */
	public Integer getH20() {
		return this.h20;	
	}
	
	/**
	 * 21:00-22:00
	 * @param h21 设置 h21 属性值为参数值 h21
	 */
	public void setH21(Integer h21) {
		this.h21 = h21;
	}
	/**
	 * 21:00-22:00
     * @return 获取h21属性值
     */
	public Integer getH21() {
		return this.h21;	
	}
	
	/**
	 * 22:00-23:00
	 * @param h22 设置 h22 属性值为参数值 h22
	 */
	public void setH22(Integer h22) {
		this.h22 = h22;
	}
	/**
	 * 22:00-23:00
     * @return 获取h22属性值
     */
	public Integer getH22() {
		return this.h22;	
	}
	
	/**
	 * 23:00-00:00
	 * @param h23 设置 h23 属性值为参数值 h23
	 */
	public void setH23(Integer h23) {
		this.h23 = h23;
	}
	/**
	 * 23:00-00:00
     * @return 获取h23属性值
     */
	public Integer getH23() {
		return this.h23;	
	}
	
	
}
	

	
