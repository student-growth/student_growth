package com.info.controller;

import com.info.common.ReturnValue;
import com.info.dto.UserInfoDto;
import com.info.service.UserService;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yue
 * @Date : 2020/6/2 / 10:23
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/getUser")
    public ReturnValue<UserInfoDto> getUserInfo(@RequestParam("userId") String userId){
         return userService.getStudentInfo(userId);
    }

}
