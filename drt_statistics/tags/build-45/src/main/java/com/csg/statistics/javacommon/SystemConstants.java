
package com.csg.statistics.javacommon;

/**
 * Description: 系统常量 Company: Syni
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
    
    // 业务场景：注册
    public static final String BIZ_SCENARIO_DRT_REGISTER = "drt_register";
    
    // 业务场景：找回登录密码
    public static final String BIZ_SCENARIO_DRT_RESET_PASSWORD = "drt_reset_password";
    
    // 业务场景：绑定银行卡
    public static final String BIZ_SCENARIO_DRT_BIND_CARD_INFO = "drt_bind_card_info";
    
    // 业务场景：手机查询用电户号
    public static final String BIZ_SCENARIO_DRT_BIND_ELECODE = "drt_bind_eleCode";
    
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
    
    public static final Integer LOGIN_ERROR = 5;// 登录失败次数
    
    public static final Integer VALIDATE_FAILCOUNT = 3;// 验证码验证失败次数
    
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
    
    public static final String SET_SYS_MSG = "1";// 消息设置，1开启系统消息通知功能，2开启电费账单消息通知功能
    
    public static final String SET_ELE_MSG = "2";// 消息设置，1开启系统消息通知功能，2开启电费账单消息通知功能
    
    public static final String NEWS_ALL_TYPE = "0";// 查询消息状态设置，0全部，1未读，2已读，3删除
    
    public static final String NEWS_NOTREAD_TYPE = "0";// 查询消息状态设置，0全部，1未读，2已读，3删除
    
    public static final String NEWS_HAVEREAD_TYPE = "0";// 查询消息状态设置，0全部，1未读，2已读，3删除
    
    public static final String NEWS_DELETE_TYPE = "0";// 查询消息状态设置，0全部，1未读，2已读，3删除
    
    public static final String PAYTXCODEBANK = "pay.txCode.bank";// 支付鉴权接口txcode
    
    public static final String BANKBINDCODE_YSE = "0";// 支付鉴权接口绑卡
    
    public static final String BANKBINDCODE_NO = "1";// 支付鉴权接口解绑
    
    public static final String PAYAPISUCCESSCODE = "0000";// 支付接口返回成功标志
    
    public static final String CHECKREGISTCODE_YES = "0";// 检查手机号是否注册，0未注册，1已注册
    
    public static final String CHECKREGISTCODE_NO = "1";// 检查手机号是否注册，0未注册，1已注册
    
    public static final String FINANCE_TYPE_JY = "01"; // 资产代办类型 交易
    
    // 获取积分的类型：0表示收益产生的积分，1表示消费产生的积分，2表示推广产生的积分
    public static final String EARNINGS_TYPE_PROCEEDS = "0";// 0表示收益产生的积分
    
    public static final String EARNINGS_TYPE_CONSUME = "1";// 1表示消费产生的积分
    
    public static final String EARNINGS_TYPE_SPREAD = "2";// 2表示推广产生的积分
}
