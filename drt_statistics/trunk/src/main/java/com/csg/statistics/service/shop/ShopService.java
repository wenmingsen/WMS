package com.csg.statistics.service.shop;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(name = "${shop.api.id}")
public interface ShopService {
	
	@RequestMapping("${shop.ctx}/ins/api/interface/getMallItem")
	public Map<String, Object> getMallItem();
	
	@RequestMapping("${shop.ctx}/ins/api/testFinanceShop")
	public String test();

}
