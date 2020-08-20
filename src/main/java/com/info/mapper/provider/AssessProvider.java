package com.info.mapper.provider;

import com.info.entity.AssessEntity;
import com.info.mapper.sql.SQLBuilder;
import com.info.util.DateUtil;
import org.springframework.util.unit.DataUnit;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/8/20 / 18:11
 */
public class AssessProvider {

    private final static String TABLE_NAME = "student_assess";
    private final static String COLUMN_NAME = "self_id,other_id,psy,moral";

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
                .append(entity.getPsy()).append(",")
                .append(entity.getMoral())
                .append(")");
        return sql.toString();
    }


    public String updateByPrimary(Map<String,Object> param){
        AssessEntity entity  =  (AssessEntity) param.get("entity");
        SQLBuilder sql = new SQLBuilder();
        sql.UPDATE(TABLE_NAME)
                .SET("psy="+entity.getPsy(),"moral="+entity.getMoral(),
                        "modify_time='"+ DateUtil.getNowDateString(DateUtil.FORMAT_STYLE_2)+"'")
                .WHERE("self_id="+entity.getId(),"other_id="+entity.getOther());
        return sql.getSQL();
    }
}
