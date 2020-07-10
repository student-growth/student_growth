package com.info.exception;

import com.info.common.StateMsg;

/**
 * @author : yue
 * @Date : 2020/7/8 / 22:11
 */
public class SystemException extends Exception {

    public SystemException(){
        super();
    }

    public SystemException(StateMsg err){
        super(err.getMsg());
    }
}
