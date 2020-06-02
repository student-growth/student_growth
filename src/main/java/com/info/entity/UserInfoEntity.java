package com.info.entity;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author : yue
 * @Date : 2020/5/31 / 23:14
 */
@Data
@ToString
@Accessors(chain = true)
@TableName("db_user_info")
public class UserInfoEntity extends Model<UserInfoEntity>{
    /**
     * user_id 学号
     */
    @TableId(value = "userId")
    private String userId;
    private String userName;
    private String department;
    private String major;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
