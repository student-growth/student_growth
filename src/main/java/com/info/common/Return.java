package com.info.common;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/7/11 / 16:12
 */
@Data
public class Return<T> {
    private int code;

    private String msg;

    private String sysError;

    private T data;
}
