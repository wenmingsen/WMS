
package com.csg.intshop.javacommon;

/**
 * Description: 系统常量
 * Company: Syni
 *
 * @author 杨彬俊
 * @version 1.0
 * @since 2017-11-01
 */
public class SystemConstants {
    
    // 响应状态码，0：成功
    public static final String RESULT_CODE_SUCCESS = "0";
    
    // 响应状态码，-1：失败
    public static final String RESULT_CODE_FAILED = "-1";
    
    // 业务场景：登录
    public static final String BIZ_SCENARIO_DRT_LOGIN = "drt_login";
    
    // 响应状态码，-1：失败(重复登陆)
 	public static final String RESULT_CODE_UNUSUAL = "201";
    
 	//登录超时
  	public static final String LOGIN_TIME_OUT = "101";
  	
  	
    // 业务场景：注册
    public static final String BIZ_SCENARIO_DRT_REGISTER = "drt_register";
    
    // 业务场景：找回登录密码
    public static final String BIZ_SCENARIO_DRT_RESET_PASSWORD = "drt_reset_password";
    
    // 业务场景：绑定银行卡
    public static final String BIZ_SCENARIO_DRT_BIND_CARD_INFO = "drt_bind_card_info";
    
    // 业务场景：绑定用电户号
    public static final String BIZ_SCENARIO_DRT_BIND_YDHH = "drt_bind_ydhh";
    
    // 登录账户操作类型，登录：0
    public static final String ACCOUNT_OPERATE_RECORD_TYPE_LOGIN = "0";
    
    // 登录账户操作类型，登出：1
    public static final String ACCOUNT_OPERATE_RECORD_TYPE_LOGOUT = "1";
    
    // 登录账户操作类型，修改密码：2
    public static final String ACCOUNT_OPERATE_RECORD_TYPE_RESET_PWD = "2";
    
    // 登录账户操作类型，修改手势密码：3
    public static final String ACCOUNT_OPERATE_RECORD_TYPE_RESET_GESTURE_PWD = "3";
    
    public static final String LOGIN_SIGN = "loginUser";// 登录成功session标识
    
    public static final String LOGIN_VIEW_SIGN = "loginViewUser";// 登录成功VIEWsession标识
    
    public static final String LOGINTYPE_PASSWORD = "1";// 密码登录
    
    public static final String LOGINTYPE_GESTURE = "2";// 手势登录
    
    public static final String GESTUREPASSWORD_DISABLE = "0";// 关闭手势密码
    
    public static final String GESTUREPASSWORD_ENABLE = "1";// 开启手势密码
    
    public static final Integer LOGIN_STATUS0 = 0;// 登录状态,0:正常,1:异常,2:锁定
    
    public static final Integer LOGIN_STATUS1 = 1;// 登录状态,0:正常,1:异常,2:锁定
    
    public static final Integer LOGIN_STATUS2 = 2;// 登录状态,0:正常,1:异常,2:锁定
    
    public static final String CHECKGESTUREPWD_FLAG = "checkGesturePwdFlag";// 是否判断了手势密码验证
    
    public static final String CHECK_VALIDATE_MESSAGE_FLAG = "checkValidateMsgFlag"; // 是否通过短信验证码验证
    
    public static final String VALIDATE_NO_TYPE = "0";// 实名验证，0未认证
    
    public static final String VALIDATE_WAIT_TYPE = "1";// 实名验证，1验证中
    
    public static final String VALIDATE_SUCCESS_TYPE = "2";// 实名验证，2验证成功
    
    public static final String VALIDATE_FAIL_TYPE = "3";// 实名验证，3验证失败
    
    public static final String COMMON_FLAG_FALSE = "0"; // 通用标志-否
    
    public static final String COMMON_FLAG_TRUE = "1"; // 通用标志-是
    
    public static final String BIND_STATUS_TYPE_BINGING = "0"; // 绑定状态，0绑定
    
    public static final String BIND_STATUS_TYPE_WAITING = "1"; // 绑定状态，1待校验
    
    public static final String BIND_STATUS_TYPE_UNBIND = "2"; // 绑定状态，2解绑
    
    // 交易类型
    public static final String ALL_TRANSACT_RECORD_TYPE = "0";// 0所有交易记录类型，1预付交易记录，2预购交易记录
    
    public static final String PREPAID_TRANSACT_RECORD_TYPE = "1";// 0所有交易记录类型，1预付交易记录，2预购交易记录
    
    public static final String ADVANCE_TRANSACT_RECORD_TYPE = "2";// 0所有交易记录类型，1预付交易记录，2预购交易记录
    
