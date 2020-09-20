package com.info.entity;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/9/10 / 12:06
 */
@Data
public class ScholarshipEntity {

    private String id;

    private String studentId;

    private String applyName;

    private String applyStatus;

    private String applyMark;

    private int applyLevel;

    private String semester;

    //审核结果
    private String auditResults;



}
