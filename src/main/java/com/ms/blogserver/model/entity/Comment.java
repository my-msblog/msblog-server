package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: zhh
 * @time: 2021/6/2
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "ms_comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = 6377491897431574439L;
    @TableId
    private Long id;
    private String content;
    private Long articleId;
    private Long commenterId;
    private Long respondentId;
    private Long parentId;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
