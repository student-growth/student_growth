package com.info.common;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import java.util.List;

/**
 * @author : yue
 * @Date : 2020/5/31 / 23:32
 */
@Data
public class ReturnValue<T> extends Return<T> {
	public ReturnValue() {
	}

	public ReturnValue(List<T> list, int currpage, int totalrecords, int totalpages) {
		this.list = list;
		this.currpage = currpage;
		this.totalrecords = totalrecords;
		this.totalpages = totalpages;
	}

	private int state = StateMsg.StateMsg_100.getState();
	private String msg = StateMsg.StateMsg_100.getMsg();

	public void setStateMsg(StateMsg stateMsg) {
		this.state = stateMsg.getState();
		this.msg = stateMsg.getMsg();
	}

	public void setStateMsg(StateMsg state, String param){
		this.state = state.getState();
		this.msg = "["+param+"]"+state.getMsg();
	}


	/**
	 * //返回数字信息
	 */
	private List<T> list;

	/**
	 * //系统报错信息
	 */
	private String systemerrormsg;

	/**
	 * //当前页数
	 */
	private int currpage = 0;
	/**
	 * //全记录数
	 */
	private long totalrecords = 0;
	/**
	 *  //全页数
	 */
	private int totalpages = 0;


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
