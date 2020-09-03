package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ApplyEntity;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/8/23 / 16:18
 */
public interface ApplyMapper extends BaseMapper<ApplyEntity> {

    @InsertProvider(type = com.info.mapper.provider.ApplyProvider.class,
            method = "insertApplyInfo")
    Integer insert(@Param("entity") ApplyEntity entity);

    @SelectProvider(type = com.info.mapper.provider.ApplyProvider.class,
            method = "selectApplyInfo")
    List<ApplyEntity> selectApplyInfo(@Param("studentId") String studentId);

    @SelectProvider(type = com.info.mapper.provider.ApplyProvider.class,
            method = "selectApplyInfo")
    List<ApplyEntity> selectApplyByState(@Param("studentId") String id,@Param("state") String state);
}
