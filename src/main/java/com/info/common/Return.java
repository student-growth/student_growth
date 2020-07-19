package com.info.common;

import com.info.common.sysenum.StateMsg;
import com.sun.org.apache.regexp.internal.RE;
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



    Return(){
        this.code=200;
        this.msg="操作成功";
    }

    Return(T data){

        this.code=200;
        this.msg ="操作成功";
    }

    public void setStateMsg(StateMsg stateMsg) {
        this.code = stateMsg.getState();
        this.msg = stateMsg.getMsg();
    }
}
