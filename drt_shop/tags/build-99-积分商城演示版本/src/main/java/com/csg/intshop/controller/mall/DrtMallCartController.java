package com.csg.intshop.controller.mall;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.entity.DrtMallCart;
import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallCartService;
import com.csg.intshop.service.DrtMallItemService;
import com.csg.intshop.util.DateTimeUtils;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.UUIDUtil;
import com.csg.personcenter.entity.mybatis.DrtAccount;



/**
 * DrtMallCart 积分商城购物车表
 *
 * @author  温明森
 * @since   1.8
 * @version 2018年01月24日 温明森
 */
@RestController
@RequestMapping("/api/drtMallCart")
public class DrtMallCartController{
	
	//日志
	private Logger log = LoggerFactory.getLogger(DrtMallCartController.class);

	/** drtMallCartService 积分商城购物车表 */
	@Autowired
	private DrtMallCartService drtMallCartService;
	
	/** DrtMallItemService 积分商城商品池 */
	@Autowired
	private DrtMallItemService drtMallItemService;
	
	/**
	 * 更新购物车商品数量
	 * @param request 请求 其中格式：mallCartJson[{"itemId": "001", "itemNum": "12"},{"itemId": "002", "itemNum": "10"}]
	 * @param session 会话
	 * @return resultMap 结果集
	 * 
	 */
	@RequestMapping("synMallCart")
	@ResponseBody
	public Map<String,Object> updateMallCartNum(HttpServletRequest request, HttpSession session){
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		try {
			// 获取购物车信息
			String strMallCartJson = request.getParameter("mallCartJson");
			JSONArray jsonArray = JSONArray.fromObject(strMallCartJson);
			if(StringUtils.isBlank(strMallCartJson) || jsonArray == null){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "数据获取失败！", "");
			}
			// 获取用户所有的购物车商品，对比前台传递的商品集，做同步操作
			Map<String, DrtMallCart> itemCartMap = this.getUserMallCartItems(drtAccount.getAccountId());
			// 待更新
			List<DrtMallCart> updateList = new ArrayList<DrtMallCart>();
			// 待新增
			List<DrtMallCart> insertList = new ArrayList<DrtMallCart>();
			// 待删除
			Set<String> deleteSet = new HashSet<String>();
	        for ( int i=0, iSize=jsonArray.size(); i < iSize; i++){
	        	JSONObject jsonObject = jsonArray.getJSONObject(i);  
	        	String itemId = jsonObject.getString("itemId");
	        	int itemNum = NumberUtils.toInt(jsonObject.getString("itemNum"), 0);
	        	if(StringUtils.isNotBlank(itemId)){
	        		if(!CollectionUtils.isEmpty(itemCartMap) && itemCartMap.containsKey(itemId)){
	        			if(itemNum > 0){
	        				// 更新数量
	        				DrtMallCart drtMallCart = itemCartMap.get(itemId);
	        				drtMallCart.setItemNum(itemNum);
	        				updateList.add(drtMallCart);
	        				// 删除itemCartMap
	        				itemCartMap.remove(itemId);
	        			}
	        		}else{
	        			// 判断商品是否存在
	        			DrtMallItem drtMallItem = drtMallItemService.selectByPrimaryKey(itemId);
	    	        	if(drtMallItem != null){
	    	        		DrtMallCart drtMallCart = new DrtMallCart();
	    	        		// 新增
	    	        		drtMallCart.setId(UUIDUtil.generateUUID());
	    	        		drtMallCart.setAccountId(drtAccount.getAccountId());
	    	        		drtMallCart.setCreatorId(drtAccount.getAccountId());
	    	        		drtMallCart.setCreatorName(drtAccount.getNickname());
	    	        		drtMallCart.setCreateTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
	    	        		drtMallCart.setLastModifierId(drtAccount.getAccountId());
	    	        		drtMallCart.setLastModifierName(drtAccount.getNickname());
	    	        		drtMallCart.setLastModifierTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
	    	        		insertList.add(drtMallCart);
	    	        	}
	        		}
	        	}  
	        } 
	        
	        // 删除
	        if(!CollectionUtils.isEmpty(itemCartMap)){
	        	deleteSet = itemCartMap.keySet();
	        }
	        // 同步
	        drtMallCartService.synMallCart(updateList, insertList, deleteSet);
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "操作成功！", "");
		} catch (Exception e) {
			log.error("updateMallCartNum-{}更新购物车商品数量异常", e);
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "操作失败！", "");
	}

	/**
	 * 获取用户所有的购物车商品
	 * @param accountId 电融通账户ID
	 * @return Map<String, DrtMallCart> key:商品ID value:购物车记录
	 */
	private Map<String, DrtMallCart> getUserMallCartItems(String accountId) {
		Map<String, DrtMallCart> itemCartMap = new HashMap<String, DrtMallCart>();
		DrtMallCart queryDrtMallCart = new DrtMallCart();
		queryDrtMallCart.setAccountId(accountId);
		List<DrtMallCart> drtMallCartList = drtMallCartService.selectList(queryDrtMallCart);
		if(!CollectionUtils.isEmpty(drtMallCartList)){
			for (DrtMallCart drtMallCart : drtMallCartList) {
				itemCartMap.put(drtMallCart.getItemId(), drtMallCart);
			}
		}
		return itemCartMap;
	}
	
	/**
	 * 购物车添加商品
	 * @param request 请求 其中格式：itemId
	 * @param session 会话
	 * @return resultMap 结果集
	 * 
	 */
	@RequestMapping("mallCartAddItem")
	@ResponseBody
	public Map<String,Object> mallCartAddItem(HttpServletRequest request, HttpSession session){
		//获取当前用户
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		//商品ID
		String itemId = request.getParameter("itemId");
		//商品ID非空校验
		if(StringUtils.isEmpty(itemId)){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品ID为空", "");
		}
		//判断商品是否存在
		DrtMallItem drtMallItem = drtMallItemService.selectByPrimaryKey(itemId);
		if(drtMallItem == null){
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品不存在", "");
		}
		try {
			//判断当前用户的购物车中是否存在该商品
			DrtMallCart drtMallCartForQuery = new DrtMallCart();
			drtMallCartForQuery.setAccountId(drtAccount.getAccountId());
			drtMallCartForQuery.setItemId(itemId);
			List<DrtMallCart> drtMallCartList = drtMallCartService.selectList(drtMallCartForQuery);
			//当前用户的购物车中不存在该商品，添加一条新纪录
			if(drtMallCartList.size() < 1){
				//封装drtMallCart实体
				DrtMallCart drtMallCart = getDrtMallCart(drtAccount,itemId);
				drtMallCartService.insert(drtMallCart);
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "添加成功", "");
			}else{//当前用户的购物车中存在该商品，更新当前记录
				DrtMallCart drtMallCart = drtMallCartList.get(0);
				drtMallCart.setItemNum(drtMallCart.getItemNum()+1);
				drtMallCartService.updateIfNotNull(drtMallCart);
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "添加成功", "");
			}
		} catch (Exception e) {
			log.error("mallCartAddItem-{}购物车添加商品异常", e);
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "添加失败", "");
	}
	
	/**
	 * 封装drtMallCart实体
	 * @param accountId 电融通账户ID
	 * @param itemId 商品ID
	 * @return DrtMallCart 购物车实体
	 */
	private DrtMallCart getDrtMallCart(DrtAccount drtAccount,String itemId) {
		DrtMallCart drtMallCart = new DrtMallCart();
		drtMallCart.setAccountId(drtAccount.getAccountId());
		drtMallCart.setCreateTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
		drtMallCart.setCreatorId(drtAccount.getAccountId());
		drtMallCart.setCreatorName(drtAccount.getNickname());
		drtMallCart.setId(UUIDUtil.generateUUID());
		drtMallCart.setIsDelete(1);
		drtMallCart.setItemId(itemId);
		drtMallCart.setItemNum(1);
		drtMallCart.setLastModifierId(drtAccount.getAccountId());
		drtMallCart.setLastModifierName(drtAccount.getNickname());
		drtMallCart.setLastModifierTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
		return drtMallCart;
	}
}
	

