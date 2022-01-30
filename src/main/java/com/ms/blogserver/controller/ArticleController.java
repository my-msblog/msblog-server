package com.ms.blogserver.controller;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseController;
import com.ms.blogserver.core.constant.contexts.ErrorContexts;
import com.ms.blogserver.core.constant.contexts.PermissionContexts;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultFactory;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.model.dto.CommentSubmitDTO;
import com.ms.blogserver.model.dto.GiveLikesDTO;
import com.ms.blogserver.model.dto.IdDTO;
import com.ms.blogserver.model.vo.*;
import com.ms.blogserver.service.entity.ArticleService;
import com.ms.blogserver.service.entity.CategoryService;
import com.ms.blogserver.service.entity.CommentService;
import com.ms.blogserver.service.entity.TagService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @ApiOperation(value="获取指定id文章")
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
    @ApiOperation(value = "获取文章评论")
    @PostMapping(value = "/comment")
    public Result<PageInfo<CommentItemVO>> getComment(@RequestBody IdDTO dto) throws Exception {
        try {
            String token = this.getHeaderToken();
            PageInfo<CommentItemVO> data = commentService.getPageByArticle(dto, token);
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
    @ApiOperation(value = ErrorContexts.INTERFACE_WIP)
    @PostMapping(value = "/user/comment")
    public Result<Object> getUserComment() {
        return null;
    }

    /**
     * 评论提交
     * @param dto
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "评论提交接口")
    @PostMapping(value = "/comment/submit")
    @RequiresPermissions(logical = Logical.OR, value = {PermissionContexts.USERS_MANAGEMENT,
            PermissionContexts.CONTENT_MANAGEMENT, PermissionContexts.ROLES_MANAGEMENT,})
    public Result<String> submitComment(CommentSubmitDTO dto) throws Exception{
        try {
            String token = this.getHeaderToken();
            commentService.commentSubmit(dto, token);
            return ResultFactory.buildSuccessResult();
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 日期分页
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("日期分页")
    @PostMapping(value = "/date/page")
    public Result<PageInfo<ArchiveVO>> getByDate(@RequestBody BaseDTO dto) throws Exception {
        try {
            PageInfo<ArchiveVO> pageInfo = articleService.getPageByTimesLine(dto);
            return ResultFactory.buildSuccessResult(pageInfo);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    @ApiOperation(value="文章分类列表")
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
    @ApiOperation("获取同一类别的文章")
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
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("标签列表")
    @GetMapping(value = "/list/tag")
    public Result<List<TagVO>> getTagList() throws Exception{
        try{
            List<TagVO> list = tagService.getTagList();
            return ResultFactory.buildSuccessResult(list);
        } catch (Exception e){
           throw this.exceptionHandle(e);
        }
    }

    @ApiOperation(value = "评论点赞接口")
    @PostMapping(value = "/comment/like")
    public Result<?> commentLike(@RequestBody GiveLikesDTO dto) throws Exception{
        try {
            commentService.
            return ResultFactory.buildSuccessResult();
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

}
