package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.Permission;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 获取权限名称
     * @param pid
     * @return
     */
    String getNameById(Long pid);
}

