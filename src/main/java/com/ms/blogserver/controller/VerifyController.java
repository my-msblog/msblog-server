package com.ms.blogserver.controller;

import com.ms.blogserver.core.constant.contexts.VerifyContexts;
import com.ms.blogserver.core.exception.CustomException;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.PhoneDTO;
import com.ms.blogserver.model.vo.CaptchaVO;
import com.ms.blogserver.service.api.CaptchaService;
import com.ms.blogserver.service.api.TokenService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/1
 */
@RestController
@Slf4j
@RequestMapping(value = "/code")
public class VerifyController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CaptchaService captchaService;

    /**
     * 发送手机验证码
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "发送手机验证码")
    @PostMapping(value = "/sms")
    public Result<String> sendSmsCode(@RequestBody PhoneDTO dto){
        try {
            tokenService.sendSms(dto.getPhone());
            return ResultFactory.buildSuccessResult(VerifyContexts.VERIFY_SUCCESS);
        }catch (Exception e){
            throw new CustomException(e);
        }

    }

    /**
     * 获取算式验证码
     *
     * @return
     */
    @ApiOperation(value = "获取算式验证码")
    @GetMapping(value = "/captcha/arithmetic")
    public Result<CaptchaVO> arithmetic(){
        try {
            CaptchaVO captchaVO = captchaService.createArithmetic();
            return ResultFactory.buildSuccessResult(captchaVO);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    /**
     * 获取验证码（字母+数字）
     *
     * @return
     */
    @ApiOperation(value = "获取验证码（字母+数字）")
    @GetMapping(value = "/captcha/spec")
    public Result<CaptchaVO> spec(){
        try {
            CaptchaVO captchaVO = captchaService.createSpec();
            return ResultFactory.buildSuccessResult(captchaVO);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
}
