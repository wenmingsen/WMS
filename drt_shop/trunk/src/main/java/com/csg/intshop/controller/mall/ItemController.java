package com.csg.intshop.controller.mall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.common.enums.ErrorEnum;
import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.entity.DrtMallItemQuery;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallItemService;
import com.csg.intshop.service.DrtShopConfigService;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.ResultMapHelper;
import com.github.pagehelper.PageHelper;

/**
 * 商品管理接口
 *
 * @author 曾令鹏
 * @since jdk1.8
 * @version 2018年2月1日 曾令鹏
 */
@RestController
@RequestMapping("/ins/mall/item/*")
public class ItemController {

	/** DrtMallItemService 积分商城商品池 */
	@Autowired
	private DrtMallItemService drtMallItemService;

	/** DrtShopConfigService 积分商城配置表 */
	@Autowired
	private DrtShopConfigService drtShopConfigService;

	/** 电子商城接口 工具类 */
	@Autowired
	private ElectronicMallApiUtil electronicMallApiUtil;

	/**
	 * 获取商品详情
	 * 
	 * @param request
	 * 		itemId： 商品ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("item")
	private Map<String, Object> item(HttpServletRequest request){
		try {
			// 商品ID
			String itemId = request.getParameter("itemId");
			if (StringUtils.isBlank(itemId)) {
				return ResultMapHelper.result(
						SystemConstants.RESULT_CODE_FAILED, "商品ID不能为空！");
			}
			DrtMallItem drtMallItem = drtMallItemService
					.selectByPrimaryKey(itemId);
			if (drtMallItem == null) {
				return ResultMapHelper.result(
						SystemConstants.RESULT_CODE_FAILED, "商品不存在！");
			}
			LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
			paramMap.put("skuCode", drtMallItem.getSkuCode());
			JSONObject jsonObject = electronicMallApiUtil.postJson(
					ElectronicMallApiUrlEnum.GET_SKU_DETAIL_INFO, paramMap);
			// 执行成功
			if ("Y".equals(jsonObject.get("success"))
					&& ErrorEnum.NORMAL.getResultCode().equals(
							jsonObject.get("resultCode"))) {
				double price = NumberUtils.toDouble(String.valueOf(jsonObject.get("price")), 0);
				// 商品价格必须至少大于等于1分钱
				if(price >= 0.01){
					// 商城配置项
					Map<String, String> drtShopConfigMap = drtShopConfigService.getDrtShopConfigMap();
					int rate = NumberUtils.toInt(drtShopConfigMap.get("RATE"), 1);
					// 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
					int skuStatus = NumberUtils.toInt(String.valueOf(jsonObject.get("skuStatus")), 4);
					Map<String, Object> item = new HashMap<>();
					item.put("itemId", drtMallItem.getId());
					item.put("exchangeRate", drtMallItem.getExchangeRate());
					item.put("skuName", String.valueOf(jsonObject.get("skuName")));
					item.put("companyId", String.valueOf(jsonObject.get("companyId")));
					item.put("companyName", String.valueOf(jsonObject.get("companyName")));
					item.put("storeCode", String.valueOf(jsonObject.get("storeCode")));
					item.put("storeName", String.valueOf(jsonObject.get("storeName")));
					item.put("vendorSkuId", String.valueOf(jsonObject.get("vendorSkuId")));
					item.put("skuBarCode", String.valueOf(jsonObject.get("skuBarCode")));
					item.put("skuAdword", String.valueOf(jsonObject.get("skuAdword")));
					item.put("purchaseCatId", String.valueOf(jsonObject.get("purchaseCatId")));
					item.put("purchaseCatName", String.valueOf(jsonObject.get("purchaseCatName")));
					item.put("brandId", String.valueOf(jsonObject.get("brandId")));
					item.put("brandName", String.valueOf(jsonObject.get("brandName")));
					item.put("skuStatus", skuStatus);
					item.put("skuEarnings", (long) (price * rate));
					item.put("detailDescContent",
							String.valueOf(jsonObject.get("detailDescContent")));
					item.put("packageList", String.valueOf(jsonObject.get("packageList")));
					item.put("skuType", String.valueOf(jsonObject.get("skuType")));
					item.put("isCustom", String.valueOf(jsonObject.get("isCustom")));
					item.put("minBuyQuantity",
							String.valueOf(jsonObject.get("minBuyQuantity")));
					item.put("storage", String.valueOf(jsonObject.get("storage")));
					item.put("weight", String.valueOf(jsonObject.get("weight")));
					item.put("volume", String.valueOf(jsonObject.get("volume")));
					item.put("stockUnit", String.valueOf(jsonObject.get("stockUnit")));
					// 商品SKU属性集合
					JSONArray skuPropArray = JSONArray.fromObject(jsonObject.get("skuPropList"));
					if(!CollectionUtils.isEmpty(skuPropArray)){
						List<Map<String, Object>> skuPropList = new ArrayList<>();
						for(int i=0; i<skuPropArray.size(); i++){
							JSONObject skuProp = skuPropArray.getJSONObject(i);
							if(skuProp != null){
								Map<String, Object> skuPropMap = new HashMap<String, Object>();
								skuPropMap.put("propGroupName", skuProp.get("propGroupName"));
								skuPropMap.put("propName", skuProp.get("propName"));
								skuPropMap.put("propValue", skuProp.get("propValue"));
								skuPropList.add(skuPropMap);
							}
						}
						item.put("skuPropList", skuPropList);
					}
					// 商品SKU介绍图片
					JSONArray imgArray = JSONArray.fromObject(jsonObject.get("imgList"));
					if(!CollectionUtils.isEmpty(imgArray)){
						List<Map<String, Object>> imgList = new ArrayList<>();
						for(int i=0; i<imgArray.size(); i++){
							JSONObject img = imgArray.getJSONObject(i);
							if(img != null){
								Map<String, Object> skuPropMap = new HashMap<String, Object>();
								skuPropMap.put("imgLocationSerNum", img.get("imgLocationSerNum"));
								skuPropMap.put("imgUrl", img.get("imgUrl"));
								skuPropMap.put("imgType", img.get("imgType"));
								imgList.add(skuPropMap);
							}
						}
						// 排序
						imgList = imgList.stream().sorted(ItemController::comparator).collect(Collectors.toList());
						item.put("imgList", imgList);
					}
					// 商品详情图片集合
					JSONArray detailDescImgArray = JSONArray.fromObject(jsonObject.get("detailDescImgList"));
					if(!CollectionUtils.isEmpty(detailDescImgArray)){
						List<Map<String, Object>> detailDescImgList = new ArrayList<>();
						for(int i=0; i<detailDescImgArray.size(); i++){
							JSONObject img = detailDescImgArray.getJSONObject(i);
							if(img != null){
								Map<String, Object> detailDescImgMap = new HashMap<String, Object>();
								detailDescImgMap.put("imgLocationSerNum", img.get("imgLocationSerNum"));
								detailDescImgMap.put("imgUrl", img.get("imgUrl"));
								detailDescImgMap.put("imgType", img.get("imgType"));
								detailDescImgList.add(detailDescImgMap);
							}
						}
						// 排序
						detailDescImgList = detailDescImgList.stream().sorted(ItemController::comparator).collect(Collectors.toList());
						item.put("detailDescImgList", detailDescImgList);
					}
					if(skuStatus != 3){
						try {
							// 将非上架商品同步至系统商品
							DrtMallItem mallItem = new DrtMallItem();
							mallItem.setId(itemId);
							mallItem.setItemState(skuStatus);
							drtMallItemService.updateIfNotNull(drtMallItem);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return ResultMapHelper.result(
							SystemConstants.RESULT_CODE_SUCCESS, "商品查询成功！", JSONObject.fromObject(item).toString());
				}
			}
		} catch (Exception e) {
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED,
				"商品查询失败！");
	}

	/**
	 * 获取商品列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("items")
	private Map<String, Object> items(HttpServletRequest request) {
		try {
			// 分页参数
			int pageNumber = NumberUtils.toInt(
					request.getParameter("pageNumber"), 1);
			int pageSize = NumberUtils.toInt(request.getParameter("pageSize"),
					10);
			// 0-综合排序  1-销量优先  2-积分降序 3-积分升序
			int sortType = NumberUtils.toInt(
					request.getParameter("sortType"), 0);
			// 关键字
			String keyword = request.getParameter("keyword");
			if(pageSize > 20){
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED,
						"每页显示数目不能超过20！");
			}
			// 排序方式
			String sortName = "sortNum asc";
			if(sortType == 1){
				sortName = "exchangeRate desc";
			}else if(sortType == 2){
				sortName = "skuEarnings desc";
			}else if(sortType == 3){
				sortName = "skuEarnings asc";
			}
			DrtMallItemQuery drtMallItemQuery = new DrtMallItemQuery();
			// 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
			drtMallItemQuery.setItemState(3);
			// 关键字
			if(StringUtils.isNotBlank(keyword)){
				drtMallItemQuery.setKeyword(keyword);
			}
			PageHelper.startPage(pageNumber, pageSize, false, false);
			PageHelper.orderBy(sortName);
			List<DrtMallItem> drtMallItemList = drtMallItemService
					.selectListByDrtMallItemQuery(drtMallItemQuery);
			if(CollectionUtils.isEmpty(drtMallItemList)){
				return ResultMapHelper.result(
						SystemConstants.RESULT_CODE_SUCCESS, "暂无数据！");
			}
			// 封装数据：key-商品编码skuCode
			Map<String, DrtMallItem> skuCodeMap = new LinkedHashMap<>();
			for (DrtMallItem drtMallItem : drtMallItemList) {
				skuCodeMap.put(drtMallItem.getSkuCode(), drtMallItem);
			}
			Set<String> skuCodeSet = skuCodeMap.keySet();
			String skuCodes = StringUtils.join(
					skuCodeSet.toArray(new String[skuCodeSet.size()]), ",");
			LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
			paramMap.put("skuCode", skuCodes);
			JSONObject jsonObject = electronicMallApiUtil.postJson(
					ElectronicMallApiUrlEnum.GET_SKU_INFO_LIST, paramMap);
			// 执行成功
			if ("Y".equals(jsonObject.get("success"))
					&& ErrorEnum.NORMAL.getResultCode().equals(
							jsonObject.get("resultCode"))) {
				JSONArray skuList = JSONArray.fromObject(jsonObject
						.get("skuList"));
				if (!CollectionUtils.isEmpty(skuList)) {
					// 获取积分商城配置项
					Map<String, String> drtShopConfigMap = drtShopConfigService
							.getDrtShopConfigMap();
					// 比例：元-->积分
					int rate = NumberUtils.toInt(drtShopConfigMap.get("RATE"),
							1);
					// 封装数据
					List<Map<String, Object>> items = new ArrayList<>();
					Map<String, Object> item = null;
					for (int i = 0, size = skuList.size(); i < size; i++) {
						JSONObject skuInfo = skuList.getJSONObject(i);
						String skuCode = String.valueOf(skuInfo.get("skuCode"));
						if (skuCodeMap.get(skuCode) != null) {
							// 单位：元
							double price = NumberUtils.toDouble(
									String.valueOf(skuInfo.get("price")), 0);
							item = new HashMap<>();
							item.put("itemId", skuCodeMap.get(skuCode).getId());
							item.put("exchangeRate", skuCodeMap.get(skuCode)
									.getExchangeRate());
							item.put("skuName", skuInfo.get("skuName"));
							item.put("imgUrl", skuInfo.get("imgUrl"));
							item.put("skuEarnings",
									String.valueOf((long) (price * rate)));
							items.add(item);
						}
					}
					return ResultMapHelper.result(
							SystemConstants.RESULT_CODE_SUCCESS, "商品查询成功！",
							items);
				}
			}
		} catch (Exception e) {
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED,
				"商品查询失败！");
	}
	
	public static int comparator(Map<String, Object> map1, Map<String, Object> map2) {
        if (map1 == null && map2 == null)
            return 0;

        if (map1 == null || map2 == null) {
            throw new NullPointerException();
        }

        int imgLocationSerNum1 = NumberUtils.toInt(String.valueOf(map1.get("imgLocationSerNum")), 5);
        int imgLocationSerNum2 = NumberUtils.toInt(String.valueOf(map2.get("imgLocationSerNum")), 5);

        return imgLocationSerNum1 - imgLocationSerNum2;
    }

}
