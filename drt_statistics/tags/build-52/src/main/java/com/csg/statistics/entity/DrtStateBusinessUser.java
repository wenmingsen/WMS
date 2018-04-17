package com.csg.statistics.entity;

import java.sql.Timestamp;


/**
 * 业务统计--用户
 * 
 * @author 李城
 * 
 * */
public class DrtStateBusinessUser {

	/**id*/
	private String  id;
	
	/**新增用户日统计*/
	private Integer newUserCount;
	
	/**新增用户月统计*/
	private Integer newUserMonthCount;
	
	/**新增用户周统计*/
	private Integer newUserWeekCount;
	
	/**实名认证日统计*/
	private Integer idcardCount;
	
	/**实名认证周统计*/
	private Integer idcardWeekCount;
	
	/**实名认证月统计*/
	private Integer idcardMonthCount;
	
	/**活跃度日统计*/
	private Integer recordCount;
	
	/**活跃度月统计*/
	private Integer recordMonthCount;
	
	/**活跃度周统计*/
	private Integer recordWeekCount;
	
	/**绑定银行卡日统计*/
	private Integer bangkCount;
	
	/**绑定银行卡月统计*/
	private Integer bangkMonthCount;
	
	/**绑定银行卡周统计*/
	private Integer bankWeekCount;
	
	/**统计时间*/
	private Timestamp statisticsTime;
	
	/**供电单位id*/
	private String  gddwId;
	
	/**供电单位名称*/
	private String gddwName;

	/**创建时间*/
	private Timestamp  createTime;
	
	/**
	 * @return createTime
	 */
	public Timestamp getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime 要设置的 createTime
	 */
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return newUserCount
	 */
	public Integer getNewUserCount() {
		return newUserCount;
	}

	/**
	 * @param newUserCount 要设置的 newUserCount
	 */
	public void setNewUserCount(Integer newUserCount) {
		this.newUserCount = newUserCount;
	}

	/**
	 * @return newUserMonthCount
	 */
	public Integer getNewUserMonthCount() {
		return newUserMonthCount;
	}

	/**
	 * @param newUserMonthCount 要设置的 newUserMonthCount
	 */
	public void setNewUserMonthCount(Integer newUserMonthCount) {
		this.newUserMonthCount = newUserMonthCount;
	}

	/**
	 * @return newUserWeekCount
	 */
	public Integer getNewUserWeekCount() {
		return newUserWeekCount;
	}

	/**
	 * @param newUserWeekCount 要设置的 newUserWeekCount
	 */
	public void setNewUserWeekCount(Integer newUserWeekCount) {
		this.newUserWeekCount = newUserWeekCount;
	}

	/**
	 * @return idcardCount
	 */
	public Integer getIdcardCount() {
		return idcardCount;
	}

	/**
	 * @param idcardCount 要设置的 idcardCount
	 */
	public void setIdcardCount(Integer idcardCount) {
		this.idcardCount = idcardCount;
	}

	/**
	 * @return idcardWeekCount
	 */
	public Integer getIdcardWeekCount() {
		return idcardWeekCount;
	}

	/**
	 * @param idcardWeekCount 要设置的 idcardWeekCount
	 */
	public void setIdcardWeekCount(Integer idcardWeekCount) {
		this.idcardWeekCount = idcardWeekCount;
	}

	/**
	 * @return idcardMonthCount
	 */
	public Integer getIdcardMonthCount() {
		return idcardMonthCount;
	}

	/**
	 * @param idcardMonthCount 要设置的 idcardMonthCount
	 */
	public void setIdcardMonthCount(Integer idcardMonthCount) {
		this.idcardMonthCount = idcardMonthCount;
	}

	/**
	 * @return recordCount
	 */
	public Integer getRecordCount() {
		return recordCount;
	}

	/**
	 * @param recordCount 要设置的 recordCount
	 */
	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * @return recordMonthCount
	 */
	public Integer getRecordMonthCount() {
		return recordMonthCount;
	}

	/**
	 * @param recordMonthCount 要设置的 recordMonthCount
	 */
	public void setRecordMonthCount(Integer recordMonthCount) {
		this.recordMonthCount = recordMonthCount;
	}

	/**
	 * @return recordWeekCount
	 */
	public Integer getRecordWeekCount() {
		return recordWeekCount;
	}

	/**
	 * @param recordWeekCount 要设置的 recordWeekCount
	 */
	public void setRecordWeekCount(Integer recordWeekCount) {
		this.recordWeekCount = recordWeekCount;
	}

	/**
	 * @return bangkCount
	 */
	public Integer getBangkCount() {
		return bangkCount;
	}

	/**
	 * @param bangkCount 要设置的 bangkCount
	 */
	public void setBangkCount(Integer bangkCount) {
		this.bangkCount = bangkCount;
	}

	/**
	 * @return bangkMonthCount
	 */
	public Integer getBangkMonthCount() {
		return bangkMonthCount;
	}

	/**
	 * @param bangkMonthCount 要设置的 bangkMonthCount
	 */
	public void setBangkMonthCount(Integer bangkMonthCount) {
		this.bangkMonthCount = bangkMonthCount;
	}

	/**
	 * @return bankWeekCount
	 */
	public Integer getBankWeekCount() {
		return bankWeekCount;
	}

	/**
	 * @param bankWeekCount 要设置的 bankWeekCount
	 */
	public void setBankWeekCount(Integer bankWeekCount) {
		this.bankWeekCount = bankWeekCount;
	}

	/**
	 * @return statisticsTime
	 */
	public Timestamp getStatisticsTime() {
		return statisticsTime;
	}

	/**
	 * @param statisticsTime 要设置的 statisticsTime
	 */
	public void setStatisticsTime(Timestamp statisticsTime) {
		this.statisticsTime = statisticsTime;
	}

	/**
	 * @return gddwId
	 */
	public String getGddwId() {
		return gddwId;
	}

	/**
	 * @param gddwId 要设置的 gddwId
	 */
	public void setGddwId(String gddwId) {
		this.gddwId = gddwId;
	}

	/**
	 * @return gddwName
	 */
	public String getGddwName() {
		return gddwName;
	}

	/**
	 * @param gddwName 要设置的 gddwName
	 */
	public void setGddwName(String gddwName) {
		this.gddwName = gddwName;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
