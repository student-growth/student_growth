package com.info.controller;

import com.info.common.ReturnValue;
import com.info.dto.UserInfoDto;
import com.info.dto.UserRegisterDto;
import com.info.formbean.PageBean;
import com.info.formbean.UserFormBean;
import com.info.formbean.UserIdFormBean;
import com.info.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TerryJ on 2020/03/04.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ReturnValue<UserInfoDto> getUserInfo(@RequestBody UserIdFormBean userIdFormBean) {
        return userService.getUserInfo(userIdFormBean);
    }

    //注册接口
    @ApiOperation("用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ReturnValue  register(@RequestBody UserFormBean userFormBean) {
        return userService.register(userFormBean);
    }

    //分页接口,取出手机号码为 1345开头的用户列表信息,每页3条记录。
    @ApiOperation("查询用户信息")
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
    public ReturnValue<UserInfoDto> getAllUser(@RequestBody PageBean userPageBean){
        return userService.getPageUserInfo(userPageBean);
    }
}

