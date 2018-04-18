$(function (){
	//根路径
	var basePath = getRootPath();
	var orderId = getUrlParam("id");
	//test订单ID
	//orderId = "e938105bd4814518a5d702c603ab7c17";
	getOrderWaybillDetail(orderId, basePath);
});

//页面初始化数据
function initDetail(resultData, basePath){
	var orderState = resultData.orderState;
	   if(orderState == 1){//1-待付款2-待发货3-已发货4-已完成5-取消订单6-删除订单
		   $("#logisticsStatus").html("待付款");
	   }else if(orderState == 2){
		   $("#logisticsStatus").html("待发货");
	   }else if(orderState == 3){
		   $("#logisticsStatus").html("发货中");
	   }else if(orderState == 4){
		   $("#logisticsStatus").html("已签收");
	   }else if(orderState == 5){
		   $("#logisticsStatus").html("已取消");
	   }
	var source = resultData.logisticsProviderName;
	var waybillNumber = resultData.waybillId;
	var imgUrl = resultData.imgUrl;
	var html = "";
	var logisticsInfoList = resultData.logisticsInfoList;
	var topHtml = "" ;
	var middleHtml = "";
	var bottomHtml = "";
	if(logisticsInfoList != null && logisticsInfoList.length == 1){
		//仅有一条数据时，调用该html 
		var onlyTopHtml = ' <li class="timeline-top" style="margin-bottom: -10px;"><div class="tl-top" ></div>'
			+'<div class="tl-heading" >'
				 +'<p>'+logisticsInfoList[0].deliveryInfoProgress+'</p>'
				 +'<span>'+logisticsInfoList[0].deliveryTime+'</span></div> </li>';
		html = onlyTopHtml;
	}else if(logisticsInfoList != null && logisticsInfoList.length > 1){
		//多条数据时，调用如下该html
		for(var i = 0;i<logisticsInfoList.length;i++){
			if(i == logisticsInfoList.length-1){
				topHtml = ' <li class="timeline-top"><div class="tl-top"></div>'
									+'<div class="tl-heading" >'
										 +'<p>'+logisticsInfoList[i].deliveryInfoProgress+'</p>'
										 +'<span>'+logisticsInfoList[i].deliveryTime+'</span></div> </li>';
			}else if(i > 0 && i != logisticsInfoList.length-1){
				middleHtml += '<li class="timeline-inverted"><div class="tl-circ"></div>'
										      +'<div class="tl-middle">'
										      		+'<p>'+logisticsInfoList[i].deliveryInfoProgress+'</p>'
										      	 	+'<span>'+logisticsInfoList[i].deliveryTime+'</span></div> </li>';
			}else if(i == 0){
				bottomHtml = '<li class="timeline-inverted" style="margin-bottom: -10px;">'
										      +'<div class="tl-circ"></div>'
										       +'<div class="tl-bottom" style="">'
										      		+'<p>'+logisticsInfoList[i].deliveryInfoProgress+'</p>'
										      	 	+'<span>'+logisticsInfoList[i].deliveryTime+'</span></div></li>';
			}
		}
		html = topHtml+middleHtml+bottomHtml;
	}
	//图片路径
	$("#imgUrl").attr("src", imgUrl);
	//信息来源
	$("#source").html(source);
	//运单号
	$("#waybillNumber").html(waybillNumber);
	//物流跟踪信息
	$("#logisticsDetail").html(html);
}
//调用物流后台信息
function getOrderWaybillDetail(orderId, basePath){
	$.ajax({
        type: "POST",
        async : false,
        url: basePath+"/ins/mall/order/getOrderWaybillDetail",
        dataType:"json",
        data: {orderId: orderId},
        success: function(resultData){
        	/*if(resultData == "-1" || resultData.state != "0"){
        		layer.msg("物流信息获取失败！", {
        			icon : 0
        		});
        	}*/
        	if(resultData.state == "0"){
        		//进行数据封装
        		initDetail(resultData.data, basePath);
        	}      	 
        }/*,
        error: function(resultData){
        	layer.msg("物流信息获取失败！", {
    			icon : 0
    		});
        }*/
    });
}