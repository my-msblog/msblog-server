package com.ms.blogserver.controller;

import com.ms.blogserver.constant.contexts.RoleContexts;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.vo.MenuVO;
import com.ms.blogserver.service.api.AccountService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/5/21
 */
@RestController
@RequestMapping(value = "/info")
public class UserInfoController extends BaseController {

    @Autowired
    private AccountService accountService;


    /**
     * 用户权限菜单
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/menu")
    @RequiresRoles(value = {RoleContexts.SYSTEM_ADMIN,RoleContexts.CONTENT_MANAGER},logical = Logical.OR)
    public Result getMenu() throws Exception {
        try {
            String token = getHeaderToken();
            List<MenuVO> menu = accountService.getMenu(token);
            return ResultFactory.buildSuccessResult(menu);
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }
    @GetMapping("/role")
    public Result getUserRole() throws Exception {
        try {
            String token = getHeaderToken();
            return ResultFactory.buildSuccessResult(accountService.getRole(token));
        } catch (Exception e){
            throw exceptionHandle(e);
        }
    }

}
