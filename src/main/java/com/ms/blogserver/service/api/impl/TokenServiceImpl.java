package com.ms.blogserver.service.api.impl;

import com.ms.blogserver.core.exception.CustomAuthorizedException;
import com.ms.blogserver.core.constant.contexts.DigitalContexts;
import com.ms.blogserver.core.constant.contexts.LoginContexts;
import com.ms.blogserver.core.constant.contexts.RedisKeyContexts;
import com.ms.blogserver.core.constant.contexts.VerifyContexts;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.converter.vo.UserVoConverter;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.UserVO;
import com.ms.blogserver.service.api.TokenService;
import com.ms.blogserver.utils.RedisUtils;
import com.ms.blogserver.utils.RegularUtils;
import com.ms.blogserver.utils.SmsVerify;
import com.ms.blogserver.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/26
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public String createToken(String username, HttpServletResponse response) throws UnsupportedEncodingException {
        Long currentTimeMillis = System.currentTimeMillis();
        String token= TokenUtils.sign(username,currentTimeMillis);
        redisUtils.set(username,currentTimeMillis, TokenUtils.REFRESH_EXPIRE_TIME);
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return token;
    }

    @Override
    public boolean removeToken(String token) {
        try {
            String account = TokenUtils.getAccount(token);
            Long currentTime= TokenUtils.getCurrentTime(token);
            if (redisUtils.hasKey(account)) {
                Long currentTimeMillisRedis = (Long) redisUtils.get(account);
                if (currentTimeMillisRedis.equals(currentTime)) {
                    redisUtils.del(account);
                    log.info(LoginContexts.LOGOUT_SUCCESS);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new CustomAuthorizedException();
        }
    }

    @Override
    public boolean hasLogin(String token) {
        String account = TokenUtils.getAccount(token);
        return redisUtils.hasKey(account);
    }

    @Override
    public UserVO setToken(User user,String token) {
        UserVO userVO = UserVoConverter.INSTANCE.toData(user);
        userVO.setToken(token);
        return userVO;
    }

    @Override
    public void saveCode(Integer code) {
        redisUtils.set(RedisKeyContexts.SMS_CODE,code, DigitalContexts.ONE_MINUTES);
    }

    @Override
    public void getVerifyCode(String code) throws CustomException {
        Integer verifyCode = (Integer)redisUtils.get(RedisKeyContexts.SMS_CODE);
        if (verifyCode == null){
            throw new CustomException(VerifyContexts.VERIFY_NO_FOUND_ERROR);
        }
        if (!code.equals(verifyCode.toString())){
            throw new CustomException(VerifyContexts.VERIFY_ERROR);
        }
    }

    @Override
    public void sendSms(String phone) {
        if (phone == null || !RegularUtils.isMobileExact(phone)){
            throw new CustomException(VerifyContexts.RIGHT_PHONE_NUMBER);
        }
        Integer code = SmsVerify.sendSms(phone);
        saveCode(code);
    }
}
