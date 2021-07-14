package com.ms.blogserver.controller;

import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.model.dto.GetCommentDTO;
import com.ms.blogserver.service.ArticleService;
import com.ms.blogserver.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@RestController
@Slf4j
@RequestMapping(value = "article")
public class ArticleController extends BaseController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    /**
     * 获取指定文章
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/get")
    public Result getArticle(Long id) throws Exception {
        try {
            return ResultFactory.buildSuccessResult(articleService.getArticleById(id));
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 获取文章评论
     *
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/comment")
    public Result getComment(@RequestBody GetCommentDTO dto) throws Exception {
        try {
            return ResultFactory.buildSuccessResult(commentService.getPageByArticle(dto));
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 用户评论
     *
     * @return
     */
    @PostMapping(value = "/user/comment")
    public Result getUserComment(){
        return null;
    }

    @PostMapping(value = "/date")
    public Result getByDate() throws Exception {
        try {
            return null;
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }
}
