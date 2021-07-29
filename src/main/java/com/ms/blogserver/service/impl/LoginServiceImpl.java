package com.ms.blogserver.service.impl;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.exception.CustomAuthorizedException;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.service.LoginService;
import com.ms.blogserver.service.TokenService;
import com.ms.blogserver.service.UserService;
import com.ms.blogserver.utils.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        if (tokenService.removeToken(token)) {
            throw new CustomException(LoginContexts.NO_LOGIN_USER);
        }
    }

    @Override
    public void authentication(String token) {
        if (!tokenService.hasLogin(token)) {
            throw new CustomAuthorizedException();
        }
    }
}
