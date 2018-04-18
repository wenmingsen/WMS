package com.csg.intshop.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.csg.intshop.entity.DrtShopAddress;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtAdministrativeRegionService;
import com.csg.personcenter.entity.mybatis.DrtAccount;
/**
 * 
 * @author liulei3
 *查询省城市街道等信息Controller
 */
@Controller
@RequestMapping("/ins/DAR")
public class DrtAdministrativeRegionController {
	/**注入DrtAdministrativeRegionService*/
	@Resource
	private  DrtAdministrativeRegionService drtAdministrativeRegionService;
	
	@RequestMapping("/queryPCT")
	@ResponseBody
	public Map<String,Object> queryPCT(DrtShopAddress drtShopAddress,HttpServletRequest request){
		DrtAccount drtAccount = (DrtAccount)request.getSession().getAttribute(SystemConstants.LOGIN_SIGN);
		String accountId = drtAccount.getAccountId();
		drtShopAddress.setAccountId(accountId);
		Map<String,Object> objMap = drtAdministrativeRegionService.selectList(drtShopAddress);
		return objMap;
	}
}
