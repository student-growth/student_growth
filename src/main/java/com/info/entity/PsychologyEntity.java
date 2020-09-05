package com.info.entity;

import com.info.annotation.SQLAlias;
import lombok.Data;

import java.util.Date;

/**
 * @author : yue
 * @Date : 2020/9/4 / 14:09
 * psychology test
 * table Name: psy_test
 */
@Data
public class PsychologyEntity {

    @SQLAlias(name = "student_id")
    private String studentId;

    @SQLAlias(name = "test_type")
    private String testName;

    @SQLAlias(name = "test_time")
    private Date testTime;

    private String result;
}
