package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 角色对应菜单id
 * @author: zhh
 * @time: 2021/5/21
 */
@Data
@TableName(value = "ms_role_permission")
public class RolePermission implements Serializable {
    private Long id;
    private Long rid;
    private Long pid;
}
