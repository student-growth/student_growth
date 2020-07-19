package com.info.common;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/7/11 / 16:15
 * 返回不带分页信息的对象
 */
@Data
public class ReturnData<T> extends Return<T> {


    private T data;

    public ReturnData(){
        super();
    }

    public ReturnData(T data){
        super(data);
    }
}
