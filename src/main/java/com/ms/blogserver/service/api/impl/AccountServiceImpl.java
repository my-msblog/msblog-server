package com.ms.blogserver.service.api.impl;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.exception.CustomAuthorizedException;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.model.entity.Role;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.MenuVO;
import com.ms.blogserver.service.api.AccountService;
import com.ms.blogserver.service.entity.MenuService;
import com.ms.blogserver.service.entity.RoleService;
import com.ms.blogserver.service.entity.UserService;
import com.ms.blogserver.utils.TokenUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/5
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Override
    public List<MenuVO> getMenu(String token) {
        String username = TokenUtils.getAccount(token);
        if (StringUtils.isEmpty(username)){
            throw new CustomAuthorizedException(LoginContexts.USER_ERROR);
        }
        Long uid = userService.findByUserName(username).getId();
        if (Objects.isNull(uid)){
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
        return menuService.filterMenuList(uid);
    }

    @Override
    public String getRole(String token) {
        String username = TokenUtils.getAccount(token);
        if (StringUtils.isEmpty(username)){
            throw new CustomAuthorizedException(LoginContexts.USER_ERROR);
        }
        User user = userService.findByUserName(username);
        if (Objects.isNull(user)){
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
        Role role = roleService.getById(user.getId());
        return role.getName();
    }
}
