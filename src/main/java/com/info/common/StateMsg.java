package com.info.common;

/**
 * Created by TerryJ on 2020/03/04.
 *
 */
public enum StateMsg {


    StatusMsg_100(100,"操作成功"),

    //系统级别 1xx
    StatusMsg_101(101,"参数错误"),
    StatusMsg_102(102,"没有数据"),
    StatusMsg_103(103,"DB ERROR"),
    StatusMsg_104(104,"手机号码不存在"),
    StatusMsg_105(105,"文件上传大小受限"),
    StatusMsg_144(144,"操作失败"),
    //添加的参数需求
    StatusMsg_145(145,"手机号码不能为空"),
    StatusMsg_146(146,"用户名重复"),
    //业务 2xx
    StatusMsg_201(201,"账号或密码不正确"),
    StatusMsg_202(202,"用户不存在"),

    //角色 目录,
    StatusMsg_301(301,"角色不能为空"),
    StatusMsg_302(302,"角色已存在"),
    StatusMsg_303(303,"该角色已绑定账户无法删除"),

    ;


    // 构造方法
    StateMsg(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    private String msg;
    private int state;


    // 普通方法
    public static String getMsg(int state) {
        for (StateMsg stateMsg : StateMsg.values()) {
            if (stateMsg.state == state) {
                return stateMsg.msg;
            }
        }
        return null;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
