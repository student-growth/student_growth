package com.info.mapper.provider;

import com.info.entity.QuesEntity;
import com.info.mapper.sql.SQLBuilder;
import com.info.util.RandomUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/7/20 / 21:13
 */
public class QuesProvider {

    private static final int ID_LENGTH=12;
    private static final String TABLE_NAME="question";
    private static final String ID=TABLE_NAME+".id";
    private static final String CATEGORY =TABLE_NAME+".category";
    private static final String TITLE =TABLE_NAME+".title";
    private static final String CONTENT =TABLE_NAME+".content";
    private static final String RECEIVER = TABLE_NAME+".receiver";

    public String insertOne(Map<String,Object> params){
        SQLBuilder SQL = new SQLBuilder();
        QuesEntity entity = (QuesEntity)params.get("question");
        SQL.INSERT_INTO(TABLE_NAME,ID,CATEGORY,TITLE,CONTENT,RECEIVER)
                .VALUE(RandomUtil.getRandomNum(ID_LENGTH),
                        entity.getCategory(),
                        entity.getTitle(),
                        entity.getContent(),
                        entity.getReceiver());

        return SQL.getSQL();
    }
}
