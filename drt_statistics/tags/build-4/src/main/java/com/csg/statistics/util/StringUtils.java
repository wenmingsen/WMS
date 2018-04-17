package com.csg.statistics.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.springframework.util.StringUtils {
	public static void main(String[] args) {
		System.out.println(checkMobile("15019949268"));
//		System.out.println(camel);
//		System.out.println(camel2Underline(camel));
	}

	/**
	  * 下划线转驼峰法
	  * @param line 源字符串
	  * @param smallCamel 大小驼峰,是否为小驼峰
	  * @return 转换后的字符串
	  * iHaveAnIpang3Pig <--I_HAVE_AN_IPANG3_PIG
	  */
	public static String underline2Camel(String line, boolean smallCamel) {
		if (line == null || "".equals(line)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
			int index = word.lastIndexOf('_');
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰法转下划线
	 * @param line 源字符串
	 * @return 转换后的字符串
	 * iHaveAnIpang3Pig -->I_HAVE_AN_IPANG3_PIG
	 */
	public static String camel2Underline(String line) {
		if (line == null || "".equals(line)) {
			return "";
		}
		line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end() == line.length() ? "" : "_");
		}
		return sb.toString();
	}

	/**
	 * 截取字符串
	 */
	public static String substring(String str, int length) {
		if (str != null && !str.equals("")) {
			return str.length() > length ? str.substring(0, length) + "..." : str;
		} else {
			return "";
		}

	}

	/**
	 * 字符串是否为空
	 */
	public static boolean isEmpty(String str) {
		return !hasText(str);
	}

	/**
	 * <p>Description: 
	 * 	检查手机号码是否合法
	 * </p>        
	 * @exist_problem     
	 * @amendment_history 
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile) {
		String mobileScope = "134,135,136,137,138,139,150,151,152,157,158,159,187,188,130,131,132,153,155,156,133,185,186,180,189";
		String mobileLen = "11,13";
		String mobilePrefix = "86";
		return checkMobileStr(mobile, mobileScope, mobileLen, mobilePrefix);
	}

	/**
	 * <p>Description:
	 *  	检查一批手机号码是否合法
	 * </p>        
	 * @exist_problem     
	 * @amendment_history 
	 * @param mobiles
	 * @return
	 */
	public static List<String> checkMobile(List<String> mobiles) {

		List<String> error = new LinkedList<String>();
		String checkMobile = "true";
		String mobileScope = "134,135,136,137,138,139,150,151,152,157,158,159,187,188,130,131,132,153,155,156,133,185,186,180,189";
		String mobileLen = "11,13";
		String mobilePrefix = "86";
		// ADD BY ANDERS 040413 增加判断是否检测手机的配置
		if (!checkMobile.equals("true"))
			return error;

		if (mobiles == null)
			return error;
		for (int i = 0; i < mobiles.size(); i++) {
			String str_mobile = mobiles.get(i);
			if (!checkMobileStr(str_mobile, mobileScope, mobileLen, mobilePrefix))
				error.add(str_mobile);
		}
		return error;

	}

	public static boolean checkMobileStr(String mobile, String startStr, String lengthStr, String affixStr) {
		List<String> arr_start = parseStr(startStr);
		List<String> arr_length = parseStr(lengthStr);
		List<String> arr_affix = parseStr(affixStr);
		if (mobile == null || mobile.trim().length() == 0)
			return false;
		if (arr_start.contains("0") && mobile.startsWith("0")) {// 对小灵通的判断
			Pattern p = Pattern.compile("^(?:0(?:10|2[0-57-9]|[3-9]\\d{2})\\d{7,8})$");
			Matcher m = p.matcher(mobile);
			return m.matches();
		}

		boolean pass = false;
		if (lengthStr.trim().length() > 0) {
			for (int i = 0; i < arr_length.size(); i++) {
				int length = Integer.parseInt((String) arr_length.get(i));
				if (mobile.length() == length) {
					pass = true;
					break;
				}
			}
		} else {
			pass = true;
		}
		if (!pass)
			return false;

		for (int i = 0; i < arr_affix.size(); i++) {
			String str_affix = (String) arr_affix.get(i);
			if (mobile.startsWith(str_affix))
				mobile = mobile.substring(str_affix.length());
		}
		pass = false;
		if (startStr.trim().length() > 0) {
			for (int i = 0; i < arr_start.size(); i++) {
				String start = (String) arr_start.get(i);
				if (mobile.startsWith(start)) {
					mobile = mobile.substring(start.length());
					pass = true;
					break;
				}
			}
		} else {
			pass = true;
		}
		if (!pass)
			return false;

		for (int i = 0; i < mobile.length(); i++) {
			String su = mobile.substring(i, i + 1);
			if (!((su.compareTo("0") >= 0) && (su.compareTo("9") <= 0)))
				return false;
		}
		return true;

	}

	/** 解析字符串 */
	private static List<String> parseStr(String str) {
		List<String> result = new ArrayList<String>();
		StringTokenizer chk1 = new StringTokenizer(str, ",");
		while (chk1.hasMoreTokens()) {
			String get = chk1.nextToken();
			if (get.indexOf("-") != -1) {
				StringTokenizer chk2 = new StringTokenizer(get, "-");
				int start = Integer.parseInt(chk2.nextToken());
				int end = Integer.parseInt(chk2.nextToken());
				for (int i = start; i >= start && i <= end; i++) {
					result.add(String.valueOf(i));
				}
			} else {
				result.add(get);
			}

			result.add(get);
		}
		return result;
	}

	/**
	 * 判断字符串是否是数字组成
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (str == null || str.trim().equals(""))
			return false;

		java.util.regex.Pattern p = null;
		java.util.regex.Matcher m = null;
		try {
			p = java.util.regex.Pattern.compile("[^0-9]");
			m = p.matcher(str);
			if (m.find())
				return false;
		} catch (Exception e) {
		}
		return true;
	}

	/**
	 * 获取随机字符串
	 */
	public static String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();

		StringBuffer buffer = new StringBuffer("0123456789");
		int range = buffer.length();
		Random r = new Random();

		for (int i = 0; i < length; i++)
			sb.append(buffer.charAt(r.nextInt(range)));

		return sb.toString();
	}

	/**
	 * 判断邮件地址是否合法
	 */
	public static boolean isEmailAdressFormat(String email) {

		if (isEmpty(email))
			return false;

		boolean isExist = false;

		Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
		Matcher m = p.matcher(email);
		boolean b = m.matches();
		if (b) {
			isExist = true;
		}
		return isExist;
	}

	/**
	 * 判断网址是否合法
	 */
	public static boolean isUrl(String url) {

		if (isEmpty(url))
			return false;

		String regEx = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
		Pattern p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		return matcher.matches();
	}

	/**
	 * 判断电话号码是否合法
	 */
	public static boolean isPhoneNumber(String phoneNum) {

		if (isEmpty(phoneNum))
			return false;

		String regex = "(((010|02\\d{1}|0[3-9]\\d{2})\\d{7,8}|1(3\\d{1}|58|59)\\d{8}))";

		return (Pattern.matches(regex, phoneNum));
	}

	/**拆分字符串
	 * andrew add for GroupSend mobile number process
	 * @param hid
	 * @param sp
	 * @return
	 */
	public static List<String> splitForMobile(String mobiles, String splitBy) {
		String[] mobile = mobiles.split(splitBy);
		List<String> mobileList = new ArrayList<String>();
		for (int i = 0; i < mobile.length; i++) {
			mobile[i] = mobile[i].trim();// .replaceAll("\r",
											// "").replaceAll("\t", "");
			if (mobile[i] != null && !mobile[i].equals("")) {
				mobileList.add(mobile[i]);
			}
		}
		// mobile = new String[mobileList.size()];
		// return (String[])mobileList.toArray(mobile);
		return mobileList;
	}

}
