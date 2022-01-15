package com.ms.blogserver.controller;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseController;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.model.dto.GetCommentDTO;
import com.ms.blogserver.model.vo.*;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.service.entity.CategoryService;
import com.ms.blogserver.service.entity.CommentService;
import com.ms.blogserver.service.entity.TagService;
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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

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
        } catch (Exception e) {
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
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 用户评论
     *
     * @return
     */
    @PostMapping(value = "/user/comment")
    public Result getUserComment() {
        return null;
    }

    /**
     * 日期分页
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/date/page")
    public Result<PageInfo<ArchiveVO>> getByDate(@RequestBody BaseDTO dto) throws Exception {
        try {
            PageInfo<ArchiveVO> pageInfo = articleService.getPageByTimesLine(dto);
            return ResultFactory.buildSuccessResult(pageInfo);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    @GetMapping(value = "/list/category")
    public Result<List<CategoryVO>> getCategory() throws Exception {
        try {
            List<CategoryVO> list = categoryService.getList();
            return ResultFactory.buildSuccessResult(list);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 获取同一类别的文章
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/category/{id}")
    public Result<List<ArticleCategoryVO>> getCategoryIdList(@PathVariable Integer id) throws Exception {
        try{
            List<ArticleCategoryVO> list = articleService.getArticleListByCategory(id);
            return ResultFactory.buildSuccessResult(list);
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 标签列表
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/list/tag")
    public Result<List<TagVO>> getTagList() throws Exception{
        try{
            List<TagVO> list = tagService.getTagList();
            return ResultFactory.buildSuccessResult(list);
        } catch (Exception e){
           throw this.exceptionHandle(e);
        }
    }

}
