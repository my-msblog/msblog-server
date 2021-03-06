package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
@Data
@TableName(value = "ms_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 3008131596444969296L;
    @TableId
    private Long id;
    @TableField(value = "name_en")
    private String name;
    private String described;
    private String url;
}
