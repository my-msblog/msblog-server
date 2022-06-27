package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.core.constant.contexts.LoginContexts;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.mapper.UserRoleMapper;
import com.ms.blogserver.model.entity.UserRole;
import com.ms.blogserver.service.entity.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
@Service
public class UserRoleServiceImpl extends EntityServiceImpl<UserRole, UserRoleMapper> implements UserRoleService {

    @Override
    public Long getRidByUid(Long uid) throws CustomException {
        Long rid = baseMapper.selectOne(new QueryWrapper<UserRole>().eq("uid", uid)).getRid();
        if (Objects.isNull(rid)) {
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
        return rid;
    }
}
