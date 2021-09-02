package com.ms.blogserver.config.jwt;

import org.apache.shiro.authc.AuthenticationToken;

import java.io.Serializable;

/**
 * @description: jwt token实体类
 * @author: zhh
 * @time: 2021/5/24
 */
public class JwtToken implements AuthenticationToken, Serializable {
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
