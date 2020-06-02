package com.info.converter;

import com.info.dto.UserInfoDto;
import com.info.entity.UserInfoEntity;
import org.mapstruct.Mapper;

/**
 * @author : yue
 * @Date : 2020/6/2 / 11:23
 * 用户bean转换 接口
 */
@Mapper(componentModel="spring")
public interface UserInfoConverter {

    UserInfoDto userInfoConverter(UserInfoEntity userInfoEntity);
}
