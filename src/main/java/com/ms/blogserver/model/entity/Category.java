package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/4
 */
@Data
@TableName(value = "ms_category")
public class Category implements Serializable {
    private static final long serialVersionUID = -8923382842793444344L;
    @TableId
    private Integer id;
    private Integer categoryId;
    private String category;
}
