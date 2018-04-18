package com.csg.intshop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csg.intshop.service.DrtShopOrderDetailService;


/**
 * DrtShopOrderDetail 积分商城订单子表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Controller
@RequestMapping("/opr/intshop")
public class DrtShopOrderDetailController{

	/** drtShopOrderDetailService 积分商城订单子表 */
	@Autowired
	private DrtShopOrderDetailService drtShopOrderDetailService;
	
	
	
}
	

