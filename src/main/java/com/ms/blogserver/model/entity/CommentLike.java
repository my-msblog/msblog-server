package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/25
 */
@Data
@AllArgsConstructor
@Builder
@TableName(value = "ms_comment_like")
public class CommentLike implements Serializable {
    private static final long serialVersionUID = -2648352234015045985L;
    @TableId
    private Long id;
    private Long userId;
    private Long commentId;
    private Integer isLike;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public CommentLike() {}

}
