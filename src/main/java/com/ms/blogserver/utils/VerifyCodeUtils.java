package com.ms.blogserver.utils;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;

import java.awt.*;
import java.io.IOException;

/**
 * @description: 验证码生成
 * @author: zhh
 * @time: 2021/6/1
 */
public class VerifyCodeUtils {

    /**
     * 数字验证码
     * png图片
     * 字符类型
     * TYPE_DEFAULT         数字和字母混合
     * TYPE_ONLY_NUMBER     纯数字
     * TYPE_ONLY_CHAR       纯字母
     * TYPE_ONLY_UPPER      纯大写字母
     * TYPE_ONLY_LOWER      纯小写字母
     * TYPE_NUM_AND_UPPER   数字和大写字母
     *
     * @return {@link SpecCaptcha}
     * @throws IOException         io异常
     * @throws FontFormatException 字体格式异常
     */
    public static SpecCaptcha digitalCaptcha() throws IOException, FontFormatException {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        //设置字体
        specCaptcha.setFont(Captcha.FONT_1);
        //specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER); //设置字符类型
        return specCaptcha;
    }

    /**
     * 算术表达式
     *
     * @return {@link ArithmeticCaptcha}
     */
    public static ArithmeticCaptcha arithmeticCaptcha(){
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        //captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        //captcha.text();  // 获取运算的结果：5
        return captcha;
    }

}
