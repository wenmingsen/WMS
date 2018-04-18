package com.csg.intshop.controller.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.common.enums.ErrorEnum;
import com.csg.intshop.entity.DrtMallApplication;
import com.csg.intshop.entity.DrtMallOrder;
import com.csg.intshop.service.DrtMallApplicationService;
import com.csg.intshop.service.DrtMallOrderService;
import com.csg.intshop.util.ElectronicMallApiUtil;
import com.csg.intshop.util.HttpUtil;
import com.csg.intshop.util.HttpUtil.JsonResult;
import com.csg.intshop.util.Md5Utils;
import com.csg.intshop.util.StringUtils;
import com.csg.intshop.util.UUIDUtil;
import com.github.pagehelper.PageHelper;

/**
 * 积分商城对南网电子商城提供接口
 *
 */
@RestController
@RequestMapping("/ins/api/*")
public class MallRemoteController {
	
	public static final Logger LOG = LoggerFactory.getLogger(MallRemoteController.class);
	
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat SDFyyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	private DrtMallApplicationService drtMallApplicationService;
	
	/** DrtMallOrderService 积分商城订单表 */
	@Autowired
	private DrtMallOrderService drtMallOrderService;
	
	@Autowired
	private ElectronicMallApiUtil electronicMallApiUtil;

	
	/**
	 * 南网商城获取积分商城token
	 * @param username 用户名
	 * @param password 密码
	 * @param requestTime 请求时间
	 * @param authSign 认证签名
	 */
	@RequestMapping(value = {"getToken", "mall/remote/getToken"})
	public ModelMap getToken(@RequestBody String strToken){
		ModelMap model = new ModelMap();
		model.put("token", "");
		model.put("currTime", "");
		model.put("expire", "");
		model.put("originaToken", "");
		if(StringUtils.isEmpty(strToken)){
			model.put("statusCode", 2);																			   //认证签名不一致
			return model;
		}
		JSONObject jsonObject = JSONObject.fromObject(strToken);
		String username = String.valueOf(jsonObject.get("username"));
		String password = String.valueOf(jsonObject.get("password"));
		String requestTime = String.valueOf(jsonObject.get("requestTime"));
		String authSign = String.valueOf(jsonObject.get("authSign"));
		try{
			Date currentTime = new Date();
			String md5authSign = Md5Utils.encodeMd5ToLowerCase(username+password+requestTime+password);				   //根据南网商城传过来的用户名，密码，请求时间生成认证签名
			if(!md5authSign.equals(authSign)){																		   //判断认证签名是否一致
				model.put("statusCode", 2);																			   //认证签名不一致
				return model;
			}
			DrtMallApplication drtMallApplication = new DrtMallApplication();
			drtMallApplication.setUsername(username);																   //南网商城传过来的用户名
			drtMallApplication.setPassword(password);																   //南网商城传过来的密码
			List<DrtMallApplication> drtMallApplicationList = drtMallApplicationService.selectList(drtMallApplication);//根据用户名密码查询积分商城应用接入表数据
			if(!CollectionUtils.isEmpty(drtMallApplicationList)){										   //判断积分商城应用接入表是否查的到数据
				DrtMallApplication dma = drtMallApplicationList.get(0);												   //用户名唯一所以如果有数据则认为只有一条，取第一个
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(currentTime);																		   //当前时间
				calendar.add(Calendar.SECOND, dma.getExpire()==null?0:dma.getExpire());								   //超时时间，单位秒
				
				String token = String.valueOf(UUIDUtil.generateUUID());													   //生成token
				dma.setOriginaToken(dma.getToken());
				dma.setToken(token);
				dma.setCurrTime(Long.valueOf(SDFyyyyMMddHHmmss.format(currentTime)));
				dma.setInvalidTime(Long.valueOf(SDFyyyyMMddHHmmss.format(calendar.getTime())));
				dma.setLastModifierTime(Long.valueOf(SDFyyyyMMddHHmmss.format(currentTime)));
				drtMallApplicationService.update(dma);
				
				model.put("statusCode", 1);
				model.put("token", token);
				model.put("currTime", SDF.format(currentTime));
				model.put("expire", dma.getExpire());
				model.put("originaToken", dma.getOriginaToken());
			}else{
				model.put("statusCode", 3);																			   //账户认证失败（用户或密码不正确）。
			}
		}catch(Exception e){
			//服务器异常。
			model.put("statusCode", 5);
			e.printStackTrace();
			LOG.error("南网商城获取积分商城token",e);
		}
		return model;
	}
		
