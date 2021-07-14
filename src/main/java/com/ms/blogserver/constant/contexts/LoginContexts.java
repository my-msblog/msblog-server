package com.ms.blogserver.constant.contexts;

import com.ms.blogserver.aspect.annotation.PermissionCheck;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/14
 */
public interface LoginContexts {

    String USER_ERROR = "用户状态异常，请重新登录";

    String INPUT_USER_NAME = "用户名不能为空";

    String INPUT_PASSWORD = "密码不能为空";

    String USER_IS_NOT_EXIST = "用户不存在";

    String AUTHENTIC_FAIL = "认证失败：身份验证异常";

    String PASSWORD_IS_ERROR = "密码错误";

    String LOGOUT_SUCCESS = "成功登出";

    String NO_LOGIN_USER = "用户未登录";

    String NAME_HAS_EXIST = "用户名已存在";

    String REGISTER_SUCCESS = "注册成功";

    String TOKEN_ERROR = "token验证失败";

    String TOKEN_INVALID = "无效token";

    String ID_IS_NULL = "传入id不能为空";

    String INSUFFICIENT_USER_PERMISSIONS = "用户权限不足";

    String EMAIL_ERROR = "邮箱格式错误";

}
