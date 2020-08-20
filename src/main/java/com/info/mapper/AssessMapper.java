package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.AssessEntity;
import org.apache.ibatis.annotations.InsertProvider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @author : yue
 *         2020/8/20 / 18:09
 */
public interface AssessMapper extends BaseMapper<AssessEntity> {

    @InsertProvider(type = com.info.mapper.provider.AssessProvider.class,
            method = "saveAssess")
    Integer insert(@Param("entity") AssessEntity entity);


    @SelectProvider(type = com.info.mapper.provider.AssessProvider.class,
            method = "selectByPrimary")
    AssessEntity selectByPrimary(@Param("id") String id, @Param("other") String other);

    @UpdateProvider(type = com.info.mapper.provider.AssessProvider.class,
            method = "updateByPrimary")
    int update(@Param("entity") AssessEntity entity);
}
