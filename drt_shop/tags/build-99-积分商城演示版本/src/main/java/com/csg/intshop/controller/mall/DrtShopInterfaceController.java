package com.csg.intshop.controller.mall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.entity.DrtMallItem;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.entity.DrtMallOrderDetail;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallApplicationService;
import com.csg.intshop.service.DrtMallItemService;
import com.csg.intshop.service.DrtMallOrderDetailService;
import com.csg.intshop.service.DrtMallOrderService;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.ResultMapHelper;
import com.github.pagehelper.StringUtil;


/**
 * DrtShopInterfaceController 接口
 *
 */
@RestController
@RequestMapping("/ins/drtShop/interface")
public class DrtShopInterfaceController{

	public static final Logger LOG = LoggerFactory.getLogger(DrtShopInterfaceController.class);
	
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat SDFyyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static final String RESULT_CODE_SUCCESS = "0";
	
	public static final String RESULT_CODE_FAILED = "-1";
	
	@Autowired
	private DrtMallOrderService drtMallOrderService;
	
	@Autowired
	private DrtMallOrderDetailService drtMallOrderDetailService;
	
	@Autowired
	private DrtMallItemService drtMallItemService;
	
	@Autowired
	private DrtMallApplicationService drtMallApplicationService;
	
	/**
	 * 获取商品信息接口
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/{url}",method = RequestMethod.POST)
	public String getSku(@PathVariable("url")String url,String id){
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("data", "");
		jsonobject.put("code", RESULT_CODE_FAILED);
		jsonobject.put("msg", "操作失败！");
		try{
			if(StringUtil.isNotEmpty(id)){																	//id不能为空
				DrtMallItem drtMallItem = drtMallItemService.selectByPrimaryKey(id);						//根据id查询积分商城商品池
				if(drtMallItem!=null&&StringUtil.isNotEmpty(drtMallItem.getSkuCode())){						//如果积分商城商品池有数据
					jsonobject.put("drtMallItem", drtMallItem);
					LinkedHashMap<String, String> paramMap = new LinkedHashMap<String,String>();
					paramMap.put("skuCode", drtMallItem.getSkuCode());										//获取积分商城商品池的商品SKU编号
					ElectronicMallApiUtil electronicMallApiUtil = new ElectronicMallApiUtil();
					JSONObject postJson = null;
					if("getSkuInfoList".equals(url)){														//调用南网商城获取积分商品池商品信息
						postJson = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.GET_SKU_INFO_LIST, paramMap);
					}else if("getSkuDetailInfo".equals(url)){												//调用南网商城获取商品详情信息
						postJson = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.GET_SKU_DETAIL_INFO, paramMap);
					}
					if(postJson!=null){																		//返回的积分商品池商品信息不为空
						jsonobject.put("data", postJson);
						String success = String.valueOf(postJson.get("success"));
						String resultMessage = String.valueOf(postJson.get("resultMessage"));
						String resultCode = String.valueOf(postJson.get("resultCode"));
						if("Y".equals(success)&&"00".equals(resultCode)){									//积分商品池商品信息返回数据标识正常
							if("getSkuInfoList".equals(url)){												//南网商城获取积分商品池商品信息
								List<JSONObject> skuList = (List<JSONObject>) postJson.get("skuList");
								interfaceGetSkuInfoList(skuList);											//南网商城商品信息的商品状态与电融通积分商城商品池的商品状态是否一致，
								jsonobject.put("code", RESULT_CODE_SUCCESS);
								jsonobject.put("msg", "执行成功！");
							}else if("getSkuDetailInfo".equals(url)){										//南网商城获取商品详情信息
								List<JSONObject> skuList = Arrays.asList(postJson);
								interfaceGetSkuInfoList(skuList);												//如果不一致则更新电融通积分商城商品池的商品状态
								jsonobject.put("code", RESULT_CODE_SUCCESS);
								jsonobject.put("msg", "执行成功！");
							}
						}else{																				//积分商品池商品信息返回数据标识不正常就抛出异常
							throw new Exception("resultCode:"+resultCode+",resultMessage:"+resultMessage);
						}
					}
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("获取商品信息接口",e);
		}
		return jsonobject.toString();
	}
	
	/**
	 * 查询订单物流信息
	 * @param integralOrderCode 订单编号
	 * @return 订单物流信息
	 */
	@RequestMapping(value = "/getOrderWaybillDetail",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getOrderWaybillDetail(String integralOrderCode){
		try{
			if(StringUtil.isNotEmpty(integralOrderCode)){										    		   //订单编号不能为空
				DrtMallOrder drtMallOrder = new DrtMallOrder();
				drtMallOrder.setOrderCode(integralOrderCode);
				List<DrtMallOrder> drtMallOrderList = drtMallOrderService.selectListByinterface(drtMallOrder); //根据订单编号查询数据
				if(drtMallOrderList!=null&&drtMallOrderList.size()>0){							    		   //如果数据不为空就查询订单物流信息
					LinkedHashMap<String, String> paramMap = new LinkedHashMap<String,String>();
					paramMap.put("integralOrderCode", integralOrderCode);						    		   //订单编号
					ElectronicMallApiUtil electronicMallApiUtil = new ElectronicMallApiUtil();
					JSONObject postJson = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.GET_ORDER_WAYBILL_DETAIL, paramMap);
					DrtMallOrderDetail drtMallOrderDetail = new DrtMallOrderDetail();
					drtMallOrderDetail.setOrderCode(integralOrderCode);;
					List<DrtMallOrderDetail>  drtMallOrderDetailList = drtMallOrderDetailService.selectList(drtMallOrderDetail);
					JSONObject jsonobject = new JSONObject();
					jsonobject.put("postJson", postJson);
					jsonobject.put("drtMallOrderDetailList", drtMallOrderDetailList);
					return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "执行成功！", jsonobject==null?"":jsonobject.toString());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("查询订单物流信息",e);
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "执行失败！", "");
	}
	
	
	/**
	 * 南网商城商品信息的商品状态与电融通积分商城商品池的商品状态是否一致，如果不一致则更新电融通积分商城商品池的商品状态
	 * @param skuList 南网商城商品信息
	 */
	public void interfaceGetSkuInfoList(List<JSONObject> skuList){
		if(skuList!=null&&skuList.size()>0){
			Map<String,Object> jsonMap = new HashMap<String,Object>();
			List<String> skuCodeList = new ArrayList<String>();
			Iterator<JSONObject> skuListIterator = skuList.iterator();
			while(skuListIterator.hasNext()){
				JSONObject jsonObject = skuListIterator.next();
				String skuCode = String.valueOf(jsonObject.get("skuCode"));
				String skuStatus = String.valueOf(jsonObject.get("skuStatus"));
				if(StringUtil.isNotEmpty(skuCode)){
					skuCodeList.add(skuCode);																	 //南网商城商品SKU编号，用于查询电融通积分商城商品池对应的商品SKU编号数据
					jsonMap.put(skuCode, skuStatus);														 	 //南网商城商品SKU编号，南网商城商品状态
				}
				/*if("4".equals(skuStatus)){
					skuListIterator.remove();
				}*/
			}
			if(skuCodeList.size()>0){																			 //南网商城商品SKU编号数据不为空
				jsonMap.put("skuCodeList", skuCodeList);
				List<DrtMallItem> drtMallItemList = drtMallItemService.selectListByinterface(jsonMap);			 //根据南网商城商品SKU编号集合查询数据
				if(drtMallItemList!=null&&drtMallItemList.size()>0){								  			 //电融通积分商城商品池对应的商品SKU编号数据不为空
					Iterator<DrtMallItem> drtMallItemIterator = drtMallItemList.iterator();
					while (drtMallItemIterator.hasNext()) {											  			 //遍历电融通积分商城商品池对应的商品SKU编号数据
						DrtMallItem drtMallItem = drtMallItemIterator.next();
						String skuCode = drtMallItem.getSkuCode();									  			 //电融通积分商城商品池的商品SKU编号
						if(jsonMap.get(skuCode)!=null){												 			 //南网商城商品SKU编号
							Integer itemState = drtMallItem.getItemState();							  			 //电融通积分商城商品池的商品状态
							Integer jsonSkuStatus = Integer.valueOf(String.valueOf(jsonMap.get(skuCode)).trim());//南网商城商品状态
							if(StringUtil.isNotEmpty(skuCode)										  			 //如果南网商城商品SKU编号不为空
									&&jsonSkuStatus!=null											 	 		 //并且南网商城商品状态不为空
									&&itemState!=null												  			 //并且电融通积分商城商品池的商品状态不为空
									&&jsonSkuStatus!=itemState){									 			 //并且南网商城商品状态与电融通积分商城商品池的商品状态不一致
								drtMallItem.setItemState(jsonSkuStatus);
								continue;
							}
						}
						drtMallItemIterator.remove();
					}
					if(drtMallItemList.size()>0){
						drtMallItemService.updateBatchByinterface(drtMallItemList);
					}
				}
			}
		}
	}
}
	

