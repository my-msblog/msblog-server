package com.ms.blogserver.service.entity.impl;

import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.mapper.PermissionMapper;
import com.ms.blogserver.model.entity.Permission;
import com.ms.blogserver.service.entity.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
@Service
public class PermissionServiceImpl extends EntityServiceImpl<Permission,PermissionMapper> implements PermissionService {
    @Override
    public String getNameById(Long pid) {
        return baseMapper.selectById(pid).getName();
    }
}
