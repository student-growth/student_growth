package com.info.mapper.provider;

import com.info.entity.ArticleEntity;
import com.info.util.RandomUtil;

import java.util.Map;

/**
 * @author : yue
 * @Date : 2020/7/19 / 19:44
 */

public class ArticleProvider {

    private static final String TABLE_NAME="news";
    private static final String ID=TABLE_NAME+".id";
    private static final String TITLE = TABLE_NAME+".title";
    private static final String ABSTRACT  = TABLE_NAME+".abstract";
    private static final String AUTHOR =TABLE_NAME+".author";
    private static final String FILE_NAME = TABLE_NAME+".file_name";


    public String insertArticle(Map<String,Object> params){
        ArticleEntity article = (ArticleEntity)params.get("article");
        StringBuilder sql =new StringBuilder();
        article.setId(RandomUtil.getRandomNum(8));
        sql.append("insert into ").append(TABLE_NAME).append("(")
                .append(ID).append(",")
                .append(TITLE).append(",")
                .append(ABSTRACT).append(",")
                .append(AUTHOR).append(",")
                .append(FILE_NAME).append(") values (")
                .append("'").append(article.getId()).append("',")
                .append("'").append(article.getTitle()).append("',")
                .append("'").append(article.getSummary()).append("',")
                .append("'").append(article.getAuthor()).append("',")
                .append("'").append(article.getFileName()).append("')")
                ;
        return sql.toString();
    }


    public String selectOne(Map<String,Object> params){
        String id =(String)params.get("id");
        StringBuilder sql =new StringBuilder();
        sql.append("select ")
                .append(TITLE).append(",")
                .append(ABSTRACT).append(",")
                .append(AUTHOR).append(",")
                .append(FILE_NAME).append( " as fileName")
                .append(" from ")
                .append(TABLE_NAME).append( " where id=").append(id)
        ;

        return sql.toString();
    }
}
