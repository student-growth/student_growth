package com.info.mapper.provider;

import com.info.entity.ApplyEntity;
import com.info.mapper.sql.SQLBuilder;
import com.info.util.DateUtil;
import jdk.nashorn.api.scripting.ScriptUtils;
import org.springframework.util.unit.DataUnit;

import java.util.Date;
import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/8/23 / 16:26
 */
public class ApplyProvider {

    public String insertApplyInfo(Map<String, Object> param) {
        ApplyEntity entity = (ApplyEntity) param.get("entity");
        SQLBuilder sql = new SQLBuilder();
        sql.INSERT_INTO("apply",
                "id",
                "student_id",
                "apply_id",
                "apply_name",
                "form_temp",
                "form_data",
                "image",
                "apply_state")
                .VALUE(entity.getId(),
                        entity.getStudentId(),
                        entity.getApplyId(),
                        entity.getApplyName(),
                        entity.getFormTemp(), entity.getFormData(),
                        entity.getImage(), entity.getApplyState());
        return sql.getSQL();
    }


    public String selectApplyInfo(Map<String, Object> params) {
        String studentId = (String) params.get("studentId");
        StringBuilder sql = new StringBuilder();
        sql.append("select id ,student_id as studentId,apply_id as applyId, apply_name as applyName ," +
                "form_temp as formTemp,form_data as formData,image,apply_state as applyState")
                .append(" from apply")
                .append(" where student_id='").append(studentId).append("'");
        if (params.get("state") != null) {
            String state = (String) params.get("state");
            sql.append(" and apply_state='").append(state).append("'");
        }

        return sql.toString();
    }

    //更新状态
    public String updateState(Map<String, Object> params) {

        String state = (String) params.get("state");
        //UPDATE news SET title='q123' WHERE id='864474'
        StringBuilder sql = new StringBuilder();
        sql.append("update apply set")
                .append(" apply_state='").append(state).append("'");
        if (params.get("id") == null) {
            String studentId = (String) params.get("studentId");
            String applyId = (String) params.get("applyId");
            sql.append(" where student_id='").append(studentId).append("'")
                    .append(" and apply_id='").append(applyId).append("'");
        } else {
            String id = (String) params.get("id");
            sql.append(" where id='").append(id).append("'");
        }

        return sql.toString();
    }


    public String updateApplyContent(Map<String, Object> params) {
        ApplyEntity entity = (ApplyEntity) params.get("entity");
        if (entity == null) {
            return "";
        }
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE apply SET ")
                .append(" form_data='").append(entity.getFormData()).append("',");
        //若没有修改图片
        if (entity.getImage() == null || entity.getImage().equals("")) {
            sql.append(" image='").append(entity.getImage()).append("',");
        }
        sql.append(" apply_state='").append(entity.getApplyState()).append("',")
                .append(" remarks='").append(entity.getRemarks()).append("',")
                .append(" modify_time='").append(new Date()).append("',")
                .append(" WHERE id='").append(entity.getId()).append("'");

        return sql.toString();

    }
}
