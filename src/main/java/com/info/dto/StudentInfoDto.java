package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * 2020/7/8 / 20:24
 *
 * Controller 和 Service 层交换的数据包
 */
@Data
public class StudentInfoDto {

    private String id;

    private String name;

    private String grade;

    private String department;

    private String major;

    private String sex;

}
