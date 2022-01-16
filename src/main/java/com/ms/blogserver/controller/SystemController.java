package com.ms.blogserver.controller;

import com.ms.blogserver.core.constant.contexts.LoginContexts;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultCode;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.core.exception.ProgramException;
import com.ms.blogserver.model.vo.*;
import com.ms.blogserver.service.api.SystemService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/9/20
 */
@RestController
@Slf4j
public class SystemController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SystemService systemService;

    @ApiOperation(value="捕获错误")
    @RequestMapping("/filter/error")
    public Result<String> handleError(){
        return ResultFactory.buildResult(ResultCode.UNAUTHORIZED, LoginContexts.TOKEN_ERROR);
    }

    @ApiOperation(value="Api接口文档")
    @GetMapping("/sys/all/interface")
    public Result<List<RequestItemVO>> getAllInterface(){
        try {
            RequestMappingHandlerMapping requestMappingHandlerMapping =
                    webApplicationContext.getBean(RequestMappingHandlerMapping.class);
            List<RequestItemVO> requestItemVOList = systemService.getAllUrl(requestMappingHandlerMapping);
            return ResultFactory.buildSuccessResult(requestItemVOList);
        }catch (Exception e) {
            throw new ProgramException(e);
        }
    }

}
