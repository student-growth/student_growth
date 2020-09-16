package com.info.mapper.provider;

import com.info.entity.AssessEntity;
import com.info.mapper.sql.SQLBuilder;
import com.info.util.DateUtil;
import org.springframework.util.unit.DataUnit;

import java.util.Map;

/**
 * @author : yue
 * @since : 2020/8/20 / 18:11
 * 学生互评分数
 */
public class AssessProvider {

    private final static String TABLE_NAME = "student_assess";
    private final static String COLUMN_NAME = "self_id,other_id,semester,psy,moral";

    public String selectByPrimary(Map<String,Object> param){
        String id  = (String) param.get("id");
        String other = (String)param.get("other");
        SQLBuilder sql = new SQLBuilder();
        sql.SELECT(COLUMN_NAME)
                .FROM(TABLE_NAME)
                .WHERE("self_id="+id,"other_id="+other);
        return sql.getSQL();
    }


    public String saveAssess(Map<String, Object> params) {

        AssessEntity entity = (AssessEntity) params.get("entity");
        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(TABLE_NAME)
                .append(" (").append(COLUMN_NAME).append(") ")
                .append("VALUE (")
                .append("'").append(entity.getId()).append("',")
                .append("'").append(entity.getOther()).append("',")
                .append("'").append(entity.getSemester()).append("',")
                .append(entity.getPsy()).append(",")
                .append(entity.getMoral())
                .append(")");
        return sql.toString();
    }


    public String updateByPrimary(Map<String,Object> param){
        AssessEntity entity = (AssessEntity) param.get("entity");
        SQLBuilder sql = new SQLBuilder();
        sql.UPDATE(TABLE_NAME)
                .SET("psy="+entity.getPsy(),"moral="+entity.getMoral(),
                        "modify_time='"+ DateUtil.getNowDateString(DateUtil.FORMAT_STYLE_2)+"'")
                .WHERE("self_id="+entity.getId(),"other_id="+entity.getOther());
        return sql.getSQL();
    }


    //查询指定学年的[自我评价]分数
    public String selectSelf(Map<String, Object> params) {
        String id = (String) params.get("id");
        String semester = (String) params.get("semester");
        StringBuilder sql = new StringBuilder();
        sql.append("select moral,psy")
                .append(" from student_assess")
                .append(" where self_id='").append(id).append("'")
                .append(" and other_id='").append(id).append("'")
                .append(" and semester='").append(semester).append("'");

        return sql.toString();
    }
    //查询指定学年我的[学生互评]分数
    public String selectOther(Map<String, Object> params) {
        String id = (String) params.get("id");
        String other = (String) params.get("other");
        String semester = (String) params.get("semester");
        StringBuilder sql = new StringBuilder();
        sql.append("select other_id,moral,psy from student_assess")
                .append(" where self_id='").append(id).append("'")
                .append(" and other_id='").append(other).append("'")
                .append(" and semester='").append(semester).append("'");

        return sql.toString();
    }


    public String selectAllScore(Map<String,Object> params){
        String id = (String) params.get("id");
        String semester = (String) params.get("semester");
        StringBuilder sql = new StringBuilder();
        sql.append("select other_id as other,moral,psy,semester from student_assess")
                .append(" where self_id='").append(id).append("'")
                .append(" and semester='").append(semester).append("'");
        return sql.toString();
    }
}
