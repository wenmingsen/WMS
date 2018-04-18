package com.csg.intshop.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csg.intshop.entity.DrtShopItem;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtShopCartService;
import com.csg.intshop.service.core.EarningsCore;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;
import com.csg.personcenter.entity.mybatis.DrtAccount;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
       

/**
 * DrtShopCart 积分商城购物车表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Controller
@RequestMapping("/ins/drtShopCart")
public class DrtShopCartController{

	private Logger log = LoggerFactory.getLogger(DrtShopCartController.class);

	
	/** drtShopCartService 积分商城购物车表 */
	@Autowired
	private DrtShopCartService drtShopCartService;
	
	/**/
	@Autowired
	private EarningsCore earningsCore;
	
	/**
	 * 统计商品种类数量
	 * @return 值
	 */
	@RequestMapping("/countGoods")
	@ResponseBody
	public Map<String,Object> countGoods(HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		return drtShopCartService.countGoods(accountId);
	}

	/**
	 * 
	 * @param id 商品主键id
	 * @return 商品详情
	 */
	@RequestMapping("/addGoods")
	@ResponseBody
	public Map<String,Object> addGoods(String id,String itemNum,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		Map<String,Object> objMap = drtShopCartService.insert(id,itemNum,accountId);
		 return objMap;
	}
	
	
	/****
	 * 积分兑换
	 * @param totalMoney 花费积分
	 * @param accountId 帐号
	 * @return 兑换结果
	 */
	@RequestMapping(value = "/ensureExchange",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> ensureExchange(String totalMoney,String orderId,String orderNo,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		log.info("当前登陆人："+accountId);
		return drtShopCartService.ensureExchange(totalMoney, orderId,orderNo,accountId);
	}
	
	/**
	 * 获取当前登录人购物车商品
	 * 
	 * @return 当前登录人购物车商品
	 * */
	@RequestMapping("/queryShooCars")
	@ResponseBody
	public List<DrtShopItem> queryShooCars(HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		//String accountId=(String) request.getSession().getAttribute("accountId");
		return drtShopCartService.selectShopCars(accountId);
	}
	
	/**
	 * 删除购物车商品
	 * 
	 * @param ids 商品id集合
	 * */
	@RequestMapping(value="/delShop",method = RequestMethod.POST)
	@ResponseBody
	public void delShop(String[] ids,HttpServletRequest request){
		if(ids==null)
			return;
		if(ids.length==0)
			return;
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("ids", Arrays.asList(ids));
		params.put("accountId", accountId);
		drtShopCartService.delDrtShop(params);
	}
	
	/**
	 * 清空购物车商品
	 * */
	@RequestMapping("/clearShopCart")
	@ResponseBody
	public void clearShopCart(HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		drtShopCartService.clearShopCart(accountId);
	}
}
	

