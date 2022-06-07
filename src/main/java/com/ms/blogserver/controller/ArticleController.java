package com.ms.blogserver.controller;

import com.github.pagehelper.PageInfo;
import com.ms.blogserver.core.base.BaseController;
import com.ms.blogserver.core.base.BaseDTO;
import com.ms.blogserver.core.constant.contexts.ErrorContexts;
import com.ms.blogserver.core.constant.contexts.PermissionContexts;
import com.ms.blogserver.core.constant.result.Result;
import com.ms.blogserver.core.constant.result.ResultFactory;
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
@RequestMapping(value = "/article")
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
     * @param dto dto
     * @return {@link Result}<{@link ArticleVO}>
     * @throws Exception 异常
     */
    @ApiOperation(value="获取指定id文章")
    @PostMapping(value = "/get")
    public Result<ArticleVO> getArticle(@RequestBody IdDTO dto) throws Exception {
        try {
            ArticleVO res = articleService.getArticleById(dto.getId());
            return ResultFactory.buildSuccessResult(res);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 获取文章评论
     *
     * @param dto dto
     * @return {@link Result}<{@link PageInfo}<{@link CommentItemVO}>>
     * @throws Exception 异常
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
     * 提交评论
     *
     * @param dto dto
     * @return {@link Result}<{@link String}>
     * @throws Exception 异常
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
     * @param dto dto
     * @return {@link Result}<{@link PageInfo}<{@link ArchiveVO}>>
     * @throws Exception 异常
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

    /**
     * 文章分类列表
     *
     * @return {@link Result}<{@link List}<{@link CategoryVO}>>
     * @throws Exception 异常
     */
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
     *
     * @param id id
     * @return {@link Result}<{@link List}<{@link ArticleCategoryVO}>>
     * @throws Exception 异常
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
     * @return {@link Result}<{@link List}<{@link TagVO}>>
     * @throws Exception 异常
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

    @ApiOperation(value = "获取同一标签的文章")
    @GetMapping(value = "/tag/{id}")
    public Result<List<ArticleCategoryVO>> getTagByIdList(@PathVariable Long id) throws Exception{
        try {
            List<ArticleCategoryVO> tagListById = tagService.getTagListById(id);
            return ResultFactory.buildSuccessResult(tagListById);
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 评论点赞接口
     *
     * @param dto dto
     * @return {@link Result}<{@link String}>
     * @throws Exception 异常
     */
    @ApiOperation(value = "评论点赞接口")
    @PostMapping(value = "/comment/like")
    public Result<String> commentLike(@RequestBody GiveLikesDTO dto) throws Exception{
        try {
            commentService.commentLike(dto);
            return ResultFactory.buildSuccessResult();
        }catch (Exception e){
            throw this.exceptionHandle(e);
        }
    }

    /**
     * 评论点赞列表
     *
     * @param dto dto
     * @return {@link Result}<{@link List}<{@link Long}>>
     * @throws Exception 异常
     */
    @ApiOperation(value = "评论点赞列表")
    @PostMapping(value = "/comment/like/list")
    public Result<List<Long>> getCommentLikeIdList( @RequestBody IdDTO dto) throws Exception {
        try {
            List<Long> idList = commentService.likeList(dto.getId());
            return ResultFactory.buildSuccessResult(idList);
        } catch (Exception e) {
            throw this.exceptionHandle(e);
        }
    }

    @GetMapping(value="/recommend")
    @ApiOperation(value = "文章推荐")
    public Result<List<ArticleRecommendVO>> getRecommend() {
        List<ArticleRecommendVO> recommend = articleService.recommend();
        return ResultFactory.buildSuccessResult(recommend);
    }

    @GetMapping(value = "/like/{id}")
    @ApiOperation(value = "文章点赞")
    public Result<String> articleLike(@PathVariable Long id){
        articleService.like(id);
        return ResultFactory.buildSuccessResult();
    }

}
