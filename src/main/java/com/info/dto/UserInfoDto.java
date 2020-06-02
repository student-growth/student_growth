package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/6/2 / 11:01
 * service -- dto --web
 * dto作为web层和service层的数据传递
 */
@Data
public class UserInfoDto {
    private String userId;
    private String userName;
    private String department;
    private String major;
}
