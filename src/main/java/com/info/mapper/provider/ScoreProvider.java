package com.info.mapper.provider;

import com.info.entity.ScoreEntity;
import com.info.mapper.sql.SQLBuilder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/7/21 / 18:49
 */
public class ScoreProvider {

    private static final String TABLE_NAME="score";

    public String selectScore(Map<String,Object> params){
        String id = (String) params.get("id");
        SQLBuilder SQL = new SQLBuilder();
        SQL.SELECT_ALL(ScoreEntity.class)
                .FROM(TABLE_NAME);
        if(params.get("semester")==null){

            SQL.WHERE("  student_id="+id);
        }else{
            String semester = (String)params.get("semester");
            SQL.WHERE("  student_id='"+id+"'","semester='"+semester+"'");
        }


        return SQL.getSQL();
    }




}
