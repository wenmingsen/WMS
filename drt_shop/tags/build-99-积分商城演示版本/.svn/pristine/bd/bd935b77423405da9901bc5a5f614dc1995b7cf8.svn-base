$(function (){
	var param = "<c:out value='${param.paramName}'/>";
	//根路径
	var basePath = getRootPath();
	initDetail(basePath);
});

function initDetail(basePath){
	var url=basePath+"/ins/drtShop/interface/getOrderWaybillDetail";
	
	var integralOrderCode = "123abcde";
	var param = {
			integralOrderCode : integralOrderCode
	}
	var logisticsStatus = "已签收"
	var source = "顺丰快递";
	var waybillNumber = "888856874521488888";
	//仅有一条数据时，调用该html 
	var onlyTopHtml = ' <li class="timeline-top" style="margin-bottom: -10px;"><div class="tl-top" ></div>'
		+'<div class="tl-heading" >'
			 +'<p>您订单中的商品已成功匹配到西南仓系统，下一战西安转运中心</p>'
			 +'<span>2018-01-23 19:24:43</span></div> </li>'
	//多余一条数据时，调用如下该html
	var topHtml = ' <li class="timeline-top"><div class="tl-top"></div>'
						+'<div class="tl-heading" >'
							 +'<p>您订单中的商品已成功匹配到西南仓系统，下一战西安转运中心</p>'
							 +'<span>2018-01-23 19:24:43</span></div> </li>'
	var middleHtml = '<li class="timeline-inverted"><div class="tl-circ"></div>'
							      +'<div class="tl-middle">'
							      		+'<p> 浙江金华市东区公司 已收件</p>'
							      	 	+'<span>2018-01-22 23:24:43</span></div> </li>'
	var bottomHtml = '<li class="timeline-inverted" style="margin-bottom: -10px;">'
							      +'<div class="tl-circ"></div>'
							       +'<div class="tl-bottom" style="">'
							      		+'<p> 您的订单已受理</p>'
							      	 	+'<span>2018-01-22 10:24:43</span></div></li>'
	var html = topHtml+middleHtml+middleHtml+bottomHtml;
	
	$("#logisticsStatus").html(logisticsStatus);
	$("#source").html(source);
	$("#waybillNumber").html(waybillNumber);
	$("#logisticsDetail").html(html);
	var obj = [
		         {itemid :"abc123",itemNum:3},
		         {itemid :"def456",itemNum:2}
		       ] ;
	var orderJson = JSON.stringify(obj);
	param = {
			orderJson: orderJson
	}
	return;
	//获取物流信息
	$.ajax({
        type: "POST",
        url: basePath+"/ins/drtShop/interface/getOrderWaybillDetail",
        dataType:"json",
        data: param,
        success: function(resultData){
        	if(resultData.state!="0"){
        		layer.msg("物流信息获取失败!", {
        			icon : 0
        		});
        	}else{
        		//进行数据封装
        		console.log("success");
        	}      	 
        },
        error: function(resultData){
        	layer.msg("物流信息获取失败!", {
    			icon : 0
    		});
        }
    });
}