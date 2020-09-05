package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.PsychologyEntity;
import com.info.mapper.provider.PsychologyProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;


/**
 * @author : yue
 * @Date : 2020/9/4 / 14:13
 */
public interface PsychologyMapper extends BaseMapper<PsychologyEntity>{


    @InsertProvider(type = PsychologyProvider.class,method ="insertOne")
    int insertOne(@Param("entity") PsychologyEntity entity);


    @SelectProvider(type = PsychologyProvider.class,method = "selectById")
    List<PsychologyEntity> selectById(@Param("id") String id,
                                      @Param("name") String name);

}
