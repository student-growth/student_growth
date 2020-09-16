package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ScholarshipEntity;
import com.info.mapper.provider.ApplyProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/9/10 / 14:51
 */
public interface ScholarshipMapper extends BaseMapper<ScholarshipEntity>{


    @InsertProvider(type = ApplyProvider.class,method = "applyScholarship")
    int insertScholarship(@Param("entity") ScholarshipEntity entity);


    @SelectProvider(type = ApplyProvider.class,method = "selectScholarship")
    List<ScholarshipEntity> selectScholarship(@Param("studentId") String Id,
                                              @Param("semester") String semester,
                                              @Param("applyName") String applyName);
}
