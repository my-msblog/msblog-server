package com.ms.blogserver.core.constant.contexts;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
public interface VerifyContexts {

    String VERIFY_NO_NULL = "验证码不能为空";

    String VERIFY_SUCCESS = "验证码发送成功";

    String VERIFY_NO_FOUND_ERROR = "验证异常,请重新发送验证码";

    String VERIFY_ERROR = "验证码错误";

    String RIGHT_PHONE_NUMBER = "请输入正确的手机号";

    String INVALID_CAPTCHA = "无效验证码";
}
