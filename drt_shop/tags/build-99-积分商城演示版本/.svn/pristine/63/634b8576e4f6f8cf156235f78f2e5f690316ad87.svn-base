package com.csg.intshop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.mapper.DrtShopOrderMapper;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;

/**
 * DrtShopOrderService 积分商城订单表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Transactional
@Service
public class DrtShopOrderService{
	
	private Logger log = LoggerFactory.getLogger(DrtShopOrderService.class);

	
	/**积分商城订单表drtShopOrderMapper接口*/
	@Autowired
	private DrtShopOrderMapper drtShopOrderMapper;

	/**
	 * 保存
	 * 
	 * @param drtShopOrder 积分商城订单表
	 */
	public void insert(DrtShopOrder drtShopOrder) throws Exception{
		drtShopOrderMapper.insert(drtShopOrder);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopOrder 积分商城订单表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtShopOrder drtShopOrder) throws Exception{
		drtShopOrderMapper.update(drtShopOrder);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单表 主键ID
	 * @return 积分商城订单表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtShopOrder selectByPrimaryKey(String id) throws Exception{
		return drtShopOrderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopOrder 积分商城订单表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtShopOrder> selectList(DrtShopOrder drtShopOrder) throws Exception{
		return drtShopOrderMapper.selectList(drtShopOrder);
	}
	
	/**
	 * 修改订单状态
	 * 
	 * @param drtShopOrder 更新对象
	 * @return 结果影响行数
	 */
	public int updShopOrderState(DrtShopOrder drtShopOrder){
		return  drtShopOrderMapper.updShopOrderState(drtShopOrder);
	}


	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城商品表 主键ID
	 * @return 积分商城商品表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public Map<String,Object> queryGoods(String orderId){
		Map<String, Object> objMap= new HashMap<String, Object>();
		List<Map<String,Object>> objList = new ArrayList<Map<String,Object>>();
		try{
			if(StringUtils.isEmpty(orderId)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "查询订单id不能为空", "");				
	            return objMap;
			}
			objList = drtShopOrderMapper.queryGoods(orderId);
			if(objList.size() != 0){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "查询成功", objList);
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "未查询到", objList);
			}
		}catch(Exception e){			
			log.error("DrtShopOrderService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "查询失败", "");
			return objMap;
		}
		return objMap;
	}


}