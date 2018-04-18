package com.csg.intshop.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtShopAddress;
import com.csg.intshop.entity.DrtShopCart;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.mapper.DrtShopAddressMapper;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;

/**
 * DrtShopAddressService 积分商城地址表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class DrtShopAddressService{
	
	
	private Logger log = LoggerFactory.getLogger(DrtShopAddressService.class);

	
	/**积分商城地址表drtShopAddressMapper接口*/
	@Autowired
	private DrtShopAddressMapper drtShopAddressMapper;

	/**
	 * 保存
	 * 
	 * @param drtShopAddress 积分商城地址表
	 */
	public Map<String,Object> insert(DrtShopAddress drtShopAddress){
		Map<String, Object> objMap= null;
		try{
			if(StringUtils.isEmpty(drtShopAddress)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "加入购物车对象不能为空", "");				
	            return objMap;
			}
			int count = drtShopAddressMapper.insert(drtShopAddress);
			if(count == Integer.parseInt(SystemConstants.COMMON_FLAG_TRUE)){				
	            objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "添加成功", "");
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加失败", "");
			}
		}catch(Exception e){			
			log.error("DrtOprNotice error:" + e.getMessage());
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
			int count = drtShopAddressMapper.update(drtShopAddress);
			if(count == Integer.parseInt(SystemConstants.COMMON_FLAG_TRUE)){				
	            objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "修改成功", "");
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "修改失败", "");
			}
		}catch(Exception e){			
			log.error("DrtOprNotice error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "修改失败", "");
			return objMap;
		}
		return objMap;
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城地址表 主键ID
	 * @return 积分商城地址表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtShopAddress selectByPrimaryKey(String id) throws Exception{
		return drtShopAddressMapper.selectByPrimaryKey(id);
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
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "地址id不能为空", "");				
	            return objMap;
			}
			List<DrtShopAddress> objList = drtShopAddressMapper.selectList(drtShopAddress);
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "", objList);
		}catch(Exception e){			
			log.error("DrtOprNotice error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "查询失败", "");
			return objMap;
		}
		return objMap;
		
	}

}