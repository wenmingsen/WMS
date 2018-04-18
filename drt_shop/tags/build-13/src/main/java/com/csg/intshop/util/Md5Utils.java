package com.csg.intshop.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author aaron
 *	md5加密
 */
public class Md5Utils {
	
	public static String encode(String source){
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(source.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return tmpStr;
	}
	public static String encodeMd5(String source){
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(source.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return tmpStr;
	}
	
	// 将字节转换为十六进制字符串
		private static String byteToHexStr(byte ib) {
			char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
					'B', 'C', 'D', 'E', 'F' };
			char[] ob = new char[2];
			ob[0] = Digit[(ib >>> 4) & 0X0F];
			ob[1] = Digit[ib & 0X0F];

			String s = new String(ob);
			return s;
		}

		// 将字节数组转换为十六进制字符串
		private static String byteToStr(byte[] bytearray) {
			String strDigest = "";
			for (int i = 0; i < bytearray.length; i++) {
				strDigest += byteToHexStr(bytearray[i]);
			}
			return strDigest;
		}
		
		/** 
		* @Title: 盐加密 
		* @Description: TODO
		* @param password
		* @param salt
		* @return String    
		* @throws 
		*/ 
		public static String encodePassword(String password,String salt){
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			md5.setEncodeHashAsBase64(false);
			// 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可
			String pwd = md5.encodePassword(password, salt);
			return pwd;
		}

		public static void main(String[] args) {
            System.out.println(Md5Utils.encodePassword("123456","accountId"));
		}
		
		
		private static String byteArrayToHexString(byte b[]) {
			StringBuffer resultSb = new StringBuffer();
			for (int i = 0; i < b.length; i++)
				resultSb.append(byteToHexString(b[i]));

			return resultSb.toString();
		}

		private static String byteToHexString(byte b) {
			int n = b;
			if (n < 0)
				n += 256;
			int d1 = n / 16;
			int d2 = n % 16;
			return hexDigits[d1] + hexDigits[d2];
		}

		public static String MD5Encode(String origin, String charsetname) {
			String resultString = null;
			try {
				resultString = new String(origin);
				MessageDigest md = MessageDigest.getInstance("MD5");
				if (charsetname == null || "".equals(charsetname))
					resultString = byteArrayToHexString(md.digest(resultString
							.getBytes()));
				else
					resultString = byteArrayToHexString(md.digest(resultString
							.getBytes(charsetname)));
			} catch (Exception exception) {
			}
			return resultString;
		}

		private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
