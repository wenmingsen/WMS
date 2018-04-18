package com.csg.intshop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.csg.intshop.entity.DrtShopItem;
import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.service.DrtShopItemService;


/**
 * DrtShopItem 积分商城商品表
 *
 * @author  徐辰
 * @since   1.8
 * @version 2017年12月18日 徐辰  
 */
@RestController
@RequestMapping("/ins/drtShopItem") 
public class DrtShopItemController{

	/**日志*/
	private Logger log = LoggerFactory.getLogger(DrtShopItemController.class);
	
	/** drtShopItemService 积分商城商品表 */
	@Autowired
	private DrtShopItemService drtShopItemService;

	@RequestMapping(value="/queryshop",method = RequestMethod.POST)
	@ResponseBody
	public List<DrtShopItem>  toMallIndex(HttpServletRequest request){
		try {
			DrtShopItem drtShopItem=new DrtShopItem();
			drtShopItem.setItemState(SystemConstants.ZERO);
			List<DrtShopItem> shopPages = drtShopItemService.seleItems(drtShopItem);
			return shopPages;
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			log.info("获取商品数据出错："+e);
			e.printStackTrace();
		}
		return  null;
	}
	
	/**
	 * 查询商品详情
	 * @param id 商品id
	 * @return 结果
	 */
	@RequestMapping(value = "/queryOrderGoods",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryOrderGoods(String id){
		Map<String,Object> objMap = drtShopItemService.selectByPrimaryKey(id);
		return objMap;
	}
}
	

