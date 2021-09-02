package com.ms.blogserver.service.api;

import com.ms.blogserver.model.entity.User;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public interface LoginService {
    /**
     * 账号密码登录
     * @param username
     * @param password
     * @return
     */
    User commonLogin(String username, String password);

    /**
     * 登出
     * @param token
     */
    void userLogout(String token);

    /**
     * 验证登录
     * @param token
     */
    void authentication(String token);

}
