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

import com.csg.intshop.bean.ShopBean;
import com.csg.intshop.bean.ShopPage;
import com.csg.intshop.entity.DrtShopItem;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.mapper.DrtShopItemMapper;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;
import com.github.pagehelper.PageHelper;


/**
 * DrtShopItemService 积分商城商品表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Transactional
@Service
public class DrtShopItemService{
	
	private Logger log = LoggerFactory.getLogger(DrtShopItemService.class);
	
	/**积分商城商品表drtShopItemMapper接口*/
	@Autowired
	private DrtShopItemMapper drtShopItemMapper;

	/**
	 * 保存
	 * 
	 * @param drtShopItem 积分商城商品表
	 */
	public void insert(DrtShopItem drtShopItem) throws Exception{
		drtShopItemMapper.insert(drtShopItem);
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopItem 积分商城商品表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtShopItem drtShopItem) throws Exception{
		drtShopItemMapper.update(drtShopItem);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城商品表 主键ID
	 * @return 积分商城商品表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public Map<String,Object> selectByPrimaryKey(String id){
		Map<String, Object> objMap= new HashMap<String, Object>();
		List<DrtShopItem> objList = new ArrayList<DrtShopItem>();
		DrtShopItem drtShopItem = null;
		try{
			if(!StringUtils.isEmpty(id)){
				 drtShopItem = drtShopItemMapper.selectByPrimaryKey(id);
				 objList.add(drtShopItem);
			}
			if(objList.size() != 0){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "查询成功", objList);
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "未查询到", objList);
			}
		}catch(Exception e){			
			log.error("DrtOprNotice error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "查询失败", "");
			return objMap;
		}
		return objMap;
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopItem 积分商城商品表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtShopItem> selectList(DrtShopItem drtShopItem) throws Exception{
		return drtShopItemMapper.selectList(drtShopItem);
	}

	
	 /**获取商品品种总数*/
	public Integer selCount(DrtShopItem drtShopItem){
		return  drtShopItemMapper.selCount(drtShopItem);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopItem 积分商城商品表
	 * @return 积分商城商品表 记录集
	 */
	public ShopPage<DrtShopItem> seleItems(ShopBean shopBean){
		ShopPage<DrtShopItem> shopItems=new  ShopPage<DrtShopItem>();
		DrtShopItem drtShopItem=new DrtShopItem();
		drtShopItem.setItemState(SystemConstants.ZERO);
		Integer count=selCount(drtShopItem);
		shopItems.setPageNumber(shopBean.getPageNumber());
		shopItems.setPageSize(shopBean.getPageSize());
		if(count!=null){
			shopItems.setTotalPageSize(count);
		}else{
			shopItems.setTotalPageSize(SystemConstants.ZERO);
		}
		PageHelper.startPage(shopBean.getPageNumber() ,shopBean.getPageSize());
		List<DrtShopItem> list= drtShopItemMapper.seleItems(drtShopItem);
		com.github.pagehelper.PageInfo<DrtShopItem> pageInfo = new com.github.pagehelper.PageInfo<DrtShopItem>(list);
		shopItems.setList(pageInfo.getList());
		return shopItems;
	}
}