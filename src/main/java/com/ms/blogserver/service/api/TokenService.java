package com.ms.blogserver.service.api;

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
public interface TokenService {
    /**
     * 创建token
     * @param username
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    String createToken(String username, HttpServletResponse response) throws UnsupportedEncodingException;

    /**
     * 移除token
     * @param token
     * @return
     */
    boolean removeToken(String token);

    /**
     * 判断用户登录
     * @param token
     * @return
     */
    boolean hasLogin(String token);

    /**
     * user 转化 vo，放token
     * @param user
     * @param token
     * @return
     */
    UserVO setToken(User user, String token);

    /**
     * code放到redis中
     * @param code
     */
    void saveCode(Integer code);

    /**
     * 验证code
     * @param code
     * @throws CustomException
     */
    void getVerifyCode(String code) throws CustomException;

    /**
     *  手机验证码发送
      * @param phone
     */
    void sendSms(String phone);
}
