package com.csg.statistics.entity;

/**
 * drt_monitor_visitor_record-->DrtMonitorVisitorRecord 访客记录
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月25日 曾令鹏
 */
public class DrtMonitorVisitorRecord{
	
	/** 主键 */
	private String id;

	/** 当前日期：yyyyMMdd */
	private Integer presentDate;

	/** 访客来源：掌厅统计数 */
	private Long palmHallCount;

	/** 访客来源：网厅统计数 */
	private Long netHallCount;

	/** 访客来源：微信统计数 */
	private Long weChatCount;

	/** 访客来源：其它统计数 */
	private Long otherCount;

	/** 过去一周访客省份分布json串 */
	private String provinceListJson;

	/** 过去一周访客城市分布json串 */
	private String cityListJson;

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
	 * 访客来源：掌厅统计数
	 * @param palmHallCount 设置 palmHallCount 属性值为参数值 palmHallCount
	 */
	public void setPalmHallCount(Long palmHallCount) {
		this.palmHallCount = palmHallCount;
	}
	/**
	 * 访客来源：掌厅统计数
     * @return 获取palmHallCount属性值
     */
	public Long getPalmHallCount() {
		return this.palmHallCount;	
	}
	
	/**
	 * 访客来源：网厅统计数
	 * @param netHallCount 设置 netHallCount 属性值为参数值 netHallCount
	 */
	public void setNetHallCount(Long netHallCount) {
		this.netHallCount = netHallCount;
	}
	/**
	 * 访客来源：网厅统计数
     * @return 获取netHallCount属性值
     */
	public Long getNetHallCount() {
		return this.netHallCount;	
	}
	
	/**
	 * 访客来源：微信统计数
	 * @param weChatCount 设置 weChatCount 属性值为参数值 weChatCount
	 */
	public void setWeChatCount(Long weChatCount) {
		this.weChatCount = weChatCount;
	}
	/**
	 * 访客来源：微信统计数
     * @return 获取weChatCount属性值
     */
	public Long getWeChatCount() {
		return this.weChatCount;	
	}
	
	/**
	 * 访客来源：其它统计数
	 * @param otherCount 设置 otherCount 属性值为参数值 otherCount
	 */
	public void setOtherCount(Long otherCount) {
		this.otherCount = otherCount;
	}
	/**
	 * 访客来源：其它统计数
     * @return 获取otherCount属性值
     */
	public Long getOtherCount() {
		return this.otherCount;	
	}
	
	/**
	 * 过去一周访客省份分布json串
	 * @param provinceListJson 设置 provinceListJson 属性值为参数值 provinceListJson
	 */
	public void setProvinceListJson(String provinceListJson) {
		this.provinceListJson = provinceListJson == null ? null : provinceListJson.trim();
	}
	/**
	 * 过去一周访客省份分布json串
     * @return 获取provinceListJson属性值
     */
	public String getProvinceListJson() {
		return this.provinceListJson;	
	}
	
	/**
	 * 过去一周访客城市分布json串
	 * @param cityListJson 设置 cityListJson 属性值为参数值 cityListJson
	 */
	public void setCityListJson(String cityListJson) {
		this.cityListJson = cityListJson == null ? null : cityListJson.trim();
	}
	/**
	 * 过去一周访客城市分布json串
     * @return 获取cityListJson属性值
     */
	public String getCityListJson() {
		return this.cityListJson;	
	}
	
	
}
	

	
