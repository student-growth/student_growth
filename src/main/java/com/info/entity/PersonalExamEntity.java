package com.info.entity;

import com.info.annotation.SQLAlias;
import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/8/27 / 15:43
 */
@Data
public class PersonalExamEntity {

    private String id;

    private String title;

    @SQLAlias(name = "content_path")
    private String contentPath;

    private String description;

    @SQLAlias(name = "result_item")
    private String resultItem;

}
