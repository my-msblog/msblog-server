package com.ms.blogserver.controller;

import com.ms.blogserver.constant.contexts.VerifyContexts;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.PhoneDTO;
import com.ms.blogserver.model.vo.CaptchaVO;
import com.ms.blogserver.service.api.CaptchaService;
import com.ms.blogserver.service.api.TokenService;
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
    @PostMapping(value = "/sms")
    public Result<String> sendSmsCode(@RequestBody PhoneDTO dto){
        try {
            tokenService.sendSms(dto.getPhone());
            return ResultFactory.buildSuccessResult(VerifyContexts.VERIFY_SUCCESS);
        }catch (Exception e){
            throw new CustomException(e.getMessage(),e);
        }

    }

    /**
     * 获取算式验证码
     *
     * @return
     */
    @GetMapping(value = "/captcha/arithmetic")
    public Result<CaptchaVO> arithmetic(){
        try {
            CaptchaVO captchaVO = captchaService.createArithmetic();
            return ResultFactory.buildSuccessResult(captchaVO);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(),e);
        }
    }

    /**
     * 获取验证码（字母+数字）
     *
     * @return
     */
    @GetMapping(value = "/captcha/spec")
    public Result<CaptchaVO> spec(){
        try {
            CaptchaVO captchaVO = captchaService.createSpec();
            return ResultFactory.buildSuccessResult(captchaVO);
        } catch (Exception e) {
            throw new CustomException(e.getMessage(),e);
        }
    }
}
