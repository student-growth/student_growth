package com.info.common;

import lombok.Data;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/5/31 / 23:32
 */
@Data
public class ReturnValue<T> {

    private T object;
    private List<T> list;
    private String systemError;
    /**
     * 分页的属性
     */
    private int currentPage=0;
    private long totalRecords = 0;
    private int totalPage = 0;

    private int state = StateMsg.StateMsg_100.getState();
    private String msg = StateMsg.StateMsg_100.getMsg();


    public ReturnValue(){}

    public ReturnValue(List<T> list,int currentPage,int totalPage,long totalRecords){
        this.list = list;
        this.totalPage=totalPage;
        this.currentPage = currentPage;
        this.totalRecords = totalRecords;
    }
    public void setStateMsg(StateMsg stateMsg){
        this.state = stateMsg.getState();
        this.msg = stateMsg.getMsg();
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        json.put("status",state);
        json.put("msg",msg);
        json.put("systemerrormsg", systemError);
        json.put("currpage", currentPage);
        json.put("totalrecords", totalRecords);
        json.put("totalpages", totalPage);
        return json;
    }

    @Override
    public String toString() {
        return  toJSON().toString();
    }
}
