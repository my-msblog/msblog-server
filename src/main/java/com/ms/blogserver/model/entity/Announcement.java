package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/11/16
 */
@Data
@TableName("ms_announcement")
public class Announcement implements Serializable {
    private static final long serialVersionUID = 5060945085132115549L;
    @TableId
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
