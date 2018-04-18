package com.csg.intshop.controller.mall;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.common.enums.ErrorEnum;
import com.csg.intshop.entity.DrtMallCart;
import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.entity.DrtMallOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallCartService;
import com.csg.intshop.service.DrtMallItemService;
import com.csg.intshop.service.DrtShopConfigService;
import com.csg.intshop.util.DateTimeUtils;
import com.csg.intshop.util.ElectronicMallApiUtil;
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
@RequestMapping("/ins/mall/cart/*")
public class CartController extends ExceptionController{
	
	//日志
	private Logger log = LoggerFactory.getLogger(CartController.class);

	/** drtMallCartService 积分商城购物车表 */
	@Autowired
	private DrtMallCartService drtMallCartService;
	
	/** DrtMallItemService 积分商城商品池 */
	@Autowired
	private DrtMallItemService drtMallItemService;
	
	/** DrtShopConfigService 积分商城配置表 */
	@Autowired
	private DrtShopConfigService drtShopConfigService;
	
	@Autowired
	private ElectronicMallApiUtil electronicMallApiUtil;
	
	
	/**
	 * 获取当前用户购物车详情
	 * 
	 * @param session 会话
	 * @return 结果集
	 * @throws Exception 
	 */
	@RequestMapping("mallCartDetail")
	public Map<String,Object> mallCartDetail(HttpSession session) throws Exception{
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		
		// 获取用户所有的购物车商品
		Map<String, Map<String, String>> itemMap = this.getItems(drtAccount.getAccountId());
		
		// 封装数据返回
		List<DrtMallOrderDetail> drtMallOrderDetailList = this.getDrtMallOrderDetailList(itemMap);
		Map<String, List<DrtMallOrderDetail>> storeData = drtMallOrderDetailList
				.stream().collect(Collectors.groupingBy(DrtMallOrderDetail::getStoreCode));
		// 封装数据
		List<Map<String, Object>> resultDataList = new ArrayList<>();
		Map<String, Object> resultData = null;
		for(Map.Entry<String, List<DrtMallOrderDetail>> entry : storeData.entrySet()){
			String storeCode = entry.getKey();
			List<DrtMallOrderDetail> orderDetailList = entry.getValue();
			if(!CollectionUtils.isEmpty(orderDetailList)){
				resultData = new HashMap<>();
				resultData.put("storeCode", storeCode);
				resultData.put("storeName", orderDetailList.get(0).getStoreName());
				resultData.put("itemList", orderDetailList);
			}
			resultDataList.add(resultData);
		}
		
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "执行成功！", resultDataList);
	}
	
	
	
	/**
	 * 更新购物车商品数量
	 * @param request 请求 其中格式：mallCartJson[{"itemId": "001", "itemNum": "12"},{"itemId": "002", "itemNum": "10"}]
	 * @param session 会话
	 * @return resultMap 结果集
	 * 
	 */
	@RequestMapping("/synMallCart")
	public Map<String,Object> synMallCart(HttpServletRequest request, HttpSession session){
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
	    	        	if(drtMallItem != null){// 新增
	    	        		DrtMallCart drtMallCart = new DrtMallCart();
	    	        		//主键
	    	        		drtMallCart.setId(UUIDUtil.generateUUID());
	    	        		//电融账号
	    	        		drtMallCart.setAccountId(drtAccount.getAccountId());
	    	        		//创建人ID
	    	        		drtMallCart.setCreatorId(drtAccount.getAccountId());
	    	        		//商品数量
	    	        		drtMallCart.setItemNum(itemNum);
	    	        		//是否删除：0-是 1-否
	    	        		drtMallCart.setIsDelete(1);
	    	        		//商品ID
    	        		    drtMallCart.setItemId(itemId);
    	        		    //创建人名称
	    	        		drtMallCart.setCreatorName(drtAccount.getNickname());
	    	        		//创建时间
	    	        		drtMallCart.setCreateTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
	    	        		//修改人ID
	    	        		drtMallCart.setLastModifierId(drtAccount.getAccountId());
	    	        		//修改人名称
	    	        		drtMallCart.setLastModifierName(drtAccount.getNickname());
	    	        		//修改时间
	    	        		drtMallCart.setLastModifierTime(NumberUtils.toLong(DateTimeUtils.converDateToString(new Date(), "yyyyMMddHHmmss")));
	    	        		insertList.add(drtMallCart);
	    	        	}
	        		}
	        	}  
	        }
	        
	        // 删除
	        if(!CollectionUtils.isEmpty(itemCartMap)){
	        	for (Map.Entry<String, DrtMallCart> entry : itemCartMap.entrySet()) {
	        		deleteSet.add(entry.getValue().getId());
				}
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
	 * 获取当前用户购物车商品总数量
	 * @param accountId 电融通账户ID
	 * @return Map<String, Object> 结果集
	 */
	@RequestMapping("totalNum")
	private Map<String, Object> getUserMallCartTotalNum(HttpSession session) {
		DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
		//累计商品总数
		int iTotalNum = 0;
		//查询条件
		DrtMallCart queryDrtMallCart = new DrtMallCart();
		queryDrtMallCart.setAccountId(drtAccount.getAccountId());
		List<DrtMallCart> drtMallCartList = drtMallCartService.selectList(queryDrtMallCart);
		if(!CollectionUtils.isEmpty(drtMallCartList)){
			for (DrtMallCart drtMallCart : drtMallCartList) {
				if(drtMallCart.getItemNum() != null){
					iTotalNum += drtMallCart.getItemNum();
				}
			}
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "操作成功！", iTotalNum);
	}

	/**
	 * 购物车添加商品
	 * @param request 请求 其中格式：itemId
	 * @param session 会话
	 * @return resultMap 结果集
	 * 
	 */
	@RequestMapping("mallCartAddItem")
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
	
	/**
	 * 获取用户购物车所有上架的商品，下架商品默认删除
	 * @param accountId 电融通账户ID
	 * @return Map<String, DrtMallCart> key:商品ID value:购物车记录
	 */
	private Map<String, DrtMallCart> getUserMallCartItems(String accountId) {
		Map<String, DrtMallCart> drtMallCartMap = new HashMap<>();
		
		// 获取系统所有上架商品
		DrtMallItem queryDrtMallItem = new DrtMallItem();
		// 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
		queryDrtMallItem.setItemState(3);
		Map<String, DrtMallItem> drtMallItemMap = this.getDrtMallItemMap(queryDrtMallItem);
		
		// 获取用户购物车所有商品
		DrtMallCart queryDrtMallCart = new DrtMallCart();
		queryDrtMallCart.setAccountId(accountId);
		List<DrtMallCart> drtMallCartList = drtMallCartService.selectList(queryDrtMallCart);
		
		if(!CollectionUtils.isEmpty(drtMallCartList)){
			for (DrtMallCart drtMallCart : drtMallCartList) {
				if(drtMallItemMap.get(drtMallCart.getItemId()) != null){
					drtMallCartMap.put(drtMallCart.getItemId(), drtMallCart);
				}else{
					try {
						// 删除用户购物车中下架商品
						drtMallCartService.delete(drtMallCart.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return drtMallCartMap;
	}
	
	/**
	 * 获取用户购物车所有上架的商品，下架商品默认删除
	 * @param accountId 电融通账户ID
	 * @return Map<String, DrtMallCart> key:商品ID value:购物车数据（itemId、itemNum、skuCode）
	 */
	private Map<String, Map<String, String>> getItems(String accountId) {
		Map<String, Map<String, String>> itemMap = new HashMap<>();
		Map<String, String> item = null;
		// 获取系统所有上架商品
		DrtMallItem queryDrtMallItem = new DrtMallItem();
		// 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
		queryDrtMallItem.setItemState(3);
		Map<String, DrtMallItem> drtMallItemMap = this.getDrtMallItemMap(queryDrtMallItem);
		
		// 获取用户购物车所有商品
		DrtMallCart queryDrtMallCart = new DrtMallCart();
		queryDrtMallCart.setAccountId(accountId);
		List<DrtMallCart> drtMallCartList = drtMallCartService.selectList(queryDrtMallCart);
		
		if(!CollectionUtils.isEmpty(drtMallCartList)){
			for (DrtMallCart drtMallCart : drtMallCartList) {
				if(drtMallItemMap.get(drtMallCart.getItemId()) != null){
					item = new HashMap<>();
					String skuCode = drtMallItemMap.get(drtMallCart.getItemId()).getSkuCode();
					item.put("itemId", drtMallCart.getItemId());
					item.put("itemNum", drtMallCart.getItemNum() + "");
					item.put("skuCode", skuCode);
					itemMap.put(skuCode, item);
				}else{
					try {
						// 删除用户购物车中下架商品
						drtMallCartService.delete(drtMallCart.getId());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return itemMap;
	}
	
	/**
	 * 获取商品信息列表，只返回上架商品
	 * 
	 * @param itemNumMap
	 *            Map<String, Integer> key-商品编码skuCode value-itemId、itemNum、skuCode
	 * @author xuchen
	 * @return List<DrtMallOrderDetail> 商品详情集合
	 * @throws Exception
	 *             出错抛出异常
	 * 
	 */
	private List<DrtMallOrderDetail> getDrtMallOrderDetailList(
			Map<String, Map<String, String>> itemNumMap) throws Exception {
		List<DrtMallOrderDetail> drtMallOrderDetailList = new ArrayList<>();
		Set<String> skuCodeSet = itemNumMap.keySet();
		String skuCodes = StringUtils.join(
				skuCodeSet.toArray(new String[skuCodeSet.size()]), ",");
		// 获取南网商品信息集
		LinkedHashMap<String, Object> skuCodeListMap = new LinkedHashMap<>();
		skuCodeListMap.put("skuCode", skuCodes);
		JSONObject jsonObject = electronicMallApiUtil.postJson(
				ElectronicMallApiUrlEnum.GET_SKU_INFO_LIST, skuCodeListMap);
		// 执行成功
		if ("Y".equals(jsonObject.get("success"))
				&& ErrorEnum.NORMAL.getResultCode().equals(
						jsonObject.get("resultCode"))) {
			// 商品SKU信息集合
			JSONArray skuInfoJsonArray = jsonObject.getJSONArray("skuList");
			if (!CollectionUtils.isEmpty(skuInfoJsonArray)) {
				// 获取积分商城配置项
				Map<String, String> drtShopConfigMap = drtShopConfigService
						.getDrtShopConfigMap();
				// 比例：元-->积分
				int rate = NumberUtils.toInt(drtShopConfigMap.get("RATE"), 1);

				for (int i = 0; i < skuInfoJsonArray.size(); i++) {
					// 获取单个商品SKU信息
					JSONObject skuInfoJson = skuInfoJsonArray.getJSONObject(i);
					String skuCode = String.valueOf(skuInfoJson.get("skuCode"));
					if(itemNumMap.get(skuCode) != null){
						String itemId = itemNumMap.get(skuCode).get("itemId");
						String skuId = String.valueOf(skuInfoJson.get("skuId"));
						String skuName = String.valueOf(skuInfoJson.get("skuName"));
						String purchaseCatId = String.valueOf(skuInfoJson
								.get("purchaseCatId"));
						String purchaseCatName = String.valueOf(skuInfoJson
								.get("purchaseCatName"));
						String brandId = String.valueOf(skuInfoJson.get("brandId"));
						String brandName = String.valueOf(skuInfoJson
								.get("brandName"));
						// 商品状态（-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3）
						String skuStatus = String.valueOf(skuInfoJson
								.get("skuStatus"));
						String price = String.valueOf(skuInfoJson.get("price"));
						String companyId = String.valueOf(skuInfoJson
								.get("companyId"));
						String companyName = String.valueOf(skuInfoJson
								.get("companyName"));
						String storeCode = String.valueOf(skuInfoJson
								.get("storeCode"));
						String storeName = String.valueOf(skuInfoJson
								.get("storeName"));
						String imgUrl = String.valueOf(skuInfoJson.get("imgUrl"));
						if ("3".equals(skuStatus)) {
							DrtMallOrderDetail drtMallOrderDetail = new DrtMallOrderDetail();
							drtMallOrderDetail.setItemId(itemId);
							drtMallOrderDetail.setSkuId(skuId);
							drtMallOrderDetail.setSkuCode(skuCode);
							drtMallOrderDetail.setSkuName(skuName);
							drtMallOrderDetail.setPurchaseCatId(purchaseCatId);
							drtMallOrderDetail.setPurchaseCatName(purchaseCatName);
							drtMallOrderDetail.setBrandId(brandId);
							drtMallOrderDetail.setBrandName(brandName);
							drtMallOrderDetail.setSkuPrice((long) (NumberUtils
									.toDouble(price, 0) * 100));
							drtMallOrderDetail.setBuyAmt(NumberUtils.toInt(
									itemNumMap.get(skuCode).get("itemNum") + "", 1));
							drtMallOrderDetail.setSkuEarnings((long) (NumberUtils
									.toDouble(price, 0) * rate));
							drtMallOrderDetail
							.setSubtotalPrice(drtMallOrderDetail
									.getSkuPrice()
									* drtMallOrderDetail.getBuyAmt());
							drtMallOrderDetail
							.setSubtotalEarnings(drtMallOrderDetail
									.getSkuEarnings()
									* drtMallOrderDetail.getBuyAmt());
							
							drtMallOrderDetail.setCompanyId(companyId);
							drtMallOrderDetail.setCompanyName(companyName);
							drtMallOrderDetail.setStoreCode(storeCode);
							drtMallOrderDetail.setStoreName(storeName);
							drtMallOrderDetail.setImgUrl(imgUrl);
							
							// 添加
							drtMallOrderDetailList.add(drtMallOrderDetail);
						} else {
							try {
								// 将非上架商品同步至系统商品
								DrtMallItem drtMallItem = new DrtMallItem();
								drtMallItem.setSkuCode(skuCode);
								drtMallItem.setSkuCode(skuStatus);
								drtMallItemService.updateBySkuCode(drtMallItem);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return drtMallOrderDetailList;
	}
	
	/**
	 * 通过查询条件获取商品
	 * 
	 * @param queryDrtMallItem
	 *            查询条件
	 * @author xuchen
	 * @return Map<String, DrtMallItem> 积分商城商品池Map<主键，实体> key-itemId, value-DrtMallItem
	 * @throws Exception
	 */
	private Map<String, DrtMallItem> getDrtMallItemMap(
			DrtMallItem queryDrtMallItem) {
		List<DrtMallItem> drtMallItemList = drtMallItemService
				.selectList(queryDrtMallItem);
		Map<String, DrtMallItem> DrtMallItemMap = new HashMap<>();
		if (!CollectionUtils.isEmpty(drtMallItemList)) {
			for (DrtMallItem drtMallItem : drtMallItemList) {
				DrtMallItemMap.put(drtMallItem.getId(), drtMallItem);
			}
		}
		return DrtMallItemMap;
	}
	
}
	

