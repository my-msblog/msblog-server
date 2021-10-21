package com.ms.blogserver.service.api.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.converter.dto.UserTableChangeDtoConverter;
import com.ms.blogserver.converter.vo.UserProfileVoConverter;
import com.ms.blogserver.exception.CustomAuthorizedException;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.entity.Role;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.entity.UserRole;
import com.ms.blogserver.model.vo.MenuVO;
import com.ms.blogserver.model.vo.UserProfileVO;
import com.ms.blogserver.service.api.AccountService;
import com.ms.blogserver.service.entity.MenuService;
import com.ms.blogserver.service.entity.RoleService;
import com.ms.blogserver.service.entity.UserRoleService;
import com.ms.blogserver.service.entity.UserService;
import com.ms.blogserver.utils.EncryptPassword;
import com.ms.blogserver.utils.PageInfoUtil;
import com.ms.blogserver.utils.RegularUtils;
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

    @Autowired
    private UserRoleService userRoleService;

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

    @Override
    public void adminUserAdd(UserTableChangeDTO dto) {
        dto.setPwd(EncryptPassword.encrypt(dto.getPwd()));
        User user = UserTableChangeDtoConverter.INSTANCE.fromData(dto);
        if (StringUtils.isNotEmpty(user.getEmail()) && !RegularUtils.isEmail(user.getEmail())){
            throw new CustomException(LoginContexts.EMAIL_ERROR);
        }
        userService.save(user);
        User newUser = userService.findByUserName(user.getUsername());
        userRoleService.save(new UserRole(newUser.getId(),dto.getRoleId()));
    }

    @Override
    public void adminChangeUser(UserTableChangeDTO dto) {
        userService.updateUser(dto);
    }

    @Override
    public PageInfo<UserProfileVO> userProfilePage(BaseDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getSize());
        List<User> userList = userService.getAll();
        List<UserProfileVO> resList = UserProfileVoConverter.INSTANCE.toDataList(userList);
        resList.forEach(userProfileVO -> {
            Long userId = userProfileVO.getId();
            userProfileVO.setRole(roleService.getRoleByUserId(userId));
        });
        PageInfo<UserProfileVO> res = new PageInfo<>();
        PageInfoUtil.transform(new PageInfo<>(userList),res);
        res.setList(resList);
        return res;
    }
}
