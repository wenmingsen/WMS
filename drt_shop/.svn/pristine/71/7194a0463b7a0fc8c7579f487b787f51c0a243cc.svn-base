package com.csg.intshop.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtShopItem;
import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.entity.DrtShopOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.mapper.DrtShopCartMapper;
import com.csg.intshop.mapper.DrtShopItemMapper;
import com.csg.intshop.mapper.DrtShopOrderDetailMapper;
import com.csg.intshop.mapper.DrtShopOrderMapper;
import com.csg.intshop.util.GetUUID;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;
import com.github.pagehelper.StringUtil;

/**
 * DrtShopOrderDetailService 积分商城订单子表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Transactional
@Service
public class DrtShopOrderDetailService{
	
	private Logger log = LoggerFactory.getLogger(DrtShopOrderDetailService.class);

	
	/**积分商城订单子表drtShopOrderDetailMapper接口*/
	@Autowired
	private DrtShopOrderDetailMapper drtShopOrderDetailMapper;
	
	/**积分商城商品表drtShopItemMapper接口*/
	@Autowired
	private DrtShopItemMapper drtShopItemMapper;
	
	/**积分商城商品表drtShopCartMapper接口*/
	@Autowired
	private DrtShopCartMapper drtShopCartMapper;
	
	/**注入DrtShopOrderMapper*/
	@Autowired
	private DrtShopOrderMapper drtShopOrderMapper;

	/**
	 * 保存
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 */
	public void insert(DrtShopOrderDetail drtShopOrderDetail) throws Exception{
		drtShopOrderDetailMapper.insert(drtShopOrderDetail);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtShopOrderDetail drtShopOrderDetail) throws Exception{
		drtShopOrderDetailMapper.update(drtShopOrderDetail);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城订单子表 主键ID
	 * @return 积分商城订单子表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtShopOrderDetail selectByPrimaryKey(String id) throws Exception{
		return drtShopOrderDetailMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopOrderDetail 积分商城订单子表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtShopOrderDetail> selectList(DrtShopOrderDetail drtShopOrderDetail) throws Exception{
		return drtShopOrderDetailMapper.selectList(drtShopOrderDetail);
	}

	/**
	 * 获取用户订单
	 * 
	 * @param params 查询条件
	 * @return 用户订单记录集
	 */
	public List<DrtShopOrderDetail> selectListByOrderID(Map<String,Object> params){
		return drtShopOrderDetailMapper.selectListByOrderID(params);
	}
	
	
	/**
	 * 生成订单
	 * @param id 商品id
	 * @param orderNum 订单号
	 * @return 结果
	 */
	public Map<String,Object> insertOrder(String id,Integer itemNum,String orderNum,String name,String addressId,String phone,String address,String accountId){
		Map<String, Object> objMap= new HashMap<String, Object>();
		try{
			List<DrtShopItem> objList = new ArrayList<DrtShopItem>();
			DrtShopItem drtShopItem = new DrtShopItem();
			drtShopItem.setCreatorId(accountId);
			String orderId = GetUUID.getUuuid();
			if(!StringUtils.isEmpty(id)){
				 drtShopItem = drtShopItemMapper.selectByPrimaryKey(id);
				 drtShopItem.setItemNum(itemNum);
				 objList.add(drtShopItem);
				 
			}else{
				 objList = drtShopItemMapper.selectList(drtShopItem);
			}
			if(objList.size()==SystemConstants.ZERO){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "未查询到商品，创建订单失败", "");
				return objMap;
			}
			//创建主订单
			int orderEarnings = 0;
			DrtShopOrder drtShopOrder = new DrtShopOrder();
			drtShopOrder.setAccountId(accountId);
			drtShopOrder.setId(orderId);
			drtShopOrder.setOrderNo(orderNum);
			drtShopOrder.setName(name);
			drtShopOrder.setPhone(phone);
			drtShopOrder.setAddressId(addressId);
			drtShopOrder.setAddress(address);
			drtShopOrder.setCreateTime(new Timestamp(System.currentTimeMillis()));
			//创建子订单
			List<DrtShopOrderDetail> objLists = new ArrayList<DrtShopOrderDetail>();
			for(DrtShopItem dto: objList){
				DrtShopOrderDetail drtShopOrderDetail = new DrtShopOrderDetail();
				orderEarnings= orderEarnings+dto.getItemNum()*dto.getItemEarnings();
				drtShopOrderDetail.setItemEarnings(dto.getItemEarnings());
				drtShopOrderDetail.setId(GetUUID.getUuuid());
				drtShopOrderDetail.setItemNum(dto.getItemNum());
				drtShopOrderDetail.setOrderEarnings(dto.getItemNum()*dto.getItemEarnings());
				drtShopOrderDetail.setItemId(dto.getId());
				drtShopOrderDetail.setCreatorId(accountId);
				drtShopOrderDetail.setOrderId(orderId);
				drtShopOrderDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
				objLists.add(drtShopOrderDetail);
			}
			drtShopOrder.setOrderEarnings(orderEarnings);
			drtShopOrderDetailMapper.insertOrder(drtShopOrder);
			drtShopOrderDetailMapper.insertList(objLists);
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "订单创建成功", orderId);
		}catch(Exception e){			
			log.error("DrtShopOrderDetailService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单创建失败", "");
			return objMap;
		}
		return objMap;
	}

	/**
	 * 获取商品信息
	 * @param id 单件商品的id
	 * @param request 获取session
	 * @return 商品信息结果
	 */
	public Map<String, Object> queryGoodsInfo(String id, String accountId) {
		Map<String, Object> objMap= new HashMap<String, Object>();
		try{
			List<DrtShopItem> objList = new ArrayList<DrtShopItem>();
			DrtShopItem drtShopItem = new DrtShopItem();
			drtShopItem.setCreatorId(accountId);
			if(!StringUtils.isEmpty(id)){
				 drtShopItem = drtShopItemMapper.selectByPrimaryKey(id);
				 objList.add(drtShopItem);
			}else{
				 objList = drtShopItemMapper.selectList(drtShopItem);
			}
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "查询成功", objList);
		}catch(Exception e){			
			log.error("DrtShopOrderDetailService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品查询失败", "");
			return objMap;
		}
		return objMap;
	}

	/**
	 * 改变订单中商品的数量以及积分值
	 * @param itemId 商品id
	 * @param itemNum 商品数量
	 * @param earnings 积分值
	 * @return 结果
	 */
	public Map<String, Object> changeNumEarnings(String itemId, String orderId,String itemNum, String earnings) {
		Map<String, Object> objMap= new HashMap<String, Object>();
		try{
			if(StringUtil.isEmpty(itemId)||StringUtil.isEmpty(orderId)||StringUtil.isEmpty(itemNum)||StringUtil.isEmpty(earnings)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单修改对象不能为空", "");
				return objMap;
			}
			DrtShopOrderDetail drtShopOrderDetail = new DrtShopOrderDetail();
			drtShopOrderDetail.setItemNum(Integer.parseInt(itemNum));
			drtShopOrderDetail.setItemId(itemId);
			drtShopOrderDetail.setOrderId(orderId);
			DrtShopOrder drtShopOrder = new DrtShopOrder();
			drtShopOrder.setId(orderId);
			drtShopOrder.setOrderEarnings(Integer.parseInt(earnings));
			drtShopOrderDetailMapper.update(drtShopOrderDetail);
			drtShopOrderMapper.update(drtShopOrder);
		}catch(Exception e){			
			log.error("DrtShopOrderDetailService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品查询失败", "");
			return objMap;
		}
		
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "订单中商品的数量以及积分值修改成功", "");
	}

	/**
	 * 修改订单地址
	 * @param drtShopOrder 地址对象
	 * @return 结果
	 */
	public Map<String, Object> updateOrderAddress(DrtShopOrder drtShopOrder) {
		Map<String, Object> objMap= new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(drtShopOrder)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "地址修改对象不能为空", "");
				return objMap;
			}
			drtShopOrderMapper.update(drtShopOrder);
		}catch(Exception e){			
			log.error("DrtShopOrderDetailService error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "订单地址修改失败", "");
			return objMap;
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "订单地址修改成功", "");
	}
}