package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.UserInfoEntity;

import org.apache.ibatis.annotations.*;

import java.awt.*;

/**
 * @author : yue
 * @Date : 2020/6/2 / 10:39
 */
public interface UserInfoMapper extends BaseMapper<UserInfoMapper> {

    @Select("select user_id, user_name, department, major from db_user_info where user_id = #{uid}")
    @Results(id = "userMap", value = {
            @Result(id = true,column = "user_id",property = "userId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "department" ,property = "department"),
            @Result(column = "major",property = "major")
    })
    UserInfoEntity getOneUser(@Param("uid") String userId);

}
