package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/7/8 / 20:24
 *
 * view 层和 service 层的传输对象
 * Controller 和 Service 层交换的数据包
 */
@Data
public class StudentInfoDto {

    private String id;

    private String name;


}
