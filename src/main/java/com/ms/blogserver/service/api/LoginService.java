package com.ms.blogserver.service.api;

import com.ms.blogserver.core.base.BaseService;
import com.ms.blogserver.model.entity.User;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
public interface LoginService extends BaseService {
    /**
     * 账号密码登录
     *
     * @param username 用户名
     * @param password 密码
     * @return {@link User}
     */
    User commonLogin(String username, String password);

    /**
     * 用户注销
     *
     * @param token 令牌
     */
    void userLogout(String token);

    /**
     * 身份验证
     *
     * @param token 令牌
     */
    void authentication(String token);

}
