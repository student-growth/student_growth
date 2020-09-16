package com.info.mapper.provider;

import com.info.entity.ApplyEntity;
import com.info.entity.ScholarshipEntity;
import com.info.mapper.sql.SQLBuilder;
import com.info.util.DateUtil;
import com.info.util.RandomUtil;
import jdk.nashorn.api.scripting.ScriptUtils;
import org.springframework.util.unit.DataUnit;

import java.util.Date;
import java.util.Map;

/**
 * @author : yue
 * @since : 2020/8/23 / 16:26
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
                "score",
                "apply_state")
                .VALUE(entity.getId(),
                        entity.getStudentId(),
                        entity.getApplyId(),
                        entity.getApplyName(),
                        entity.getFormTemp(), entity.getFormData(),
                        entity.getImage(),
                        entity.getScore(),
                        entity.getApplyState());
        return sql.getSQL();
    }


    public String selectApplyInfo(Map<String, Object> params) {
        String studentId = (String) params.get("studentId");
        StringBuilder sql = new StringBuilder();
        sql.append("select id ,student_id as studentId,apply_id as applyId, apply_name as applyName ," +
                "form_temp as formTemp,form_data as formData,image,apply_state as applyState,")
                .append(" modify_time as modifyTime")
                .append(" from apply")
                .append(" where student_id='").append(studentId).append("'");
        if (params.get("state") != null) {
            String state = (String) params.get("state");
            sql.append(" and apply_state='").append(state).append("'");
        }
//        if(params.get("semester")!=null){
//            String semester = (String)params.get("semester");
//            String start=semester.substring(0,4)+"-00-00";
//            String end=semester.substring(semester.indexOf("-")+1,4)+"-00-00";
//            sql.append(" and (modify_time between '").append(start).append("'")
//                    .append(" and '").append(end).append("')");
//        }

        return sql.toString();
    }

    public String getTotalScore(Map<String,Object> params){
        String studentId = (String) params.get("studentId");
        String state = (String) params.get("state");
        String start=(String)params.get("start");
        String end=(String)params.get("end");
        StringBuilder sql = new StringBuilder();
        //
        sql.append("select temp.applyId as id, temp.menuId,temp.apply_name as applyName ,temp.score,")
                .append(" apply_project_menu.sort ,temp.modify_time as modifyTime")
                .append(" from apply_project_menu\n")
                .append(" RIGHT join (select apply.id as applyId ,apply_project.menu_id as menuId,")
                .append(" apply.apply_id as projectId,apply.apply_name,apply.score,apply.modify_time \n")
                .append(" from apply LEFT JOIN apply_project\n")
                .append(" on apply.apply_id=apply_project.id\n")
                .append(" where apply.apply_state='").append(state).append("'\n")
                .append(" and apply.student_id='").append(studentId).append("'")
                .append(" and apply.modify_time BETWEEN '").append(start)
                .append("' AND '").append(end).append("') temp")
                .append(" on temp.menuId=apply_project_menu.id;");

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
                .append(" modify_time='")
                .append(DateUtil.getString(new Date(),DateUtil.FORMAT_STYLE_1)).append("'")
                .append(" WHERE id='").append(entity.getId()).append("'");

        return sql.toString();

    }



    public String selectById(Map<String,Object> params){
        String id = (String) params.get("id");
        StringBuilder sql = new StringBuilder();
        sql.append("select id,image")
                .append(" from apply ")
                .append(" where id='").append(id).append("'");

        return sql.toString();
    }


    public String applyScholarship(Map<String,Object> params){
        ScholarshipEntity entity = (ScholarshipEntity) params.get("entity");
        StringBuilder sql =new StringBuilder();
        sql.append("insert into apply_scholarship(id,student_id,apply_name,apply_mark,apply_status,apply_level)")
                .append(" value('").append(RandomUtil.getRandomNum(10)).append("',")
                .append(" '").append(entity.getStudentId()).append("',")
                .append(" '").append(entity.getApplyName()).append("',")
                .append(" '").append(entity.getApplyMark()).append("',")
                .append(" '").append(entity.getApplyStatus()).append("',")
                .append(entity.getApplyLevel()).append(")");

        return sql.toString();
    }


    public String selectScholarship(Map<String,Object> params){
        String studentId=(String)params.get("studentId");
        String semester = (String)params.get("semester");
        String applyName = (String)params.get("applyName");

        StringBuilder sql = new StringBuilder();
        sql.append("select id, apply_mark as applyMark , apply_status as applyStatus,")
                .append(" apply_level as applyLevel,  audit_results as auditResults")
                .append(" from apply_scholarship")
                .append(" where student_id='").append(studentId).append("'")
                .append(" and semester='").append(semester).append("'")
                .append(" and apply_name='").append(applyName).append("'");

        return sql.toString();
    }
}
