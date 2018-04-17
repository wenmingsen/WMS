package com.csg.statistics.service;


import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.service.finance.FinanceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


/**
 * 调资产汇总抵扣service
 * @author xian
 *
 */
@Service
public class DrtFinDealHZDKService {

	private Logger log = LoggerFactory.getLogger(DrtFinDealHZDKService.class);
	
	@Autowired
	private  FinanceService financeService;
	
	/**
	 * 调用资产汇总抵扣接口
	 * @param accountId
	 * @param elecUserId
	 * @param rechargeNum
	 * @param tId
	 * @param phone
	 * @param qfMoney
	 */
	@HystrixCommand(fallbackMethod="dealFinHZDKFailCallBack")
	public void dealFinHZDK(String accountId,String elecUserId,
			String rechargeNum,String tId,String phone,String qfMoney){
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("accountId", accountId);
		jsonObject.put("elecUserId", elecUserId);
		jsonObject.put("rechargeNum", rechargeNum);
		jsonObject.put("tId", tId);
		jsonObject.put("phone", phone);
		jsonObject.put("qfMoney", qfMoney);
		log.info("调资产汇总抵扣参数jsonObject："+jsonObject.toString());
		financeService.dealFinanceHZDK(jsonObject.toString());
	}
	public void dealFinHZDKFailCallBack(String accountId,String elecUserId,
			String rechargeNum,String tId,String phone,String qfMoney){
		log.info("dealFinHZDK跨服务调用--调用资产汇总抵扣接口失败");
	}
}
