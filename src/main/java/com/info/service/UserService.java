package com.info.service;

import com.info.common.ReturnValue;
import com.info.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : yue
 * @Date : 2020/6/2 / 10:34
 */
@Service
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;


}
