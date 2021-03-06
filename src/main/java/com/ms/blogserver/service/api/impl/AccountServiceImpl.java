package com.ms.blogserver.service.api.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.constant.contexts.LoginContexts;
import com.ms.blogserver.converter.dto.UserTableChangeDtoConverter;
import com.ms.blogserver.converter.vo.UserProfileVoConverter;
import com.ms.blogserver.core.exception.CustomAuthorizedException;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.mapper.UserMapper;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.model.dto.StatusDTO;
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
    private UserMapper userMapper;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<MenuVO> getMenu(String token) {
        String account = TokenUtils.getAccount(token);
        if (StringUtils.isEmpty(account)){
            throw new CustomAuthorizedException(LoginContexts.USER_ERROR);
        }
        Long userId = Long.parseLong(account);
        User user = userService.getById(userId);
        if (Objects.isNull(user)){
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
        return menuService.filterMenuList(user.getId());
    }

    @Override
    public String getRole(String token) {
        String account = TokenUtils.getAccount(token);
        if (StringUtils.isEmpty(account)){
            throw new CustomAuthorizedException(LoginContexts.USER_ERROR);
        }
        Long userId = Long.parseLong(account);
        User user = userService.getById(userId);
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

    @Override
    public void userStatusChange(StatusDTO dto) {
        User user = userMapper.selectUser(dto.getId());
        if (Objects.isNull(user)){
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
        Integer deleted = user.getDeleted();
        if (deleted.equals(dto.getStatus())){
            throw new CustomException(LoginContexts.USER_STATUS_EXCEPTIONS);
        }
        userMapper.updateUserStatus(user.getId(), dto.getStatus());
    }
}
