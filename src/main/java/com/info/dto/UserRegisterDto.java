package com.info.dto;

import lombok.Data;

/**
 * Created by yue on 2020/4/1.
 */
@Data
public class UserRegisterDto {

    private int uid;
    private String nickname;

    private String pwd;
    private String phonenumber;
}
