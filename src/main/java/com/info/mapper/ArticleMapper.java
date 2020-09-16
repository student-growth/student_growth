package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ArticleEntity;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/19 / 19:42
 */
public interface ArticleMapper extends BaseMapper<ArticleEntity> {



}
