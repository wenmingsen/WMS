package com.csg.statistics.service.finance;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "${finance.api.id}")
public interface FinanceService {

	//获取资产代办项
	@RequestMapping("${finance.ctx}/finance/api/unfinishedWork")
	public void unfinishedWork(@RequestBody String date);
	
	/**调用资产汇总抵扣接口**/
	@RequestMapping("${finance.ctx}/finance/api/YxHZDKapi")
	public String dealFinanceHZDK(@RequestBody String str);
	
	/**调用资产汇总抵扣接口**/
	@RequestMapping("${finance.ctx}/finance/api/clHZDKapi")
	public String disposeHZDKResult(@RequestParam("accountId")String accountId,
			@RequestParam("elecUserId")String elecUserId,
			@RequestParam("result")String result);
}
