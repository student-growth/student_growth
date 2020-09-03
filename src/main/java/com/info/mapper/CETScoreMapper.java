package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.CETScoreEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/8/26 / 15:45
 */
public interface CETScoreMapper extends BaseMapper<CETScoreEntity> {

    @SelectProvider(type = com.info.mapper.provider.CETScoreProvider.class,
            method = "selectByIdAndType")
    List<CETScoreEntity> selectByIdAndType(@Param("id") String id);




}
