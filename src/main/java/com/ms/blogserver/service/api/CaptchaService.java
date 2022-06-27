package com.ms.blogserver.service.api;

import com.ms.blogserver.core.base.BaseService;
import com.ms.blogserver.model.vo.CaptchaVO;

import java.awt.*;
import java.io.IOException;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
public interface CaptchaService extends BaseService {

    /**
     * 生成表达式验证码
     *
     * @return {@link CaptchaVO}
     */
    CaptchaVO createArithmetic();

    /**
     * 生成验证码
     *
     * @return {@link CaptchaVO}
     * @throws IOException         io
     * @throws FontFormatException 字体格式异常
     */
    CaptchaVO createSpec() throws IOException, FontFormatException;

    /**
     * 验证算术表达式
     *
     * @param key  关键
     * @param code 代码
     */
    void verifyArithmetic(String key,String code);

    /**
     * 验证通用验证码
     *
     * @param key  关键
     * @param code 代码
     */
    void verifySpec(String key,String code);
}
