package com.ms.blogserver.controller;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.contexts.VerifyContexts;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.UserDTO;
import com.ms.blogserver.service.CaptchaService;
import com.ms.blogserver.service.TokenService;
import com.ms.blogserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


@RestController
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @PostMapping(value = "/add")
    public Result userAdd(@RequestBody UserDTO userDTO,Integer code){
        if (!tokenService.getVerifyCode(code)){
            return ResultFactory.buildFailResult(VerifyContexts.VERIFY_ERROR);
        }
        String username = userDTO.getUsername();
        username = HtmlUtils.htmlEscape(username);
        userDTO.setUsername(username);
        if (userService.hasUserName(username)){
            return ResultFactory.buildFailResult(LoginContexts.NAME_HAS_EXIST);
        }
        userService.insertUser(userDTO);
        return ResultFactory.buildSuccessResult(LoginContexts.REGISTER_SUCCESS);
    }

    @PostMapping(value = "/update")
    public Result userUpdate(@RequestBody UserDTO u) throws Exception {
        try {
            userService.updateUser(u);
            return ResultFactory.buildSuccessResult(userService.findAll());
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }

    }
    @PostMapping(value = "/remove")
    public Result userDelete(@RequestBody Long id) throws CustomException {
        if (userService.removeById(id) == 1){
            return ResultFactory.buildSuccessResult(userService.findAll());
        }
        throw new CustomException("There is no data with ID "+ id+" in the database");

    }

    @PostMapping(value = "/delete")
    public Result phyDelete() throws Exception {
        try {
            userService.deleteById(1392754664565116930L);
            return ResultFactory.buildSuccessResult(userService.findAll());
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }


}
