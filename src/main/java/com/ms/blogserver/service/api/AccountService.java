package com.ms.blogserver.service.api;

import com.ms.blogserver.model.vo.MenuVO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/5
 */
public interface AccountService {
    /**
     * 获取菜单
     * @param token
     * @return
     */
    List<MenuVO> getMenu(String token);

    /**
     * 获取用户角色
     * @param token
     * @return
     */
    String getRole(String token);
}
