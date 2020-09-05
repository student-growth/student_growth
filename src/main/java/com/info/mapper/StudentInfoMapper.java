package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.Student;
import com.info.mapper.provider.StudentProvider;
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

    @SelectProvider(type = StudentProvider.class,method ="selectStudents")
    List<Student> selectStudents(@Param("id") String id,
                                 @Param("grade") String grade);




    @Select("select id,name,password,grade,department,major,sex from student where id=#{id}")
    Student getStudentById(@Param("id") String id);

    /**
     * 批量插入数据
     * @param students 学生列表信息
     * @return 插入的数量
     */
    @InsertProvider(type = com.info.mapper.provider.StudentProvider.class,
            method = "batchInsert")
    int batchInsert(@Param("list") List<Student> students);


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
