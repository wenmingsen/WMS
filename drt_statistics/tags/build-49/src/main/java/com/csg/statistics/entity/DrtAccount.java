package com.csg.statistics.entity;

/**
 * drt_account-->DrtAccount 登录账户信息表（主要包括电融通账户、密码）
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月11日 曾令鹏
 */
public class DrtAccount{
	
	/** 主键ID */
	private String accountId;

	/** 创建时间 */
	private java.sql.Timestamp createTime;

	/** 外键ID */
	private String userId;

	/** 手机号 */
	private String phone;

	/** 登录密码 */
	private String pwd;

	/** 手势密码 */
	private String gesturePwd;

	/** 状态,0:正常,1:冻结,2:锁定 */
	private String accountStatus;

	/** 头像地址 */
	private String imgUrl;

	/** 昵称 */
	private String nickname;

	/** 登录失败次数 */
	private Integer loginFailCnt;

	/** 登录失败时间 */
	private java.sql.Timestamp loginFailTime;

	/** 备注 */
	private String remark;

	/** 修改手势密码错误次数 */
	private Integer gestureFailCnt;

	/** 修改手势密码错误时间 */
	private java.sql.Timestamp gestureFailTime;

	/** 是否开启系统消息通知（0不开启，1开启） */
	private String openSysMsg;

	/** 开启系统信息通知时间 */
	private java.sql.Timestamp openSysMsgTime;

	/** 是否开启电费账单消息提醒（0不开启，1开启） */
	private String openEleBill;

	/** 来源 */
	private String source;

	/** 开启电费账单信息提醒时间 */
	private java.sql.Timestamp openEleBillTime;

	/** 总积分 */
	private Integer totalEarnings;

	/** 支付密码 */
	private String payPwd;

