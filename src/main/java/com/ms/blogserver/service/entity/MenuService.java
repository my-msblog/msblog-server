package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ms.blogserver.model.entity.Menu;
import com.ms.blogserver.model.vo.MenuVO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
public interface MenuService extends IService<Menu> {
    /**
     * 获取当前用户菜单
     * @param uid
     * @return
     */
    List<Menu> getMenusByCurrentUser(Long uid);

    /**
     * 获取父id
     * @param parentId
     * @return
     */
    List<Menu> getParentId(Integer parentId);

    /**
     * 获取菜单vo列表
     * @param uid
     * @return
     */
    List<MenuVO> filterMenuList(Long uid);

}
