package com.yt.util;

import java.util.Random;
/**
 * Title: StringGenerateUitil.java
 * Description: 
 * Company: HundSun
 * @author yangting
 * @date 2017年7月3日 上午11:02:20
 * @version 1.0
 */
public class StringGenerateUitil {
	private static Random random = new Random();
	public static String generateString(int length){
		StringBuffer stringBuffer = new StringBuffer();
		for(int i=0;i < length;i++){
			stringBuffer.append((char) (random.nextInt(58) + 65));
		}
		return stringBuffer.toString();
	}
	public static void main(String[] args) {
		System.out.println(generateString(20).getBytes().length);
		System.out.println(generateString(20));
		System.out.println(generateString(20));
	}
}
