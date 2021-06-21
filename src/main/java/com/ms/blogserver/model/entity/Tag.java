package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@ToString
@TableName(value = "ms_tag")
public class Tag {
    private Long id;
    private String name;
    private String nameZh;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalTime createTime;
}
