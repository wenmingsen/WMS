package com.csg.statistics.entity;

/**
 * drt_news-->DrtNews 消息表
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月06日 温明森
 */
public class DrtNews{
	
	/** 表主键 */
	private String tid;

	/** 电融通用户ID */
	private String accountId;

	/** 类型(1,系统消息;2,电费账单消息3.站内消息) */
	private String newType;

	/** 消息主题 */
	private String theme;

	/** 消息详细内容 */
	private String content;

	/** 关键内容 */
	private String keyword;

	/** 消息时间 */
	private java.sql.Timestamp creatTime;

	/** 消息状态 (1,未读;2,已读;3,删除) */
	private String state;

	/** 消息状态变更时间 */
	private java.sql.Timestamp changeTime;

	/**
	 * 表主键
	 * @param tid 设置 tid 属性值为参数值 tid
	 */
	public void setTid(String tid) {
		this.tid = tid == null ? null : tid.trim();
	}
	/**
	 * 表主键
     * @return 获取tid属性值
     */
	public String getTid() {
		return this.tid;	
	}
	
	/**
	 * 电融通用户ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 电融通用户ID
     * @return 获取accountId属性值
     */
	public String getAccountId() {
		return this.accountId;	
	}
	
	/**
	 * 类型(1,系统消息;2,电费账单消息3.站内消息)
	 * @param newType 设置 newType 属性值为参数值 newType
	 */
	public void setNewType(String newType) {
		this.newType = newType == null ? null : newType.trim();
	}
	/**
	 * 类型(1,系统消息;2,电费账单消息3.站内消息)
     * @return 获取newType属性值
     */
	public String getNewType() {
		return this.newType;	
	}
	
	/**
	 * 消息主题
	 * @param theme 设置 theme 属性值为参数值 theme
	 */
	public void setTheme(String theme) {
		this.theme = theme == null ? null : theme.trim();
	}
	/**
	 * 消息主题
     * @return 获取theme属性值
     */
	public String getTheme() {
		return this.theme;	
	}
	
	/**
	 * 消息详细内容
	 * @param content 设置 content 属性值为参数值 content
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
	/**
	 * 消息详细内容
     * @return 获取content属性值
     */
	public String getContent() {
		return this.content;	
	}
	
	/**
	 * 关键内容
	 * @param keyword 设置 keyword 属性值为参数值 keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword == null ? null : keyword.trim();
	}
	/**
	 * 关键内容
     * @return 获取keyword属性值
     */
	public String getKeyword() {
		return this.keyword;	
	}
	
	/**
	 * 消息时间
	 * @param creatTime 设置 creatTime 属性值为参数值 creatTime
	 */
	public void setCreatTime(java.sql.Timestamp creatTime) {
		this.creatTime = creatTime;
	}
	/**
	 * 消息时间
     * @return 获取creatTime属性值
     */
	public java.sql.Timestamp getCreatTime() {
		return this.creatTime;	
	}
	
	/**
	 * 消息状态 (1,未读;2,已读;3,删除)
	 * @param state 设置 state 属性值为参数值 state
	 */
	public void setState(String state) {
		this.state = state == null ? null : state.trim();
	}
	/**
	 * 消息状态 (1,未读;2,已读;3,删除)
     * @return 获取state属性值
     */
	public String getState() {
		return this.state;	
	}
	
	/**
	 * 消息状态变更时间
	 * @param changeTime 设置 changeTime 属性值为参数值 changeTime
	 */
	public void setChangeTime(java.sql.Timestamp changeTime) {
		this.changeTime = changeTime;
	}
	/**
	 * 消息状态变更时间
     * @return 获取changeTime属性值
     */
	public java.sql.Timestamp getChangeTime() {
		return this.changeTime;	
	}
	
	
}
	

	
