package com.csg.intshop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtShopOrderDetailService;
import com.csg.personcenter.entity.mybatis.DrtAccount;


/**
 * DrtShopOrderDetail 积分商城订单子表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Controller
@RequestMapping("/ins/drtShopOrderDetail")
public class DrtShopOrderDetailController{

	/** drtShopOrderDetailService 积分商城订单子表 */
	@Autowired
	private DrtShopOrderDetailService drtShopOrderDetailService;
	
	/**
	 * 创建主订单和子订单
	 * @param id 商品id
	 * @param orderNum 订单号
	 * @param request 获取session
	 * @return 创建结果
	 */
	@RequestMapping("/insertOrder")
	@ResponseBody
	public Map<String,Object> insertOrder(String id,String orderNum,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		Map<String,Object> objMap = drtShopOrderDetailService.insertOrder(id, orderNum, accountId);
		return objMap;
				
	}
	
}
	

