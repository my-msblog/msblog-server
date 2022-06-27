package com.ms.blogserver.service.entity;

import com.ms.blogserver.core.base.EntityService;
import com.ms.blogserver.mapper.RolePermissionMapper;
import com.ms.blogserver.model.entity.RolePermission;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
public interface RolePermissionService extends EntityService<RolePermission, RolePermissionMapper> {
    /**
     * 获取角色权限列表
     * @param roleId
     * @return
     */
    List<RolePermission> getAllPermissionByRoleId(Long roleId);
}
