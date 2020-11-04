package com.yan.goods.delivery.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/16.
 *  利用uuid生成的值
 */
public class UUIDGen {

    private static SecureRandom random = new SecureRandom();

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid2() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }
    
    public static void main(String[] args) {
		System.out.println(uuid());
	}
}
