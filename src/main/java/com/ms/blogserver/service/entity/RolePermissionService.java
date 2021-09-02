package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.RolePermission;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
public interface RolePermissionService extends IService<RolePermission> {
    /**
     * 获取角色权限列表
     * @param roleId
     * @return
     */
    List<RolePermission> getAllPermissionByRoleId(Long roleId);
}
