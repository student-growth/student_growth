package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author : yue
 * @Date : 2020/7/8 / 18:44
 */

public interface StudentInfoMapper extends BaseMapper<Student> {

    /**
     * 分页查询学生信息
     * @param start 开始编号
     * @param size 页面大小
     * @return
     */
    @Select("select id,name,grade,department,major,sex from student" +
            " LIMIT #{start},#{size}")
    List<Student> pageSelectStudent(@Param("start")int start, @Param("size")int size);




    @Select("select id,name,password,grade,department,major,sex from student where id=#{id}")
    Student getStudentById(@Param("id") String id);


    /**
     * 获取密码,登录验证
     * @param id
     * @return
     */
    @SelectProvider(type=com.info.mapper.provider.StudentProvider.class,
    method = "queryPasswordById")
    String getPassword(@Param("id") String id);


    @Update("update student set password=#{password} WHERE id=#{id}")
    int  updatePassword(@Param("id") String id,@Param("password") String password);
}
