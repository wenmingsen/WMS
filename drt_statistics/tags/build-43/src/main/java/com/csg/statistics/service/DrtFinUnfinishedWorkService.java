/**
 * 
 */
package com.csg.statistics.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.entity.DrtFinUnfinishedWork;
import com.csg.statistics.mapper.DrtFinUnfinishedWorkMapper;
import com.csg.statistics.service.finance.FinanceService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 资产待办项Service
 * 
 * @author 李城
 *
 */
@Service
public class DrtFinUnfinishedWorkService{

	private Logger log = LoggerFactory.getLogger(DrtFinUnfinishedWorkService.class);
	
	/**资产待办项Mapper*/
	@Autowired
	private  DrtFinUnfinishedWorkMapper drtFinUnfinishedWorkMapper;
	
	@Autowired
	private  FinanceService financeService;
	
	//查询电融通用户信息
	/**
	 * 获取可执行的，有效地资产待办项
	 * 
	 * @param drtFinUnfinishedWork 参数对象
	 * @return 可执行的，有效地资产待办项集合
	 * */
	public  List<DrtFinUnfinishedWork> queryDrtFinUnfinishedWorks(DrtFinUnfinishedWork drtFinUnfinishedWork){
		return drtFinUnfinishedWorkMapper.queryDrtFinUnfinishedWorks(drtFinUnfinishedWork);
	}
	
	/**
	 * 处理可执行的，有效地资产待办项
	 * 
	 * @param drtFinUnfinishedWork 可执行的，有效地资产待办项参数对象
	 * @param type 处理类型
	 * */
	@HystrixCommand(fallbackMethod="unfinishedWorkFailCallBack")
	public void  unfinishedWork(DrtFinUnfinishedWork drtFinUnfinishedWork){
		if("01".equals(drtFinUnfinishedWork.getType())){
			List<DrtFinUnfinishedWork> drtFinUnfinishedWorks=queryDrtFinUnfinishedWorks(drtFinUnfinishedWork);
			if(!drtFinUnfinishedWorks.isEmpty()){
				for(DrtFinUnfinishedWork reuslt:drtFinUnfinishedWorks){
					financeService.unfinishedWork(reuslt.getParameter());	
				}
			}else{
				log.info("unfinishedWork跨服务调用--没有可执行的处理");
			}
		}
	}
	
	public void unfinishedWorkFailCallBack(String accountId){
		log.info("unfinishedWork跨服务调用--处理可执行的，有效地资产待办项失败");
	}

}
