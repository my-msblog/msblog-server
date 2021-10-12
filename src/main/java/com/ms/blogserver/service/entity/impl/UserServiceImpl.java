package com.ms.blogserver.service.entity.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.converter.dto.UserTableChangeDtoConverter;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.constant.contexts.RoleContexts;
import com.ms.blogserver.exception.ProgramException;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.entity.UserRole;
import com.ms.blogserver.service.entity.UserRoleService;
import com.ms.blogserver.utils.RegularUtils;
import com.ms.blogserver.utils.EncryptPassword;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.mapper.UserMapper;
import com.ms.blogserver.service.entity.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public void hasUserName(String username) {
        if (findByUserName(username) == null){
            throw new CustomException(LoginContexts.NAME_HAS_EXIST);
        }
    }


    @Override
    public void insertUser(UserTableChangeDTO userDTO) {
        userDTO.setPwd(EncryptPassword.encrypt(userDTO.getPwd()));
        User user = UserTableChangeDtoConverter.INSTANCE.fromData(userDTO);
        String email = user.getEmail();
        if (StringUtils.isNotEmpty(email)) {
            if (!RegularUtils.isEmail(email)) {
                throw new CustomException(LoginContexts.EMAIL_ERROR);
            }
        }
        try {
            baseMapper.insert(user);
            // 新用户添加权限，默认权限为2
            User newUser = findByUserName(user.getUsername());
            userRoleService.save(new UserRole(newUser.getId(), RoleContexts.CONTENT_MANAGER_ID));
        } catch (Exception e) {
           throw new ProgramException(e.getMessage());
        }
    }

    @Override
    public void updateUser(UserTableChangeDTO dto) {
        User user = baseMapper.selectById(dto.getId());
        if (Objects.isNull(user)) {
            throw new CustomException("UserService-updateUser:" + LoginContexts.USER_IS_NOT_EXIST);
        }
        UserTableChangeDtoConverter.INSTANCE.fromDataNoNull(dto, user);
        baseMapper.updateById(user);
    }


    @Override
    public void deleteById(Long id) {
        baseMapper.deletedByDel(id);
    }

    @Override
    public User findByUserName(String userName) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public String getPassword(String username) {
        User byUserName = findByUserName(username);
        if (byUserName == null) {
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
        try {
            return byUserName.getPwd();
        } catch (Exception e) {
            throw new ProgramException(e.getMessage());
        }
    }
}
