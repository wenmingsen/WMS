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
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.entity.DrtShopItem;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtShopCartService;
import com.csg.intshop.service.core.EarningsCore;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;
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
	 * 
	 * @param id 商品主键id
	 * @return 商品详情
	 */
	@RequestMapping("/addGoods")
	@ResponseBody
	@HystrixCommand(fallbackMethod="addGoodsFallback")
	public Map<String,Object> addGoods(String id){
		 Map<String,Object> objMap = drtShopCartService.insert(id);
		 return objMap;
	}
	
	public Map<String,Object> addGoodsFallback(String id){
		Map<String,Object>  result=new HashMap<String,Object>();
		return result;
	}
	
	
	/****
	 * 积分兑换
	 * @param totalMoney
	 * @param accountId
	 * @return
	 */
	@RequestMapping("/ensureExchange")
	@ResponseBody
	public Map<String,Object> ensureExchange(String totalMoney,HttpServletRequest request){
		String accountId = (String) request.getSession().getAttribute("accountId");
		Map<String, Object> objMap= new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(accountId) || StringUtils.isEmpty(totalMoney)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "无法进行兑换", "");				
				return objMap;
			}
			accountId = "ff8080815f8127a4015f8f09d3ab0000";
			int totalNum = earningsCore.queryEarnings(accountId);
			if(totalNum>=Integer.parseInt(totalMoney)*100){
				objMap = earningsCore.updateEarningsFromExchange(accountId, Integer.parseInt(totalMoney)*100, "0");
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "不好意思，你的积分不够兑换，积分余额："+totalNum/100, "");				
			}
		}catch(Exception e){			
			log.error("DrtOprNotice error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "兑换失败", "");
			return objMap;
		}
		 return objMap;
	}
	
	
	/**
	 * 获取当前登录人购物车商品
	 * 
	 * @return 当前登录人购物车商品
	 * */
	@RequestMapping("/queryShooCars")
	@ResponseBody
	public List<DrtShopItem> queryShooCars(HttpServletRequest request){
		String accoundId=(String) request.getSession().getAttribute("accoundId");
		return drtShopCartService.selectShopCars(accoundId);
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
		String accountId=(String) request.getSession().getAttribute("accountId");
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("ids", Arrays.asList(ids));
		params.put("accountId", accountId);
		drtShopCartService.delDrtShop(params);
	}
}
	

