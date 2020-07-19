package com.info.dto;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/7/19 / 17:55
 */
@Data
public class ArticleDTO {

    private String title;

    private String stream;

    private String author;

    /**
     * 文章摘要 abstract
     */
    private String summary;
}
