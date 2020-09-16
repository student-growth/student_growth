package com.info.mapper.provider;

import com.info.entity.QuesEntity;
import com.info.mapper.sql.SQLBuilder;
import com.info.util.RandomUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author : yue
 * @since : 2020/7/20 / 21:13
 */
public class QuesProvider {

    private static final int ID_LENGTH=12;
    private static final String TABLE_NAME="question";
    private static final String ID=TABLE_NAME+".id";
    private static final String TITLE =TABLE_NAME+".title";
    private static final String CONTENT =TABLE_NAME+".content";
    private static final String RECEIVER = TABLE_NAME+".receiver";

    public String insertOne(Map<String,Object> params){
        SQLBuilder SQL = new SQLBuilder();
        QuesEntity entity = (QuesEntity)params.get("question");
        SQL.INSERT_INTO(TABLE_NAME,
                ID,"question.student_id",TITLE,CONTENT,RECEIVER,
                "question.feedback")
                .VALUE(RandomUtil.getRandomNum(ID_LENGTH),
                        entity.getStudentId(),
                        entity.getTitle(),
                        entity.getContent(),
                        entity.getReceiver(),
                        entity.getFeedback());

        return SQL.getSQL();
    }


    public String pageSelect(Map<String,Object> params){
        int start = (int) params.get("start");
        int size = (int) params.get("size");
        SQLBuilder SQL = new SQLBuilder();
        return SQL.SELECT(ID,TITLE,CONTENT,RECEIVER,"feedback")
                .FROM(TABLE_NAME).ORDER("modify_time desc")
                .LIMIT(start,size)
                .getSQL();
    }


    public String selectByStuID(Map<String,Object> params){
        String studentId = (String) params.get("studentId");
        SQLBuilder SQL = new SQLBuilder();
        return SQL.SELECT(ID,TITLE,CONTENT,RECEIVER,"feedback")
                .FROM(TABLE_NAME)
                .WHERE("student_id='"+studentId+"'")
                .ORDER("modify_time desc")
                .getSQL();
    }

    public String selectFeedback(Map<String,Object> params){
        String id = (String) params.get("id");
        SQLBuilder sql = new SQLBuilder();
        sql.SELECT("feedback").FROM(TABLE_NAME)
                .WHERE("id='"+id+"'");
        return sql.getSQL();
    }
}
