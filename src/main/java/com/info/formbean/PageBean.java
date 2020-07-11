package com.info.formbean;

import lombok.Data;

import java.util.List;

/**
 * Created by yue on 2020/4/1.
 * 分页包
 */
@Data
public class PageBean {

    private int currentPage;

    private int pageSize;

    private String query;

    public PageBean(){
        this.currentPage=1;
        //默认页面大小是10
        this.pageSize=10;
        this.query="";
    }

    public PageBean(int current,int size, String query){
       if(current<=0){
           this.currentPage=1;
       }else{
           this.currentPage=current;
       }
       this.pageSize =size;
       this.query = query;
    }
}
