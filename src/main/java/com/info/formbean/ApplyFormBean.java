package com.info.formbean;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/8/23 / 13:26
 * 提交申请的表单
 */
@Data
public class ApplyFormBean {

    //学号
    private String studentId;

    //申请的项目Id
    private String applyId;

    //申请项目名称
    private String applyName;

    //表单模板
    private String formTemp;

    //提交的表单信息
    private String formData;


}
