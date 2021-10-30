package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: zhh
 * @time: 2021/10/30
 */
@Data
@ToString
@TableName("ms_flow")
public class Flow implements Serializable {
    private Long id;
    private Date time;
    private int flow;
}
