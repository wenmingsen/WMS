package com.csg.intshop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.entity.DrtShopAddress;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtShopAddressService;
import com.csg.personcenter.entity.mybatis.DrtAccount;


/**
 * DrtShopAddress 积分商城地址表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@RestController
@RequestMapping("/ins/drtShopAddress")
public class DrtShopAddressController{

	/** drtShopAddressService 积分商城地址表 */
	@Autowired
	private DrtShopAddressService drtShopAddressService;

	/**
	 * 保存
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	@RequestMapping("/addAddress")
	@ResponseBody
	public Map<String,Object> addAddress(DrtShopAddress drtShopAddress,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		drtShopAddress.setAccountId(accountId);
		Map<String,Object> objMap = drtShopAddressService.insert(drtShopAddress);
		return objMap;
	}
	
	
	/**
	 * 修改
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	@RequestMapping("/updateAddress")
	@ResponseBody
	public Map<String,Object> updateAddress(DrtShopAddress drtShopAddress,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		drtShopAddress.setAccountId(accountId);
		Map<String,Object> objMap = drtShopAddressService.update(drtShopAddress);
		return objMap;
	}
	
	/**
	 * 查找
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	@RequestMapping("/queryAddressList")
	@ResponseBody
	public Map<String,Object> queryAddressList(DrtShopAddress drtShopAddress,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		drtShopAddress.setAccountId(drtAccount.getAccountId());
		Map<String,Object> objMap = drtShopAddressService.selectList(drtShopAddress);
		return objMap;
	}
	
	/**
	 * 删除地址
	 * @param drtShopAddress 选择地址id
	 * @return 删除结果
	 */
	@RequestMapping("/deleteAddress")
	@ResponseBody
	public Map<String,Object> deleteAddress(DrtShopAddress drtShopAddress){
		return drtShopAddressService.deleteAddress(drtShopAddress);
	}
}
	

