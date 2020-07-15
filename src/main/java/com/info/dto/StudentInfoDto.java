package com.info.dto;

import com.info.annotation.ExcelColumn;
import lombok.Data;

/**
 * @author : yue
 * 2020/7/8 / 20:24
 *
 * Controller 和 Service 层交换的数据包
 */
@Data
public class StudentInfoDto {

    @ExcelColumn(name = "学号")
    private String id;

    @ExcelColumn(name = "姓名")
    private String name;

    @ExcelColumn(name = "学院")
    private String department;

    @ExcelColumn(name = "专业")
    private String major;

    @ExcelColumn(name = "班级")
    private String grade;

    @ExcelColumn(name = "性别")
    private String sex;
}
