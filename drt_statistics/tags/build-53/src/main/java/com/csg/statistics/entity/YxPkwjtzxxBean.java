
package com.csg.statistics.entity;

/**
 * 生成批扣文件通知信息
 * 
 * @author 李城
 *
 */
public class YxPkwjtzxxBean {

	/**id*/
	private String id;
	
	/**代收单位编号*/
	private String DSDWBH;
	
	/**操作员编号*/
	private String CZYBM;
	
	/**文件名*/
	private String DKWJMC;
	
	/**文件路径*/
	private  String WJLJ;
	
	/**交易笔数*/
	private String JYBS;
	
	/**交易金额*/
	private String JYJE;
	
	/**对账类别*/
	private String DZLB;
	
	/**交易时间*/
	private String JYRQ;
	
	/**收费日期*/
	private String SFRQ;
	
	/**调用地区**/
	private String invokeArea;

	/**读取次数*/
	private int  DQCS;
	
	/**
	 * @return dSDWBH
	 */
	public String getDSDWBH() {
		return DSDWBH;
	}

	/**
	 * @param dSDWBH 要设置的 dSDWBH
	 */
	public void setDSDWBH(String dSDWBH) {
		DSDWBH = dSDWBH;
	}

	/**
	 * @return cZYBM
	 */
	public String getCZYBM() {
		return CZYBM;
	}

	/**
	 * @param cZYBM 要设置的 cZYBM
	 */
	public void setCZYBM(String cZYBM) {
		CZYBM = cZYBM;
	}

	/**
	 * @return dZWJMC
	 */
	public String getDKWJMC() {
		return DKWJMC;
	}



	/**
	 * @return jYBS
	 */
	public String getJYBS() {
		return JYBS;
	}

	/**
	 * @param jYBS 要设置的 jYBS
	 */
	public void setJYBS(String jYBS) {
		JYBS = jYBS;
	}

	/**
	 * @return jYJE
	 */
	public String getJYJE() {
		return JYJE;
	}

	/**
	 * @param jYJE 要设置的 jYJE
	 */
	public void setJYJE(String jYJE) {
		JYJE = jYJE;
	}

	/**
	 * @return dZLB
	 */
	public String getDZLB() {
		return DZLB;
	}

	/**
	 * @param dZLB 要设置的 dZLB
	 */
	public void setDZLB(String dZLB) {
		DZLB = dZLB;
	}

	/**
	 * @return jYRQ
	 */
	public String getJYRQ() {
		return JYRQ;
	}

	/**
	 * @param jYRQ 要设置的 jYRQ
	 */
	public void setJYRQ(String jYRQ) {
		JYRQ = jYRQ;
	}

	/**
	 * @return sFRQ
	 */
	public String getSFRQ() {
		return SFRQ;
	}

	/**
	 * @param sFRQ 要设置的 sFRQ
	 */
	public void setSFRQ(String sFRQ) {
		SFRQ = sFRQ;
	}

	/**
	 * @return wJLJ
	 */
	public String getWJLJ() {
		return WJLJ;
	}

	/**
	 * @param wJLJ 要设置的 wJLJ
	 */
	public void setWJLJ(String wJLJ) {
		WJLJ = wJLJ;
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

	public void setDKWJMC(String dKWJMC) {
		DKWJMC = dKWJMC;
	}

	public String getInvokeArea() {
		return invokeArea;
	}

	public void setInvokeArea(String invokeArea) {
		this.invokeArea = invokeArea;
	}

	
	
	/**
	 * @return dQCS
	 */
	public int getDQCS() {
		return DQCS;
	}

	/**
	 * @param dQCS 要设置的 dQCS
	 */
	public void setDQCS(int dQCS) {
		DQCS = dQCS;
	}

	/* （非 Javadoc）
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "YxPkwjtzxxBean [id=" + id + ", DSDWBH=" + DSDWBH + ", CZYBM="
				+ CZYBM + ", DKWJMC=" + DKWJMC + ", WJLJ=" + WJLJ + ", JYBS="
				+ JYBS + ", JYJE=" + JYJE + ", DZLB=" + DZLB + ", JYRQ=" + JYRQ
				+ ", SFRQ=" + SFRQ + ", invokeArea=" + invokeArea + ", DQCS="
				+ DQCS + "]";
	}

	

	
	
}
