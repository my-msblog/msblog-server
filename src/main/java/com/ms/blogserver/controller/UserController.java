package com.ms.blogserver.controller;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.converter.vo.UserVoConverter;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.model.dto.IdDTO;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.vo.UserVO;
import com.ms.blogserver.service.api.TokenService;
import com.ms.blogserver.service.api.UserOperationService;
import com.ms.blogserver.service.entity.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/11
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserOperationService userOperationService;


    /**
     * 新增用户
     *
     * @param dto 用户信息
     * @return
     */
    @PostMapping(value = "/register")
    public Result<String> userAdd(@RequestBody UserTableChangeDTO dto) throws Exception{
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
    public Result<UserVO> userUpdate(@RequestBody UserTableChangeDTO dto) throws Exception {
        try {
            tokenService.getVerifyCode(dto.getCode());
            userService.updateUser(dto);
            UserVO userVO = UserVoConverter.INSTANCE.toData(userService.getById(dto.getId()));
            return ResultFactory.buildSuccessResult(userVO);
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 删除用户
     *
     * @param dto
     * @return
     * @throws CustomException
     */
    @PostMapping(value = "/remove")
    public Result<String> userDelete(@RequestBody IdDTO dto) throws CustomException {
        if (userService.removeById(dto.getId())){
            return ResultFactory.buildSuccessResult();
        }
        throw new CustomException("There is no data with ID "+ dto.getId()+" in the database");

    }
    @PostMapping("/remove/list")
    public Result<String> userDeleteList(@RequestBody IdDTO dto) throws Exception{
        try {
            userOperationService.deleteUserListId(dto.getIdList());
            return ResultFactory.buildSuccessResult();
        }catch (Exception e){
            throw exceptionHandle(e);
        }
    }

    @PostMapping(value = "/delete")
    public Result<String> phyDelete() throws Exception {
        try {
            userService.removeById(1392754664565116930L);
            return ResultFactory.buildSuccessResult();
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }


}
