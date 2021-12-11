package com.ms.blogserver.controller;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.constant.controller.BaseController;
import com.ms.blogserver.constant.result.Result;
import com.ms.blogserver.constant.result.ResultFactory;
import com.ms.blogserver.exception.CustomException;
import com.ms.blogserver.model.dto.BaseDTO;
import com.ms.blogserver.model.dto.GetCommentDTO;
import com.ms.blogserver.model.vo.ArchiveVO;
import com.ms.blogserver.model.vo.ArticleVO;
import com.ms.blogserver.model.vo.CommentVO;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.service.entity.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 获取指定id文章
     *
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/get")
    public Result<ArticleVO> getArticle(@RequestBody Long id) throws Exception {
        try {
            ArticleVO res = articleService.getArticleById(id);
            return ResultFactory.buildSuccessResult(res);
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
    public Result<PageInfo<CommentVO>> getComment(@RequestBody GetCommentDTO dto) throws Exception {
        try {
            PageInfo<CommentVO> data = commentService.getPageByArticle(dto);
            return ResultFactory.buildSuccessResult(data);
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 当前用户文章总数
     *
     * @return
     */
    @GetMapping("/user/count")
    public Result<String> getUserArticle() {
        try {
            return ResultFactory.buildSuccessResult();
        }catch (Exception e) {
            throw new CustomException(e.getMessage());
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

    /**
     * 根据日期获取标题
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/date/page")
    public Result<PageInfo<ArchiveVO>> getByDate(BaseDTO dto) throws Exception {
        try {
            PageInfo<ArchiveVO> pageInfo = articleService.getPageByTimesLine(dto);
            return ResultFactory.buildSuccessResult(pageInfo);
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }
}
