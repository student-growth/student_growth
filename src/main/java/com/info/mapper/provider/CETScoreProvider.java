package com.info.mapper.provider;

import com.info.entity.CETScoreEntity;
import com.info.mapper.sql.SQLBuilder;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/8/26 / 15:46
 */
public class CETScoreProvider {

    private static final String TABLE_NAME="cet_score";

    public String  selectByIdAndType(Map<String,Object> param){
        String studentId = (String)param.get("id");

        SQLBuilder sql = new SQLBuilder();
        sql.SELECT_ALL(CETScoreEntity.class)
                .FROM(TABLE_NAME)
                .WHERE("student_id='"+studentId+"'");

        return sql.getSQL();
    }
}
