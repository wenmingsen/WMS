package com.csg.statistics.service.finance;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(name = "${finance.api.id}")
public interface FinanceService {

	//获取资产代办项
	@RequestMapping("${finance.ctx}/finance/api/unfinishedWork")
	public void unfinishedWork(@RequestBody String date);
}
