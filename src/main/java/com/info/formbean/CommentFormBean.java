package com.info.formbean;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/8/20 / 17:31
 * 学生评价表单数据
 */
@Data
public class CommentFormBean {


    //学号
    private String id;

    //被评价学生学号
    private String other;

    //心理分数
    private int psychology;

    //品德分数
    private int moral;

}