	/**
	 * 同步订单状态接口
	 * @param request
	 * @author xuchen
	 * @return map
	 * 		   success 状态码（Y;N）
	 * 		   resultMessage 异常信息提示
	 * 		   resultCode 异常代码KEY（00：正常；01：token失效；02：非空项为空；03：网络异常；04：数据超长；05：响应超时；06：业务异常；07：ID不存在；99：其他）
	 * @throws Exception 
	 */
	@RequestMapping(value = {"synOrderStatus", "mall/remote/synOrderStatus"})
	public JsonResult synOrderStatus(@RequestBody String strOrderJson) throws Exception{
		JSONObject jsonObject = JSONObject.fromObject(strOrderJson);
		// token
		String token = String.valueOf(jsonObject.get("token"));
		// 请求时间
		String requestTime = String.valueOf(jsonObject.get("timestamp"));
		//积分商城订单编号
		String integralOrderCode = String.valueOf(jsonObject.get("integralOrderCode"));
		//订单状态(3=已发货、4=已签收、5=已收货)
		String orderStatus = String.valueOf(jsonObject.get("orderStatus"));
		// 签名
		String authSign = String.valueOf(jsonObject.get("authSign"));
		//参数校验
		if(StringUtils.isEmpty(token) || StringUtils.isEmpty(authSign) || StringUtils.isEmpty(requestTime) || StringUtils.isEmpty(orderStatus)||StringUtils.isEmpty(integralOrderCode)){
			//非空项为空
			return HttpUtil.JsonResult.build(ErrorEnum.REQUIRED);
		}
		if(integralOrderCode.length() > 36||orderStatus.length() > 4){
			//字段超长
			return HttpUtil.JsonResult.build(ErrorEnum.OVERLONG_DATA_EXCEPTION);
		}
		// 3=已发货、4=已签收、5=已收货
		if((!"3".equals(orderStatus))&&(!"4".equals(orderStatus))&&(!"5".equals(orderStatus))){
			//订单状态错误
			return HttpUtil.JsonResult.build(ErrorEnum.BUSINESS_EXCEPTION);
		}
		// 校验签名
		LinkedHashMap<String, Object> paramMap = new LinkedHashMap<>();
		paramMap.put("integralOrderCode", integralOrderCode);
		paramMap.put("orderStatus", orderStatus);
		paramMap.put("timestamp", requestTime);
		paramMap.put("token", token);
		String sign = ElectronicMallApiUtil.sign(paramMap);
		if(!authSign.equals(sign)){
			return HttpUtil.JsonResult.build(ErrorEnum.BUSINESS_EXCEPTION);
		}
		// 校验token是否正确
		boolean checkToken = electronicMallApiUtil.checkToken(token, requestTime);
		if(!checkToken){
			return HttpUtil.JsonResult.build(ErrorEnum.TOKEN_INVALID);
		}
		//查询积分商城订单编号
		DrtMallOrder drtMallOrder = new DrtMallOrder();
		drtMallOrder.setOrderCode(integralOrderCode);
		PageHelper.startPage(1, 1);
		List<DrtMallOrder> drtMallOrderLst = drtMallOrderService.selectList(drtMallOrder);
		if(drtMallOrderLst == null || drtMallOrderLst.size() == 0){
			//积分商城订单编号不存在
			return HttpUtil.JsonResult.build(ErrorEnum.ID_NOT_EXIST_EXCEPTION);
		}
		drtMallOrder = drtMallOrderLst.get(0);
		if("4".equals(orderStatus)){
			// 不做处理
			if("2".equals(drtMallOrder.getOrderState())){
				return HttpUtil.JsonResult.build(ErrorEnum.BUSINESS_EXCEPTION);
			}
		}else if("5".equals(orderStatus)){
			// 若订单原状态：已发货
			if("3".equals(drtMallOrder.getOrderState())){
				// 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
				drtMallOrder.setOrderState(4);
				drtMallOrderService.update(drtMallOrder);
			}
		}else{
			// 订单状态：1-待付款2-待发货3-已发货4-已完成5-取消订单
			drtMallOrder.setOrderState(3);
			drtMallOrderService.update(drtMallOrder);
		}
		return HttpUtil.JsonResult.build("Y", ErrorEnum.NORMAL);
	}
	
	
}
