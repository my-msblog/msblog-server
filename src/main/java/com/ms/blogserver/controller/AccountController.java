package com.ms.blogserver.controller;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.constant.contexts.LoginContexts;
import com.ms.blogserver.constant.contexts.PermissionContexts;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.dto.StatusDTO;
import com.ms.blogserver.model.dto.UserTableChangeDTO;
import com.ms.blogserver.model.vo.UserProfileVO;
import com.ms.blogserver.service.api.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *  管理员修改用户接口
     * @param dto
     * @return
     * @throws Exception
     */
    @ApiOperation("管理员修改用户接口")
    @PostMapping("/admin/change/user")
    public Result<String> adminChangeUser(@RequestBody UserTableChangeDTO dto) throws Exception {
        try {
            accountService.adminChangeUser(dto);
            return ResultFactory.buildSuccessResult();
        }catch (Exception e) {
            throw exceptionHandle(e);
        }
    }

    /**
     * 管理员添加用户接口
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("/admin/add")
    public Result<String> adminAddUser(@RequestBody UserTableChangeDTO dto) throws Exception{
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
    public Result<PageInfo<UserProfileVO>> getByPage(@RequestBody BaseDTO dto) throws Exception {
        try {
            PageInfo<UserProfileVO> data = accountService.userProfilePage(dto);
            return ResultFactory.buildSuccessResult(data);
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }
    @PostMapping("/admin/status/change")
    public Result<String> changeStatus(@RequestBody StatusDTO dto) throws Exception{
        try{
            return ResultFactory.buildSuccessResult();
        } catch (Exception e){
            throw exceptionHandle(e);
        }
    }
}
