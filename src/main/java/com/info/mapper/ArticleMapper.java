package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ArticleEntity;
import com.info.mapper.provider.ArticleProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * @author : yue
 * @Date : 2020/7/19 / 19:42
 */
public interface ArticleMapper extends BaseMapper<ArticleEntity> {


    @InsertProvider(type = ArticleProvider.class,method = "insertArticle")
    int insertArticle(@Param("article") ArticleEntity entity);

}
