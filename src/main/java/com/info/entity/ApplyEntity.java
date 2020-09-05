package com.info.entity;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/8/23 / 14:57
 */
@Data
public class ApplyEntity {

    private String id;

    private String studentId;

    private String applyId;

    private String applyName;

    private String formTemp;

    private String formData;

    private String image;

    /**
     * 申请状态
     */
    private String applyState;

    /**
     * 人工审核后的批注
     */
    private String remarks;

}
