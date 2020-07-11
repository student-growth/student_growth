package com.info.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Tenghao Deng
 * @create 2020-07-11 21:25
 **/
@Data
@ToString
@Accessors(chain = true)
@TableName("apply_scholarship")
public class ApplyScholarship {
    private String id;
    private String student_id;
    private String apply_status;
    private String reason;
    private Date create_time;
    private Date modify_time;
}
