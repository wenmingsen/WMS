package com.csg.intshop.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtShopAddress;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.core.AddressCore;
import com.csg.intshop.util.GetUUID;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;

/**
 * DrtShopAddressService 积分商城地址表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Transactional
@Service
public class DrtShopAddressService{
	
	
	private Logger log = LoggerFactory.getLogger(DrtShopAddressService.class);

	
	/**finance地址接口*/
	@Autowired
	private AddressCore addressCore;

	/**
	 * 保存
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	public Map<String,Object> insert(DrtShopAddress drtShopAddress){
		Map<String, Object> objMap= null;
		try{
			if(StringUtils.isEmpty(drtShopAddress)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "新增地址不能为空", "");				
	            return objMap;
			}
			Map<String,Object> obj = addressCore.insert(drtShopAddress);
			if(obj.get("state").equals(SystemConstants.RESULT_CODE_SUCCESS)){				
	            objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "添加成功", "");
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加失败", "");
			}
		}catch(Exception e){			
			log.error("DrtShopAddressService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加失败", "");
			return objMap;
		}
		return objMap;
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopAddress 积分商城地址表
	 * @throws Exception 出错抛出异常
	 */
	public Map<String, Object> update(DrtShopAddress drtShopAddress) {
		Map<String, Object> objMap= null;
		try{
			if(StringUtils.isEmpty(drtShopAddress)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "地址id不能为空", "");				
	            return objMap;
			}
			Map<String,Object> obj = addressCore.update(drtShopAddress);
			if(obj.get("state").equals(SystemConstants.RESULT_CODE_SUCCESS)){				
	            objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "修改成功", "");
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "修改失败", "");
			}
		}catch(Exception e){			
			log.error("DrtShopAddressService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "修改失败", "");
			return objMap;
		}
		return objMap;
	}
	


	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopAddress 积分商城地址表
	 * @throws Exception 出错抛出异常
	 */
	public Map<String,Object> selectList(DrtShopAddress drtShopAddress){
		Map<String,Object> objMap = null;
		try{
			if(StringUtils.isEmpty(drtShopAddress)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "地址对象不能为空", "");				
	            return objMap;
			}
			Map<String,Object> objList = addressCore.selectAddressList(drtShopAddress);
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "", objList.get("data"));
		}catch(Exception e){			
			log.error("DrtShopAddressService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "查询失败", "");
			return objMap;
		}
		return objMap;
		
	}

	/**
	 * 删除地址
	 * @param drtShopAddress 选择地址id
	 * @return 删除结果
	 */
	public Map<String, Object> deleteAddress(DrtShopAddress drtShopAddress) {
		Map<String,Object> objMap = null;
		try{
			if(StringUtils.isEmpty(drtShopAddress)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "地址id不能为空", "");				
	            return objMap;
			}
			addressCore.deleteAddress(drtShopAddress);
		}catch(Exception e){			
			log.error("DrtShopAddressService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "删除失败", "");
			return objMap;
		}
		return objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "删除成功", "");
	}

}