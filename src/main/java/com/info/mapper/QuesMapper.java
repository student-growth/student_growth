package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.QuesEntity;
import com.info.mapper.provider.QuesProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author : yue  2020/7/20 / 21:13
 */
public interface QuesMapper extends BaseMapper<QuesEntity>{

    @InsertProvider(type = QuesProvider.class,method = "insertOne")
    int insertQuest(@Param("question") QuesEntity entity);

    @SelectProvider(type = QuesProvider.class,method = "selectFeedback")
    String selectFeedback(@Param("id") String id);


    @SelectProvider(type = QuesProvider.class,method = "pageSelect")
    List<QuesEntity> selectQuest(@Param("start") int start,
                                 @Param("size") int size);

    @SelectProvider(type = QuesProvider.class,method = "selectByStuID")
    List<QuesEntity> selectByStuID(@Param("studentId") String studentId);

}

