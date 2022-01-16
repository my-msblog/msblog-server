package com.ms.blogserver.controller;

import com.ms.blogserver.core.constant.contexts.LoginContexts;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultCode;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.core.exception.ProgramException;
import com.ms.blogserver.model.vo.*;
import com.ms.blogserver.service.api.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/filter/error")
    public Result<String> handleError(){
        return ResultFactory.buildResult(ResultCode.UNAUTHORIZED, LoginContexts.TOKEN_ERROR);
    }

    @GetMapping("/sys/all/interface")
    public Result<List<RequestItemVO>> getAllInterface(){
        try {

            RequestMappingHandlerMapping requestMappingHandlerMapping =
                    webApplicationContext.getBean(RequestMappingHandlerMapping.class);
            List<RequestItemVO> requestItemVOList = systemService.getAllUri(requestMappingHandlerMapping);
            return ResultFactory.buildSuccessResult(requestItemVOList);
        }catch (Exception e) {
            throw new ProgramException(e);
        }
    }

    @GetMapping("/sys/test")
    public Result<Object> test()  {
        Map<String, Object> json1 = systemService.classToJson(CommentVO.class, true );

        Map<String, Object> json0 = systemService.classToJson(MenuVO.class, true);

        List<Object> result = new ArrayList<>();
        result.add(json0);
        result.add(json1);
        return ResultFactory.buildSuccessResult(result);
    }


}
