package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.model.entity.Comment;
import com.ms.blogserver.model.dto.GetCommentDTO;
import com.ms.blogserver.model.vo.CommentVO;

import java.util.List;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
public interface CommentService extends IService<Comment> {

    /**
     * 文章评论
     * @param articleId
     * @return
     */
    List<Comment> getAllCommentByArticleId(Long articleId);

    /**
     * 评论父级id
     * @param pid
     * @return
     */
    List<CommentVO> getParentId(Long pid);

    /**
     * 文章评论分页
     * @param dto
     * @return
     */
    PageInfo<CommentVO> getPageByArticle(GetCommentDTO dto);
}
