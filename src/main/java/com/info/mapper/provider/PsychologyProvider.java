package com.info.mapper.provider;

import com.info.entity.AssessEntity;
import com.info.entity.PsychologyEntity;
import com.info.mapper.sql.SQLBuilder;

import java.util.Map;

/**
 * @author : yue
 * @since  2020/9/4 / 14:16
 * 心理测试
 */
public class PsychologyProvider {

    public String selectById(Map<String, Object> params) {
        String id = (String) params.get("id");

        SQLBuilder sql = new SQLBuilder();

        sql.SELECT_ALL(PsychologyEntity.class)
                .FROM("psy_test");
        if(params.get("name")==null){
            sql.WHERE("student_id='" + id + "'");
        }else{
            String name=(String)params.get("name");
            sql.WHERE("student_id='" + id + "'","test_type='"+name+"'");
        }

        return sql.getSQL();
    }
    public String insertOne(Map<String, Object> params) {
        PsychologyEntity entity = (PsychologyEntity) params.get("entity");
        StringBuilder sql = new StringBuilder();
        sql.append("insert into psy_test(student_id,test_type,result)")
                .append("value(")
                .append("'").append(entity.getStudentId()).append("',")
                .append("'").append(entity.getTestName()).append("',")
                .append("'").append(entity.getResult()).append("')");
        return sql.toString();
    }

}
