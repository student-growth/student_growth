package com.info.mapper.provider;

import com.info.entity.ApplyEntity;
import com.info.mapper.sql.SQLBuilder;
import jdk.nashorn.api.scripting.ScriptUtils;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/8/23 / 16:26
 */
public class ApplyProvider {

    public String insertApplyInfo(Map<String,Object> param){
        ApplyEntity entity = (ApplyEntity) param.get("entity");
        SQLBuilder sql = new SQLBuilder();
        sql.INSERT_INTO("apply",
                "student_id",
                "apply_id",
                "apply_name",
                "form_temp",
                "form_data",
                "image",
                "apply_state")
                .VALUE(entity.getStudentId(),entity.getApplyId(),
                        entity.getApplyName(),
                        entity.getFormTemp(),entity.getFormData(),
                        entity.getImage(),entity.getApplyState());
        return sql.getSQL();
    }


    public String selectApplyInfo(Map<String,Object> params){
        String studentId = (String) params.get("studentId");
        StringBuilder sql = new StringBuilder();
        sql.append("select student_id as studentId,apply_id as applyId, apply_name as applyName ," +
                "form_temp as formTemp,form_data as formData,image,apply_state as applyState")
        .append(" from apply")
        .append(" where student_id='").append(studentId).append("'");
        if(params.get("state")!=null){
            String state = (String) params.get("state");
            sql.append(" and apply_state='").append(state).append("'");
        }

        return sql.toString();
    }
}
