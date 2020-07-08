package com.info.converter;


import com.info.dto.StudentInfoDto;
import com.info.entity.UserInfoEntity;
import org.mapstruct.Mapper;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:22
 */
@Mapper(componentModel = "spring")
public interface StudentConverter {

    StudentInfoDto stuInfoConverter(UserInfoEntity entity);
}
