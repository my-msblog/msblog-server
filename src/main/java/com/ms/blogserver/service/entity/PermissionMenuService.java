package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.PermissionMenu;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/27
 */
public interface PermissionMenuService extends IService<PermissionMenu> {

    /**
     * 根据权限id获取对应的权限菜单
     * @param list
     * @return
     */
    List<PermissionMenu> getMenuIdByPid(List<Long> list);
}
