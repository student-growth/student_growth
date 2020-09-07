package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/7/20 / 21:11
 */
@Data
public class QuesDTO {

    //反馈单号
    private String id;

    private String studentId;

    private String title;

    private String content;

    private String receiver;

    private String feedback;

}
