package com.csg.intshop.service.core;


import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 积分微服务调用core
 * 
 * @author xuchen
 * 
 */
@Service
@FeignClient(name = "${elec.api.id}")
public interface EarningsCore {
	/**
	 * 积分查询方法
	 * @param accountId 账户ID
	 * @return strEarnings 积分
	 * @author xuchen
	 * @throws Exception
	 */
	@RequestMapping(value = "${elec.ctx}/elec/api/earnings/queryEarnings")
	public int queryEarnings(@RequestParam(value = "accountId")String accountId);
	
	/**
	 * 商城兑换消耗积分更新方法
	 * @param accountId 账户ID
	 * @param earnings 积分
	 * @param plusminus 正负值 1-正 0-负
	 * @author xuchen
	 * @return resultMap code标识码 msg信息
	 * @throws Exception
	 */
	@RequestMapping(value = "${elec.ctx}/elec/api/earnings/updateEarningsFromExchange")
	public Map<String, Object> updateEarningsFromExchange(@RequestParam(value="accountId") String accountId, @RequestParam(value="earnings") Integer earnings, @RequestParam(value="plusminus") String plusminus, @RequestParam(value="orderNo") String orderNo);
}
