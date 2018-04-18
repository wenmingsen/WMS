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

import com.csg.intshop.entity.DrtShopItem;
import com.csg.intshop.entity.DrtShopOrder;
import com.csg.intshop.entity.DrtShopOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.mapper.DrtShopCartMapper;
import com.csg.intshop.mapper.DrtShopItemMapper;
import com.csg.intshop.mapper.DrtShopOrderDetailMapper;
import com.csg.intshop.util.GetUUID;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;

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
	public List<DrtShopOrderDetail> selectListByUserID(Map<String,Object> params){
		return drtShopOrderDetailMapper.selectListByUserID(params);
	}
	
	
	/**
	 * 生成订单
	 * @param id
	 * @param orderNum
	 * @return
	 */
	public Map<String,Object> insertOrder(String id,String orderNum,String accountId){
		Map<String, Object> objMap= new HashMap<String, Object>();
		try{
			List<DrtShopItem> objList = new ArrayList<DrtShopItem>();
			DrtShopItem drtShopItem = null;
			String orderId = GetUUID.getUuuid();
			if(!StringUtils.isEmpty(id)){
				 drtShopItem = drtShopItemMapper.selectByPrimaryKey(id);
				 objList.add(drtShopItem);
				 
			}else{
				 objList = drtShopItemMapper.selectList(drtShopItem);
			}
			//创建主订单
			int orderEarnings = 0;
			DrtShopOrder drtShopOrder = new DrtShopOrder();
			drtShopOrder.setAccountId(accountId);
			drtShopOrder.setId(orderId);
			drtShopOrder.setOrderNo(orderNum);
			//创建子订单
			List<DrtShopOrderDetail> objLists = new ArrayList<DrtShopOrderDetail>();
			for(DrtShopItem dto: objList){
				DrtShopOrderDetail drtShopOrderDetail = new DrtShopOrderDetail();
				if(dto.getItemNum()==null){
					dto.setItemNum(1);
				}
				orderEarnings=orderEarnings+dto.getItemNum()*dto.getItemEarnings();
				drtShopOrderDetail.setItemEarnings(dto.getItemEarnings());
				drtShopOrderDetail.setId(GetUUID.getUuuid());
				drtShopOrderDetail.setItemNum(dto.getItemNum());
				drtShopOrderDetail.setOrderEarnings(dto.getItemNum()*dto.getItemEarnings());
				drtShopOrderDetail.setItemId(dto.getId());
				drtShopOrderDetail.setCreatorId(accountId);
				drtShopOrderDetail.setOrderId(orderId);
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
}