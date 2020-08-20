package com.info.entity;

import lombok.Data;

/**
 * @author : yue
 * @Date : 2020/8/20 / 18:08
 */
@Data
public class AssessEntity {


    public AssessEntity(String id,String other,int psy,int moral){
        this.id=id;
        this.other = other;
        this.psy=psy;
        this.moral = moral;
    }
    public AssessEntity(){

    }
    private String id;

    private String other;

    private int psy;

    private int moral;

}
