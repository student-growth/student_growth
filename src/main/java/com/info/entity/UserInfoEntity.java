package com.info.entity;


import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * Created by TerryJ on 2020/03/04.
 *
 */
@ToString
@Data
@Accessors(chain = true)
@TableName("db_user_info")
public class UserInfoEntity extends Model<UserInfoEntity> {

    @TableId(value = "uid", type = IdType.AUTO)
    private int uid;

    private String nickname;

    private String password;

    private String phonenumber;

    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

    @Override
    public String toString() {
        return " ";
    }
}
