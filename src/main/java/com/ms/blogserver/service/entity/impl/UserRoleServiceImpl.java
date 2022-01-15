package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.core.constant.contexts.LoginContexts;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.model.entity.UserRole;
import com.ms.blogserver.mapper.UserRoleMapper;
import com.ms.blogserver.service.entity.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public Long getRidByUid(Long uid) throws CustomException {
        try {
            return baseMapper.selectOne(new QueryWrapper<UserRole>().eq("uid",uid)).getRid();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
    }
}
