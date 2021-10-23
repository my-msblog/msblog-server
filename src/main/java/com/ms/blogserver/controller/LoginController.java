package com.ms.blogserver.controller;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.LoginDTO;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.UserVO;
import com.ms.blogserver.service.api.CaptchaService;
import com.ms.blogserver.service.api.LoginService;
import com.ms.blogserver.service.api.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/29
 */
@RestController
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private LoginService loginService;

    /**
     * 验证登录
     *
     * @return
     */
    @PostMapping(value = "api/authentication")
    public Result<String> authentication() throws Exception {
        try {
            loginService.authentication(getHeaderToken());
            return ResultFactory.buildSuccessResult();
        } catch (Exception e) {
           throw this.exceptionHandle(e);
        }
    }

    /**
     * 账号密码登录
     *
     * @param loginDTO
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login")
    public Result<UserVO> userLogin(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws Exception {
        try {
            // 验证用户
            User user = loginService.commonLogin(loginDTO.getUsername(), loginDTO.getPassword());
            // 判断验证码
            captchaService.verifyArithmetic(loginDTO.getKey(), loginDTO.getCode());
            // 生成token
            String token = tokenService.createToken(user.getUsername(), response);
            // 返回内容
            UserVO userVO = tokenService.setToken(user, token);
            return ResultFactory.buildSuccessResult(userVO);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping(value = "/logout")
    public Result<String> logout() throws Exception {
        try {
            loginService.userLogout(getHeaderToken());
            return ResultFactory.buildSuccessResult(LoginContexts.LOGOUT_SUCCESS);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }
}
