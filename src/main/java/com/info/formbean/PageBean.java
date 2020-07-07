package com.info.formbean;

import lombok.Data;

import java.util.List;

/**
 * Created by yue on 2020/4/1.
 */
@Data
public class PageBean {
    //当前页面
    private int currpage;
    //页面大小
    private int pageSize;
    //查询条件参数
    private String phone;
}
