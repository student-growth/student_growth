package com.info.mapper.extra;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.extra.ApplyExtraEntity;
import com.info.mapper.provider.ApplyProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/9/6 / 9:56
 *
 */
public interface ApplyExtraMapper extends BaseMapper<ApplyExtraEntity> {


    @SelectProvider(type = ApplyProvider.class,method = "getTotalScore")
    List<ApplyExtraEntity> getTotalScore(@Param("studentId") String studentId,
                                         @Param("start") String start,
                                         @Param("end") String end,
                                         @Param("state") String state);
}
