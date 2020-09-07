package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/8/23 / 14:59
 */
@Data
public class ApplyDTO {

    //申请单号
    private String id;

    private String studentId;

    private String applyId;

    private String applyName;

    private String formTemp;

    private String formData;

    private String image;

    //申请学生的成绩
    private int  score;

    private String applyState;
}
