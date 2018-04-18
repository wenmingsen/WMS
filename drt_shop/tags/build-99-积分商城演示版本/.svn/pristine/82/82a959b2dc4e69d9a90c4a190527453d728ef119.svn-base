/**
 * 全局包名
 */
var app = {};
/**
 * app全局路径 jxbweixin/webRoot/  172.16.101.26/wx/ "http://172.16.101.239:88/";
 */
var app_base_url = "http://172.16.101.239:88/";

/**
 * 系统默认头像
 */
var default_head_img = app_base_url + "css/img/default_user.png";

/**
 * 后台全局URL地址 172.16.100.21
 */
var basepath = "http://172.16.100.123:8080/xdjxb/api/v2/";

/**
 * 积分url
 * @type
 */
var scoreurl="http://172.16.100.123/xdjxb_score/";

/**
 * 当前页面URL
 * @type
 */
var currentURL = window.location.href;
/**
 * ajax 请求
 *
 * @param config
 *
 * 例子调用： config={ url:"", params:{}, type :"POST", //默认post dataType:"json",
 * //默认json async:false //默认false }; Ajax(config);
 *
 * @returns resObj
 */
function Ajax(config) {
	if (!config.url) {
		alert("请求URL地址不能为空！");
		return null;
	}

	var url = basepath + config.url; // url地址
	var params = config.params || {}; // ajax参数

	// 验证登录
	params.token = sessionStorage.getItem("sys_token");

	// 用户全局信息------------------------------------------------------------------
	var usertype =  sessionStorage.getItem("sys_usertype");
	params.userid =  sessionStorage.getItem("sys_userid");
	// --------------------------------------------------------------------------

	// 时间截
	params.NowDate = new Date().getTime(); // 默认追加时间参数
	var type = config.type || "POST"; // 请求方式
	var dataType = config.dataType || "json"; // 返回类型
	var async = config.async || false; // 同步 false 异步 true
	var resObj = null; // 返回值

	$.ajax({
				url : url,// 跳转到 action
				data : params,
				type : type,
				cache : false,
				async : async,
				dataType : dataType,
				success : config.success || function(data) {
					resObj = data;
				},
				error : config.error || function(req, errorObj, event) {
					if(event.name == "NetworkError" ){
						alert("登录超时，请重新登录！");
						location.href = app_base_url + "login/login.html";
					}
				}
			});
	return resObj;
}

function toJSON(str) {
	return JSON.parse(replaceLong(str)||"{}");
}

function replaceLong(str) {
	if(str){
		var reg = /:(\d{15,})/g;
		return str.replace(reg, ":\"$1\"");
	}
	return "";
}

function toString(data){
	return JSON.stringify(data);
}

/**
 * 延迟跳转页面
 *
 * @url URL 地址
 * @time 延迟时间，秒
 */
function relayToUrl(url, time) {
	window.setTimeout(function() {
				window.location.href = url;
			}, time * 1000);
}
/**
 * 弹出提示信息
 *
 * @param {}
 *            msg
 */
function showAlert(msg) {
	var msg = msg || "";
	layer.open({
				content : msg,
				shadeClose : false,
				btn : ['确定']
			});
}
/**
 * 显示信息，设定延迟时间
 *
 * @msg 显示的信息
 * @time 延迟时间 单位（秒）
 */
function showMsg(msg, time) {
	layer.open({
				content : msg,
				shadeClose : false,
				shade:false,
				time : time || 2,
				style:'background-color:rgba(31,37,33,0.7); color:#fff; border:none;border-radius: 10px;'
			});
}

/**
 * 检验是否登录
 */
$(function() {

			// 如果不是登陆页面,if条件判断不拦截的页面
			if (currentURL.indexOf("login") > -1
					|| currentURL.indexOf("register") > -1
					|| currentURL.indexOf("discovery/found.html") > -1
					|| currentURL.indexOf("discovery/detali.html") > -1
					|| currentURL.indexOf("detail/homeworkdetail.html") > -1
					|| currentURL.indexOf("detail/noticedetail.html") > -1) {
			} else {

				var sys_userid = sessionStorage.getItem("sys_userid");
				if (!sys_userid) {
					location.href = app_base_url + "login/login.html";
				}
			}

		})

/**
 * 页面错误信息
 *
 * @param data{
 *            type: "no_data", //错误图片的 class
 *            msg: "暂无数据" //提示错误信息
 *            style:"样式"}
 * @param id
 *            div的id
 */
function error(data, id) {
	var source = "<div class='prompt_container' style='{{style}}'>"
			+ "<div class='{{type}}'></div>"
			+ "<div style='padding:15px; color:#999;'>{{msg}}</div></div>";
	var render = template.compile(source);
	var html = render(data);
	document.getElementById(id).innerHTML = html;
}

/**
 * 获取URL参数
 *
 * @param {}
 *            name
 * @return {}
 */
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}



//获取焦点事件
$(function(){
	function FocusBorderColor(){
		$("input").focus(function(){
			var gradeParents = $(this).parent().parent(".weui_cell");
			$("input").parent().parent(".weui_cell").css("border-bottom","1px solid #dadada")
			gradeParents.css("border-bottom","1px solid #58ad3b");
		})
	}
	FocusBorderColor();
})

