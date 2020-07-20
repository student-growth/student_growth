package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.QuesEntity;
import com.info.mapper.provider.QuesProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

/**
 * @author : yue  2020/7/20 / 21:13
 */
public interface QuesMapper extends BaseMapper<QuesEntity>{

    @InsertProvider(type = QuesProvider.class,method = "insertOne")
    int insertQuest(@Param("question") QuesEntity entity);
}
