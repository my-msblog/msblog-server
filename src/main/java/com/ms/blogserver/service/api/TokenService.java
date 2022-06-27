package com.ms.blogserver.service.api;

import com.ms.blogserver.core.base.BaseService;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.UserVO;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/26
 */
public interface TokenService extends BaseService {
    /**
     * 创建token
     *
     * @param username 用户名
     * @param response 响应
     * @return {@link String}
     * @throws UnsupportedEncodingException 不支持编码异常
     */
    String createToken(String username, HttpServletResponse response) throws UnsupportedEncodingException;

    /**
     * 移除token
     *
     * @param token 令牌
     * @return boolean
     */
    boolean removeToken(String token);

    /**
     * 判断用户登录
     *
     * @param token 令牌
     * @return boolean
     */
    boolean hasLogin(String token);

    /**
     * user 转化 vo，放token
     *
     * @param user  用户
     * @param token 令牌
     * @return {@link UserVO}
     */
    UserVO setToken(User user, String token);

    /**
     * code放到redis中
     *
     * @param code 代码
     */
    void saveCode(Integer code);

    /**
     * 验证code
     *
     * @param code 代码
     * @throws CustomException 自定义异常
     */
    void getVerifyCode(String code) throws CustomException;

    /**
     * 手机验证码发送
     *
     * @param phone 电话
     */
    void sendSms(String phone);
}
