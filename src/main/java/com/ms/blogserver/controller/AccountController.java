package com.ms.blogserver.controller;

import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.contexts.PermissionContexts;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.service.api.AccountService;
import com.ms.blogserver.service.entity.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/8
 */
@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController extends BaseController {


    @Autowired
    private AccountService accountService;

    @PostMapping("/admin/add")
    public Result adminAddUser(@RequestBody UserTableChangeDTO dto) throws Exception{
        try {
            accountService.adminUserAdd(dto);
            return ResultFactory.buildSuccessResult(LoginContexts.REGISTER_SUCCESS);
        }catch (Exception e) {
            throw exceptionHandle(e);
        }
    }
    /**
     * 用户列表分页
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/user/page")
    @RequiresPermissions(logical = Logical.AND, value = {PermissionContexts.USERS_MANAGEMENT})
    public Result getByPage(@RequestBody BaseDTO dto) throws Exception {
        try {
            return ResultFactory.buildSuccessResult(accountService.userProfilePage(dto));
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }
}
