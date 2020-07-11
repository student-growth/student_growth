package com.info.mapper;

import com.info.entity.ApplyScholarship;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Tenghao Deng
 * @create 2020-07-11 20:55
 **/
@Mapper
public interface ScholarshipMapper {
    @Select("SELECT id,student_id,apply_status,reason,create_time,"+
    "modify_time is_delete FROM apply_scholarship WHERE student_id = #{student_id}")
    ApplyScholarship idSelectScholarship(@Param("student_id") String student_id);
}
