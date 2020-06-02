package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.UserInfoEntity;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author : yue
 * @Date : 2020/6/2 / 10:39
 */
public interface UserInfoMapper extends BaseMapper<UserInfoMapper> {
    @Select("select user_id, user_name, department, major from db_user_info where user_id = #{uid}")
    UserInfoEntity getOneUser(@Param("uid") int userId);

}
