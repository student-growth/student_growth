package com.info.entity;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/7/20 / 21:05
 * 问题反馈实体
 */
@Data
public class QuesEntity {

    private String id;

    private String studentId;

    private String title;

    private String content;

    private String receiver;

    private String feedback;



}
