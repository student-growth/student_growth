package com.info.entity;

import com.info.annotation.SQLAlias;
import lombok.Data;
import lombok.Value;

/**
 * @author : yue
 * @Date : 2020/8/22 / 22:13
 */
@Data
public class ApplyProjectEntity {


    private String id;

    @SQLAlias(name = "menu_id")
    private String menuId;

    @SQLAlias(name = "menu_name")
    private String menuName;

    private String name;


}
