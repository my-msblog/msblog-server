package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/25
 */
@Data
@ToString
@AllArgsConstructor
@Builder
@TableName(value = "ms_comment_like")
public class CommentLike implements Serializable {
    private Long id;
    private Long userId;
    private Long commentId;
    private Integer isLike;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public CommentLike() {}

}
