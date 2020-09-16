package com.info.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.AbilityEntity;
import com.info.mapper.provider.AbilityProvider;
import com.info.mapper.provider.StudentProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/9/8 / 15:26
 */
public interface AbilityMapper extends BaseMapper<AbilityEntity> {


    @SelectProvider(type = StudentProvider.class,method ="getComprehensiveRank" )
    List<AbilityEntity>  getComprehensiveRank(@Param("semester") String semester,
                                              @Param("classYear") String classYear,
                                              @Param("type") String type,
                                              @Param("limit") int limit);


    //用于获取奖学金申请的分数
    @SelectProvider(type = AbilityProvider.class,method = "selectAbilityBaseScore")
    AbilityEntity selectAbilityBaseScore(@Param("studentId") String studentId,@Param("semester") String semester);


    @SelectProvider(type = AbilityProvider.class,method = "selectAbilityAllScore")
    AbilityEntity selectAbilityAllScore(@Param("studentId") String studentId,
                                        @Param("semester") String semester);

    @SelectProvider(type = AbilityProvider.class,method = "selectPersonalizedList")
    String selectPersonalizedId(@Param("studentId") String studentId);
}
