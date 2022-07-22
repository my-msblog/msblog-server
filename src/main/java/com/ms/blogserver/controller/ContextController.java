package com.ms.blogserver.controller;

import com.ms.blogserver.core.base.BaseController;
import com.ms.blogserver.core.base.BaseOptions;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.ArticleCommitDTO;
import com.ms.blogserver.model.dto.IdDTO;
import com.ms.blogserver.model.vo.ArticleEditVO;
import com.ms.blogserver.service.api.ContextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }

    @GetMapping("/tag/list")
    public Result<List<BaseOptions<Long, String>>> tagList() throws Exception {
        try {
            return ResultFactory.buildSuccessResult(contextService.getTag());
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }

    @PostMapping("/article/commit")
    public Result<String> commit(@RequestBody ArticleCommitDTO dto) throws Exception {
        try {
            contextService.commit(dto);
            return ResultFactory.buildSuccessResult();
        } catch (Exception e) {
            throw exceptionHandle(e);
        }
    }

    @PostMapping(value = "/get/edit/article")
    public Result<ArticleEditVO> getEditArticle(@RequestBody IdDTO dto) throws Exception{
        try {
            ArticleEditVO editArticle = contextService.getEditArticle(dto);
            return ResultFactory.buildSuccessResult(editArticle);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }
}
