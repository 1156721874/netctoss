package Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import sun.misc.BASE64Encoder;

public class MD5Util {
	public static String encrypt(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("md5");
		// digest方法，对字节数组按照指定的算法（MD5）生成一个摘要（即密文）
		byte[] buf = md.digest(str.getBytes());
		// 将字节数组转换为一个字符串
		// 可以使用base64encoding可以完成任意一个字节数组转换成一个字符串。
		BASE64Encoder encoder = new BASE64Encoder();
		String str2 = encoder.encode(buf);
		return str2;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(encrypt("wang"));
		System.out.println(MD5("wang"));
		System.out.println(parseMd5("ew0a8n3g9w2abnbg89dedb8ed6fb298f8e729c15@8"));
	}

	public static String MD5(String str)  {
		String encode = str;
		StringBuilder stringbuilder = new StringBuilder();
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(encode.getBytes());
			byte[] str_encoded = md5.digest();
			for (int i = 0; i < str_encoded.length; i++) {
				if ((str_encoded[i] & 0xff) < 0x10) {
					stringbuilder.append("0");
				}
				stringbuilder.append(Long.toString(str_encoded[i] & 0xff, 16));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return stringbuilder.toString();
	}

	public static String parseMd5(String md5str) {
		int flagoffset = md5str.lastIndexOf("@");
		System.out.println("util  "+md5str);
		System.out.println("util "+md5str.substring(flagoffset+1));
		int pwdlen = Integer.valueOf( md5str.substring(flagoffset+1));
		System.out.println("pwdlen :"+pwdlen);
		String md5andpwd = md5str.substring(0, flagoffset);
		System.out.println("md5andpwd :"+md5andpwd);
		String pwd = "";
		int m=1;
		//得到明码
		for(int i=0;i<pwdlen;i++){
			pwd+=md5andpwd.charAt(m);
			m+=2;
		}
		
		return pwd;
	}
}
// e08392bb89dedb8ed6fb298f8e729c15
// e08392bb89dedb8ed6fb298f8e729c15
