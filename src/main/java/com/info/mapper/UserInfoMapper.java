package com.info.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.info.entity.UserInfoEntity;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by TerryJ on 2020/03/04.
 *
 */
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

	@Select("select uid, nickname, phonenumber from db_user_info where uid = #{uid}")
	UserInfoEntity getOneUser(@Param("uid") int userid);

	@Insert("insert into db_user_info (nickname,phonenumber) values (#{nickname},#{phonenumber})  ")
	int saveDemo(UserInfoEntity userInfoEntity);

	@Select("select nickname from db_user_info")
	List<String> getAllName();

	@Select("select nickname , phonenumber from db_user_info " +
			" where db_user_info.phonenumber like #{number} limit #{begin} , #{pageSize}")
	List<UserInfoEntity> getAllUserInfo(@Param("begin") int begin,
                                        @Param("pageSize") int pageSize,
                                        @Param("number") String phone);

	@Select("select count(uid) from db_user_info")
	int countUser();
}
