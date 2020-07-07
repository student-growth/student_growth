package com.info.service;

import com.info.common.ReturnValue;
import com.info.common.StateMsg;
import com.info.converter.UserInfoConverter;
import com.info.dto.UserInfoDto;
import com.info.dto.UserRegisterDto;
import com.info.entity.UserInfoEntity;
import com.info.formbean.PageBean;
import com.info.formbean.UserFormBean;
import com.info.formbean.UserIdFormBean;
import com.info.mapper.UserInfoMapper;

import com.info.util.BeanUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by TerryJ on 2020/03/04.
 */
@Service
public class UserService {

    @Resource
    private UserInfoConverter userInfoConverter;

    @Resource
    private UserInfoMapper userInfoMapper;

    @ApiOperation(value = "获取用户信息")
    public ReturnValue<UserInfoDto> getUserInfo(UserIdFormBean userIdFormBean) {
        ReturnValue returnValue = new ReturnValue();

        int userId = userIdFormBean.getUserId();
        if (userId <= 0) {
            returnValue.setStateMsg(StateMsg.StatusMsg_101);
            return returnValue;
        }

        UserInfoEntity userInfoEntity = userInfoMapper.getOneUser(userId);
        if (null == userInfoEntity) {
            returnValue.setStateMsg(StateMsg.StatusMsg_202);
            return returnValue;
        }

        UserInfoDto userInfoDto = userInfoConverter.userInfoConverter(userInfoEntity);

        returnValue.setObject(userInfoDto);
        return returnValue;
    }


    @ApiOperation(value = "用户注册")
    public ReturnValue register(UserFormBean userFormBean) {
        ReturnValue res = new ReturnValue();
        //check parameter
        //nickname不能重复；密码，手机号码不能为空;
        String name = userFormBean.getNickname();
        String password = userFormBean.getPassword();
        String phone = userFormBean.getPhonenumber();

        if (null == phone || null == password) {
            res.setStateMsg(StateMsg.StatusMsg_101);
            return res;
        }
        List<String> names = userInfoMapper.getAllName();
        if (names.contains(name)) {
            res.setStateMsg(StateMsg.StatusMsg_146);
            return res;
        }
        //create a bean
        UserInfoEntity bean = new UserInfoEntity();
        bean.setNickname(name);
        bean.setPassword(password);
        bean.setPhonenumber(phone);
        int resId = userInfoMapper.saveDemo(bean);
        if (resId > 0) {
            res.setStateMsg(StateMsg.StatusMsg_100);
            return res;
        } else {
            res.setStateMsg(StateMsg.StatusMsg_144);
            return res;
        }
    }


    public ReturnValue<UserInfoDto> getPageUserInfo(PageBean bean) {
        ReturnValue<UserInfoDto> res = new ReturnValue<>();
        //check parameter
        if (null == bean.getPhone()) {
            res.setStateMsg(StateMsg.StatusMsg_145);
            return res;
        }

        int current = bean.getCurrpage();
        int pageSize = bean.getPageSize();

        String phone = "%"+bean.getPhone()+"%";


        List<UserInfoEntity> users = userInfoMapper
                .getAllUserInfo((current-1) * pageSize,
                        bean.getPageSize(),
                        phone);

        List<UserInfoDto> userDto = users.stream().map(user->{
            UserInfoDto dto = new UserInfoDto();
            dto.setNickname(user.getNickname());
            dto.setPhonenumber(user.getPhonenumber());
            return dto;
        }).collect(Collectors.toList());

        int totalUser = userInfoMapper.countUser();
        int totalPage = totalUser / pageSize;
        res.setTotalpages(totalPage);
        res.setTotalrecords(totalUser);
        res.setCurrpage(current);
        res.setList(userDto);
        return res;
    }
}
