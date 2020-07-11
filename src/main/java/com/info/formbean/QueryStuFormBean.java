package com.info.formbean;

import lombok.Data;

/**
 * @author Tenghao Deng
 * @create 2020-07-11 20:03
 **/
@Data
public class QueryStuFormBean {
    private String department;
    private String major;
    public QueryStuFormBean(){ //默认
        this.department = "信息与电子工程学院";
        this.department = "电子信息工程";
    }
}
