package com.ms.blogserver.controller;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultCode;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.LoginDTO;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.UserVO;
import com.ms.blogserver.service.CaptchaService;
import com.ms.blogserver.service.TokenService;
import com.ms.blogserver.service.UserService;
import com.ms.blogserver.utils.EncryptPassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaptchaService captchaService;

    @PostMapping(value = "api/authentication")
    public Result authentication(HttpServletRequest request){
        String token = request.getHeader("token");
        return tokenService.hasLogin(token)?
                ResultFactory.buildSuccessResult() :
                ResultFactory.buildResult(ResultCode.UNAUTHORIZED,LoginContexts.NO_LOGIN_USER);
    }

    @PostMapping(value = "/login")
    public Result userLogin(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws Exception {
        try {
            User user = userService.findByUserName(loginDTO.getUsername());
            //判断验证码
            //captchaService.verifyArithmetic(loginDTO.getKey(), loginDTO.getCode());
            String realPassword =userService.getPassword(user.getUsername());
            if (realPassword.equals(EncryptPassword.encrypt( loginDTO.getPassword()))){
                UserVO userVO = tokenService.setToken(user,tokenService.CreateToken(user.getUsername(),response));
                return ResultFactory.buildSuccessResult(userVO);
            }
            return ResultFactory.buildFailResult(LoginContexts.PASSWORD_IS_ERROR);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    @GetMapping(value = "/logout")
    public Result logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        return tokenService.removeToken(token) ?
                ResultFactory.buildSuccessResult(LoginContexts.LOGOUT_SUCCESS) :
                ResultFactory.buildFailResult(LoginContexts.NO_LOGIN_USER);
    }
}
