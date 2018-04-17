package com.csg.statistics.entity;

import java.sql.Timestamp;


/**
 * 统计业务Bean
 * */
public class StatistiseBusinessUserBean {

	/**统计开始时间*/
	private Timestamp  startTime;
	
	/**统计结束时间*/
	private Timestamp  endTime;

	/**
	 * @return startTime
	 */
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime 要设置的 startTime
	 */
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return endtTime
	 */
	public Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * @param endtTime 要设置的 endtTime
	 */
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
}
