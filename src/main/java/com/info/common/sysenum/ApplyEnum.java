package com.info.common.sysenum;

/**
 * @author : yue
 * @Date : 2020/8/23 / 16:35
 */
public enum ApplyEnum {

    APPLYING("001","applying"),
    PASS("002","pass"),
    REFUSED("003","refused")
    ;


    private String code;

    private String state;

    ApplyEnum(String code,String state){
        this.code = code;
        this.state = state;
    }


    public static String getState(String code){
        for(ApplyEnum applyEnum: ApplyEnum.values()){
            if(applyEnum.code.equals(code)){
                return applyEnum.state;
            }
        }
        return "";
    }

}
