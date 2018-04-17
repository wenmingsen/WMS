/**
 * 
 */
package com.csg.statistics.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csg.statistics.entity.YxPkwjtzxxBean;
import com.csg.statistics.mapper.DrtElecDealPkwjMapper;
import com.csg.statistics.service.elec.ElecService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author 李城
 * 
 * 获取批扣文件信息并处理
 *
 */
@Service
public class DrtElecDealPkwjService {

	private Logger log = LoggerFactory.getLogger(DrtFinUnfinishedWorkService.class);
	
	/**资产待办项Mapper*/
	@Autowired
	private DrtElecDealPkwjMapper drtElecDealPkwjMapper;
	
	@Autowired
	private ElecService elecService;
	/** 
	 * 查询读取次数不超过3次的营销批扣文件通知信息
	 * 
	 * @return  读取次数不超过3次的营销批扣文件通知信息集合
	 * */
	public List<YxPkwjtzxxBean> queryYxPkwjtzxx(){
		return drtElecDealPkwjMapper.queryYxPkwjtzxx();
	}
	
	/**
	 * 处理可执行的，有效地资产待办项
	 * 
	 * @param drtFinUnfinishedWork 可执行的，有效地资产待办项参数对象
	 * @param type 处理类型
	 * */
	@HystrixCommand(fallbackMethod="dealPktzFailCallBack")
	public void  dealPktz(){
		elecService.dealPktz(queryYxPkwjtzxx());	
	}
	
	public void dealPktzFailCallBack(){
		log.info("dealPktz跨服务调用--处理批扣文件通知信息失败");
	}
}
