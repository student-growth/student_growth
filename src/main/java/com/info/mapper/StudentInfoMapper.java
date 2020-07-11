package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.ScoreEntity;
import com.info.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/8 / 18:44
 */

public interface StudentInfoMapper extends BaseMapper<Student> {

    //todo 此处需要修改列名称
    @Results(id="scoreMap",value = {
            @Result(column = "course_id" ,property = "courseId"),
            @Result(column = "course_name" ,property = "courseName"),
            @Result(column = "semester" ,property ="semester" ),
            @Result(column = "zscj",property = "score"),
            @Result(column = "pscj",property = "dailyScore"),
            @Result(column = "jmcj",property = "finalScore"),
            @Result(column = "retest_score",property = "retestScore"),
            @Result(column = "rebuild_score",property = "rebuildScore")
    })

    /**
     * 分页查询学生信息
     * @param start 开始编号
     * @param size 页面大小
     * @return
     */
    @Select("select id,name,grade,department,major,sex from student" +
            " LIMIT #{start},#{size}")
    List<Student> pageSelectStudent(@Param("start")int start, @Param("size")int size);


    /**
     * 获取密码
     * @param id
     * @return
     */
    @Select("select password from student where id=#{id}")
    String getPassword(@Param("id") String id);


//    @Select("select course_id,course_name,semester,zscj,pscj,jmcj,rebuild_score,retest_score from " +
//            " score where id=#{id}")
//    @ResultMap("scoreMap")
//    List<ScoreEntity> getScoreById(@Param("id") String id);

}
