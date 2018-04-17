/**
 * 
 */
package com.csg.statistics.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.statistics.service.DrtElecDealPkwjService;

/**
 * @author Administrator
 *
 */
@Component
public class DrtElecDealPkwjJob {

	@Autowired
	private  DrtElecDealPkwjService drtElecDealPkwjService;
	
	
	/**
	 * 定时调用处理批扣文件通知
	 * */
	public void dealPkwj(){
		drtElecDealPkwjService.dealPktz();
	}
}
