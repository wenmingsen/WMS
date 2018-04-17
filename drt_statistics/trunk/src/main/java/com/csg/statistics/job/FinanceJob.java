package com.csg.statistics.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csg.statistics.entity.DrtFinUnfinishedWork;
import com.csg.statistics.javacommon.SystemConstants;
import com.csg.statistics.service.DrtFinUnfinishedWorkService;
import com.csg.statistics.util.DateTimeUtils;

@Component
public class FinanceJob {

	/**业务用户统计service*/
	@Autowired
	private  DrtFinUnfinishedWorkService drtFinUnfinishedWorkService;
	

	/**
	 * 资产代办业务（01-交易）
	 * 
	 * */
	public void finUnfinishedWorkJob(){
		DrtFinUnfinishedWork drtFinUnfinishedWork=new DrtFinUnfinishedWork();
		drtFinUnfinishedWork.setExecuteTime(DateTimeUtils.getCurrentTime());
		drtFinUnfinishedWork.setIsValid(SystemConstants.ACCOUNT_OPERATE_RECORD_TYPE_LOGOUT);
		drtFinUnfinishedWork.setType(SystemConstants.FINANCE_TYPE_HK);
		drtFinUnfinishedWorkService.unfinishedWork(drtFinUnfinishedWork);
	}
}
