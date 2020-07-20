package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ArticleEntity;
import com.info.mapper.provider.ArticleProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/19 / 19:42
 */
public interface ArticleMapper extends BaseMapper<ArticleEntity> {


    @InsertProvider(type = ArticleProvider.class,method = "insertArticle")
    int insertArticle(@Param("article") ArticleEntity entity);



    @SelectProvider(type = ArticleProvider.class,method = "selectOne")
    ArticleEntity  selectArticle(@Param("id") String id);


    @SelectProvider(type = ArticleProvider.class ,method = "pageSelect")
    List<ArticleEntity> selectArticleList(@Param("start") int start,
                                          @Param("size") int size);

}
