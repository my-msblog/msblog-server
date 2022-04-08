package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.model.dto.CommentSubmitDTO;
import com.ms.blogserver.model.dto.GiveLikesDTO;
import com.ms.blogserver.model.dto.IdDTO;
import com.ms.blogserver.model.entity.Comment;
import com.ms.blogserver.model.vo.CommentItemVO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
public interface CommentService extends IService<Comment> {

    /**
     * 评论父级id
     *
     * @param pid pid
     * @return {@link List}<{@link CommentItemVO}>
     */
    List<CommentItemVO> getByParentId(Long pid);

    /**
     * 文章评论分页
     *
     * @param dto   dto
     * @param token 令牌
     * @return {@link PageInfo}<{@link CommentItemVO}>
     */
    PageInfo<CommentItemVO> getPageByArticle(IdDTO dto, String token);

    /**
     * 评论提交
     * @param dto 提交信息
     * @param token token
     */
    void commentSubmit(CommentSubmitDTO dto, String token);


    /**
     * 评论点赞
     *
     * @param dto dto
     */
    void commentLike(GiveLikesDTO dto);

    /**
     * 评论点赞列表
     *
     * @param id id
     * @return {@link List}<{@link Long}>
     */
    List<Long> likeList(Long id);

}
