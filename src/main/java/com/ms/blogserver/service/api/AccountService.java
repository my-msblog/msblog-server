package com.ms.blogserver.service.api;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.core.base.BaseService;
import com.ms.blogserver.model.dto.StatusDTO;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.vo.MenuVO;
import com.ms.blogserver.model.vo.UserProfileVO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/5
 */
public interface AccountService extends BaseService {
    /**
     * 获取菜单
     *
     * @param token 令牌
     * @return {@link List}<{@link MenuVO}>
     */
    List<MenuVO> getMenu(String token);

    /**
     * 获取用户角色
     *
     * @param token 令牌
     * @return {@link String}
     */
    String getRole(String token);

    /**
     * 管理员添加用户
     *
     * @param dto dto
     */
    void adminUserAdd(UserTableChangeDTO dto);

    /**
     * 管理员修改用户
     *
     * @param dto dto
     */
    void adminChangeUser(UserTableChangeDTO dto);

    /**
     * 后台用户管理分页列表
     *
     * @param dto dto
     * @return {@link PageInfo}<{@link UserProfileVO}>
     */
    PageInfo<UserProfileVO> userProfilePage(BaseDTO dto);

    /**
     * 修改用户状态
     *
     * @param dto dto
     */
    void userStatusChange(StatusDTO dto);
}
