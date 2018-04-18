package com.csg.intshop.controller.mall;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.entity.DrtAccountVO;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtAccountService;
import com.csg.intshop.util.ResultMapHelper;
import com.csg.personcenter.entity.mybatis.DrtAccount;

/**
 * 个人信息
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年02月08日 曾令鹏
 */
@RestController
@RequestMapping("/ins/mall/me/*")
public class MeController {
	
	/** DrtAccountService 登录账户信息表（主要包括电融通账户、密码） */
	@Autowired
	private DrtAccountService drtAccountService;
	
	/**
	 * 获取用户信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("getMeInfo")
	public Map<String, Object> getMeInfo(HttpSession session){
		try{
			DrtAccount drtAccount = (DrtAccount)session.getAttribute(SystemConstants.LOGIN_SIGN);
			DrtAccountVO DrtAccountVO = drtAccountService.selectByPrimaryKey(drtAccount.getAccountId());
			if(DrtAccountVO != null){
				Map<String, Object> result = new HashMap<>();
				result.put("imgUrl", DrtAccountVO.getImgUrl());
				result.put("nickname", DrtAccountVO.getNickname());
				return ResultMapHelper.result(SystemConstants.RESULT_CODE_SUCCESS, "查询成功！", result);
			}
		}catch(Exception e){
		}
		return ResultMapHelper.result(SystemConstants.RESULT_CODE_FAILED, "查询失败！");
	}
	
}
