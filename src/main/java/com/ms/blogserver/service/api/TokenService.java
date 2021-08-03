package com.ms.blogserver.service.api;

import com.ms.blogserver.exception.CustomException;
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

    String CreateToken(String username, HttpServletResponse response) throws UnsupportedEncodingException;

    boolean removeToken(String token);

    boolean hasLogin(String token);

    UserVO setToken(User user, String token);

    void saveCode(Integer code);

    boolean getVerifyCode(Integer code) throws CustomException;

    void sendSMS(String phone);
}
