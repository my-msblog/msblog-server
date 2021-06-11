package com.ms.blogserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Data
@ToString
@TableName(value = "")
public class Tags {
    private Long id;
    private String name;
    private String nameZh;
}
