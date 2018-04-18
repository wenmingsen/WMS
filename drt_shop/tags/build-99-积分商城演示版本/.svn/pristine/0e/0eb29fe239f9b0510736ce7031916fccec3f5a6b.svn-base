/**
 * 
 */
package com.csg.intshop.controller.mall;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ElectronicMallApiUrlEnum;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtMallOrderService;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.intshop.util.StringUtils;
import com.github.pagehelper.PageHelper;

/**
 * 确认收货接口
 * 
 * @author xuchen
 * 
 */
@RestController
@RequestMapping("/ins/api/orderUpdate")
public class DrtShopReceiptConfrimController {
	
	/** DrtMallOrderService 积分商城订单表 */
	@Autowired
	private DrtMallOrderService drtMallOrderService;
	
	@Autowired
	private ElectronicMallApiUtil electronicMallApiUtil;
	
	/**
	 * 确认收货接口
	 * @param request
	 * @author xuchen
	 * @return map
	 * 		   success 状态码（Y;N）
	 * 		   resultMessage 异常信息提示
	 * 		   resultCode 异常代码KEY（00：正常；01：token失效；02：非空项为空；03：网络异常；04：数据超长；05：响应超时；06：业务异常；07：ID不存在；99：其他）
	 * @throws Exception 
	 */
	@RequestMapping("/synReceiptConfrim")
	public Map<String,Object> synReceiptConfrim(HttpServletRequest request) throws Exception{
		//积分商城订单编号
		String integralOrderCode = request.getParameter("integralOrderCode");
		//参数校验
		if(StringUtils.isEmpty(integralOrderCode)){//积分商城订单编号为空
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_ORDERCODE_NULL, "");				
		}
		if(integralOrderCode.length() > 36){//积分商城订单编号超长
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_ORDERCODE_OVERLENGTH, "");				
		}
		//查询积分商城订单编号
		DrtMallOrder drtMallOrder = new DrtMallOrder();
		drtMallOrder.setOrderCode(integralOrderCode);
		PageHelper.startPage(1, 1);
		List<DrtMallOrder> drtMallOrderLst = drtMallOrderService.selectList(drtMallOrder);
		if(drtMallOrderLst == null || drtMallOrderLst.size() == 0){//积分商城订单编号不存在
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.ERROR_MSG_ORDERCODE_NOT_EXIST, "");	
		}
		//确认收货接口
		LinkedHashMap<String, String> paramMap = new LinkedHashMap<String, String>();
		paramMap.put("integralOrderCode", integralOrderCode);
		JSONObject jsonObject = electronicMallApiUtil.postJson(ElectronicMallApiUrlEnum.SYN_RECEIPT_CONFRIM, paramMap);
		if("Y".equals(String.valueOf(jsonObject.get("success")))){
			//更新本地订单
			drtMallOrder = drtMallOrderLst.get(0);
			drtMallOrder.setOrderState(4);
			drtMallOrderService.update(drtMallOrder);
		}else{
			return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, SystemConstants.RESULT_CODE_FAILED, "");
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, String.valueOf(jsonObject.get("resultMessage")), "");
	}
}
