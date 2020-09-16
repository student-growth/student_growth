package com.info.common.sysenum;

/**

 * @author : yue
 * @Date : 2020/5/31 / 23:35
 */

public enum  StateMsg {
    /**
     * 操作成功
     */
    StateMsg_200(200,"操作成功"),

    StateMsg_101(101,"参数错误"),
    StateMsg_102(102,"未选择文件"),
    StateMsg_103(103,"文件名称为空"),
    StateMsg_104(104,"查询数据为空"),
    StateMsg_105(105,"文件上传失败"),
    StateMsg_106(106,"文件不存在"),
    StateMsg_107(107,"Excel文件标题出现错误"),

    StateMsg_202(202,"学号错误或密码错误"),
    StateMsg_205(205,"账户不存在"),
    StateMsg_203(203,"输入密码错误"),
    StateMsg_204(204,"[上传文件] 创建文件夹失败"),

    StateMsg_303(303,"数据库操作失败"),


    StateMsg_401(401,"学号为空"),


    StateMsg_500(500,"操作失败")
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
