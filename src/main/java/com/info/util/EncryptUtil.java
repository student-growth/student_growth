package com.info.util;

import lombok.Data;
import org.springframework.util.DigestUtils;

/**
 * @author : yue
 * @Date : 2020/7/11 / 16:18
 */
@Data
public class EncryptUtil {

    public static String  encryptMD5(String msg){
        return DigestUtils.md5DigestAsHex(msg.getBytes());
    }
}
