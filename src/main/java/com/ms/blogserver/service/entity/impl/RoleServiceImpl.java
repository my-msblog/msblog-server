package com.ms.blogserver.service.entity.impl;

import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.core.constant.contexts.ErrorContexts;
import com.ms.blogserver.core.exception.ProgramException;
import com.ms.blogserver.mapper.RoleMapper;
import com.ms.blogserver.model.entity.Role;
import com.ms.blogserver.service.entity.RoleService;
import com.ms.blogserver.service.entity.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
@Service
public class RoleServiceImpl extends EntityServiceImpl<Role,RoleMapper> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;


    @Override
    public String getRoleByUserId(Long userId) {
        Long rid = userRoleService.getRidByUid(userId);
        Role role = baseMapper.selectById(rid);
        if (Objects.isNull(role)){
            throw new ProgramException(ErrorContexts.DATABASE_INNER_TABLE);
        }
        return role.getName();
    }
}
