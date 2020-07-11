package com.info.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author : yue
 * @Date : 2020/7/8 / 18:34
 * 学生实体
 */
@Data
@ToString
@TableName("student")
public class Student {
    private String id;

    private String name;

    private String password;

    private String grade;

    private String department;

    private String major;

    private String sex;

    private String isDelete;
}