    /*public static final String RECORD_INTO_TYPE = "0";// 0转入，1转出，2扣缴，3买入
    
    public static final String RECORD_ROLL_OUT_TYPE = "1";// 0转入，1转出，2买入，3扣缴
    
    public static final String RECORD_BUY_TYPE = "2";// 0转入，1转出，2买入，3扣缴
    
    public static final String RECORD_REDUCE_TYPE = "3";// 0转入，1转出，2买入，3扣缴
*/    
    // 消息
    public static final String SALES_PROMOTION_ACTIVITY = "0";// 0促销活动，1重大新闻，2通知
    
    public static final String BIIG_NEWS = "1";// 0促销活动，1重大新闻，2通知
    
    public static final String INFORMATION = "2";// 0促销活动，1重大新闻，2通知
    
    public static final String SYS_MESSAGE_TYPE = "0";// 0系统消息，1电费账单消息
    
    public static final String ELE_MESSAGE_TYPE = "1";// 0系统消息，1电费账单消息
    
    public static final String PREPAID_TRANSACT_ACTIVITY = "0";// 0预付电费活动信息，1预购电费活动信息
    
    public static final String ADVANCE_TRANSACT_ACTIVITY = "1";// 0预付电费活动信息，1预购电费活动信息
    
    //账户开通状态
    public static final String DEDUCT_VALUE_FALSE = "0";// 开通状态关闭
    
    public static final String DEDUCT_VALUE_TRUE = "1";// 开通状态开通
    
    // 收益类型
    public static final String ALL_EARNINGS_RECORD_TYPE = "0";// 收益类型：0全部，1预付，2预购
    
    public static final String PREPAID_EARNINGS_RECORD_TYPE = "1";// 收益类型：0全部，1预付，2预购
    
    public static final String ADVANCE_EARNINGS_RECORD_TYPE = "2";// 收益类型：0全部，1预付，2预购
    
    // 接口txcode
    public static final String PAYTXCODEBANK = "pay.txCode.bank";// 支付鉴权接口txcode
    
    public static final String PAYTXCODEDEDUCTION = "pay.txCode.deduction";// 划扣接口txcode
    
    public static final String PAYTXCODEPAYSTATE = "pay.txCode.payState";// 交易状态接口txcode
    
    public static final String PAYTXCODEQUERYCHAN = "pay.txCode.queryChan";// 支付通道查询接口txcode
    
    public static final String PAYTXCODEREDEMPTION = "pay.txCode.redemption";// 赎回接口txcode
    
    public static final String PAYTXCODECHECKMESSAGE = "pay.txCode.checkMessage";//短信码验证txcode
    
    // 预付预购账户交易明细表的类型名称
    public static final String PP_ACCOUNT_TYPE = "0";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String PO_ACCOUNT_TYPE = "1";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String BANKCARD_ACCOUNT_TYPE = "2";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String ALIPAY_ACCOUNT_TYPE = "3";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String WECHAT_ACCOUNT_TYPE = "4";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String PP_TYPE_TOSTRING = "预付电费";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String PO_TYPE_TOSTRING = "预购电费";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String BANKCRAD_TYPE_TOSTRING = "银行卡支付";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String ALIPAY_TYPE_TOSTRING = "支付宝支付";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String WECHAT_TYPE_TOSTRING = "微信支付";// 类型名称：0预付电费1预购电费2银行卡支付3支付宝支付4微信支付
    
    public static final String PAYAPISUCCESSCODE = "0000";// 支付接口返回成功标志
    
    //预购交易返回结果
    public static final String BUY_RESULT_TOSTRING = "确认成功";// 类型名称：充值成功返回：确认成功
    
    public static final String PAY_RESULT_TOSTRING = "支付成功";// 类型名称：扣缴成功返回：支付成功
    
    
    // 预付/预购资金流向（转入/赎回）
    public static final String PRE_PAY_ACCOUNT = "0";// 流向（0：转入；1：赎回）
    
    public static final String PRE_BUY_ACCOUNT = "1";// 流向（0：转入；1：赎回）
    
    // 预付/预购（赎回）结果状态
    public static final String IS_IN_CHECK = "0";// 结果（0：审核中；1：赎回成功；2：赎回不成功）
    
    public static final String IS_BACK_SUCCESS = "1";// 结果（0：审核中；1：赎回成功；2：赎回不成功）
    
    public static final String IS_BACK_FAILED = "2";// 结果（0：审核中；1：赎回成功；2：赎回不成功）
    
    // 通讯类型
    public static final String COMM_TYPE_SYNCHRONOUS = "0";// 同步
    
    public static final String COMM_TYPE_ASYNCHRONOUS = "1";// 异步
    
    // 系统标识
    public static final String SYS_CODE_DRT = "DRT";// 电融通
    
    // 摘要
    public static final String ZY_CODE = "DRT";// 电融通
    
    // 是否删除
    public static final String IS_DELETE_TRUE = "0";// 已删除
    
    public static final String IS_DELETE_FALSE = "1";// 未删除
    
    // 累计收益所需查询时间
    public static final String SELECT_EARNINGS_MONTH = "1";// 1个月
    
