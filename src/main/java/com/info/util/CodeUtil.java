package com.info.util;

import org.springframework.util.DigestUtils;

/**
 * @author : yue
 * @Date : 2020/7/8 / 18:23
 * 密码加密
 */
public class CodeUtil {

    /***
     * MD5加密
     */
    public static  String  encrypt(String msg){
        return DigestUtils.md5DigestAsHex(msg.getBytes());
    }

}
