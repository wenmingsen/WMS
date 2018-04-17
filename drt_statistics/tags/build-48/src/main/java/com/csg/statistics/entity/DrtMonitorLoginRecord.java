package com.csg.statistics.entity;

/**
 * drt_monitor_login_record-->DrtMonitorLoginRecord 用户登录记录表
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月22日 曾令鹏
 */
public class DrtMonitorLoginRecord{
	
	/** 主键 */
	private String id;

	/** 当前日期：yyyyMMdd */
	private Integer presentDate;

	/** 用户ID */
	private String accountId;

	/** 手机号码 */
	private String phone;

	/** 省份名称 */
	private String provinceName;

	/** 市名称 */
	private String cityName;

	/** IP地址 */
	private String ipAddr;

	/** 设备类型 */
	private Integer deviceType;

	/** 请求数 */
	private Integer requestCount;

	/** 当天登录次数 */
	private Integer loginNumber;

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
	 * 手机号码
	 * @param phone 设置 phone 属性值为参数值 phone
	 */
	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}
	/**
	 * 手机号码
     * @return 获取phone属性值
     */
	public String getPhone() {
		return this.phone;	
	}
	
	/**
	 * 省份名称
	 * @param provinceName 设置 provinceName 属性值为参数值 provinceName
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName == null ? null : provinceName.trim();
	}
	/**
	 * 省份名称
     * @return 获取provinceName属性值
     */
	public String getProvinceName() {
		return this.provinceName;	
	}
	
	/**
	 * 市名称
	 * @param cityName 设置 cityName 属性值为参数值 cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName == null ? null : cityName.trim();
	}
	/**
	 * 市名称
     * @return 获取cityName属性值
     */
	public String getCityName() {
		return this.cityName;	
	}
	
	/**
	 * IP地址
	 * @param ipAddr 设置 ipAddr 属性值为参数值 ipAddr
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr == null ? null : ipAddr.trim();
	}
	/**
	 * IP地址
     * @return 获取ipAddr属性值
     */
	public String getIpAddr() {
		return this.ipAddr;	
	}
	
	/**
	 * 设备类型
	 * @param deviceType 设置 deviceType 属性值为参数值 deviceType
	 */
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * 设备类型
     * @return 获取deviceType属性值
     */
	public Integer getDeviceType() {
		return this.deviceType;	
	}
	
	/**
	 * 请求数
	 * @param requestCount 设置 requestCount 属性值为参数值 requestCount
	 */
	public void setRequestCount(Integer requestCount) {
		this.requestCount = requestCount;
	}
	/**
	 * 请求数
     * @return 获取requestCount属性值
     */
	public Integer getRequestCount() {
		return this.requestCount;	
	}
	
	/**
	 * 当天登录次数
	 * @param loginNumber 设置 loginNumber 属性值为参数值 loginNumber
	 */
	public void setLoginNumber(Integer loginNumber) {
		this.loginNumber = loginNumber;
	}
	/**
	 * 当天登录次数
     * @return 获取loginNumber属性值
     */
	public Integer getLoginNumber() {
		return this.loginNumber;	
	}
	
	
}
	

	
