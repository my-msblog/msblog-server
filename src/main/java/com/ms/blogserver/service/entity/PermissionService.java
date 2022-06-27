package com.ms.blogserver.service.entity;

import com.ms.blogserver.core.base.EntityService;
import com.ms.blogserver.mapper.PermissionMapper;
import com.ms.blogserver.model.entity.Permission;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
public interface PermissionService extends EntityService<Permission, PermissionMapper> {
    /**
     * 获取权限名称
     * @param pid
     * @return
     */
    String getNameById(Long pid);
}

