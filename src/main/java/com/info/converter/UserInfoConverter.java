package com.info.converter;

import com.info.dto.UserInfoDto;
import com.info.entity.UserInfoEntity;
import org.mapstruct.Mapper;

/**
 * 用户bean 转换
 */
@Mapper(componentModel = "spring")
public interface UserInfoConverter {

    UserInfoDto userInfoConverter(UserInfoEntity userInfoEntity);
}
