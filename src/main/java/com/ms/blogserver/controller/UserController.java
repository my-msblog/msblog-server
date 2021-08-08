package com.ms.blogserver.controller;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.contexts.VerifyContexts;
import com.ms.blogserver.converter.vo.UserVOConverter;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.UserDTO;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.entity.User;
import com.ms.blogserver.model.vo.UserVO;
import com.ms.blogserver.service.api.TokenService;
import com.ms.blogserver.service.entity.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 新增用户
     *
     * @param dto 用户信息
     * @return
     */
    @PostMapping(value = "/add")
    public Result userAdd(@RequestBody UserTableChangeDTO dto) throws Exception{
        try {
            tokenService.getVerifyCode(dto.getCode());
            dto.setUsername( HtmlUtils.htmlEscape(dto.getUsername()));
            // 判断用户名是否存在
            userService.hasUserName(dto.getUsername());
            userService.insertUser(dto);
            return ResultFactory.buildSuccessResult(LoginContexts.REGISTER_SUCCESS);
        } catch (Exception e) {
           throw exceptionHandle(e);
        }
    }

    /**
     * 修改用户信息
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/update")
    public Result userUpdate(@RequestBody UserTableChangeDTO dto) throws Exception {
        try {
            tokenService.getVerifyCode(dto.getCode());
            userService.updateUser(dto);
            UserVO userVO = UserVOConverter.INSTANCE.toData(userService.getUserByID(dto.getId()));
            return ResultFactory.buildSuccessResult(userVO);
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     * @throws CustomException
     */
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
