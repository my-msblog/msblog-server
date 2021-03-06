package com.ms.blogserver.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @description:
 * @author: zhh
 * @time: 2021/5/20
 */
@Data
@TableName(value = "ms_article")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article implements Serializable {
    private static final long serialVersionUID = 609371007308965968L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String contentMd;
    private Long writerId;
    private String cover;
    private Integer reading;
    private Integer type;
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;

}
