package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户角色
 * @author: zhh
 * @time: 2021/5/21
 */
@Data
@TableName(value = "ms_role")
public class Role implements Serializable {
    private static final long serialVersionUID = -7108206296562721061L;
    @TableId
    private Long id;
    private String name;
    private String nameZh;
    private int enabled;

    public Role() {
    }
}
