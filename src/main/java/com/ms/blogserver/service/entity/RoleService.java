package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.Role;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id获取角色信息
     * @param userId
     * @return
     */
    String getRoleByUserId(Long userId);
}
