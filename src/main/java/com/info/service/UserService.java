package com.info.service;

import com.info.common.ReturnValue;
import com.info.common.StateMsg;
import com.info.converter.UserInfoConverter;
import com.info.dto.UserInfoDto;
import com.info.dto.UserRegisterDto;
import com.info.entity.UserInfoEntity;

import com.info.mapper.UserInfoMapper;

import com.info.util.BeanUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yue
 *
 */
@Service
public class UserService {

    @Resource
    private UserInfoConverter userInfoConverter;

    @Resource
    private UserInfoMapper userInfoMapper;

}
