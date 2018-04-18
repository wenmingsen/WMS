package com.csg.intshop.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csg.intshop.entity.DrtShopCart;
import com.csg.intshop.entity.DrtShopItem;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.mapper.DrtShopCartMapper;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;
import com.csg.intshop.util.UUIDUtil;
import com.github.pagehelper.StringUtil;

/**
 * DrtShopCartService 积分商城购物车表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰
 */
@Transactional
@Service
public class DrtShopCartService{
	
	private Logger log = LoggerFactory.getLogger(DrtShopCartService.class);

	
	/**积分商城购物车表drtShopCartMapper接口*/
	@Autowired
	private DrtShopCartMapper drtShopCartMapper;
	
	/**
	 * 统计商品种类数量
	 * @return 数量
	 */
	public Map<String, Object> countGoods(){
		int count = drtShopCartMapper.countGoods();
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "添加成功", count);
	}

	/**
	 * 保存
	 * 
	 * @param drtShopCart 积分商城购物车表
	 */
	public Map<String, Object> insert(String id,String accountId) {
		Map<String, Object> objMap= null;
		DrtShopCart drtShopCart = new DrtShopCart();
		int count = 0;
		try{
			if(StringUtils.isEmpty(id)){
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "加入购物车对象不能为空", "");				
	            return objMap;
			}
			drtShopCart = drtShopCartMapper.queryItemId(id);
			if(drtShopCart !=null && drtShopCart.getItemNum()> SystemConstants.ZERO){
				drtShopCart.setItemId(id);
				drtShopCart.setAccountId(accountId);
				drtShopCart.setItemNum(drtShopCart.getItemNum()+1);
				count = drtShopCartMapper.updateByItemId(drtShopCart);
			}else{
				drtShopCart = new DrtShopCart();
				drtShopCart.setItemId(id);
				drtShopCart.setId(UUIDUtil.generateUUID());
				drtShopCart.setItemNum(1);
				drtShopCart.setAccountId(accountId);
				drtShopCart.setCreatorId(accountId);
				drtShopCart.setCreatorName(" ");
				count = drtShopCartMapper.insert(drtShopCart);
			}
			if(count == Integer.parseInt(SystemConstants.COMMON_FLAG_TRUE)){				
	            objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "添加成功", "");
			}else{
				objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加失败", "");
			}
		}catch(Exception e){			
			log.error("DrtShopCart error:" + e.getMessage());
			objMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加失败", "");
			return objMap;
		}
		return objMap;
	}
	
	/**
	 * 更新
	 * <p>通过主键更新记录</p>
	 * 
	 * @param drtShopCart 积分商城购物车表
	 * @throws Exception 出错抛出异常
	 */
	public void update(DrtShopCart drtShopCart) throws Exception{
		drtShopCartMapper.update(drtShopCart);
	}
	
	/**
	 * 通过主键获取单条记录
	 * 
	 * @param id 积分商城购物车表 主键ID
	 * @return 积分商城购物车表 单条记录
	 * @throws Exception 出错抛出异常
	 */
	public DrtShopCart selectByPrimaryKey(String id) throws Exception{
		return drtShopCartMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过自定义非空字段获取记录集
	 * 
	 * @param drtShopCart 积分商城购物车表
	 * @throws Exception 出错抛出异常
	 */
	public List<DrtShopCart> selectList(DrtShopCart drtShopCart) throws Exception{
		return drtShopCartMapper.selectList(drtShopCart);
	}
	
	/**
	 * 查询当前用户购物车商品
	 * 
	 * @param 用户id
	 * @return  当前用户购物车商品
	 * */
	public List<DrtShopItem> selectShopCars(String accountId){
		return drtShopCartMapper.selectShopCars(accountId);
	}
	
	/**
	 * 删除购物车商品
	 * 
	 * @param ids 商品id集合
	 * */
	public void delDrtShop(Map<String,Object> params){
		drtShopCartMapper.delDrtShop(params);
	}

}