package com.csg.statistics.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.service.elec.ElecService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 调电费查询总欠费service
 * @author xian long 2018 03 28
 *
 */
@Service
public class DrtElecDealGetAllQFService {
	
	private Logger log = LoggerFactory.getLogger(DrtElecDealGetAllQFService.class);
	@Autowired
	private ElecService elecService;

	/**
	 * 调用电费查询总欠费并生成欠费账单接口
	 * @param accountId
	 * @param elecUserId
	 */
	@HystrixCommand(fallbackMethod="dealGetAllQFFailCallBack")
	public String dealGetAllQF(String accountId,String elecUserId){
		return elecService.findAllQF(accountId, elecUserId);
	}
	
	public String dealGetAllQFFailCallBack(String accountId,String elecUserId){
		log.info("dealGetAllQF跨服务调用--调用查询总欠费并生成欠费账单接口失败--accountId"+accountId+" elecUserId:"+elecUserId);
		return "";
	}
}
