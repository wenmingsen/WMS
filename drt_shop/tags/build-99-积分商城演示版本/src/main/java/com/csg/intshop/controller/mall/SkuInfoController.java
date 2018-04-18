
package com.csg.intshop.controller.mall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.entity.DrtItemDTO;
import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallItemService;
import com.csg.intshop.service.DrtMallOrderService;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.HttpUtil;
import com.csg.intshop.util.ResultMapHelper;
import com.github.pagehelper.StringUtil;

/**
 * DrtShopItem 商品信息
 *
 * @author 惠新宇
 * @since 1.8
 * @version 2017年12月18日 惠新宇
 */
@RestController
@RequestMapping("/ins/drtShop/interface")
public class SkuInfoController {
    
    public static final Logger LOG = LoggerFactory.getLogger(SkuInfoController.class);
    
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
    @Autowired
    private DrtMallOrderService drtMallOrderService;
    
    @Autowired
    private DrtMallItemService drtMallItemService;
    
    @Autowired
    private ElectronicMallApiUtil electronicMallApiUtil;
    
    /**
	 * 
	 */
    /**
     * 查询商品信息
     * 
     * @param request
     * @return 结果
     */
    @RequestMapping(value = "/orderInfoList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> orderInfoList(HttpServletRequest request) {
        // 获取参数
        String orderJson = request.getParameter("orderJson");
        Map<String, String> itemMap = new HashMap<String, String>();
        JSONArray itemsJson = JSONArray.fromObject(orderJson);
        Map<String, Object> resultMap = new HashMap<String, Object>();// 返回值
        // 1 同本地商品池进行比较
        String skuCode = "";
        JSONObject postJson = null;
        for (int i = 0; i < itemsJson.size(); i++) {
            JSONObject jsonObject = itemsJson.getJSONObject(i);
            String itemId = jsonObject.getString("itemId");
            String itemNum = jsonObject.getString("itemNum");
            DrtMallItem drtMallItem = drtMallItemService.selectByPrimaryKey(itemId);// 获取商品sku编号
            if (drtMallItem != null && StringUtil.isNotEmpty(drtMallItem.getSkuCode())) {
                if (skuCode == "") {
                    skuCode = drtMallItem.getSkuCode();
                    // 临时变量，存放商品code 和数量
                    itemMap.put(skuCode, itemNum);
                } else {
                    skuCode = skuCode + "," + drtMallItem.getSkuCode();
                }
            }
        }
        try {
            // 调用远程商品池信息
            LinkedHashMap<String, String> paramMap = new LinkedHashMap<String, String>();
            paramMap.put("skuCode", skuCode);
            postJson = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.GET_SKU_INFO_LIST, paramMap);
            if (postJson != null) {// 查询成功
                if (String.valueOf(postJson.get("success")) != null) {
                    if (postJson.get("success").toString().equals("Y")) {// 有数据
                        JSONArray jsonArray = postJson.getJSONArray("skuList");
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                            if (String.valueOf(postJson.get("skuCode")) != null) {
                                // 添加需要购买的商品的数量
                                jsonObject.put("itemNum", itemMap.get(postJson.get("skuCode")));
                            }
                        }
                        // 转换 JSONArray-->List
                        List<DrtItemDTO> itemLst = JSONArray.toList(jsonArray, new DrtItemDTO(), new JsonConfig());// 封装为list
                        // 更新远程下架的商品到本地数据库
                        for (DrtItemDTO drtItemDTO : itemLst) {
                            String state = drtItemDTO.getSkuStatus();
                            if (!("3".equals(state))) {// 为非上架--更新本地数据商品状态
                                DrtMallItem drtMallItem = new DrtMallItem();
                                drtMallItem.setSkuCode(drtItemDTO.getSkuCode());
                                drtMallItem.setSkuCode(state);
                                drtMallItemService.updateBySkuCode(drtMallItem);
                                itemLst.remove(drtItemDTO);// 将更新后的数据从集合中移除，不做为数据返回
                            }
                        }
                        // 分组保存
                        Map<String, List<DrtItemDTO>> resultData = itemLst.stream().collect(
                            Collectors.groupingBy(DrtItemDTO::getStoreCode));
                        resultMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "查询成功", resultData);
                    } else {// 商品全部下架
                        resultMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "查询成功", "商品全部下架");
                    }
                    
                }
            }
        } catch (Exception e) {
            resultMap = ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "商品查询失败", "");
            e.printStackTrace();
            LOG.error("获取商品信息接口", e);
        }
        return resultMap;
    }
    
    /**
     * 查询订单物流信息
     * 
     * @param integralOrderCode 订单编号
     * @return 订单物流信息
     */
    @ResponseBody
    public JSONObject getOrderWaybillDetail(String integralOrderCode) {
        try {
            if (StringUtil.isNotEmpty(integralOrderCode)) { // 订单编号不能为空
                DrtMallOrder drtMallOrder = new DrtMallOrder();
                drtMallOrder.setOrderCode(integralOrderCode);
                List<DrtMallOrder> drtMallOrderList = drtMallOrderService.selectList(drtMallOrder); // 根据订单编号查询数据
                if (drtMallOrderList != null && drtMallOrderList.size() > 0) { // 如果数据不为空就查询订单物流信息
                    Map<String, String> paramMap = new HashMap<>();
                    paramMap.put("integralOrderCode", integralOrderCode); // 订单编号
                    JSONObject postJson = HttpUtil.build().postJson(
                        ElectronicMallApiUtil.BASE_URL + ElectronicMallApiUrlEnum.GET_ORDER_WAYBILL_DETAIL.getValue(),
                        paramMap);
                    return postJson; // 返回订单物流信息
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("查询订单物流信息", e);
        }
        return null;
    }
    
    /**
     * 南网商城商品信息的商品状态与电融通积分商城商品池的商品状态是否一致，如果不一致则更新电融通积分商城商品池的商品状态
     * 
     * @param skuList 南网商城商品信息
     */
    public void interfaceGetSkuInfoList(List<JSONObject> skuList) {
        if (skuList != null && skuList.size() > 0) {
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            List<String> skuCodeList = new ArrayList<String>();
            for (JSONObject jsonObject : skuList) {
                String skuCode = String.valueOf(jsonObject.get("skuCode"));
                String skuStatus = String.valueOf(jsonObject.get("skuStatus"));
                if (StringUtil.isNotEmpty(skuCode)) {
                    skuCodeList.add(skuCode); // 南网商城商品SKU编号，用于查询电融通积分商城商品池对应的商品SKU编号数据
                    jsonMap.put("jsonSkuCode", skuCode); // 南网商城商品SKU编号
                    jsonMap.put("jsonSkuStatus", skuStatus); // 南网商城商品状态
                }
            }
            if (skuCodeList.size() > 0) { // 南网商城商品SKU编号数据不为空
                jsonMap.put("skuCodeList", skuCodeList);
                List<DrtMallItem> drtMallItemList = drtMallItemService.selectListByinterface(jsonMap); // 根据南网商城商品SKU编号集合查询数据
                if (drtMallItemList != null && drtMallItemList.size() > 0) { // 电融通积分商城商品池对应的商品SKU编号数据不为空
                    Iterator<DrtMallItem> drtMallItemIterator = drtMallItemList.iterator();
                    while (drtMallItemIterator.hasNext()) { // 遍历电融通积分商城商品池对应的商品SKU编号数据
                        DrtMallItem drtMallItem = drtMallItemIterator.next();
                        String skuCode = drtMallItem.getSkuCode(); // 电融通积分商城商品池的商品SKU编号
                        if (jsonMap.get(skuCode) != null) { // 南网商城商品SKU编号
                            Integer itemState = drtMallItem.getItemState(); // 电融通积分商城商品池的商品状态
                            Integer jsonSkuStatus = Integer.valueOf(String.valueOf(jsonMap.get(skuCode)).trim());// 南网商城商品状态
                            if (StringUtil.isNotEmpty(skuCode) // 如果南网商城商品SKU编号不为空
                                && jsonSkuStatus != null // 并且南网商城商品状态不为空
                                && itemState != null // 并且电融通积分商城商品池的商品状态不为空
                                && jsonSkuStatus != itemState) { // 并且南网商城商品状态与电融通积分商城商品池的商品状态不一致
                                drtMallItem.setItemState(jsonSkuStatus);
                                continue;
                            }
                        }
                        drtMallItemIterator.remove();
                    }
                    drtMallItemService.updateBatchByinterface(drtMallItemList);
                }
            }
        }
    }
}
