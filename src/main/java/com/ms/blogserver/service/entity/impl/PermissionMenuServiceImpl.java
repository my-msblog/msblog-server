package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ms.blogserver.core.base.EntityServiceImpl;
import com.ms.blogserver.mapper.PermissionMenuMapper;
import com.ms.blogserver.model.entity.PermissionMenu;
import com.ms.blogserver.service.entity.PermissionMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
@Service
public class PermissionMenuServiceImpl
        extends EntityServiceImpl<PermissionMenu,PermissionMenuMapper> implements PermissionMenuService {
    @Override
    public List<PermissionMenu> getMenuIdByPid(List<Long> list) {
        return baseMapper.selectList(new QueryWrapper<PermissionMenu>().in("pid",list));
    }
}
