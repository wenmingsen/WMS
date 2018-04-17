package com.csg.statistics.service.elec;

import com.csg.statistics.entity.YxPkwjtzxxBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Service
@FeignClient(name = "${elec.api.id}")
public interface ElecService {
	
	@RequestMapping("${elec.ctx}/elec/api/testStatisticsElec")
	public String testStatisticsElec();

	/*@RequestMapping(value="${elec.ctx}/elec/yx/dealPktz",method = RequestMethod.POST)
	public void dealPktz(@RequestBody List<YxPkwjtzxxBean> list);*/
	@RequestMapping(value="${elec.ctx}/elec/yx/dealPktz",method = RequestMethod.POST)
	public void dealPktz(@RequestBody YxPkwjtzxxBean yxPkwjtzxxBean);
	
	/**
	 * 调电费查询总欠费并生成欠费账单接口
	 * @param accountId
	 * @param elecUserId
	 * @return
	 */
	@RequestMapping("${elec.ctx}/elec/api/findAllQF")
	public String findAllQF(@RequestParam("accountId") String accountId,
			@RequestParam("elecUserId")String elecUserId);
	
	@RequestMapping("${elec.ctx}/elec/api/deal")
	public String deal();
	
}
