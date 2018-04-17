package com.csg.statistics.service.elec;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csg.statistics.entity.YxPkwjtzxxBean;

@Service
@FeignClient(name = "${elec.api.id}")
public interface ElecService {

	@RequestMapping("${elec.ctx}/elec/yx/dealPktz")
	public void dealPktz(List<YxPkwjtzxxBean> list);
}
