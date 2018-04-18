package com.csg.common.config;

public class CommonContants {

	// 响应状态码，0：成功返回
    public static final String RESULT_CODE_SUCCESS = "00";
    
    public static final String COMMON_FLAG_FALSE = "0"; // 通用标志-否
    
    public static final String COMMON_FLAG_TRUE = "1"; // 通用标志-是
    public static final String XY_BIND = "1"; //营销绑定
    public static final String XY_UNBIND = "2"; // 营销解绑
    
    
    public static final String FAILE_QUEUE_TYPE_BIND="1";//失败类型，绑定解绑接口
    public static final String FAILE_QUEUE_TYPE_TBYE="2";//失败类型，同步余额
    
    public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";
    public static final String DATE_PATTERN_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN_DAY_01 = "yyyyMMdd";
    public static final String DATE_PATTERN_DAY_02 = "MM/dd/yyyy";
    public static final String DATE_PATTERN_DAY_03 = "MMdd";
    public static final String DATE_PATTERN_MONTH_01 = "yyyyMM";
    public static final String DATE_PATTERN_MONTH = "yyyy-MM";

    
    
 // 接口txcode
    public static final String PAYTXCODEBANK = "pay.txCode.bank";// 支付鉴权接口txcode
    
    public static final String PAYTXCODEDEDUCTION = "pay.txCode.deduction";// 划扣接口txcode
    
    public static final String PAYTXCODEPAYSTATE = "pay.txCode.payState";// 交易状态接口txcode
    
    public static final String PAYTXCODEQUERYCHAN = "pay.txCode.queryChan";// 支付通道查询接口txcode
    
    public static final String PAYTXCODEREDEMPTION = "pay.txCode.redemption";// 赎回接口txcode
    
    public static final String PAYTXCODECHECKMESSAGE = "pay.txCode.checkMessage";//短信码验证txcode
    
    
    // 通讯类型
    public static final String COMM_TYPE_SYNCHRONOUS = "0";// 同步
    
    public static final String COMM_TYPE_ASYNCHRONOUS = "1";// 异步
    
    // 系统标识
    public static final String SYS_CODE_DRT = "DRT";// 电融通
    
    // 摘要
    public static final String ZY_CODE = "DRT";// 电融通
    public static final String PAYAPISUCCESSCODE = "0000";// 支付接口返回成功标志
    
    //支付平台接口报文支付方式00银行卡支付01支付宝支付02微信支付
    public static final String BANKCARD_PAY_WAY = "00";//银行卡支付
    public static final String ALIPAY_PAY_WAY = "01";//支付宝支付
    public static final String WECHAT_PAY_WAY = "02";//微信支付
    
    //支付平台接口报文交易类型00-预购 01-预付 02-预存 03-预付赎回
    private static final String PO_DEAL_WAY = "00";//预购
    private static final String PP_DEAL_WAY = "01";//预付
    private static final String PS_DEAL_WAY = "02";//预存
    private static final String PB_DEAL_WAY = "03";//预付赎回
    
    //支付平台接口报文证件类型
    public static final String USER_TYPE_SFZ = "0";//身份证
    
	public static final String YXAPI_CODE_NORMAL = "00";// 营销接口返回成功标志——正常状态
	public static final String YXAPI_CODE_ERROR_01 = "01";// 营销接口异常——相关参数为空异常
	public static final String YXAPI_CODE_ERROR_02 = "02";// 营销接口异常——营销接口异常
	public static final String YXAPI_CODE_ERROR_03 = "03";// 营销接口异常——营销接口超时
	public static final String YXAPI_CODE_ERROR_04 = "04";// 营销接口异常——调营销接口异常
	public static final String YXAPI_CODE_ERROR = "99";// 异常状态
	
	//同步余额费用类型（1、充值，2、赎回，3、缴费）
	public static final String TBYE_PS_TYPE = "1";//充值
	public static final String TBYE_PAY_TYPE = "2";//赎回
	public static final String TBYE_BUY_TYPE = "3";//缴费
	
	//SMS请求渠道
	public static final String SMS_CALLTYPE = "drt";

	public static final String feignToken = "feignToken";//微服务调用tokenkey
	public static final String feignTokenValue = "drtqwertyuiop";//微服务调用token默认value
	public static final String isFeignCheck = "isFeignCheck";////是否开启微服务间调用验证
	
	
	public static final int springRedisSessionTimeOut = 3600;//redis超时时间秒做单位
}
