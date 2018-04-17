package com.csg.statistics.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Description:电费扣费帐单表
 * Company: Syni
 * @author 李达才
 * @since 2017-12-1
 *
 */
public class DrtEleFeepay {

	private String tid;
	
	/** 电费扣费帐单号(交易流水号) */
	private String feepayNum;
	
	/** 扣费类型（1、预付，2、预购）*/
	private String feepayLx;
	
	/** 帐单生成时间 */
	private Timestamp creatTime;
	
	/** 地区编号 */
	private String orgId;
	
	/** 供电所编号 */
	private String gdsCode;
	
	/** 供电单位编码 */
	private String gdbm;
	
	/** 应扣金额(以分为单位) */
	private Integer ykje;
	
	/** 用户编号 */
	private String ydhh;
	
	/** 结算户号 */
	private String jshh;
	
	/** 结算户号名称 */
	private String jshmc;
	
	/** 电融通账号 */
	private String drtAccountId;
	
	/** 电费年月 */
	private String dfny;
	
	/** 本金(以分为单位) */
	private Integer bj;
	
	/** 违约金(以分为单位) */
	private Integer wyj;
	
	/** 是否开通自动划扣 */
	private String isAutoPay;
	
	/** 扣款银行代码 */
	private String kkyhdm;
	
	/** 账务流水号 */
	private String zwlsh;
	
	/** 划扣类型(1,批扣;2,单笔) */
	private String payType;
	
	/** 扣费结果(0成功,-1失败) */
	private String feepayResult;
	
	/** 备注 */
	private String bz;
	
	/** 电融通收费流水号 */
	private String drtsflsh;
	
	/** 扣款结果代码 */
	private String kkjgdm;
	
	/** 实收流水号 */
	private String sslsh;
	
	/** 实扣金额(以分为单位) */
	private Integer skje;
	
	/** 扣款日期 */
	private String kkrq;
	
	/** 扣款时间 */
	private String kksj;
	
	/** 单笔划扣接收状态，成功返回'0'，错误返回-1 */
	private String dbdkjszt;
	
	/** 单笔划扣异常信息，成功返回空，错误返回系统处理异常信息 */
	private String dbdkycxx;
	
	/** 当前余额(以分为单位) */
	private Integer dqye;
	
	/** 同步结果，成功返回'0'，错误返回-1 */
	private String jszt;
	
	/** 同步异常信息，成功返回空，错误返回系统处理异常信息 */
	private String ycxx;
	
	/** 同步处理时间 */
	private Timestamp clsj;
	
	/** 开始时间 */
	private Date startTime;
	
	/** 结束时间 */
	private Date endTime;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getFeepayNum() {
		return feepayNum;
	}

	public void setFeepayNum(String feepayNum) {
		this.feepayNum = feepayNum;
	}

	public String getFeepayLx() {
		return feepayLx;
	}

	public void setFeepayLx(String feepayLx) {
		this.feepayLx = feepayLx;
	}

	public Timestamp getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getGdsCode() {
		return gdsCode;
	}

	public void setGdsCode(String gdsCode) {
		this.gdsCode = gdsCode;
	}

	public String getGdbm() {
		return gdbm;
	}

	public void setGdbm(String gdbm) {
		this.gdbm = gdbm;
	}

	public Integer getYkje() {
		return ykje;
	}

	public void setYkje(Integer ykje) {
		this.ykje = ykje;
	}

	public String getYdhh() {
		return ydhh;
	}

	public void setYdhh(String ydhh) {
		this.ydhh = ydhh;
	}

	public String getJshh() {
		return jshh;
	}

	public void setJshh(String jshh) {
		this.jshh = jshh;
	}

	public String getJshmc() {
		return jshmc;
	}

	public void setJshmc(String jshmc) {
		this.jshmc = jshmc;
	}

	public String getDrtAccountId() {
		return drtAccountId;
	}

	public void setDrtAccountId(String drtAccountId) {
		this.drtAccountId = drtAccountId;
	}

	public String getDfny() {
		return dfny;
	}

	public void setDfny(String dfny) {
		this.dfny = dfny;
	}

	public Integer getBj() {
		return bj;
	}

	public void setBj(Integer bj) {
		this.bj = bj;
	}

	public Integer getWyj() {
		return wyj;
	}

	public void setWyj(Integer wyj) {
		this.wyj = wyj;
	}

	public String getIsAutoPay() {
		return isAutoPay;
	}

	public void setIsAutoPay(String isAutoPay) {
		this.isAutoPay = isAutoPay;
	}

	public String getKkyhdm() {
		return kkyhdm;
	}

	public void setKkyhdm(String kkyhdm) {
		this.kkyhdm = kkyhdm;
	}

	public String getZwlsh() {
		return zwlsh;
	}

	public void setZwlsh(String zwlsh) {
		this.zwlsh = zwlsh;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getFeepayResult() {
		return feepayResult;
	}

	public void setFeepayResult(String feepayResult) {
		this.feepayResult = feepayResult;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getDrtsflsh() {
		return drtsflsh;
	}

	public void setDrtsflsh(String drtsflsh) {
		this.drtsflsh = drtsflsh;
	}

	public String getKkjgdm() {
		return kkjgdm;
	}

	public void setKkjgdm(String kkjgdm) {
		this.kkjgdm = kkjgdm;
	}

	public String getSslsh() {
		return sslsh;
	}

	public void setSslsh(String sslsh) {
		this.sslsh = sslsh;
	}

	public Integer getSkje() {
		return skje;
	}

	public void setSkje(Integer skje) {
		this.skje = skje;
	}

	public String getKkrq() {
		return kkrq;
	}

	public void setKkrq(String kkrq) {
		this.kkrq = kkrq;
	}

	public String getKksj() {
		return kksj;
	}

	public void setKksj(String kksj) {
		this.kksj = kksj;
	}

	public String getDbdkjszt() {
		return dbdkjszt;
	}

	public void setDbdkjszt(String dbdkjszt) {
		this.dbdkjszt = dbdkjszt;
	}

	public String getDbdkycxx() {
		return dbdkycxx;
	}

	public void setDbdkycxx(String dbdkycxx) {
		this.dbdkycxx = dbdkycxx;
	}

	public Integer getDqye() {
		return dqye;
	}

	public void setDqye(Integer dqye) {
		this.dqye = dqye;
	}

	public String getJszt() {
		return jszt;
	}

	public void setJszt(String jszt) {
		this.jszt = jszt;
	}

	public String getYcxx() {
		return ycxx;
	}

	public void setYcxx(String ycxx) {
		this.ycxx = ycxx;
	}

	public Timestamp getClsj() {
		return clsj;
	}

	public void setClsj(Timestamp clsj) {
		this.clsj = clsj;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