    // 交电费支付类型
    public static final String TYPE_PAYELEC_BUY = "0";// 预购代扣
    
    public static final String TYPE_PAYELEC_PAY = "1";// 预付代扣
    
    public static final String TYPE_PAYELEC_BANK = "2";// 银行卡支付
    
    public static final String TYPE_PAYELEC_WX = "3";// 微信支付
    
    public static final String TYPE_PAYELEC_ZFB = "4";// 支付宝支付
    
    // 预购预付开通或关闭（ 0为关闭，1为开通）
    public static final String PRE_PAYORBUY_OPEN = "1";// 1为开通
    
    public static final String PRE_PAYORBUY_CLOSE = "0";// 0为关闭
    
    // 预购预付开通类型 0代表预付，1代表预购
    public static final String PRE_PAY_TYPE = "0";// 预付
    
    public static final String PRE_BUY_TYPE = "1";// 预购
    
    // 预购预付代扣开通或关闭（ 0为关闭，1为开通）
    public static final String TYPE_PAYELEC_PAYORBUY_OPEN = "1";// 1为开通
    
    public static final String TYPE_PAYELEC_PAYORBUY_CLOSE = "0";// 0为关闭
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
    
    //用电户欠费账单是否缴清0未交1缴清
    public static final String IS_NOT_PAY = "0";//未交
    public static final String IS_HAVE_PAY = "1";//缴清

    
    public static final String TYPE_EARNINGS_PO = "0"; //预购收益
    public static final String TYPE_EARNINGS_OTHER = "1";//其他收益

    
    //预购账户交易类型（1充值3扣缴）
    public static final String PAY_IS_IN = "1";//充值
    public static final String PAY_IS_OUT = "3";//扣缴
    
    //电费查询类型（0查欠费1查历史账单）
    public static final String ACCOUNT_ELEC_FEES = "0";//查欠费
    public static final String ACCOUNT_ELEC_BILL = "1";//查历史账单
    
    //电费扣费账单表扣费帐单状态(00-待交易,01-已交易)
    public static final String FINANCE_NOT_PAY = "0";//待交易
    public static final String FINANCE_IS_PAY = "1";//已交易
    
    //消息表消息状态（0：全部；1：未读；2：已读；3：删除）
    public static final String ACCOUNT_ALL_NEWS = "0";//全部
    public static final String ACCOUNT_NOT_NEWS = "1";//未读
    public static final String ACCOUNT_HAVE_NEWS = "2";//已读
    public static final String ACCOUNT_DELETE_NEWS = "3";//删除

    //积分更新类型标示（1：交电费;2：积分兑换）
    public static final String EARNINGS_FROM_TYPE_PAY_ELEC = "1";//交电费
    public static final String EARNINGS_FROM_TYPE_EXCHANGE = "2";//积分兑换
    
    //积分更新类型内容（1：交电费;2：积分兑换）
    public static final String EARNINGS_FROM_TYPE_NAME_PAY_ELEC = "交电费";//交电费
    public static final String EARNINGS_FROM_TYPE_NAME_EXCHANGE = "积分兑换";//积分兑换
    
    //积分更新信息（0：积分更新成功；1：账户不存在；2：积分余额不足；3：积分更新失败）
    public static final String SUCCESS_MSG_EARNINGS_UPDATE = "积分更新成功！";//积分更新成功
    public static final String ERROR_MSG_ACCOUNT_NOT_EXIST = "账户不存在！";//账户不存在
    public static final String ERROR_MSG_EARNINGS_NOT_ENOUGH = "积分余额不足！";//积分余额不足
    public static final String ERROR_MSG_EARNINGS_UPDATE = "积分更新失败！";//积分更新失败
    public static final String ERROR_MSG_ACCOUNT_NULL = "账户ID为空！";//账户ID为空
    public static final String ERROR_MSG_PLUSMINUS_NULL = "正负值为空！";//正负值ID为空
    public static final String ERROR_MSG_FROMTYPE_NULL = "来源类型为空！";//来源类型为空
    public static final String ERROR_MSG_MONEY_NULL = "金额为空！";//金额为空
    
    //积分规则类型(1.余额积分 2.交费积分 3.推广积分)
    public static final String EARNINGS_RULE_BALANCE = "1";//余额积分
    public static final String EARNINGS_RULE_PAY_ELEC = "2";//交费积分
    public static final String EARNINGS_RULE_SPREAD = "3";//推广积分
    
    //正负值
    public static final String PLUS = "1";//正
    public static final String MINUS = "0";//负
    
    //单位换算
    public static final double DIVISION100 = 0.01;//除以100
    public static final Integer MULTIPLICATION100 = 100;//乘100
    public static final Integer ZERO = 0;//0
    
    //订单状态
    public static final Integer ORDERSTATE = 2;//兑换成功
}
