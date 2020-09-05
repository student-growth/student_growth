package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.AssessEntity;

import com.info.mapper.provider.AssessProvider;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

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


    @SelectProvider(type = AssessProvider.class,method = "selectAllScore")
    List<AssessEntity> selectAllScore(@Param("id") String id,
                                      @Param("semester") String semester);




    @SelectProvider(type = AssessProvider.class,method = "selectSelf")
    AssessEntity selectSelfScore(@Param("id") String id,
                                     @Param("semester") String semester);


    @SelectProvider(type = AssessProvider.class,method = "selectOther")
    List<AssessEntity> selectOtherScore(@Param("id") String id,
                                            @Param("other") String other,
                                            @Param("semester") String semester);
}
