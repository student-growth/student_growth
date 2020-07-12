package com.info.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/7/11 / 16:00
 */
public class StudentProvider {

    public String queryPasswordById(Map<String ,Object> params){
        return new SQL(){
            {
                SELECT("password");
                FROM("student");
                WHERE("id="+params.get("id"));
            }
        }.toString();
    }
}
