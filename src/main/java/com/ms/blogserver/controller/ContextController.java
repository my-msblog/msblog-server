package com.ms.blogserver.controller;

import com.ms.blogserver.core.base.BaseController;
import com.ms.blogserver.core.base.BaseOptions;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.service.api.ContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2022/3/13
 */
@RestController
@RequestMapping("/context")
@Slf4j
public class ContextController extends BaseController {

    @Autowired
    private ContextService contextService;

    @GetMapping("/category/list")
    public Result<List<BaseOptions<Integer, String>>> categoryList() throws Exception {
        try {
            List<BaseOptions<Integer, String>> category = contextService.getCategory();
            return ResultFactory.buildSuccessResult(category);
        }catch (Exception e){
            throw exceptionHandle(e);
        }
    }

    @GetMapping("/tag/list")
    public Result<List<BaseOptions<Long, String>>> tagList() throws Exception {
        try{
            return ResultFactory.buildSuccessResult(contextService.getTag());
        }catch (Exception e) {
            throw exceptionHandle(e);
        }
    }
}
