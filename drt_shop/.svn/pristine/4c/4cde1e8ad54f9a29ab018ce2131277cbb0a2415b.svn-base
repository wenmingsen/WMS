package com.csg.intshop.service.core;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csg.intshop.entity.DrtAdministrativeRegion;
import com.csg.intshop.entity.DrtShopAddress;


/**
 * 地址微服务调用core
 * 
 * @author 刘磊
 * 
 */
@Service
@FeignClient(name = "${finance.api.id}")
public interface AddressCore {

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtAdministrativeRegion 
	 * @return  记录集
	 */
	@RequestMapping(value = "${finance.ctx}/finance/api/queryPCT")
	Map<String,Object> selectList(@RequestBody DrtShopAddress drtShopAddress);
	
	
	/**
	 * 保存
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	@RequestMapping(value = "${finance.ctx}/finance/api/addAddress")
	Map<String,Object> insert(@RequestBody DrtShopAddress drtShopAddress);
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	@RequestMapping(value = "${finance.ctx}/finance/api/updateAddress")
	Map<String,Object> update(@RequestBody DrtShopAddress drtShopAddress);
	

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopAddress 积分商城地址表
	 * @return 积分商城地址表 记录集
	 */
	@RequestMapping(value = "${finance.ctx}/finance/api/queryAddressList")
	Map<String,Object> selectAddressList(@RequestBody DrtShopAddress drtShopAddress);

	/**
	 * 删除地址
	 * @param drtShopAddress 选择地址id
	 * @return 删除结果
	 */
	@RequestMapping(value = "${finance.ctx}/finance/api/deleteAddress")
	Map<String,Object> deleteAddress(@RequestBody DrtShopAddress drtShopAddress);
}
