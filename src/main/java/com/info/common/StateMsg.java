package com.info.common;

/**

 * @author : yue
 * @Date : 2020/5/31 / 23:35
 */

public enum  StateMsg {
    /**
     * 操作成功
     */
    StateMsg_100(100,"操作成功"),
    StateMsg_101(101,"参数错误"),
    StateMsg_102(102,"操作失败"),
    StateMsg_103(103,"数据库操作失败"),

    StateMsg_202(202,"用户不存在"),
    ;

    private int state;
    private String msg;

    StateMsg(int state,String msg){
        this.state=state;
        this.msg=msg;
    }

    /**
     * 获取状态码对应的意思
     * @param state 状态码
     * @return msg
     */
    public static String getMsg(int state){
        for(StateMsg stateMsg: StateMsg.values()){
            if(stateMsg.state==state){
                return stateMsg.msg;
            }
        }
        return "";
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
