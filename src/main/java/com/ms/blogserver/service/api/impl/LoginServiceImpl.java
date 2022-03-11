package com.ms.blogserver.service.api.impl;

import com.ms.blogserver.core.constant.contexts.LoginContexts;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.core.exception.CustomAuthorizedException;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.service.api.LoginService;
import com.ms.blogserver.service.api.TokenService;
import com.ms.blogserver.service.entity.UserService;
import com.ms.blogserver.utils.EncryptPassword;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;
    @Override
    public User commonLogin(String username, String password) {
        User user = userService.findByUserName(username);
        if (Objects.isNull(user)){
            throw new CustomException(LoginContexts.USER_IS_NOT_EXIST);
        }
        String realPassword =userService.getPassword(user.getUsername());
        if (!realPassword.equals(EncryptPassword.encrypt(password))){
            throw new CustomException(LoginContexts.PASSWORD_IS_ERROR);
        }
        return user;
    }

    @Override
    public void userLogout(String token) {
        if(StringUtils.isEmpty(token)){
            throw new CustomException(LoginContexts.TOKEN_ERROR);
        }
        if (!tokenService.removeToken(token)) {
            throw new CustomException(LoginContexts.TOKEN_INVALID);
        }
    }

    @Override
    public void authentication(String token) {
        if (StringUtils.isEmpty(token)){
            throw new CustomException(LoginContexts.TOKEN_INVALID);
        }
        if (!tokenService.hasLogin(token)) {
            throw new CustomAuthorizedException(LoginContexts.TOKEN_ERROR);
        }
    }
}
