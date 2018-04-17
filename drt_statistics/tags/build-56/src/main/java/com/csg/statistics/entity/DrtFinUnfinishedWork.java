package com.csg.statistics.entity;

import java.sql.Timestamp;


/**
 * 资产待办项
 * 
 * @author 李城
 * */
public class DrtFinUnfinishedWork {

	/**ID*/
	private  String tid;
	
	/**类型*/
	private  String type;
	
	/**类型说明*/
	private  String typeName;
	
	/**生成时间*/
	private  Timestamp creatTime;
	
	/**执行时间（当前时间大于执行时间，可以执行）*/
	private  Timestamp executeTime;
	
	/**是否有效(1是生效；0是无效;)*/
	private  String isValid;
	
	/**参数内容*/
	private  String parameter;

	/**
	 * @return tid
	 */
	public String getTid() {
		return tid;
	}

	/**
	 * @param tid 要设置的 tid
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type 要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName 要设置的 typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return creatTime
	 */
	public Timestamp getCreatTime() {
		return creatTime;
	}

	/**
	 * @param creatTime 要设置的 creatTime
	 */
	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	/**
	 * @return executeTime
	 */
	public Timestamp getExecuteTime() {
		return executeTime;
	}

	/**
	 * @param executeTime 要设置的 executeTime
	 */
	public void setExecuteTime(Timestamp executeTime) {
		this.executeTime = executeTime;
	}

	/**
	 * @return isValid
	 */
	public String getIsValid() {
		return isValid;
	}

	/**
	 * @param isValid 要设置的 isValid
	 */
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return parameter
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * @param parameter 要设置的 parameter
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	
}
