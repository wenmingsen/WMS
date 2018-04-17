package com.csg.statistics.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csg.statistics.entity.DrtFinPoAccount;
import com.csg.statistics.javacommon.SystemConstants;
import com.csg.statistics.service.DrtFinPoAccountService;
import com.csg.statistics.service.DrtFinPoEarningsComputingRecordService;

/**
 * 积分操作Controller
 * 
 * @author  曾令鹏
 * @since   1.8
 * @version 2017年12月11日 曾令鹏
 */
@RestController
@RequestMapping("/statistics/points/*")
public class PointsController {
	
	/**日志*/
	private Logger log = LoggerFactory.getLogger(PointsController.class);
	
	/** 理财账户信息表（预购） */
	@Autowired
	private DrtFinPoAccountService drtFinPoAccountService;
	
	/** 预购账户积分收益计算明细表 */
	@Autowired
	private DrtFinPoEarningsComputingRecordService drtFinPoEarningsComputingRecordService;
	
	
	/**
	 * 更新积分收益余额
	 * 
	 * @param poAccountId 预购账户ID
	 * @param money 金额 单位：分
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("moneyConsume")
	public Map<String, Object> moneyConsume(String poAccountId, @RequestParam(defaultValue="0")Long money) throws Exception{
		if(StringUtils.isBlank(poAccountId)){
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("state", SystemConstants.RESULT_CODE_FAILED);
			resultMap.put("message", "预购账户ID不能为空！");
			return resultMap;
		}
		if(money == null || money.longValue() == 0){
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("state", SystemConstants.RESULT_CODE_FAILED);
			resultMap.put("message", "金额必须大于0！");
			return resultMap;
		}
		DrtFinPoAccount drtFinPoAccount = drtFinPoAccountService.selectByPrimaryKey(poAccountId);
		if(drtFinPoAccount == null){
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("state", SystemConstants.RESULT_CODE_FAILED);
			resultMap.put("message", "预购账户不存在！");
			return resultMap;
		}
		if(drtFinPoAccount.getPoTotalMoney() == null || drtFinPoAccount.getPoTotalMoney().longValue() < money){
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("state", SystemConstants.RESULT_CODE_FAILED);
			resultMap.put("message", "预购账户余额不足！");
			return resultMap;
		}
		try {
			int r = drtFinPoEarningsComputingRecordService.moneyConsume(poAccountId, money);
			if(r == 1){
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("state", SystemConstants.RESULT_CODE_SUCCESS);
				resultMap.put("message", "执行成功！");
				return resultMap;
			}
		} catch (Exception e) {
			log.error("预购账户ID：" + poAccountId + "更新积分收益余额失败！更新金额为：" + money);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("state", SystemConstants.RESULT_CODE_FAILED);
			resultMap.put("message", "执行错误！");
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("state", SystemConstants.RESULT_CODE_FAILED);
		resultMap.put("message", "执行失败！");
		return resultMap;
	}
	
}
