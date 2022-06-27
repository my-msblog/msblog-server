package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.mapper.RolePermissionMapper;
import com.ms.blogserver.model.entity.RolePermission;
import com.ms.blogserver.service.entity.RolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
@Service
public class RolePermissionServiceImpl
        extends EntityServiceImpl<RolePermission,RolePermissionMapper> implements RolePermissionService {

    @Override
    public List<RolePermission> getAllPermissionByRoleId(Long roleId) {
        List<RolePermission> list = baseMapper.selectList(new QueryWrapper<RolePermission>().eq("rid",roleId));
        return list;
    }
}
