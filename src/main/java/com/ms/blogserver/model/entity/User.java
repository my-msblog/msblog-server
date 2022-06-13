package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@TableName(value = "ms_user")
public class User implements Serializable {

    private static final long serialVersionUID = -8727723255082076014L;
    @TableId(type = IdType.ASSIGN_ID)
    private Long id ;
    private String username;
    private String pwd;
    private String phone;
    private String introduction;
    private int sex;
    private String email;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @Version
    @TableField(fill = FieldFill.INSERT)
    private int version;
    @TableLogic
    private int deleted;


    public User() {
    }

    public User(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public User(String username, String pwd, String phone, String email) {
        this.username = username;
        this.pwd = pwd;
        this.phone = phone;
        this.email = email;
    }
}
