package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/10/30
 */
@Data
@TableName("ms_flow")
public class Flow implements Serializable {
    private static final long serialVersionUID = -2415954874038185527L;
    @TableId
    private Long id;
    private Date time;
    private Integer flow;
}
