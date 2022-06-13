package com.ms.blogserver.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/24
 */
@Data
@Builder
public class CommentSubmitBO implements Serializable {
    private static final long serialVersionUID = 5297097845253121808L;
    private Long commentId;
    private String context;
    private String replyTime;
    private Long userId;

    public CommentSubmitBO(){}

    public CommentSubmitBO(Long commentId, String context, String replyTime){
        this.commentId = commentId;
        this.context = context;
        this.replyTime = replyTime;
    }

    public CommentSubmitBO(Long commentId, String context, String replyTime, Long userId){
        this.commentId = commentId;
        this.context = context;
        this.replyTime = replyTime;
        this.userId = userId;
    }
}
