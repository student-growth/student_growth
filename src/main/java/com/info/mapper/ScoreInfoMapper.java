package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ScoreEntity;
import com.info.mapper.provider.ScoreProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : yue 2020/7/11 / 15:29
 */

public interface ScoreInfoMapper extends BaseMapper<ScoreEntity>{


    @SelectProvider(type = ScoreProvider.class,method = "selectOne")
    List<ScoreEntity> selectScoreById(@Param("id") String id);


}
