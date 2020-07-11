package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ScoreEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/11 / 15:29
 */

public interface ScoreInfoMapper extends BaseMapper<ScoreEntity>{

    /**
     *  todo 此处需要修改列名称
     */
    @Results(id="scoreMap",
            value = {
                @Result(column = "course_id" ,property = "courseId"),
                @Result(column = "course_name" ,property = "courseName"),
                @Result(column = "semester" ,property ="semester" ),
                @Result(column = "zscj",property = "score"),
                @Result(column = "pscj",property = "dailyScore"),
                @Result(column = "jmcj",property = "finalScore"),
                @Result(column = "retest_score",property = "retestScore"),
                @Result(column = "rebuild_score",property = "rebuildScore")
    })
    @Select("select course_id,course_name,semester,zscj,pscj,jmcj,rebuild_score,retest_score from " +
            " score where student_id=#{id}")
    List<ScoreEntity> selectScoreById(@Param("id") String id);

}
