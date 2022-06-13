package com.ms.blogserver.model.entity;

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
@TableName(value = "ms_permission_menu")
public class PermissionMenu implements Serializable {
    private static final long serialVersionUID = 287724524931373237L;
    @TableId
    private Long id;
    private Long pid;
    private int mid;

}
