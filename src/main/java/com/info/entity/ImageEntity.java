package com.info.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author : yue
 * @Date : 2020/7/12 / 17:26
 */
@Data
public class ImageEntity {

    private String id;

    private String name;

    private String path;

    /**
     * 图片上传时间
     */
    private Date createTime;

}
