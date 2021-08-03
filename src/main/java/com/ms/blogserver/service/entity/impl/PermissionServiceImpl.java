package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.model.entity.Permission;
import com.ms.blogserver.mapper.PermissionMapper;
import com.ms.blogserver.service.entity.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public String getNameById(Long pid) {
        return baseMapper.selectById(pid).getName();
    }
}
