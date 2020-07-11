package com.info.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author : yue
 * @Date : 2020/7/11 / 16:00
 */
public class StudentProvider {

    public String queryPasswordById(final int id){
        return new SQL(){
            {
                SELECT("password");
                FROM("student");
                WHERE("id="+id);
            }
        }.toString();
    }
}
