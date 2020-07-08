package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/8 / 18:44
 */
public interface StudentInfoMapper extends BaseMapper<Student> {

    /**
     * 分页查询学生信息
     * @param start
     * @param size 页面大小
     * @return
     */
    @Select("select id,name,grade,department,major,sex from student" +
            " LIMIT #{start},#{size}")
    List<Student> pageSelectStudent(@Param("start")int start, @Param("size")int size);
}