	/**
	 * 主键ID
	 * @param accountId 设置 accountId 属性值为参数值 accountId
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId == null ? null : accountId.trim();
	}
	/**
	 * 主键ID
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
	 * 外键ID
	 * @param userId 设置 userId 属性值为参数值 userId
	 */
	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}
	/**
	 * 外键ID
     * @return 获取userId属性值
     */
	public String getUserId() {
		return this.userId;	
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
	 * 登录密码
	 * @param pwd 设置 pwd 属性值为参数值 pwd
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}
	/**
	 * 登录密码
     * @return 获取pwd属性值
     */
	public String getPwd() {
		return this.pwd;	
	}
	
	/**
	 * 手势密码
	 * @param gesturePwd 设置 gesturePwd 属性值为参数值 gesturePwd
	 */
	public void setGesturePwd(String gesturePwd) {
		this.gesturePwd = gesturePwd == null ? null : gesturePwd.trim();
	}
	/**
	 * 手势密码
     * @return 获取gesturePwd属性值
     */
	public String getGesturePwd() {
		return this.gesturePwd;	
	}
	
	/**
	 * 状态,0:正常,1:冻结,2:锁定
	 * @param accountStatus 设置 accountStatus 属性值为参数值 accountStatus
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus == null ? null : accountStatus.trim();
	}
	/**
	 * 状态,0:正常,1:冻结,2:锁定
     * @return 获取accountStatus属性值
     */
	public String getAccountStatus() {
		return this.accountStatus;	
	}
	
	/**
	 * 头像地址
	 * @param imgUrl 设置 imgUrl 属性值为参数值 imgUrl
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl == null ? null : imgUrl.trim();
	}
	/**
	 * 头像地址
     * @return 获取imgUrl属性值
     */
	public String getImgUrl() {
		return this.imgUrl;	
	}
	
	/**
	 * 昵称
	 * @param nickname 设置 nickname 属性值为参数值 nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}
	/**
	 * 昵称
     * @return 获取nickname属性值
     */
	public String getNickname() {
		return this.nickname;	
	}
	
	/**
	 * 登录失败次数
	 * @param loginFailCnt 设置 loginFailCnt 属性值为参数值 loginFailCnt
	 */
	public void setLoginFailCnt(Integer loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}
	/**
	 * 登录失败次数
     * @return 获取loginFailCnt属性值
     */
	public Integer getLoginFailCnt() {
		return this.loginFailCnt;	
	}
	
	/**
	 * 登录失败时间
	 * @param loginFailTime 设置 loginFailTime 属性值为参数值 loginFailTime
	 */
	public void setLoginFailTime(java.sql.Timestamp loginFailTime) {
		this.loginFailTime = loginFailTime;
	}
	/**
	 * 登录失败时间
     * @return 获取loginFailTime属性值
     */
	public java.sql.Timestamp getLoginFailTime() {
		return this.loginFailTime;	
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
	
	/**
	 * 修改手势密码错误次数
	 * @param gestureFailCnt 设置 gestureFailCnt 属性值为参数值 gestureFailCnt
	 */
	public void setGestureFailCnt(Integer gestureFailCnt) {
		this.gestureFailCnt = gestureFailCnt;
	}
	/**
	 * 修改手势密码错误次数
     * @return 获取gestureFailCnt属性值
     */
	public Integer getGestureFailCnt() {
		return this.gestureFailCnt;	
	}
	
	/**
	 * 修改手势密码错误时间
	 * @param gestureFailTime 设置 gestureFailTime 属性值为参数值 gestureFailTime
	 */
	public void setGestureFailTime(java.sql.Timestamp gestureFailTime) {
		this.gestureFailTime = gestureFailTime;
	}
	/**
	 * 修改手势密码错误时间
     * @return 获取gestureFailTime属性值
     */
	public java.sql.Timestamp getGestureFailTime() {
		return this.gestureFailTime;	
	}
	
	/**
	 * 是否开启系统消息通知（0不开启，1开启）
	 * @param openSysMsg 设置 openSysMsg 属性值为参数值 openSysMsg
	 */
	public void setOpenSysMsg(String openSysMsg) {
		this.openSysMsg = openSysMsg == null ? null : openSysMsg.trim();
	}
	/**
	 * 是否开启系统消息通知（0不开启，1开启）
     * @return 获取openSysMsg属性值
     */
	public String getOpenSysMsg() {
		return this.openSysMsg;	
	}
	
	/**
	 * 开启系统信息通知时间
	 * @param openSysMsgTime 设置 openSysMsgTime 属性值为参数值 openSysMsgTime
	 */
	public void setOpenSysMsgTime(java.sql.Timestamp openSysMsgTime) {
		this.openSysMsgTime = openSysMsgTime;
	}
	/**
	 * 开启系统信息通知时间
     * @return 获取openSysMsgTime属性值
     */
	public java.sql.Timestamp getOpenSysMsgTime() {
		return this.openSysMsgTime;	
	}
	
	/**
	 * 是否开启电费账单消息提醒（0不开启，1开启）
	 * @param openEleBill 设置 openEleBill 属性值为参数值 openEleBill
	 */
	public void setOpenEleBill(String openEleBill) {
		this.openEleBill = openEleBill == null ? null : openEleBill.trim();
	}
	/**
	 * 是否开启电费账单消息提醒（0不开启，1开启）
     * @return 获取openEleBill属性值
     */
	public String getOpenEleBill() {
		return this.openEleBill;	
	}
	
	/**
	 * 来源
	 * @param source 设置 source 属性值为参数值 source
	 */
	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}
	/**
	 * 来源
     * @return 获取source属性值
     */
	public String getSource() {
		return this.source;	
	}
	
	/**
	 * 开启电费账单信息提醒时间
	 * @param openEleBillTime 设置 openEleBillTime 属性值为参数值 openEleBillTime
	 */
	public void setOpenEleBillTime(java.sql.Timestamp openEleBillTime) {
		this.openEleBillTime = openEleBillTime;
	}
	/**
	 * 开启电费账单信息提醒时间
     * @return 获取openEleBillTime属性值
     */
	public java.sql.Timestamp getOpenEleBillTime() {
		return this.openEleBillTime;	
	}
	
	/**
	 * 总积分
	 * @param totalEarnings 设置 totalEarnings 属性值为参数值 totalEarnings
	 */
	public void setTotalEarnings(Integer totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	/**
	 * 总积分
     * @return 获取totalEarnings属性值
     */
	public Integer getTotalEarnings() {
		return this.totalEarnings;	
	}
	
	/**
	 * 支付密码
	 * @param payPwd 设置 payPwd 属性值为参数值 payPwd
	 */
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd == null ? null : payPwd.trim();
	}
	/**
	 * 支付密码
     * @return 获取payPwd属性值
     */
	public String getPayPwd() {
		return this.payPwd;	
	}
	
	
}
	

	
