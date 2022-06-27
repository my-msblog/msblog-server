package com.ms.blogserver.service.entity;

import com.ms.blogserver.core.base.EntityService;
import com.ms.blogserver.mapper.RoleMapper;
import com.ms.blogserver.model.entity.Role;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
public interface RoleService extends EntityService<Role, RoleMapper> {

    /**
     * 根据用户id获取角色信息
     * @param userId
     * @return
     */
    String getRoleByUserId(Long userId);
}
