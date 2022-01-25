package com.ms.blogserver.service.entity;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.ms.blogserver.model.dto.CommentSubmitDTO;
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
     * @param pid
     * @return
     */
    List<CommentItemVO> getByParentId(Long pid);

    /**
     * 文章评论分页
     * @param dto
     * @return
     */
    PageInfo<CommentItemVO> getPageByArticle(IdDTO dto, String token);

    /**
     * 评论提交
     * @param dto 提交信息
     * @param token token
     */
    void commentSubmit(CommentSubmitDTO dto, String token);



}
