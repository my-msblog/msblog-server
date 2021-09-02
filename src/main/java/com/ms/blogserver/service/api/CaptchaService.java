package com.ms.blogserver.service.api;

import com.ms.blogserver.model.vo.CaptchaVO;

import java.awt.*;
import java.io.IOException;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
public interface CaptchaService {

    /**
     * 生成表达式验证码
     * @return
     */
    CaptchaVO createArithmetic();

    /**
     * 生成验证码
     * @return
     * @throws IOException
     * @throws FontFormatException
     */
    CaptchaVO createSpec() throws IOException, FontFormatException;
    /**
     * 验证算术表达式
     * @param key
     * @param code
     */
    void verifyArithmetic(String key,String code);
    /**
     * 验证通用验证码
     * @param key
     * @param code
     */
    void verifySpec(String key,String code);
}
