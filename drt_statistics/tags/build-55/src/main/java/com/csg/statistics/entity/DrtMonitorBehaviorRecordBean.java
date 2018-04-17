package com.csg.statistics.entity;

/**
 * 用户行为记录表-查询实体
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月19日 曾令鹏
 */
public class DrtMonitorBehaviorRecordBean extends DrtMonitorBehaviorRecord {
	
	/** 用户行为开始时间：yyyyMMddHHmmss */
	private Long behaviorTimeStart;
	
	/** 用户行为结束时间：yyyyMMddHHmmss */
	private Long behaviorTimeEnd;
	
	/**
	 * 用户行为开始时间：yyyyMMddHHmmss
     * @return 获取behaviorTimeStart属性值
     */
	public Long getBehaviorTimeStart() {
		return behaviorTimeStart;
	}
	
	/**
	 * 用户行为开始时间：yyyyMMddHHmmss
	 * @param behaviorTimeStart 设置 behaviorTimeStart 属性值为参数值 behaviorTimeStart
	 */
	public void setBehaviorTimeStart(Long behaviorTimeStart) {
		this.behaviorTimeStart = behaviorTimeStart;
	}
	
	/**
	 * 用户行为结束时间：yyyyMMddHHmmss
     * @return 获取behaviorTimeEnd属性值
     */
	public Long getBehaviorTimeEnd() {
		return behaviorTimeEnd;
	}
	
	/**
	 * 用户行为结束时间：yyyyMMddHHmmss
	 * @param behaviorTimeEnd 设置 behaviorTimeEnd 属性值为参数值 behaviorTimeEnd
	 */
	public void setBehaviorTimeEnd(Long behaviorTimeEnd) {
		this.behaviorTimeEnd = behaviorTimeEnd;
	}
	
}
