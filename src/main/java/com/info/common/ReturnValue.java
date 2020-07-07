package com.info.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * Created by TerryJ on 2020/03/04.
 *
 */
@Data
public class ReturnValue<T> {

	public ReturnValue() {
	}

	public ReturnValue(List<T> list, int currpage, int totalrecords, int totalpages) {
		this.list = list;
		this.currpage = currpage;
		this.totalrecords = totalrecords;
		this.totalpages = totalpages;
	}

	private int state = StateMsg.StatusMsg_100.getState();//状态码
	private String msg = StateMsg.StatusMsg_100.getMsg();//描述

	public void setStateMsg(StateMsg stateMsg) {
		this.state = stateMsg.getState();
		this.msg = stateMsg.getMsg();
	}

	private T Object;//返回对象信息
	private List<T> list;//返回数字信息

	private String systemerrormsg;//系统报错信息

	private int currpage = 0;    //当前页数
	private long totalrecords = 0;    //全记录数
	private int totalpages = 0;  //全页数

	// create by build json plugin
	public JSONObject toJson() {
		JSONObject jo = new JSONObject();
		jo.put("status", state);
		jo.put("msg", msg);

		jo.put("systemerrormsg", systemerrormsg);
		jo.put("currpage", currpage);
		jo.put("totalrecords", totalrecords);
		jo.put("totalpages", totalpages);
		return jo;
	}

	@Override
	public String toString() {
		return toJson().toString();
	}
}
