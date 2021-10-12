package com.ms.blogserver.service.api;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.vo.MenuVO;
import com.ms.blogserver.model.vo.UserProfileVO;

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

    /**
     * 管理员添加用户
     * @param dto
     */
    void adminUserAdd(UserTableChangeDTO dto);

    /**
     * 管理员修改用户
     * @param dto
     */
    void adminChangeUser(UserTableChangeDTO dto);

    /**
     * 后台用户管理分页列表
     * @param dto
     * @return
     */
    PageInfo<UserProfileVO> userProfilePage(BaseDTO dto);
}
