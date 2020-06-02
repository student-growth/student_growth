package com.info.service;

import com.info.common.ReturnValue;

import com.info.converter.UserInfoConverter;
import com.info.dto.UserInfoDto;
import com.info.entity.UserInfoEntity;
import com.info.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import io.swagger.annotations.ApiOperation;

import static com.info.common.StateMsg.StateMsg_101;
import static com.info.common.StateMsg.StateMsg_202;

/**
 * @author : yue
 * @Date : 2020/6/2 / 10:34
 */
@Service
public class UserService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserInfoConverter userInfoConverter;

    @ApiOperation(value = "获取学生信息")
    public ReturnValue<UserInfoDto> getStudentInfo(String userId) {
        ReturnValue<UserInfoDto> result = new ReturnValue<>();
        //check params
        if (userId.length() <= 0) {
            result.setStateMsgDetail(StateMsg_101, "用户Id不能为空");
            return result;
        }
        UserInfoEntity user  =  userInfoMapper.getOneUser(userId);
        if(null == user){
            result.setStateMsg(StateMsg_202);
            return result;
        }
        UserInfoDto userDto = userInfoConverter.userInfoConverter(user);
        result.setData(userDto);
        return result;
    }

}
