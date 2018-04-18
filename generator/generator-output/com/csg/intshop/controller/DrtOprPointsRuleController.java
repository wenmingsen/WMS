package com.csg.intshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.service.DrtOprPointsRuleService;


/**
 * DrtOprPointsRule 积分规则
 *
 * @author  曾令鹏
 * @since   1.8
 * @version 2018年02月06日 曾令鹏
 */
@RestController
@RequestMapping("/api/v1/drtOprPointsRule/*")
public class DrtOprPointsRuleController{

	/** drtOprPointsRuleService 积分规则 */
	@Autowired
	private DrtOprPointsRuleService drtOprPointsRuleService;

	
}
	

