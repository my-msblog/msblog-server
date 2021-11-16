package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/16
 */
@Data
@ToString
@TableName("ms_announcement")
public class Announcement {
    private Long id;
    private String announcement;
    private Long createUser;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    private int deleted;
}
