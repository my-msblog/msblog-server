package com.ms.blogserver.service;

import com.ms.blogserver.model.entity.User;

public interface LoginService {

    User commonLogin(String username, String password);

    void userLogout(String token);

    void authentication(String token);

}
