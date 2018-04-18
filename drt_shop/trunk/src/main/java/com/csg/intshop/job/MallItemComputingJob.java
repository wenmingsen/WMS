package com.csg.intshop.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallItemService;
import com.csg.intshop.service.DrtShopConfigService;
import com.csg.intshop.util.DateTimeUtils;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.GetUUID;
import com.csg.intshop.util.ResultMapHelper;

/**
 * 积分商城获取南网商城商品池商品信息任务(被统计微服务调用)
 *
 * @author 温明森
 * @since JDK1.8
 * @version 2018年01月24日 温明森
 */
@RestController
@RequestMapping("/ins/api/interface")
public class MallItemComputingJob {

	// DrtMallItemService
	@Autowired
	DrtMallItemService drtMallItemService;

	// electronicMallApiUtil
	@Autowired
	ElectronicMallApiUtil electronicMallApiUtil;

	@Autowired
	private DrtShopConfigService drtShopConfigService;

	// 日志
	private Logger log = LoggerFactory.getLogger(MallItemComputingJob.class);

	// 状态码
	private final static String SUCCESS = "Y";

	/**
	 * 积分商城获取南网商城商品池商品信息
	 * 
	 */
	@RequestMapping("/getMallItem")
	public Map<String, Object> getMallItemComputingJob() {
		// 查询所有积分商品池信息map
		Map<String, DrtMallItem> drtMallItemMap = new HashMap<String, DrtMallItem>();
		// 待更新List
		List<DrtMallItem> lstUpdateDrtMallItem = new ArrayList<DrtMallItem>();
		// 待新增List
		List<DrtMallItem> lstInsertDrtMallItem = new ArrayList<DrtMallItem>();
		try {
			Map<String, String> drtShopConfigMap = drtShopConfigService
					.getDrtShopConfigMap();
			// HTTP请求参数
			LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
			// 积分商品池编号
			paramMap.put("poolNum", drtShopConfigMap.get("POOL_NUM"));
			// 商品数量(获取的商品数量最多三百)
			paramMap.put("skuNum", "300");
			for (int pageNum = 1; true; pageNum++) {
				// 页数
				paramMap.put("pageNum", pageNum + "");
				// 获取南网商城商品池商品信息
				JSONObject jsonObject = electronicMallApiUtil.postJson(
						ElectronicMallApiUrlEnum.GET_SKU_CODE, paramMap);
				if (SUCCESS.equals(String.valueOf(jsonObject.get("success")))
						&& ErrorEnum.NORMAL.getResultCode().equals(
								String.valueOf(jsonObject.get("resultCode")))
						&& StringUtils.isNotBlank(String.valueOf(jsonObject
								.getString("skuCode")))) {
					// strSkuCode商品SKU编号，以逗号隔开
					String strSkuCode = String.valueOf(jsonObject
							.getString("skuCode"));
					// 查询条件
					DrtMallItem queryDrtMallItem = new DrtMallItem();
					// 查询所有积分商品池信息
					List<DrtMallItem> lstMallItem = drtMallItemService
							.selectList(queryDrtMallItem);
					if (!CollectionUtils.isEmpty(lstMallItem)) {
						for (DrtMallItem objDrtMallItem : lstMallItem) {
							drtMallItemMap.put(objDrtMallItem.getSkuCode(),
									objDrtMallItem);
						}
					}
					// 拆分所有SKU编码
					String[] strSkuCodes = strSkuCode.split(",");

					// 获取电子商城商品的名称和价格
					Map<String, Map<String, Object>> items = this
							.getSkuInfoList(strSkuCodes);
					//
					if (strSkuCodes.length > 0) {
						for (int i = 0; i < strSkuCodes.length; i++) {
							if (!CollectionUtils.isEmpty(drtMallItemMap)
									&& drtMallItemMap
											.containsKey(strSkuCodes[i])) {// 更新商品状态为上架
								DrtMallItem objUpdate = drtMallItemMap
										.get(strSkuCodes[i]);
								// 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
								if (null != objUpdate.getItemState()) {
									objUpdate.setItemState(3);
									// 设置商品名称和价格
									if (!CollectionUtils.isEmpty(items)) {
										if (items.containsKey(strSkuCodes[i])) {
											objUpdate.setSkuName(items
													.get(strSkuCodes[i])
													.get("skuName").toString());
											// 比例：元-->积分
											int rate = NumberUtils.toInt(
													drtShopConfigMap
															.get("RATE"), 1);
											// 单位：元
											double price = NumberUtils
													.toDouble(
															String.valueOf(items
																	.get(strSkuCodes[i])
																	.get("skuPrice")),
															0);
											objUpdate
													.setSkuEarnings((long) (price * rate * 100));
										}
									}
									lstUpdateDrtMallItem.add(objUpdate);
								}
								drtMallItemMap.remove(strSkuCodes[i]);
							} else {// 积分商城池没有，则新增
								DrtMallItem objDrtMallItem = new DrtMallItem();
								// UID
								objDrtMallItem.setId(GetUUID.getUuuid());
								// 排序编号
								Random rand = new Random();
								// 设置一百内随机数
								int iSortNum = rand.nextInt(100);
								objDrtMallItem.setSortNum(iSortNum);
								// 商品SKU编号
								objDrtMallItem.setSkuCode(strSkuCodes[i]);
								// 设置商品名称和价格
								if (!CollectionUtils.isEmpty(items)) {
									if (items.containsKey(strSkuCodes[i])) {
										objDrtMallItem.setSkuName(items
												.get(strSkuCodes[i])
												.get("skuName").toString());
										// 比例：元-->积分
										int rate = NumberUtils
												.toInt(drtShopConfigMap
														.get("RATE"), 1);
										// 单位：元
										double price = NumberUtils.toDouble(
												String.valueOf(items.get(
														strSkuCodes[i]).get(
														"skuPrice")), 0);
										objDrtMallItem
												.setSkuEarnings((long) (price * rate * 100));
									}
								}
								// 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
								objDrtMallItem.setItemState(3);
								// 兑换量
								objDrtMallItem.setExchangeRate(0);
								// 定时无法获取登陆人信息
								objDrtMallItem.setCreatorName("");
								objDrtMallItem.setCreatorId("");
								objDrtMallItem.setLastModifierName("");
								objDrtMallItem.setLastModifierId("");
								// 0-是 1-否
								objDrtMallItem.setIsDelete(1);
								// 创建时间
								String strCreateTime = DateTimeUtils
										.converDateToString(new Date(),
												"yyyyMMddHHmmss");
								objDrtMallItem.setCreateTime(Long
										.valueOf(strCreateTime));
								// 修改时间
								String strLastModifierTime = DateTimeUtils
										.converDateToString(new Date(),
												"yyyyMMddHHmmss");
								objDrtMallItem.setLastModifierTime(Long
										.valueOf(strLastModifierTime));
								lstInsertDrtMallItem.add(objDrtMallItem);
							}
						}
					}
					if (!CollectionUtils.isEmpty(drtMallItemMap)) {// 所剩下的商品更新状态为下架
						Set<String> mapSkuCodes = drtMallItemMap.keySet();
						for (String skuCode : mapSkuCodes) {
							DrtMallItem objUpdate = drtMallItemMap.get(skuCode);
							// 商品状态：-1:商品已删除、3：已上架、4：已下架、6：店铺已停用，默认为3
							if (null != objUpdate.getItemState()) {
								objUpdate.setItemState(4);
								// 设置商品名称和价格
								if (!CollectionUtils.isEmpty(items)) {
									if (items.containsKey(skuCode)) {
										objUpdate.setSkuName(items.get(skuCode)
												.get("skuName").toString());
										// 比例：元-->积分
										int rate = NumberUtils
												.toInt(drtShopConfigMap
														.get("RATE"), 1);
										// 单位：元
										double price = NumberUtils.toDouble(
												String.valueOf(items.get(
														skuCode)
														.get("skuPrice")), 0);
										objUpdate
												.setSkuEarnings((long) (price * rate * 100));
									}
								}
								lstUpdateDrtMallItem.add(objUpdate);
							}
						}
					}
				} else {
					log.error("getMallItemComputingJob-{}积分商城获取南网商城商品池商品信息出错 resultCode："
							+ String.valueOf(jsonObject.get("resultCode")));
					break;
				}
			}
			// 同步积分商城商品池
			drtMallItemService.synMallItem(lstUpdateDrtMallItem,
					lstInsertDrtMallItem);
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS,
					"商品池商品信息成功!");
		} catch (Exception e) {
			log.error("getMallItemComputingJob-{}积分商城获取南网商城商品池商品信息异常", e);
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED,
					"商品池商品信息异常!");
		}
	}

	/**
	 * 获取SKU编码商品详情的价格和名称
	 * 
	 * @param drtShopConfigMap
	 *            积分商品池编号
	 * @param strSkuCode
	 *            SkuCodes
	 * @param items
	 *            商品信息集合
	 * @return List<Map<String, Object>>
	 */
	private Map<String, Map<String, Object>> getSkuInfoList(String[] strSkuCode) {
		// 用于封装返回值
		Map<String, Map<String, Object>> skuCodeMap = new HashMap<>();

		int totalPage = 0;
		int pageSize = 12;
		if (strSkuCode.length <= pageSize) {
			totalPage = 1;
		} else {
			totalPage = (strSkuCode.length + pageSize - 1) / pageSize;
		}
		int j = 0;
		for (int pageNum = 0; pageNum < totalPage; pageNum++) {
			Set<String> skuCodeSet = new HashSet<>();
			int maxPageSize = strSkuCode.length - pageNum * pageSize;
			pageSize = maxPageSize > pageSize ? pageSize : maxPageSize;
			for (; j < pageSize + pageNum * 12; j++) {
				skuCodeSet.add(strSkuCode[j]);
			}
			String skuCodes = StringUtils.join(
					skuCodeSet.toArray(new String[skuCodeSet.size()]), ",");
			LinkedHashMap<String, Object> itemsParamMap = new LinkedHashMap<>();
			itemsParamMap.put("skuCode", skuCodes);
			JSONObject itemsJsonObject = electronicMallApiUtil.postJson(
					ElectronicMallApiUrlEnum.GET_SKU_INFO_LIST, itemsParamMap);

			// 执行成功
			if ("Y".equals(itemsJsonObject.get("success"))
					&& ErrorEnum.NORMAL.getResultCode().equals(
							itemsJsonObject.get("resultCode"))) {
				JSONArray skuList = JSONArray.fromObject(itemsJsonObject
						.get("skuList"));
				if (!CollectionUtils.isEmpty(skuList)) {
					Map<String, Object> item = null;
					for (int i = 0, size = skuList.size(); i < size; i++) {
						JSONObject skuInfo = skuList.getJSONObject(i);
						String skuCode = String.valueOf(skuInfo.get("skuCode"));
						item = new HashMap<>();
						item.put("skuCode", skuCode);
						item.put("skuName", skuInfo.get("skuName"));
						item.put("skuPrice",
								String.valueOf(skuInfo.get("price")));
						skuCodeMap.put(skuCode, item);
					}
				}
			}
		}

		return skuCodeMap;
	}
}
